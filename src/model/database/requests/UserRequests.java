package model.database.requests;

import controller.parser.edit.adminparser.AllUsersParser;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.UserContentEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserHardwareEntity;
import model.database.worldonlinedb.UserPersonalDataEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 17.10.13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class UserRequests {

    private static LoggerFactory loggerFactory=new LoggerFactory(Component.Database,UserRequests.class);

    public static void editTag(UserEntity userEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> userEntity = new ArrayList<UserEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            userEntity = (ArrayList<UserEntity>) session.createCriteria(UserEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userEntity;
    }

    public static UserEntity getUserByID(Long userID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (UserEntity) session.get(UserEntity.class, userID);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean addUser(UserEntity user) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static UserEntity createNewUser(UserEntity user) {
        user.setUserPersonalData(new UserPersonalDataEntity());
        user.setUserContent(new UserContentEntity());
        user.setUserHardware(new UserHardwareEntity());
        return user;
    }

    public static boolean contains(UserEntity user) {
        if (user != null) {
            UserEntity userFromBase = getUserByID(user.getUserID());
            if (userFromBase != null && user.equals(userFromBase)) {
                return true;
            }
        }
        return false;
    }

    public static void addUsers(List<UserEntity> users) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for (UserEntity user : users) {
                session.save(user);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean isDeviceIDUnique(String deviceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean b=true;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT UserHardware.UserHardwareID FROM UserHardware " +
                    "WHERE UserHardware.DeviceUniqueKey=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,deviceID);
            rs = ps.executeQuery();
            if (rs.first()) {
             b=false;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return b;
    }

    public static ArrayList<UserEntity> getAllUsers(int firstElem, int maxElems) {
        ArrayList<UserEntity> userEntities = new ArrayList<UserEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            userEntities = (ArrayList<UserEntity>) session.createCriteria(UserEntity.class).setFirstResult(firstElem).setMaxResults(maxElems).list();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userEntities;
    }

    public static long countUser() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(UserEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public static ArrayList<UserEntity> getAllUserss(AllUsersParser parser) {
        ArrayList<UserEntity> users = new ArrayList<UserEntity>();
      /*  DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "WHERE 1=1";
            if (parser.getCardID() != null) {
                sql += " AND Card.CardID=?";
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += " AND Card.CardName=?";
            }
            if (parser.getCardType() != null) {
                sql += " AND Card.CardType=?";
            }
            sql += " LIMIT " + parser.getFirstElem() + ", " + parser.getMaxItems();
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (parser.getCardID() != null) {
                ps.setLong(i, parser.getCardID());
                i++;
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                ps.setString(i, parser.getCardName());
                i++;
            }
            if (parser.getCardType() != null) {
                ps.setInt(i, parser.getCardType().getValue());
                i++;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                CardEntity cardEntity = getCardFromResultSet(rs);
                cards.add(cardEntity);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }*/
        return users;
    }

    public static Long countUser(AllUsersParser parser) {
        return 0L;
    }
}