package model.database.requests;

import helper.TimeManager;
import model.additionalentity.admin.SimpleUserInfo;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;
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
import java.util.ArrayList;
import java.util.List;

public class UserRequests {

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserRequests.class);

    public static void editUser(UserEntity userEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
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

    public static void userOnline(UserEntity userEntity) {
        userEntity.getUserContent().setLastConnectionTimestamp(TimeManager.currentTime());
        editUser(userEntity);
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
//            if (userEntity.getUserContent() == null) {
//                loggerFactory.error("user entity " + userID + " personal data is null");
//            }
            return userEntity;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
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
        DatabaseConnection dbConnection = new DatabaseConnection("isDeviceIDUnique");
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
        } catch (Exception e) {
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
        DatabaseConnection dbConnection = new DatabaseConnection("isUserExist");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return b;
    }

    public static RestoreUserResponse getUserByDeviceID(String deviceID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getUserByDeviceID");
        PreparedStatement ps = null;
        ResultSet rs = null;
        RestoreUserResponse restoreUserResponse = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT User.UserID FROM User " +
                    "JOIN UserHardware ON (User.UserHardwareID=UserHardware.UserHardwareID) " +
                    "WHERE UserHardware.DeviceUniqueKey = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deviceID);
            rs = ps.executeQuery();
            if (rs.first()) {
                restoreUserResponse = new RestoreUserResponse();
                restoreUserResponse.setUserID(rs.getLong("User.UserID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return restoreUserResponse;
    }

    public static void deleteOldUserInfo(long userID) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteOldUserInfo");
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
            sql = "DELETE FROM UserPrice " +
                    "WHERE UserPrice.UserPriceID IN ( " +
                    "SELECT " +
                    "ids " +
                    "FROM ( " +
                    "SELECT " +
                    "temp.UserPriceID AS ids " +
                    "FROM UserPrice AS temp " +
                    "JOIN UserContent ON (temp.UserContentID = UserContent.UserContentID) " +
                    "JOIN User ON (User.UserContentID = UserContent.UserContentID) " +
                    "WHERE User.userID = ? " +
                    ")AS additionalTable)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void updateUserContent(UserContentEntity userContent) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userContent);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void userGlobalUpdated(UserEntity userEntity) {
        userEntity.getUserContent().setLastSynchronizeTimestamp(TimeManager.currentTime());
        updateUserContent(userEntity.getUserContent());
    }

    public static ArrayList<SimpleUserInfo> getAllSimpleUsers(long offset, int limit) {
        DatabaseConnection dbConnection = new DatabaseConnection("getAllSimpleUsers");
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SimpleUserInfo> simpleUserInfos = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT User.UserID, " +
                    "User.UserRegistrationTimestamp, " +
                    "UserHardware.MobilePlatform, " +
                    "UserPersonalData.UserLanguage, " +
                    "UserPersonalData.AdditionalInformation, " +
                    "UserContent.LastConnectionTimestamp, " +
                    "UserContent.LastSynchronizeTimestamp " +
                    "FROM User " +
                    "JOIN UserHardware ON (User.UserHardwareID=UserHardware.UserHardwareID) " +
                    "JOIN UserContent ON (User.UserContentID=UserContent.UserContentID) " +
                    "JOIN UserPersonalData ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "ORDER BY UserContent.LastConnectionTimestamp DESC LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, offset);
            ps.setInt(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
                simpleUserInfo.setUserID(rs.getLong("User.UserID"));
                simpleUserInfo.setUserLanguage(LanguageType.parseInt(rs.getInt("UserPersonalData.UserLanguage")));
                simpleUserInfo.setAdditionalInformation(rs.getString("UserPersonalData.AdditionalInformation"));
                simpleUserInfo.setLastConnectionTimestamp(rs.getTimestamp("UserContent.LastConnectionTimestamp"));
                simpleUserInfo.setLastSynchronizeTimestamp(rs.getTimestamp("UserContent.LastSynchronizeTimestamp"));
                simpleUserInfo.setUserRegistrationTimestamp(rs.getTimestamp("User.UserRegistrationTimestamp"));
                simpleUserInfo.setMobilePlatform(MobilePlatform.parseInt(rs.getInt("UserHardware.MobilePlatform")));
                simpleUserInfos.add(simpleUserInfo);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleUserInfos;
    }

    public static void userOnline(Long userID) {
        DatabaseConnection dbConnection = new DatabaseConnection("userOnline");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE UserContent JOIN User ON (User.UserContentID=UserContent.UserContentID) " +
                    "SET LastConnectionTimestamp=? " +
                    "WHERE User.UserID=?";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, TimeManager.currentTime());
            ps.setLong(2, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }
}