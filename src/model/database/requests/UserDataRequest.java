package model.database.requests;

import model.additionalentity.phone.CardUpdateAggregator;
import model.additionalentity.phone.PricesUpdateAggregator;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.UserCardEntity;
import model.database.worldonlinedb.UserPriceEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.exception.LockAcquisitionException;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDataRequest {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserDataRequest.class);

    public static UserCardEntity getUserCard(Long userCardID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (UserCardEntity) session.get(UserCardEntity.class, userCardID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //prices to update
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Price.PriceID FROM Price " +
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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
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
                return getUserCard(rs.getLong("UserCard.UserCardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static UserPriceEntity getUserPrice(Long userID, Long priceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
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
                return getUserPrice(rs.getLong("UserPrice.UserPriceID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }
}
