package model.database.requests;

import model.additionalentity.CompleteMenuInfo;
import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.MenuInfo;
import model.additionalentity.SimpleMenu;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 20.01.14
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class MenuRequest {
    private static LoggerFactory logger = new LoggerFactory(Component.Database, MenuRequest.class);

    public static void addMenu(MenuEntity menuEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(menuEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addMenuCardLink(MenuCardLinkEntity menuCardLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(menuCardLinkEntity);
            session.getTransaction().commit();
            System.out.println("add menucardlink " + menuCardLinkEntity.getMenuCardLinkID());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static MenuEntity getMenu(Long menuID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (MenuEntity) session.get(MenuEntity.class, menuID);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void setParent(MenuEntity menu) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE Menu " +
                    "SET ParentMenuID=? " +
                    "WHERE MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menu.getParentMenu().getMenuID());
            ps.setLong(2, menu.getMenuID());
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static MenuInfo getMenuInfo(Long menuID, LanguageType language) {
        MenuInfo menuInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT * FROM Menu " +
                    "JOIN TextGroup " +
                    "ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Menu AS SubMenu " +
                    "ON (SubMenu.ParentMenuID=Menu.MenuID) " +
                    "LEFT OUTER JOIN TextGroup AS SubMenuTextGroup " +
                    "ON (SubMenuTextGroup.TextGroupID=SubMenu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text AS SubMenuText " +
                    "ON (SubMenuText.TextGroupID=SubMenuTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Menu AS ParentMenu " +
                    "ON Menu.ParentMenuID =ParentMenu.MenuID " +
                    "LEFT OUTER JOIN TextGroup AS ParentTextGroup " +
                    "ON (ParentTextGroup.TextGroupID=ParentMenu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text AS ParentText " +
                    "ON (ParentText.TextGroupID=ParentTextGroup.TextGroupID) " +
                    "WHERE " +
                    "Text.LanguageID=" + language.getValue() + " " +
                    "AND (SubMenuText.LanguageID=? " +
                    "OR SubMenuText.LanguageID IS NULL) " +
                    "AND (ParentText.LanguageID=? " +
                    "OR ParentText.LanguageID IS NULL) " +
                    "AND Menu.MenuID=? " +
                    "ORDER BY SubMenu.Number";

            ps = connection.prepareStatement(sql);
            if (menuID != null) {
                ps.setInt(1, language.getValue());
                ps.setInt(2, language.getValue());
                ps.setLong(3, menuID);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                if (menuInfo == null) {
                    menuInfo = new MenuInfo();
                }
                getMenuInfo(menuInfo, rs);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menuInfo;
    }

    private static void getRootMenuInfo(MenuInfo menuInfo, ResultSet rs) throws SQLException {
        Long subMenuID = rs.getLong("SubMenu.MenuID");
        if (subMenuID != 0 && !rs.wasNull()) {
            SimpleMenu submenuInfo = new SimpleMenu();
            setMenuValues(submenuInfo, rs, "SubMenu", "SubMenuText");
            menuInfo.getSubmenus().add(submenuInfo);
        }
    }

    private static void getMenuInfo(MenuInfo menuInfo, ResultSet rs) throws SQLException {
        Long menu = rs.getLong("Menu.MenuID");
        if (menuInfo.getMenu() == null && !rs.wasNull() && menu != 0) {
            SimpleMenu simpleMenu = new SimpleMenu();
            setMenuValues(simpleMenu, rs, "Menu", "Text");
            menuInfo.setMenu(simpleMenu);
        }
        Long parent = rs.getLong("ParentMenu.MenuID");
        if (menuInfo.getParentMenu() == null && !rs.wasNull() && parent != 0) {
            SimpleMenu simpleMenu = new SimpleMenu();
            setMenuValues(simpleMenu, rs, "ParentMenu", "ParentText");
            menuInfo.setParentMenu(simpleMenu);
        }
        Long subMenuID = rs.getLong("SubMenu.MenuID");
        if (subMenuID != 0 && !rs.wasNull()) {
            SimpleMenu submenuInfo = new SimpleMenu();
            setMenuValues(submenuInfo, rs, "SubMenu", "SubMenuText");
            menuInfo.getSubmenus().add(submenuInfo);
        }
    }

    private static void setMenuValues(SimpleMenu simpleMenu, ResultSet rs, String menu, String text) throws SQLException {
        simpleMenu.setMenuID(rs.getLong(menu + ".MenuID"));
        simpleMenu.setMenuName(rs.getString(text + ".Text"));
    }

    public static MenuEntity getMenu(ResultSet rs) throws SQLException {
        return getMenu(rs, "Menu");
    }

    private static MenuEntity getMenu(ResultSet rs, String menu) throws SQLException {
        MenuEntity menuEntity = null;
        Long menuID = rs.getLong(menu + ".MenuID");
        if (menuID != 0 && !rs.wasNull()) {
            menuEntity = new MenuEntity();
            menuEntity.setMenuID(menuID);
            menuEntity.setMenuType(rs.getInt(menu + ".MenuType"));
            menuEntity.setNumber(rs.getInt(menu + ".Number"));
        }
        return menuEntity;
    }

    public static MenuInfo getRootMenuInfo(LanguageType language) {
        MenuInfo menuInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT * FROM Menu AS SubMenu " +
                    "JOIN TextGroup AS SubMenuTextGroup " +
                    "ON (SubMenuTextGroup.TextGroupID=SubMenu.NameTextGroupID) " +
                    "JOIN Text AS SubMenuText " +
                    "ON(SubMenuText.TextGroupID=SubMenuTextGroup.TextGroupID) " +
                    "WHERE " +
                    "SubMenu.ParentMenuID IS NULL AND " +
                    "SubMenuText.LanguageID=? " +
                    "ORDER BY SubMenu.Number";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, language.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (menuInfo == null) {
                    menuInfo = new MenuInfo();
                }
                getRootMenuInfo(menuInfo, rs);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menuInfo;
    }

    public static CompleteMenuInfo getCompleteMenuInfo(Long menuId) {
        CompleteMenuInfo completeMenuInfo = null;
        MenuInfo menuInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT * FROM Menu " +
                    "JOIN TextGroup  " +
                    "ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "JOIN Text " +
                    "ON(Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Image ON (Menu.IconImageID=Image.ImageID)" +
                    "WHERE Menu.MenuID=? ";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuId);
            rs = ps.executeQuery();
            completeMenuInfo = getCompleteMenuInfo(rs);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return completeMenuInfo;
    }

    public static CompleteMenuInfo getCompleteMenuInfo(ResultSet rs) throws SQLException {
        if (rs.first()) {
            MenuEntity menuEntity = getMenu(rs);
            CompleteMenuInfo completeMenuInfo = new CompleteMenuInfo(menuEntity);
            setAdditionalCompleteMenuInfo(completeMenuInfo, rs, "Text", "TextGroup");
            while (rs.next()) {
                setAdditionalCompleteMenuInfo(completeMenuInfo, rs, "Text", "TextGroup");
            }
            return completeMenuInfo;
        }
        return null;
    }

    public static void setAdditionalCompleteMenuInfo(CompleteMenuInfo completeMenuInfo, ResultSet rs, String text, String textGroup) throws SQLException {
        CompleteTextGroupInfo completeTextGroupInfo;
        if (completeMenuInfo.getCompleteTextGroupInfo() == null) {
            TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, textGroup);
            completeTextGroupInfo = new CompleteTextGroupInfo(textGroupEntity);
            completeMenuInfo.setCompleteTextGroupInfo(completeTextGroupInfo);
        } else {
            completeTextGroupInfo = completeMenuInfo.getCompleteTextGroupInfo();
        }
        TextRequest.getCompleteTextGroupInfo(rs, completeTextGroupInfo, text);
    }

    public static MenuEntity getMenuByName(String name) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Text " +
                    "JOIN TextGroup ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Menu ON (Menu.NameTextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Text.Text LIKE ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long menuID = rs.getLong("Menu.MenuID");
                logger.info("return " + menuID);
                if (menuID != 0 && !rs.wasNull()) {
                    //todo rewrite with rs.get
                    return getMenu(menuID);
                } else {
                    logger.debug(name + " not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        logger.info("return null");
        return null;
    }

    public static void addMenuCardLink(ArrayList<MenuCardLinkEntity> menuCardLinkEntities) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for (MenuCardLinkEntity menuCardLinkEntity : menuCardLinkEntities) {
                session.merge(menuCardLinkEntity);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static LinkedList<Long> getMenuList() {
        LinkedList<Long> menuIDList = new LinkedList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Menu.MenuID FROM Menu";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long menuID = rs.getLong("Menu.MenuID");
                menuIDList.add(menuID);
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        logger.info("return null");
        return menuIDList;
    }
}
