package model.database.requests;

import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardCoordinateEntity;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinateRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, CoordinateRequest.class);

    public static boolean addCardCoordinate(CardCoordinateEntity cardCoordinateEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.save(cardCoordinateEntity);
            session.getTransaction().commit();
            session.flush();
            return true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardCoordinateEntity getCardCoordinate(long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT CardCoordinate.CardID," +
                    "CardCoordinate.CardCoordinateID, " +
                    "CardCoordinate.Latitude, " +
                    "CardCoordinate.Longitude " +
                    "FROM CardCoordinate " +
                    "JOIN Card ON (Card.CardID=CardCoordinate.CardID) " +
                    "WHERE Card.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                CardCoordinateEntity cardCoordinate = new CardCoordinateEntity();
                cardCoordinate.setCard(CardRequest.getCardByID(rs.getLong("CardCoordinate.CardID")));
                cardCoordinate.setCardCoordinateID(rs.getLong("CardCoordinate.CardCoordinateID"));
                cardCoordinate.setLatitude(rs.getDouble("CardCoordinate.Latitude"));
                cardCoordinate.setLongitude(rs.getDouble("CardCoordinate.Longitude"));
                return cardCoordinate;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static boolean editCardCoordinate(CardCoordinateEntity cardCoordinate) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.update(cardCoordinate);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static void addCardCoordinate(CardEntity cardEntity) {
        CardCoordinateEntity cardCoordinateEntity = new CardCoordinateEntity();
        cardCoordinateEntity.setCard(cardEntity);
        addCardCoordinate(cardCoordinateEntity);
    }

    public static boolean cardCoordinateExist(CardEntity cardEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT 1 " +
                    "FROM CardCoordinate " +
                    "JOIN Card ON (Card.CardID=CardCoordinate.CardID) " +
                    "WHERE Card.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardEntity.getCardID());
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
}

