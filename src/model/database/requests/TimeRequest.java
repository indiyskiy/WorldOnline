package model.database.requests;


import model.additionalentity.admin.CardUrgencyTime;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.admin.SimpleDate;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileUrgencyTime;
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
import java.util.HashMap;


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

    public static UrgencyTimeEntity getMaxUrgencyTime(CardEntity cardEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("getMaxUrgencyTime");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * " +
                    "FROM UrgencyTime " +
                    "WHERE UrgencyTime.cardID=? " +
                    "  ORDER BY UrgencyTime.OnTimestamp DESC LIMIT 1";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardEntity.getCardID());
            rs = ps.executeQuery();
            if (rs.first()) {
                long urgencyTimeEntityID = rs.getLong("UrgencyTime.UrgencyTimeID");
                return getUrgencyTimeEntity(urgencyTimeEntityID);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static boolean disableOldEvents() {
        DatabaseConnection dbConnection = new DatabaseConnection("containsUrgencyTimeEntity");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") final String sql = "UPDATE Card  " +
                    "JOIN UrgencyTime ON (Card.CardID=UrgencyTime.cardID) " +
                    "SET Card.CardState=" + CardState.NotActive.getValue() + ", " +
                    "Card.LastUpdateTimestamp=Now() " +
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
                    "SET Card.CardState=" + CardState.Deleted.getValue() + ", " +
                    "Card.LastUpdateTimestamp=Now() " +
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


    public static void setUrgencyTime(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setSourceCardLinks");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM Card " +
                            "LEFT OUTER JOIN UrgencyTime ON (UrgencyTime.cardID=Card.CardID) " +
                            "WHERE Card.CardID=? " +
                            "ORDER BY UrgencyTime.OnTimestamp DESC";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long urgencyTimeID = rs.getLong("UrgencyTime.UrgencyTimeID");
                if (urgencyTimeID != 0 && !rs.wasNull()) {
                    CardUrgencyTime cardUrgencyTime = new CardUrgencyTime();
//                    SimpleUrgencyTime simpleUrgencyTime = new SimpleUrgencyTime();
                    cardUrgencyTime.setStart(new SimpleDate(rs.getTimestamp("UrgencyTime.OnTimestamp")));
                    cardUrgencyTime.setEnd(new SimpleDate(rs.getTimestamp("UrgencyTime.OffTimestamp")));
                    cardUrgencyTime.setUrgencyTimeID(rs.getLong("UrgencyTime.UrgencyTimeID"));
                    card.getUrgencyTimes().add(cardUrgencyTime);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static void setMobileUrgencyTimes(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileUrgencyTimes");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "UrgencyTime.cardID, " +
                    "UrgencyTime.OnTimestamp, " +
                    "UrgencyTime.OffTimestamp," +
                    "UrgencyTime.UrgencyTimeID " +
                    "FROM UrgencyTime " +
                    "WHERE UrgencyTime.cardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("UrgencyTime.CardID"));
                MobileUrgencyTime mobileUrgencyTime = new MobileUrgencyTime();
                mobileUrgencyTime.setStart(rs.getTimestamp("UrgencyTime.OnTimestamp"));
                mobileUrgencyTime.setEnd(rs.getTimestamp("UrgencyTime.OffTimestamp"));
                mobileUrgencyTime.setUrgencyTimeID(rs.getLong("UrgencyTime.UrgencyTimeID"));
                mobileCardInfo.getMobileUrgencyTimes().add(mobileUrgencyTime);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }
}
