package model.database.requests;

import model.additionalentity.MenuInfo;
import model.additionalentity.SimpleMenu;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static MenuInfo getCompleteMenuInfo(Long menuID, LanguageType language) {
        MenuInfo menuInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT * FROM Menu " +
                    "JOIN TextGroup " +
                    "ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "JOIN Text ON(Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Menu AS Submenu " +
                    "ON (Submenu.ParentMenuID=Menu.MenuID) " +
                    "LEFT OUTER JOIN TextGroup AS SubMenuTextGroup " +
                    "ON (SubmenuTextGroup.TextGroupID=Submenu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text AS SubText " +
                    "ON (SubText.TextGroupID=SubMenuTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Menu AS ParentMenu " +
                    "ON Menu.ParentMenuID =ParentMenu.MenuID " +
                    "LEFT OUTER JOIN TextGroup AS ParentTextGroup " +
                    "ON (ParentTextGroup.TextGroupID=ParentMenu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text AS ParentText " +
                    "ON (ParentText.TextGroupID=ParentTextGroup.TextGroupID) " +
                    "WHERE " +
                    "Text.LanguageID=" + language.getValue() +
                    " AND (SubText.LanguageID=" + language.getValue() +
                    " OR SubText.LanguageID IS NULL) "+
                    " AND (ParentText.LanguageID=" + language.getValue() +
                    " OR ParentText.LanguageID IS NULL) "+
                    " AND Menu.MenuID=?";

            ps = connection.prepareStatement(sql);
            if (menuID != null) {
                ps.setLong(1, menuID);
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

    private static void getMenuInfo(MenuInfo menuInfo, ResultSet rs) throws SQLException {
        Long menu=rs.getLong("Menu.MenuID");
        if (menuInfo.getMenu() == null  && !rs.wasNull() && menu!=0) {
            SimpleMenu simpleMenu=new SimpleMenu();
            setMenuValues(simpleMenu, rs, "Menu", "Text");
            menuInfo.setMenu(simpleMenu);
        }
        Long parent=rs.getLong("ParentMenu.MenuID");
        if(menuInfo.getParentMenu()==null && !rs.wasNull() && parent!=0){
            SimpleMenu simpleMenu=new SimpleMenu();
            setMenuValues(simpleMenu, rs, "ParentMenu", "ParentText");
            menuInfo.setParentMenu(simpleMenu);
        }
        Long subMenuID = rs.getLong("Submenu.MenuID");
        if (subMenuID != 0 && !rs.wasNull()) {
            SimpleMenu submenuInfo = new SimpleMenu();
            setMenuValues(submenuInfo, rs, "Submenu", "SubText");
            menuInfo.getSubmenus().add(submenuInfo);
        }
    }

    private static void setMenuValues(SimpleMenu simpleMenu, ResultSet rs,String menu, String text) throws SQLException {
        simpleMenu.setMenuID(rs.getLong(menu+".MenuID"));
        simpleMenu.setMenuName(rs.getString(text+".Text"));
    }

    private static MenuEntity getMenu(ResultSet rs) throws SQLException {
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

    public static void main(String[] args) {
        System.out.println(getCompleteMenuInfo(1L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(2L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(4L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(8L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(16L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(32L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(64L,LanguageType.Russian).getMenu().getMenuName());
        System.out.println(getCompleteMenuInfo(100L,LanguageType.Russian).getMenu().getMenuName());
    }
}
