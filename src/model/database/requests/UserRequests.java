package model.database.requests;

import model.additionalentity.CompleteCardInfo;
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
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
            UserEntity userFromBase = getUserByID(user.getUserId());
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

}