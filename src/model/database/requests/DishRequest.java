package model.database.requests;

import controller.parser.adminparser.AddCategoryParser;
import helper.StringHelper;
import model.additionalentity.admin.CompletePriceInfo;
import model.additionalentity.admin.SimpleCategory;
import model.additionalentity.admin.SimpleDish;
import model.additionalentity.phone.MobileDish;
import model.additionalentity.phone.MobileDishCategory;
import model.additionalentity.phone.MobileDishTag;
import model.additionalentity.phone.MobilePrice;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.dishes.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DishRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, DishRequest.class);

    public static boolean addDishTag(DishTagEntity dishTagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishTagEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addPrice(PriceEntity priceEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(priceEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addCardPriceLink(CardPriceLinkEntity cardPriceLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(cardPriceLinkEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDishTagDishLink(DishTagDishLinkEntity dishTagDishLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishTagDishLinkEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDish(DishEntity dishEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean addDishCategory(DishCategoryEntity dishCategoryEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(dishCategoryEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static ArrayList<MobilePrice> getAllMobilePrices(Long userID, int limit, int offset) {
        ArrayList<MobilePrice> mobilePrices = new ArrayList<>();
        HashMap<Long, MobilePrice> mobilePriceHashMap = new HashMap<>();
        HashMap<Long, HashMap<Long, MobileDish>> dishesMap = new HashMap<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String priceLimitString = getPriseLimitedIDs(limit, offset);
            if (priceLimitString == null || priceLimitString.isEmpty()) {
                return mobilePrices;
            }
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql =
                    "SELECT Price.PriceID, " +
                            "Dish.DishID, " +
                            "Dish.Cost, " +
                            "Dish.DishCategoryID, " +
                            "DishText.Text, " +
                            "DishTag.DishTagID," +
                            "PriceName.Text " +
                            "FROM Price " +
                            "JOIN Dish ON (Dish.PriceID=Price.PriceID) " +
//                            "JOIN TextGroup AS DishTextGroup ON (Dish.DishNameID=DishTextGroup.TextGroupID) " +
                            "JOIN Text AS DishText ON (Dish.DishNameID=DishText.TextGroupID) " +
                            "LEFT OUTER JOIN DishTagDishLink ON (DishTagDishLink.DishID=Dish.DishID) " +
                            "LEFT OUTER JOIN DishTag ON (DishTagDishLink.DishTagID=DishTag.DishTagID) " +
                            "JOIN UserPersonalData ON (UserPersonalData.UserLanguage=DishText.LanguageID) " +
                            "JOIN User ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
//                            "LEFT OUTER JOIN TextGroup AS PriceNameGroup ON (Price.PriceNameID=PriceNameGroup.TextGroupID) " +
                            "LEFT OUTER JOIN Text AS PriceName ON (Price.PriceNameID=PriceName.TextGroupID AND PriceName.LanguageID=UserPersonalData.UserLanguage) " +
//                            "JOIN (SELECT FilterPrice.PriceID FROM Price AS FilterPrice " +
//                            "JOIN CardPriceLink ON (FilterPrice.PriceID=CardPriceLink.PriceID) " +
//                            "JOIN Card ON (Card.CardID=CardPriceLink.CardID) " +
//                            "WHERE Card.CardState=" + CardState.Active.getValue() + " " +
//                            "ORDER BY FilterPrice.PriceID LIMIT ?,?) " +
//                            "AS T ON (Price.PriceID=T.PriceID) " +
                            "WHERE User.UserID=? AND Price.PriceID IN (" + priceLimitString + ")";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
//            ps.setLong(1, offset);
//            ps.setLong(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long priceID = rs.getLong("Price.PriceID");
                if (priceID != 0 && !rs.wasNull()) {
                    MobilePrice mobilePrice;
                    if (mobilePriceHashMap.containsKey(priceID)) {
                        mobilePrice = mobilePriceHashMap.get(priceID);
                    } else {
                        mobilePrice = new MobilePrice();
                        mobilePrice.setPriceID(priceID);
                        mobilePrice.setName(rs.getString("PriceName.Text"));
                        mobilePriceHashMap.put(priceID, mobilePrice);
                        mobilePrices.add(mobilePrice);
                    }
                    HashMap<Long, MobileDish> dishEntityHashMap;
                    if (dishesMap.containsKey(priceID)) {
                        dishEntityHashMap = dishesMap.get(priceID);
                    } else {
                        dishEntityHashMap = new HashMap<>();
                        dishesMap.put(priceID, dishEntityHashMap);
                    }
                    Long dishID = rs.getLong("Dish.DishID");
                    if (dishID != 0 && !rs.wasNull()) {
                        MobileDish mobileDish;
                        if (dishEntityHashMap.containsKey(dishID)) {
                            mobileDish = dishEntityHashMap.get(dishID);
                        } else {
                            mobileDish = new MobileDish();
                            dishEntityHashMap.put(dishID, mobileDish);
                            mobileDish.setDishID(dishID);
                            mobileDish.setCost(rs.getDouble("Dish.Cost"));
                            mobileDish.setDishCategoryID(rs.getLong("Dish.DishCategoryID"));
                            mobileDish.setName(rs.getString("DishText.Text"));
                            mobilePrice.getMobileDishes().add(mobileDish);
                        }
                        Long dishTagID = rs.getLong("DishTag.DishTagID");
                        if (dishTagID != 0 && !rs.wasNull()) {
                            mobileDish.getTagSet().add(dishTagID);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobilePrices;
    }

    private static String getPriseLimitedIDs(int limit, int offset) {
        ArrayList<Object> mobilePriceIDs = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT Price.PriceID FROM Price  " +
                    "JOIN CardPriceLink ON (Price.PriceID=CardPriceLink.PriceID) " +
                    "JOIN Card ON (Card.CardID=CardPriceLink.CardID) " +
                    "WHERE Card.CardState=" + CardState.Active.getValue() + " " +
                    "ORDER BY Price.PriceID LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, offset);
            ps.setLong(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                mobilePriceIDs.add(rs.getLong("Price.PriceID"));
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return StringHelper.getStringFromArray(mobilePriceIDs);
    }

    public static ArrayList<MobileDishCategory> getAllMobileDishCategories(Long userID) {
        ArrayList<MobileDishCategory> mobileDishCategories = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT " +
                    "DishCategory.DishCategoryID, " +
                    "DishCategory.Position, " +
                    "Text.Text " +
                    "FROM DishCategory " +
                    "JOIN TextGroup ON (DishCategory.NameID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN UserPersonalData ON (Text.LanguageID=UserPersonalData.UserLanguage) " +
                    "JOIN User ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileDishCategory mobileDishCategory = new MobileDishCategory();
                mobileDishCategory.setDishCategoryID(rs.getLong("DishCategory.DishCategoryID"));
                mobileDishCategory.setName(rs.getString("Text.Text"));
                mobileDishCategory.setPosition(rs.getInt("DishCategory.Position"));
                mobileDishCategories.add(mobileDishCategory);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileDishCategories;
    }

    public static ArrayList<MobileDishTag> getAllMobileDishTags(Long userID) {
        ArrayList<MobileDishTag> mobileDishTags = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT " +
                    "DishTag.DishTagID, " +
                    "Text.Text " +
                    "FROM DishTag " +
                    "JOIN TextGroup ON (DishTag.NameID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN UserPersonalData ON (Text.LanguageID=UserPersonalData.UserLanguage) " +
                    "JOIN User ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileDishTag mobileDishTag = new MobileDishTag();
                mobileDishTag.setTagID(rs.getLong("DishTag.DishTagID"));
                mobileDishTag.setTagName(rs.getString("Text.Text"));
                mobileDishTags.add(mobileDishTag);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileDishTags;
    }

    public static CompletePriceInfo getCompletePriceInfo(Long priceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Price.PriceID,TextGroup.TextGroupID,Text.Text FROM Price " +
                    "LEFT OUTER JOIN TextGroup ON (Price.PriceNameID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE (Text.LanguageID IS NULL OR Text.LanguageID=" + LanguageType.Russian.getValue() + ") " +
                    "AND Price.PriceID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, priceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                CompletePriceInfo completePriceInfo = new CompletePriceInfo();
                completePriceInfo.setPriceID(rs.getLong("Price.PriceID"));
                completePriceInfo.setName(rs.getString("Text.Text"));
                completePriceInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));
                ArrayList<SimpleDish> simpleDishes = getPriceDishes(priceID);
                completePriceInfo.setCategories(simpleDishes);
                completePriceInfo.setCards(CardRequest.getAllPriceCards(priceID));
                return completePriceInfo;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    private static ArrayList<SimpleDish> getPriceDishes(Long priceID) {
        ArrayList<SimpleDish> simpleDishes = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "Dish.DishID AS DishID, " +
                    "Text.Text AS DishName, " +
                    "TextGroup.TextGroupID AS TextGroupID, " +
                    "CatTextGroup.TextGroupID AS CatTextGroupID, " +
                    "DishCategory.DishCategoryID AS DishCategoryID, " +
                    "CatText.Text AS CatName, " +
                    "Dish.Cost AS Coast " +
                    "FROM Price " +
                    "JOIN Dish ON (Dish.PriceID=Price.PriceID) " +
                    "JOIN TextGroup ON (Dish.DishNameID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN DishCategory ON (Dish.DishCategoryID=DishCategory.DishCategoryID) " +
                    "LEFT OUTER JOIN TextGroup AS CatTextGroup ON (CatTextGroup.TextGroupID=DishCategory.NameID) " +
                    "LEFT OUTER JOIN Text AS CatText ON (CatText.TextGroupID=CatTextGroup.TextGroupID) " +
                    "WHERE (Text.LanguageID IS NULL OR Text.LanguageID=" + LanguageType.Russian.getValue() + ") " +
                    "AND (CatText.LanguageID IS NULL OR CatText.LanguageID=" + LanguageType.Russian.getValue() + ") " +
                    "AND(Price.PriceID=?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, priceID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleDish simpleDish = new SimpleDish();
                simpleDish.setDishID(rs.getLong("DishID"));
                simpleDish.setName(rs.getString("DishName"));
                simpleDish.setCoast(rs.getDouble("Coast"));
                simpleDish.setTextGroupID(rs.getLong("TextGroupID"));
                simpleDish.setCategoryID(rs.getLong("DishCategoryID"));
                simpleDish.setCategoryName(rs.getString("CatName"));
                simpleDish.setCategoryTextGroupID(rs.getLong("CatTextGroupID"));
                simpleDishes.add(simpleDish);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleDishes;
    }

    public static void addDishes(ArrayList<DishEntity> dishList) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            for (DishEntity dishEntity : dishList) {
                session.save(dishEntity);
            }
            transaction.commit();
            session.flush();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static PriceEntity getPrice(Long priceID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (PriceEntity) session.get(PriceEntity.class, priceID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static DishCategoryEntity getCategory(Long categoryID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (DishCategoryEntity) session.get(DishCategoryEntity.class, categoryID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean isDishExist(String nameRu, String nameEn, Double coast, Long priceID) {
        loggerFactory.debug("find " + nameRu + " " + nameEn + " " + coast + " " + priceID);
        if (priceID == 0) {
            loggerFactory.debug("priceID==0 => not found");
            return false;
        }
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "* " +
                    "FROM Dish " +
                    "JOIN Text AS TextRu ON (Dish.DishNameID = TextRu.TextGroupID AND TextRu.LanguageID = 1) " +
                    "JOIN Text AS TextEn ON (Dish.DishNameID = TextEn.TextGroupID AND TextEn.LanguageID = 2) " +
//                    "JOIN CardPriceLink ON (CardPriceLink.PriceID=Dish.PriceID) " +
                    "WHERE TextRu.Text LIKE ? AND  TextEn.Text LIKE ? AND Dish.Cost=? AND Dish.PriceID=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nameRu);
            ps.setString(2, nameEn);
            ps.setDouble(3, coast);
            ps.setLong(4, priceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                loggerFactory.debug("found=>true");
                return true;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        loggerFactory.debug("not found=>false");
        return false;
    }

    public static DishCategoryEntity findCategory(String catEn, String catRu, Long priceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "DishCategory.DishCategoryID " +
                    "FROM Dish " +
                    "JOIN DishCategory ON (Dish.DishCategoryID=DishCategory.DishCategoryID) " +
                    "JOIN Text AS TextRu ON (DishCategory.NameID = TextRu.TextGroupID AND TextRu.LanguageID = 1) " +
                    "JOIN Text AS TextEn ON (DishCategory.NameID = TextEn.TextGroupID AND TextEn.LanguageID = 2) " +
//                    "JOIN CardPriceLink ON (CardPriceLink.PriceID=Dish.PriceID) " +
                    "WHERE TextRu.Text LIKE ? AND  TextEn.Text LIKE ? AND Dish.PriceID=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, catRu);
            ps.setString(2, catEn);
            ps.setLong(3, priceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long catID = rs.getLong("DishCategory.DishCategoryID");
                return getDishCategory(catID);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    private static DishCategoryEntity getDishCategory(Long catID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (DishCategoryEntity) session.get(DishCategoryEntity.class, catID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addCategory(AddCategoryParser addCatParser) {
        addDishCategory(addCatParser.getDishCategoryEntity());
    }

    public static ArrayList<SimpleCategory> getPriceCategories(Long priceID) {
        ArrayList<SimpleCategory> simpleCategories = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "DishCategory.DishCategoryID AS DishCategoryID, " +
                    "CatText.Text AS CatName, " +
                    "CatText.TextGroupID AS CatTextGroupID " +
                    "FROM Price " +
                    "LEFT OUTER JOIN DishCategory ON (Price.PriceID=DishCategory.PriceID) " +
                    "LEFT OUTER JOIN TextGroup AS CatTextGroup ON (CatTextGroup.TextGroupID=DishCategory.NameID) " +
                    "LEFT OUTER JOIN Text AS CatText ON (CatText.TextGroupID=CatTextGroup.TextGroupID) " +
                    "WHERE  (CatText.LanguageID IS NULL OR CatText.LanguageID=" + LanguageType.Russian.getValue() + ") " +
                    "AND(Price.PriceID=?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, priceID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleCategory simpleCategory = new SimpleCategory();
                simpleCategory.setName(rs.getString("CatName"));
                simpleCategory.setTextGroupID(rs.getLong("CatTextGroupID"));
                simpleCategory.setCategoryID(rs.getLong("DishCategoryID"));
                simpleCategories.add(simpleCategory);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleCategories;
    }

    public static void addDishesSQL(ArrayList<DishEntity> dishList) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO Dish (Cost, DishCategoryID, DishNameID, PriceID) VALUES ";
            for (int i = 0; i < dishList.size(); i++) {
                DishEntity dishEntity = dishList.get(i);
                if (dishEntity.getDishName() != null && dishEntity.getDishName().getTextGroupID() == null) {
                    TextRequest.addTextGroup(dishEntity.getDishName());
                }
                @Language("MySQL") String sqlAdd = "(" + dishEntity.getCost() +
                        "," + dishEntity.getDishCategory().getDishCategoryID() +
                        "," + dishEntity.getDishName().getTextGroupID() +
                        "," + dishEntity.getPrice().getPriceID() +
                        ")";
                if (i != dishList.size() - 1) {
                    sqlAdd += ",";
                }
                sql += sqlAdd;
            }
            loggerFactory.debug("sql= " + sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static DishEntity getDish(Long dishID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (DishEntity) session.get(DishEntity.class, dishID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void deleteDish(DishEntity dishEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM Dish WHERE Dish.DishID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, dishEntity.getDishID());
            ps.executeUpdate();
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }
}

