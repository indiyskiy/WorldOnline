package model.database.requests;

import model.additionalentity.phone.MobileDish;
import model.additionalentity.phone.MobileDishCategory;
import model.additionalentity.phone.MobileDishTag;
import model.additionalentity.phone.MobilePrice;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.dishes.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
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

    public static ArrayList<MobilePrice> getAllMobilePrices(Long userID) {
        ArrayList<MobilePrice> mobilePrices = new ArrayList<>();
        HashMap<Long, MobilePrice> mobilePriceHashMap = new HashMap<>();
        HashMap<Long, HashMap<Long, MobileDish>> dishesMap = new HashMap<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql =
                    "SELECT Price.PriceID, " +
                            "Dish.DishID, " +
                            "Dish.Cost, " +
                            "Dish.DishCategoryID, " +
                            "DishText.Text, " +
                            "DishTag.DishTagID " +
                            "FROM Price " +
                            "JOIN Dish ON (Dish.PriceID=Price.PriceID) " +
                            "JOIN TextGroup AS DishTextGroup ON (Dish.DishNameID=DishTextGroup.TextGroupID) " +
                            "JOIN Text AS DishText ON (DishTextGroup.TextGroupID=DishText.TextGroupID) " +
                            "LEFT OUTER JOIN DishTagDishLink ON (DishTagDishLink.DishID=Dish.DishID) " +
                            "LEFT OUTER JOIN DishTag ON (DishTagDishLink.DishTagID=DishTag.DishTagID) " +
                            "JOIN UserPersonalData ON (UserPersonalData.UserLanguage=Text.LanguageID) " +
                            "JOIN User ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                            "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
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
}
