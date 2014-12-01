package model.database.requests;

import helper.StringHelper;
import helper.TimeManager;
import model.additionalentity.phone.CardUpdateAggregator;
import model.additionalentity.phone.PricesUpdateAggregator;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.GlobalUpdateEntity;
import model.database.worldonlinedb.UserCardEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserPriceEntity;
import model.logger.LoggerFactory;
import model.phone.responseentity.UserGlobalUpdateStatus;
import org.hibernate.Session;
import org.hibernate.exception.LockAcquisitionException;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class UserDataRequest {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserDataRequest.class);

    public static UserCardEntity getUserCard(Long userCardID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        UserCardEntity userCardEntity = null;
        try {
            userCardEntity = (UserCardEntity) session.get(UserCardEntity.class, userCardID);
        } catch (Exception e) {
            loggerFactory.error(e);
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userCardEntity;
    }

    public static UserPriceEntity getUserPrice(Long userPriceID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (UserPriceEntity) session.get(UserPriceEntity.class, userPriceID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void addUserCard(UserCardEntity userCard) throws InterruptedException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(userCard);
            session.getTransaction().commit();
            session.flush();
        } catch (LockAcquisitionException e) {
            loggerFactory.error(e);
            Thread.sleep(1000);
            loggerFactory.error("ok, go re addUserCard");
            if (session != null && session.isOpen()) {
                session.close();
            }
            addUserCard(userCard);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void addUserPrice(UserPriceEntity userPrice) throws InterruptedException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(userPrice);
            session.getTransaction().commit();
            session.flush();
        } catch (LockAcquisitionException e) {
            loggerFactory.error(e);
            Thread.sleep(1000);
            loggerFactory.error("ok, go re addUserPrice");
            if (session != null && session.isOpen()) {
                session.close();
            }
            addUserPrice(userPrice);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void updateUserCard(UserCardEntity userCard) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userCard);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void updateUserPrice(UserPriceEntity userPrice) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userPrice);
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

    public static boolean contains(UserCardEntity userCardEntity) {
        if (userCardEntity != null) {
            UserCardEntity userCardFromBase = getUserCard(userCardEntity.getUserCardID());
            if (userCardFromBase != null && userCardEntity.equals(userCardFromBase)) {
                return true;
            }
        }
        return false;
    }


    public static boolean contains(UserPriceEntity userPriceEntity) {
        if (userPriceEntity != null) {
            UserPriceEntity userPriceFromBase = getUserPrice(userPriceEntity.getUserPriceID());
            if (userPriceFromBase != null && userPriceEntity.equals(userPriceFromBase)) {
                return true;
            }
        }
        return false;
    }

    public static CardUpdateAggregator getCardUpdateInfo(Long userID) {
        CardUpdateAggregator cardUpdateAggregator = new CardUpdateAggregator();
        setCardsForUpdate(userID, cardUpdateAggregator);
        setCardsForDelete(userID, cardUpdateAggregator);
        setCardsForDownLoad(userID, cardUpdateAggregator);
        return cardUpdateAggregator;
    }

    public static PricesUpdateAggregator getPricesUpdateInfo(Long userID) {
        PricesUpdateAggregator pricesUpdateAggregator = new PricesUpdateAggregator();
        setPricesForDelete(userID, pricesUpdateAggregator);
        setPricesForDownLoad(userID, pricesUpdateAggregator);
        setPricesForUpdate(userID, pricesUpdateAggregator);
        return pricesUpdateAggregator;
    }

    private static void setCardsForDownLoad(Long userID, CardUpdateAggregator cardUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardsForDownLoad");
        PreparedStatement ps = null;
        ResultSet rs = null;
        //cards to update
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID FROM Card " +
                    "WHERE Card.CardState=" + CardState.Active.getValue() + " AND Card.CardID NOT IN (" +
                    "SELECT UserCard.CardID FROM UserCard " +
                    "JOIN UserContent ON (UserContent.UserContentID=UserCard.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID) " +
                    "WHERE User.UserID=? " +
                    ")";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                cardUpdateAggregator.getCardsForDownload().add(rs.getLong("Card.CardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setPricesForDownLoad(Long userID, PricesUpdateAggregator priceUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setPricesForDownLoad");
        PreparedStatement ps = null;
        ResultSet rs = null;
        //prices to update
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT Price.PriceID FROM Price " +
                    "JOIN CardPriceLink ON (Price.PriceID=CardPriceLink.PriceID) " +
                    "JOIN Card ON (CardPriceLink.CardID=Card.CardID) " +
                    "WHERE Price.PriceID NOT IN (" +
                    "SELECT UserPrice.PriceID FROM UserPrice " +
                    "JOIN UserContent ON (UserContent.UserContentID=UserPrice.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID) " +
                    "WHERE User.UserID=? " +
                    ") AND Card.CardState=" + CardState.Active.getValue();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                priceUpdateAggregator.getPricesForDownload().add(rs.getLong("Price.PriceID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setCardsForDelete(Long userID, CardUpdateAggregator cardUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardsForDelete");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID FROM Card " +
                    "JOIN UserCard ON (Card.CardID=UserCard.CardID) " +
                    "JOIN UserContent ON (UserCard.UserContentID=UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID)" +
                    "WHERE Card.LastUpdateTimestamp>UserCard.LastUpdateTimeStamp AND User.UserID=? AND Card.CardState!=" + CardState.Active.getValue();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                cardUpdateAggregator.getCardsForDelete().add(rs.getLong("Card.CardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setPricesForDelete(Long userID, PricesUpdateAggregator priceUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setPricesForDelete");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT UserPrice.PriceID FROM UserPrice " +
                    "JOIN Price ON (Price.PriceID=UserPrice.PriceID) " +
                    "JOIN UserContent ON (UserPrice.UserContentID=UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID)" +
                    "WHERE Price.LastUpdateTimestamp>UserPrice.LastUpdateTimeStamp AND User.UserID=? " +
                    "AND UserPrice.PriceID NOT IN (SELECT CardPriceLink.PriceID FROM CardPriceLink " +
                    "JOIN Card ON (Card.CardID=CardPriceLink.CardID) WHERE Card.CardState=" + CardState.Active.getValue() + ")";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                priceUpdateAggregator.getPricesForDelete().add(rs.getLong("Price.PriceID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setCardsForUpdate(Long userID, CardUpdateAggregator cardUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardsForUpdate");
        PreparedStatement ps = null;
        ResultSet rs = null;
        //cards to update
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID FROM Card " +
                    "JOIN UserCard ON (Card.CardID=UserCard.CardID) " +
                    "JOIN UserContent ON (UserCard.UserContentID=UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID)" +
                    "WHERE Card.LastUpdateTimestamp>UserCard.LastUpdateTimeStamp AND User.UserID=? AND Card.CardState=" + CardState.Active.getValue();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                cardUpdateAggregator.getCardsForUpdate().add(rs.getLong("Card.CardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setPricesForUpdate(Long userID, PricesUpdateAggregator priceUpdateAggregator) {
        DatabaseConnection dbConnection = new DatabaseConnection("setPricesForUpdate");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Price.PriceID FROM Price " +
                    "JOIN UserPrice ON (Price.PriceID=UserPrice.PriceID) " +
                    "JOIN UserContent ON (UserPrice.UserContentID=UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID) " +
                    "JOIN CardPriceLink ON (Price.PriceID=CardPriceLink.CardID) " +
                    "JOIN Card ON (Card.CardID=CardPriceLink.CardID) " +
                    "WHERE Price.LastUpdateTimestamp>UserPrice.LastUpdateTimeStamp AND User.UserID=? AND Card.CardState=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            ps.setInt(2, CardState.Active.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                priceUpdateAggregator.getPricesForUpdate().add(rs.getLong("Price.PriceID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }


    public static UserCardEntity getUserCard(Long userID, Long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getUserCard");
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserCardEntity userCardEntity = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT UserCard.UserCardID FROM UserCard " +
                    "JOIN User ON (User.UserContentID=UserCard.UserContentID) " +
                    "WHERE UserCard.CardID=? AND User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            ps.setLong(2, userID);
            rs = ps.executeQuery();
            if (rs.first()) {
                userCardEntity = getUserCard(rs.getLong("UserCard.UserCardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return userCardEntity;
    }

    public static UserPriceEntity getUserPrice(Long userID, Long priceID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getUserPrice 1");
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserPriceEntity userPriceEntity = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT UserPrice.UserPriceID FROM UserPrice " +
                    "JOIN User ON (User.UserContentID=UserPrice.UserContentID) " +
                    "WHERE UserPrice.PriceID=? AND User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, priceID);
            ps.setLong(2, userID);
            rs = ps.executeQuery();
            if (rs.first()) {
                userPriceEntity = getUserPrice(rs.getLong("UserPrice.UserPriceID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return userPriceEntity;
    }

    public static void userGlobalUpdateRequest(UserEntity userEntity) {
        userEntity.getUserContent().setLastSynchronizeTimestamp(TimeManager.currentTime());
        userEntity.getUserContent().setLastConnectionTimestamp(TimeManager.currentTime());
        UserRequests.updateUserContent(userEntity.getUserContent());
    }

    public static void updateTags() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            GlobalUpdateEntity globalUpdateEntity = (GlobalUpdateEntity) session.get(GlobalUpdateEntity.class, 1L);
            globalUpdateEntity.setTagsUpdateTime(TimeManager.currentTime());
            session.update(globalUpdateEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void updateDishCategories() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            GlobalUpdateEntity globalUpdateEntity = (GlobalUpdateEntity) session.get(GlobalUpdateEntity.class, 1L);
            globalUpdateEntity.setDishCategoriesUpdateTime(TimeManager.currentTime());
            session.update(globalUpdateEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void updateDishTags() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            GlobalUpdateEntity globalUpdateEntity = (GlobalUpdateEntity) session.get(GlobalUpdateEntity.class, 1L);
            globalUpdateEntity.setDishTagsUpdateTime(TimeManager.currentTime());
            session.update(globalUpdateEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void updateMenus() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            GlobalUpdateEntity globalUpdateEntity = (GlobalUpdateEntity) session.get(GlobalUpdateEntity.class, 1L);
            globalUpdateEntity.setMenusUpdateTime(TimeManager.currentTime());
            session.update(globalUpdateEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void updateParameterType() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            GlobalUpdateEntity globalUpdateEntity = (GlobalUpdateEntity) session.get(GlobalUpdateEntity.class, 1L);
            globalUpdateEntity.setParameterTypeUpdateTime(TimeManager.currentTime());
            session.update(globalUpdateEntity);
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

    public static UserGlobalUpdateStatus getUserStatus(Long userID) {

        DatabaseConnection dbConnection = new DatabaseConnection("getUserPrice 2");
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserGlobalUpdateStatus userGlobalUpdateStatus = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT UserContent.LastSynchronizeTimestamp, " +
                    "GlobalUpdate.DishCategoriesUpdateTime, " +
                    "GlobalUpdate.DishTagsUpdateTime, " +
                    "GlobalUpdate.MenusUpdateTime, " +
                    "GlobalUpdate.ParameterTypeUpdateTime, " +
                    "GlobalUpdate.TagsUpdateTime " +
                    "FROM UserContent " +
                    "JOIN User ON (User.UserContentID=UserContent.UserContentID) " +
                    "JOIN GlobalUpdate ON (1=1)" +
                    "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            if (rs.first()) {
                userGlobalUpdateStatus = new UserGlobalUpdateStatus();
                Timestamp lastSynchronizeTimestamp = rs.getTimestamp("UserContent.LastSynchronizeTimestamp");
                Timestamp dishCategoriesUpdateTime = rs.getTimestamp("GlobalUpdate.DishCategoriesUpdateTime");
                Timestamp dishTagsUpdateTime = rs.getTimestamp("GlobalUpdate.DishTagsUpdateTime");
                Timestamp menusUpdateTime = rs.getTimestamp("GlobalUpdate.MenusUpdateTime");
                Timestamp parameterTypeUpdateTime = rs.getTimestamp("GlobalUpdate.ParameterTypeUpdateTime");
                Timestamp tagsUpdateTime = rs.getTimestamp("GlobalUpdate.TagsUpdateTime");
                loggerFactory.debug("lastSynchronizeTimestamp " + lastSynchronizeTimestamp);
                loggerFactory.debug("dishCategoriesUpdateTime " + dishCategoriesUpdateTime);
                loggerFactory.debug("dishTagsUpdateTime " + dishTagsUpdateTime);
                loggerFactory.debug("menusUpdateTime " + menusUpdateTime);
                loggerFactory.debug("parameterTypeUpdateTime " + parameterTypeUpdateTime);
                loggerFactory.debug("tagsUpdateTime " + tagsUpdateTime);

                if (lastSynchronizeTimestamp == null || lastSynchronizeTimestamp.before(dishCategoriesUpdateTime)) {
                    userGlobalUpdateStatus.setNeedToDishCategoriesUpdate(true);
                } else {
                    userGlobalUpdateStatus.setNeedToDishCategoriesUpdate(false);
                }
                if (lastSynchronizeTimestamp == null || lastSynchronizeTimestamp.before(dishTagsUpdateTime)) {
                    userGlobalUpdateStatus.setNeedToDishTagsUpdate(true);
                } else {
                    userGlobalUpdateStatus.setNeedToDishTagsUpdate(false);
                }
                if (lastSynchronizeTimestamp == null || lastSynchronizeTimestamp.before(menusUpdateTime)) {
                    userGlobalUpdateStatus.setNeedToMenusUpdate(true);
                } else {
                    userGlobalUpdateStatus.setNeedToMenusUpdate(false);
                }
                if (lastSynchronizeTimestamp == null || lastSynchronizeTimestamp.before(parameterTypeUpdateTime)) {
                    userGlobalUpdateStatus.setNeedToParameterTypeUpdate(true);
                } else {
                    userGlobalUpdateStatus.setNeedToParameterTypeUpdate(false);
                }
                if (lastSynchronizeTimestamp == null || lastSynchronizeTimestamp.before(tagsUpdateTime)) {
                    userGlobalUpdateStatus.setNeedToTagsUpdate(true);
                } else {
                    userGlobalUpdateStatus.setNeedToTagsUpdate(false);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return userGlobalUpdateStatus;
    }


    public static void updateUserCards(UserEntity user, ArrayList<Object> cardsForUpdate, Timestamp currentTime) {
        if (cardsForUpdate.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("updateUserCards");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "UPDATE UserCard SET UserCard.LastUpdateTimeStamp=? " +
                    "WHERE UserCard.CardID IN (" + StringHelper.getStringFromArray(cardsForUpdate) + ") AND UserCard.UserContentID=?";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, currentTime);
            ps.setLong(2, user.getUserContent().getUserContentID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void createUserCards(UserEntity user, ArrayList<Long> cardsForCreate, Timestamp currentTime) {
        if (cardsForCreate.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("createUserCards");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO UserCard (CardID,DownloadTimeStamp,LastUpdateTimeStamp,UserContentID) " +
                    "VALUES ";
            for (Long aCardsForCreate : cardsForCreate) {
                sql += "(" + aCardsForCreate + ",'" + currentTime + "','" + currentTime + "'," + user.getUserContent().getUserContentID() + "),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }


    public static void createUserPrices(UserEntity user, ArrayList<Long> pricesForCreate, Timestamp currentTime) {
        if (pricesForCreate.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("createUserPrices");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO UserPrice (PriceID,DownloadTimeStamp,LastUpdateTimeStamp,UserContentID) " +
                    "VALUES ";
            for (Long aPriceForCreate : pricesForCreate) {
                sql += "(" + aPriceForCreate + ",'" + currentTime + "','" + currentTime + "'," + user.getUserContent().getUserContentID() + "),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }


    public static void updateUserPrices(UserEntity user, ArrayList<Object> pricesForUpdate, Timestamp currentTime) {
        if (pricesForUpdate.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("updateUserCards");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "UPDATE UserPrice SET UserPrice.LastUpdateTimeStamp=? " +
                    "WHERE UserPrice.PriceID IN (" + StringHelper.getStringFromArray(pricesForUpdate) + ") AND UserPrice.UserContentID=?";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, currentTime);
            ps.setLong(2, user.getUserContent().getUserContentID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

}
