package model.database.requests;


import model.constants.Component;
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
import java.sql.SQLException;


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
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }
}
