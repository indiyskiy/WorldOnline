package model.database.requests;

import controller.phone.entity.AllMenusRequest;
import model.additionalentity.MenuInfo;
import model.additionalentity.ParentMenu;
import model.additionalentity.SimpleMenu;
import model.additionalentity.admin.CardInfo;
import model.additionalentity.admin.CardMenu;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.admin.CompleteMenuInfo;
import model.additionalentity.phone.MenuCompleteInformation;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Component;
import model.constants.adminenumerations.RepositionDirection;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MenuRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, MenuRequest.class);

    public static void addMenuSafe(MenuEntity menuEntity, String name) {
        MenuEntity tmp = getMenuByName(name);
        if (tmp == null) {
            addMenu(menuEntity);
        } else {
            menuEntity.setAll(tmp);
        }
    }

    public static void addMenu(MenuEntity menuEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(menuEntity);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addMenuCardLinkSafe(MenuCardLinkEntity menuCardLinkEntity) {
        if (!cardMenuLinkExist(menuCardLinkEntity.getCard().getCardID(), menuCardLinkEntity.getMenu().getMenuID())) {
            addMenuCardLink(menuCardLinkEntity);
        }
    }

    private static boolean cardMenuLinkExist(Long cardID, Long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("cardMenuLinkExist");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT MenuCardLink.MenuCardLinkID FROM MenuCardLink " +
                    "WHERE MenuCardLink.MenuID=? AND MenuCardLink.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            ps.setLong(2, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }


    private static void addMenuCardLink(MenuCardLinkEntity menuCardLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(menuCardLinkEntity);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static MenuEntity getMenu(Long menuID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (MenuEntity) session.get(MenuEntity.class, menuID);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public static void setParent(MenuEntity menu) {
        DatabaseConnection dbConnection = new DatabaseConnection("setParent");
        PreparedStatement ps = null;
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static MenuInfo getMenuInfo(Long menuID, LanguageType language) {
        MenuInfo menuInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection("getMenuInfo");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT * FROM Menu " +
                    "JOIN TextGroup " +
                    "ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
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
                    "(Text.LanguageID=" + language.getValue() + " OR Text.LanguageID IS NULL) " +
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
            loggerFactory.error(e);
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
        DatabaseConnection dbConnection = new DatabaseConnection("getRootMenuInfo");
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menuInfo;
    }

    public static CompleteMenuInfo getCompleteMenuInfo(Long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getCompleteMenuInfo");
        PreparedStatement ps = null;
        ResultSet rs = null;
        CompleteMenuInfo completeMenuInfo = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT " +
                    "Text.Text, " +
                    "Menu.MenuID, " +
                    "ParentMenu.MenuID, " +
                    "ParentText.Text, " +
                    "Menu.IconImageID," +
                    "Menu.Number, " +
                    "TextGroup.TextGroupID " +
                    "FROM Menu " +
                    "LEFT OUTER JOIN TextGroup " +
                    "ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text ON(Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Image ON (Menu.IconImageID=Image.ImageID) " +
                    "LEFT OUTER JOIN Menu AS ParentMenu ON (Menu.ParentMenuID=ParentMenu.MenuID) " +
                    "LEFT OUTER JOIN TextGroup AS ParentTextGroup ON (ParentTextGroup.TextGroupID=ParentMenu.NameTextGroupID) " +
                    "LEFT OUTER JOIN Text as ParentText ON (ParentText.TextGroupID=ParentTextGroup.TextGroupID) " +
                    "WHERE Menu.MenuID=? " +
                    "AND (ParentText.LanguageID=" + LanguageType.Russian.getValue() + " OR ParentText.LanguageID IS NULL) " +
                    "AND (Text.LanguageID=" + LanguageType.Russian.getValue() + " OR Text.LanguageID IS NULL) ";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            rs = ps.executeQuery();
            if (rs.first()) {
                completeMenuInfo = new CompleteMenuInfo();
                completeMenuInfo.setName(rs.getString("Text.Text"));
                completeMenuInfo.setIconID(rs.getLong("Menu.IconImageID"));
                completeMenuInfo.setMenuID(rs.getLong("Menu.MenuID"));
                completeMenuInfo.setNumber(rs.getInt("Menu.Number"));
                completeMenuInfo.setParentID(rs.getLong("ParentMenu.MenuID"));
                completeMenuInfo.setParentName(rs.getString("ParentText.Text"));
                completeMenuInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));

            }

        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return completeMenuInfo;
    }


    public static MenuEntity getMenuByName(String name) {
        DatabaseConnection dbConnection = new DatabaseConnection("getMenuByName");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Menu " +
                    "JOIN TextGroup ON (Menu.NameTextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Text.Text LIKE ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.first()) {
                MenuEntity menuEntity;
                Long menuID = rs.getLong("Menu.MenuID");
                if (menuID != 0 && !rs.wasNull()) {
                    menuEntity = model.database.requests.MenuRequest.getMenu(menuID);
                    return menuEntity;
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }


    public static void addMenuCardLink(ArrayList<MenuCardLinkEntity> menuCardLinkEntities) {
        for (MenuCardLinkEntity menuCardLinkEntity : menuCardLinkEntities) {
            if (!cardMenuLinkExist(menuCardLinkEntity.getCard().getCardID(), menuCardLinkEntity.getMenu().getMenuID())) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                try {
                    session.beginTransaction();
                    session.save(menuCardLinkEntity);
                    session.getTransaction().commit();
                    session.flush();
                } catch (Exception e) {
                    loggerFactory.error(e);
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
            }
        }
    }

    public static LinkedList<Long> getMenuList() {
        LinkedList<Long> menuIDList = new LinkedList<>();
        DatabaseConnection dbConnection = new DatabaseConnection("getMenuList");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menuIDList;
    }

    public static MenuCompleteInformation getMenuCompleteInformation(controller.phone.entity.MenuRequest menuRequest) {
        MenuCompleteInformation menuCompleteInformation = new MenuCompleteInformation();
        DatabaseConnection dbConnection = new DatabaseConnection("getMenuCompleteInformation");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "Menu.IconImageID, " +
                    "Menu.MenuID, " +
                    "Menu.ParentMenuID, " +
                    "Menu.Number," +
                    "Text.Text " +
                    "FROM Menu " +
                    "JOIN User ON(User.UserID=?) " +
                    "JOIN UserPersonalData ON(User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "JOIN TextGroup ON (TextGroup.TextGroupID=Menu.NameTextGroupID) " +
                    "JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID AND Text.LanguageID=UserPersonalData.UserLanguage) " +
                    "WHERE Menu.MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuRequest.getUserID());
            ps.setLong(2, menuRequest.getMenuID());
            rs = ps.executeQuery();
            if (rs.first()) {
                parseCompleteMenu(menuCompleteInformation, rs);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menuCompleteInformation;
    }

    private static void parseCompleteMenu(MenuCompleteInformation menuCompleteInformation, ResultSet rs) throws SQLException {
        Long menuID = rs.getLong("MenuID");
        Long iconImageID = rs.getLong("IconImageID");
        Long parentMenuID = rs.getLong("ParentMenuID");
        String text = rs.getString("Text");
        Integer number = rs.getInt("Number");
        Integer cardCounter = rs.getInt("CardCounter");
        menuCompleteInformation.setMenuID(menuID);
        menuCompleteInformation.setIconImageID(iconImageID);
        menuCompleteInformation.setParentMenuID(parentMenuID);
        menuCompleteInformation.setText(text);
        menuCompleteInformation.setNumber(number);
        menuCompleteInformation.setCardCounter(cardCounter);
    }

    public static ArrayList<MenuCompleteInformation> getAllMenusCompleteInformation(AllMenusRequest allMenusRequest) {
        ArrayList<MenuCompleteInformation> menusCompleteInformation = new ArrayList<MenuCompleteInformation>();
        DatabaseConnection dbConnection = new DatabaseConnection("getAllMenusCompleteInformation");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        HashMap<Long, MenuCompleteInformation> menuCompleteInformationHashMap = new HashMap<>();
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "(SELECT DISTINCT  " +
                    "Menu.MenuID AS MenuID, " +
                    "Menu.IconImageID AS IconImageID, " +
                    "Menu.ParentMenuID AS ParentMenuID, " +
                    "Menu.Number AS Number, " +
                    "Text.Text AS Text, " +
                    "Count(MenuCardLink.MenuCardLinkID) AS CardCounter  " +
                    "FROM Menu " +
                    "JOIN User ON (User.UserID = ?) " +
                    "JOIN UserPersonalData ON (User.UserPersonalDataID = UserPersonalData.UserPersonalDataID) " +
                    "JOIN TextGroup ON (TextGroup.TextGroupID = Menu.NameTextGroupID) " +
                    "JOIN Text ON (TextGroup.TextGroupID = Text.TextGroupID AND Text.LanguageID = UserPersonalData.UserLanguage) " +
                    "LEFT OUTER JOIN MenuCardLink ON (MenuCardLink.MenuID = Menu.MenuID) " +
                    "LEFT OUTER JOIN Card ON (Card.CardID = MenuCardLink.CardID) " +
                    "WHERE (Card.CardState = 1 OR Card.CardID IS NULL) " +
                    "GROUP BY Menu.MenuID) " +
                    " UNION " +
                    "(SELECT DISTINCT  " +
                    "Menu.MenuID AS MenuID, " +
                    "Menu.IconImageID AS IconImageID, " +
                    "Menu.ParentMenuID AS ParentMenuID, " +
                    "Menu.Number AS Number, " +
                    "Text.Text AS Text, " +
                    "  0 AS CardCounter " +
                    "FROM Menu " +
                    "  JOIN User ON (User.UserID = ?) " +
                    "  JOIN UserPersonalData ON (User.UserPersonalDataID = UserPersonalData.UserPersonalDataID) " +
                    "  JOIN TextGroup ON (TextGroup.TextGroupID = Menu.NameTextGroupID) " +
                    "  JOIN Text ON (TextGroup.TextGroupID = Text.TextGroupID AND Text.LanguageID = UserPersonalData.UserLanguage) " +
                    "  LEFT OUTER JOIN MenuCardLink ON (MenuCardLink.MenuID = Menu.MenuID) " +
                    "  LEFT OUTER JOIN Card ON (Card.CardID = MenuCardLink.CardID) " +
                    "WHERE (Menu.MenuID NOT IN(SELECT DISTINCT " +
                    "                            Menu.MenuID " +
                    "                          FROM Menu " +
                    "                            LEFT OUTER JOIN MenuCardLink ON (MenuCardLink.MenuID = Menu.MenuID) " +
                    "                            LEFT OUTER JOIN Card ON (Card.CardID = MenuCardLink.CardID) " +
                    "                          WHERE (Card.CardState = 1 OR Card.CardID IS NULL) " +
                    "                          GROUP BY Menu.MenuID " +
                    ")))";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, allMenusRequest.getUserID());
            ps.setLong(2, allMenusRequest.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                MenuCompleteInformation menuCompleteInformation = new MenuCompleteInformation();
                parseCompleteMenu(menuCompleteInformation, rs);
                menusCompleteInformation.add(menuCompleteInformation);
                menuCompleteInformationHashMap.put(menuCompleteInformation.getMenuID(), menuCompleteInformation);
            }
            recountMenus(menuCompleteInformationHashMap);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }

        return menusCompleteInformation;
    }

    private static void recountMenus(HashMap<Long, MenuCompleteInformation> menuCompleteInformationHashMap) {
        for (MenuCompleteInformation menuCompleteInformation : menuCompleteInformationHashMap.values()) {
            menuCompleteInformation.setReadyForCounting(true);
            menuCompleteInformation.setCounted(false);
        }
        boolean b = true;
        while (b) {
            b = false;
            for (MenuCompleteInformation menuCompleteInformation : menuCompleteInformationHashMap.values()) {
                menuCompleteInformation.setReadyForCounting(true);
            }
            for (MenuCompleteInformation menuCompleteInformation : menuCompleteInformationHashMap.values()) {
                if (!menuCompleteInformation.isCounted()) {
                    Long parentID = menuCompleteInformation.getParentMenuID();
                    if (parentID != null) {
                        MenuCompleteInformation parent = menuCompleteInformationHashMap.get(parentID);
                        if (parent != null) {
                            parent.setReadyForCounting(false);
                        }
                    }
                }
            }
            for (MenuCompleteInformation menuCompleteInformation : menuCompleteInformationHashMap.values()) {
                if (menuCompleteInformation.isReadyForCounting() && !menuCompleteInformation.isCounted()) {
                    menuCompleteInformation.setCounted(true);
                    Long parentID = menuCompleteInformation.getParentMenuID();
                    MenuCompleteInformation parent = menuCompleteInformationHashMap.get(parentID);
                    if (parent != null) {
                        parent.setCardCounter(parent.getCardCounter() + menuCompleteInformation.getCardCounter());
                        b = true;
                    }
                }
            }
        }
    }

    public static MenuEntity getPhotoMenu() {
        return getMenuByName("Photo");
    }

    public static void setCardMenus(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardMenus");
        ArrayList<CardMenu> cardMenus = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM Card " +
                            "JOIN MenuCardLink ON (Card.CardID=MenuCardLink.CardID) " +
                            "JOIN Menu ON (Menu.MenuID=MenuCardLink.MenuID) " +
                            "JOIN TextGroup ON (Menu.NameTextGroupID=TextGroup.TextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "WHERE Card.CardID=? AND Text.LanguageID=" + LanguageType.Russian.getValue();
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardMenu cardMenu = new CardMenu();
                cardMenu.setMenuID(rs.getLong("Menu.MenuID"));
                cardMenu.setName(rs.getString("Text.Text"));
                cardMenu.setCardMenuID(rs.getLong("MenuCardLink.MenuCardLinkID"));
                cardMenus.add(cardMenu);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardMenuArrayList(cardMenus);
    }

    public static ArrayList<ParentMenu> getLastLevelMenus() {
        DatabaseConnection dbConnection = new DatabaseConnection("getLastLevelMenus");
        ArrayList<ParentMenu> menus = new ArrayList<>();
        HashMap<Long, ParentMenu> parentMenuHashSet = new HashMap<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT DISTINCT " +
                            "Menu.MenuID, " +
                            "Text.Text, " +
                            "Parent.MenuID, " +
                            "ParentText.Text " +
                            "FROM Menu " +
                            "JOIN TextGroup ON (Menu.NameTextGroupID = TextGroup.TextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID = TextGroup.TextGroupID) " +
                            "LEFT OUTER JOIN Menu AS tmp ON (tmp.ParentMenuID = Menu.MenuID) " +
                            "LEFT OUTER JOIN Menu AS Parent ON (Parent.MenuID=Menu.ParentMenuID) " +
                            "LEFT OUTER JOIN TextGroup AS ParentTextGroup ON (ParentTextGroup.TextGroupID=Parent.NameTextGroupID) " +
                            "LEFT OUTER JOIN Text AS ParentText ON (ParentText.TextGroupID=ParentTextGroup.TextGroupID)" +
                            "WHERE " +
                            "tmp.MenuID IS NULL AND Text.LanguageID =" + LanguageType.Russian.getValue() + " " +
                            "AND (ParentText.TextID IS NULL OR ParentText.LanguageID=" + LanguageType.Russian.getValue() + ")";
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            while (rs.next()) {
                ParentMenu parentMenu;
                Long parentMenuID = rs.getLong("Parent.MenuID");
                if (parentMenuHashSet.containsKey(parentMenuID)) {
                    parentMenu = parentMenuHashSet.get(parentMenuID);
                } else {
                    parentMenu = new ParentMenu();
                    parentMenu.setName(rs.getString("ParentText.Text"));
                    parentMenu.setParentMenuID(parentMenuID);
                    parentMenuHashSet.put(parentMenuID, parentMenu);
                    menus.add(parentMenu);
                }
                SimpleMenu menu = new SimpleMenu();
                menu.setMenuID(rs.getLong("Menu.MenuID"));
                menu.setMenuName(rs.getString("Text.Text"));
                parentMenu.getCardMenus().add(menu);
//                loggerFactory.debug("add "+menu.getMenuName()+" to "+parentMenu.getName());
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return menus;
    }

    public static void deleteCardMenu(Long cardMenuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteCardMenu");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM MenuCardLink WHERE MenuCardLink.MenuCardLinkID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardMenuID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void deleteMenu(Long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteMenu");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM Menu WHERE Menu.MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static ArrayList<CardInfo> getAllSimpleCardsOfMenu(Long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getAllSimpleCardsOfMenu");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CardInfo> cardInfos = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Card " +
                    "JOIN MenuCardLink ON (Card.CardID=MenuCardLink.CardID) " +
                    "JOIN Menu ON (MenuCardLink.MenuID=Menu.MenuID) " +
                    "WHERE Menu.MenuID=? " +
                    "ORDER BY Card.CardState, Card.CardName";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardInfo cardInfo = new CardInfo();
                cardInfo.setCardID(rs.getLong("Card.CardID"));
                cardInfo.setName(rs.getString("Card.CardName"));
                cardInfo.setCardState(CardState.parseInt(rs.getInt("Card.CardState")));
                cardInfo.setCardType(CardType.parseInt(rs.getInt("Card.CardType")));
                cardInfos.add(cardInfo);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
        return cardInfos;
    }

    public static MenuEntity getNearbyMenu(Long menuID, Long rootMenuID, RepositionDirection repositionDirection) {
        DatabaseConnection dbConnection = new DatabaseConnection("getNearbyMenu");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql;
            if (repositionDirection == RepositionDirection.DOWN) {
                sql = "SELECT Menu.MenuID FROM Menu " +
                        "JOIN Menu AS HomeMenu ON (Menu.Number>HomeMenu.Number) " +
                        "WHERE HomeMenu.MenuID=? " +
                        "AND Menu.ParentMenuID=? " +
                        "AND HomeMenu.ParentMenuID=? " +
                        "ORDER BY Menu.Number LIMIT 1";
            } else {
                if (repositionDirection == RepositionDirection.UP) {
                    sql = "SELECT Menu.MenuID FROM Menu " +
                            "JOIN Menu AS HomeMenu ON (Menu.Number<HomeMenu.Number) " +
                            "WHERE HomeMenu.MenuID=? " +
                            "AND Menu.ParentMenuID=? " +
                            "AND HomeMenu.ParentMenuID=? " +
                            "ORDER BY Menu.Number DESC LIMIT 1";
                } else {
                    return null;
                }
            }
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            ps.setLong(2, rootMenuID);
            ps.setLong(3, rootMenuID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return getMenu(rs.getLong("Menu.MenuID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static void updateMenu(MenuEntity menuEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(menuEntity);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addMenuCardLink(MenuEntity menuEntity, CardEntity cardEntity) {
        MenuCardLinkEntity menuCardLinkEntity = new MenuCardLinkEntity();
        menuCardLinkEntity.setMenu(menuEntity);
        menuCardLinkEntity.setCard(cardEntity);
        addMenuCardLinkSafe(menuCardLinkEntity);
    }

    public static void updateMenuIcon(MenuEntity menuEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("updateMenuIcon");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE Menu " +
                    "SET IconImageID=? " +
                    "WHERE MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuEntity.getIconImage().getImageID());
            ps.setLong(2, menuEntity.getMenuID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void deleteCardMenuByMenuID(long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteCardMenuByMenuID");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE " +
                    "FROM MenuCardLink " +
                    "WHERE MenuCardLink.MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }


    public static int countSubmenus(long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("countSubmenus");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT count(*) AS c " +
                    "FROM Menu " +
                    "WHERE Menu.ParentMenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getInt("c");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0;
    }

    public static void setMobileMenus(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileMenus");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT MenuCardLink.MenuID, MenuCardLink.CardID " +
                    "FROM  MenuCardLink " +
                    "WHERE MenuCardLink.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("MenuCardLink.CardID"));
                mobileCardInfo.getMenuIDs().add(rs.getLong("MenuCardLink.MenuID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static Integer getMaxPosition(Long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getMaxPosition");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Max(Menu.Number) AS c " +
                    "FROM Menu " +
                    "WHERE Menu.ParentMenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, menuID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getInt("c");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0;
    }
}

