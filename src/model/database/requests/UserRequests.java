package model.database.requests;

import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.UserContentEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserHardwareEntity;
import model.database.worldonlinedb.UserPersonalDataEntity;
import model.logger.LoggerFactory;
import model.phone.responseentity.RestoreUserResponse;
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

public class UserRequests {

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserRequests.class);

    public static void editUser(UserEntity userEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
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
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userEntity;
    }


    public static UserEntity getUserByID(Long userID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);
            if (userEntity.getUserContent() == null) {
                loggerFactory.error("user entity " + userID + " personal data is null");
            }
            return userEntity;
        } finally {
            if (session != null && session.isOpen()) {
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
            session.flush();
            return true;
        } finally {
            if (session != null && session.isOpen()) {
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
        try {
            session.beginTransaction();
            for (UserEntity user : users) {
                session.save(user);
            }
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean isDeviceIDUnique(String deviceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean b = true;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT UserHardware.UserHardwareID FROM UserHardware " +
                    "WHERE UserHardware.DeviceUniqueKey=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deviceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                b = false;
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
        try {
            Transaction transaction = session.beginTransaction();
            userEntities = (ArrayList<UserEntity>) session.createCriteria(UserEntity.class).setFirstResult(firstElem).setMaxResults(maxElems).list();
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userEntities;
    }

    public static long countUser() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(UserEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }


    public static boolean isUserExist(Long userID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT User.UserID FROM User " +
                    "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            if (rs.first()) {
                b = true;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return b;
    }

    public static RestoreUserResponse getUserByDeviceID(String deviceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT User.UserID FROM User " +
                    "JOIN UserHardware ON (User.UserHardwareID=UserHardware.UserHardwareID) " +
                    "WHERE UserHardware.DeviceUniqueKey = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deviceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                RestoreUserResponse restoreUserResponse = new RestoreUserResponse();
                restoreUserResponse.setUserID(rs.getLong("User.UserID"));
                return restoreUserResponse;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static void deleteOldUserInfo(long userID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "DELETE FROM UserCard " +
                    "WHERE UserCard.UserCardID IN ( " +
                    "SELECT " +
                    "ids " +
                    "FROM ( " +
                    "SELECT " +
                    "temp.UserCardID AS ids " +
                    "FROM UserCard AS temp " +
                    "JOIN UserContent ON (temp.UserContentID = UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID = UserContent.UserContentID) " +
                    "WHERE User.userID = ? " +
                    ")AS additionalTable)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }
}