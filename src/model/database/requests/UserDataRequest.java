package model.database.requests;

import model.additionalentity.phone.CardUpdateAggregator;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.UserCardEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public static void addUserCard(UserCardEntity userCard) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(userCard);
            session.getTransaction().commit();
            session.flush();
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

    public static boolean contains(UserCardEntity userCardEntity) {
        if (userCardEntity != null) {
            UserCardEntity userCardFromBase = getUserCard(userCardEntity.getUserCardID());
            if (userCardFromBase != null && userCardEntity.equals(userCardFromBase)) {
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
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void setCardsForDelete(Long userID, CardUpdateAggregator cardUpdateAggregator) {
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
                    "WHERE Card.LastUpdateTimestamp>UserCard.LastUpdateTimeStamp AND User.UserID=? AND Card.CardState!=" + CardState.Active.getValue();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                cardUpdateAggregator.getCardsForDelete().add(rs.getLong("Card.CardID"));
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }
}
