package model.database.requests;


import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UrgencyTimeEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TimeRequest {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserDataRequest.class);

    public static UrgencyTimeEntity getUrgencyTimeEntity(Long urgencyTimeEntityID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (UrgencyTimeEntity) session.get(UrgencyTimeEntity.class, urgencyTimeEntityID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void addUrgencyTimeEntity(UrgencyTimeEntity urgencyTimeEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(urgencyTimeEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void updateUrgencyTimeEntity(UrgencyTimeEntity urgencyTimeEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(urgencyTimeEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean containsUrgencyTimeEntity(CardEntity cardEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("containsUrgencyTimeEntity");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT UrgencyTime.UrgencyTimeID " +
                    "FROM UrgencyTime " +
                    "WHERE UrgencyTime.cardID=?";
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


    public static boolean disableOldEvents() {
        DatabaseConnection dbConnection = new DatabaseConnection("containsUrgencyTimeEntity");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") final String sql = "UPDATE Card  " +
                    "JOIN UrgencyTime ON (Card.CardID=UrgencyTime.cardID) " +
                    "SET Card.CardState=" + CardState.NotActive.getValue() + " " +
                    "WHERE " +
                    "Card.CardState=" + CardState.Active.getValue() + " AND " +
                    "UrgencyTime.OffTimestamp!=UrgencyTime.OnTimestamp AND " +
                    "UrgencyTime.OffTimestamp IS NOT NULL AND " +
                    "UrgencyTime.OnTimestamp IS NOT NULL AND " +
                    "(TO_DAYS(UrgencyTime.OffTimestamp)-TO_DAYS(Now()))<=-2 AND " +
                    "UrgencyTime.UrgencyTimeID NOT IN ( " +
                    "  SELECT UrgencyTime.UrgencyTimeID FROM UrgencyTime " +
                    "  JOIN UrgencyTime AS tmp ON (tmp.cardID=UrgencyTime.cardID) " +
                    "  WHERE tmp.OffTimestamp>UrgencyTime.OffTimestamp " +
                    ") ";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
        return false;
    }


    public static boolean deleteElderEvents() {
        DatabaseConnection dbConnection = new DatabaseConnection("containsUrgencyTimeEntity");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") final String sql = "UPDATE Card  " +
                    "JOIN UrgencyTime ON (Card.CardID=UrgencyTime.cardID) " +
                    "SET Card.CardState=" + CardState.Deleted.getValue() + " " +
                    "WHERE " +
                    "Card.CardState=" + CardState.NotActive.getValue() + " AND " +
                    "UrgencyTime.OffTimestamp!=UrgencyTime.OnTimestamp AND " +
                    "UrgencyTime.OffTimestamp IS NOT NULL AND " +
                    "UrgencyTime.OnTimestamp IS NOT NULL AND " +
                    "(TO_DAYS(UrgencyTime.OffTimestamp)-TO_DAYS(Now()))<=-28 AND " +
                    "UrgencyTime.UrgencyTimeID NOT IN ( " +
                    "  SELECT UrgencyTime.UrgencyTimeID FROM UrgencyTime " +
                    "  JOIN UrgencyTime AS tmp ON (tmp.cardID=UrgencyTime.cardID) " +
                    "  WHERE tmp.OffTimestamp>UrgencyTime.OffTimestamp " +
                    ") ";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
        return false;
    }


}
