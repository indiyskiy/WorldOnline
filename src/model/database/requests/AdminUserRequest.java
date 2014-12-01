package model.database.requests;

import helper.Md5Hash;
import helper.TimeManager;
import model.additionalentity.admin.LoginRequest;
import model.additionalentity.admin.ParsedRegistrationRequest;
import model.constants.Component;
import model.constants.MailConsts;
import model.constants.ProtectAdminLevel;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.AdminUserAdditionalInfoEntity;
import model.database.worldonlinedb.AdminUserEntity;
import model.database.worldonlinedb.AdminUserSessionEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminUserRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, AdminUserRequest.class);

    public static ProtectAdminLevel getProtectedLevel(String key) {
        DatabaseConnection dbConnection = new DatabaseConnection("getProtectedLevel");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ProtectAdminLevel protectAdminLevel = ProtectAdminLevel.Unregistered;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT AdminUser.AdminRole FROM AdminUser  " +
                    "JOIN AdminUserSession ON (AdminUserSession.AdminUserID=AdminUser.AdminUserID) " +
                    "WHERE AdminUserSession.SessionKey=? " +
                    "AND AdminUser.Confirmed=TRUE";
            ps = connection.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            if (rs.first()) {
                protectAdminLevel = ProtectAdminLevel.parseInt(rs.getInt("AdminUser.AdminRole"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return protectAdminLevel;
    }

    public static String getKey(LoginRequest loginRequest) {
        DatabaseConnection dbConnection = new DatabaseConnection("getKey");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String key = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT AdminUserName,AdminUserPassword " +
                    "FROM AdminUser " +
                    "WHERE AdminUserName=? " +
                    "AND adminUserPassword=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, loginRequest.getLogin());
            ps.setString(2, loginRequest.getPassword());
            rs = ps.executeQuery();
            if (rs.first()) {
                String login = rs.getString("AdminUserName");
                String password = rs.getString("AdminUserPassword");
                key = Md5Hash.getMd5Hash(login + " @#$%^ " + password);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return key;
    }


    public static boolean addAdminUser(AdminUserEntity adminUser) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(adminUser);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }


    public static boolean editAdminUser(AdminUserEntity adminUser) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.update(adminUser);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static Long countUsers() {
        Long res = null;
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            res = (Long) session.createCriteria(AdminUserEntity.class).setProjection(Projections.rowCount()).uniqueResult();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return res;
    }


    public static void addRootAdminUser() {
        if (AdminUserRequest.countUsers(ProtectAdminLevel.Administrator) == 0) {
            addUser("root", "eva01hashin", true, ProtectAdminLevel.Administrator, MailConsts.Evdokimov, "root", "root");
        }
        if (AdminUserRequest.countUsers(ProtectAdminLevel.HeightModerator) == 0) {
            addUser("hModerator", "ufkfrnbrfjgfcyjcnt", true, ProtectAdminLevel.HeightModerator, MailConsts.Evdokimov, "Height", "Moderator");
        }
        if (AdminUserRequest.countUsers(ProtectAdminLevel.HeightModerator) == 0) {
            addUser("moderator", "sudoaptgetinstall", true, ProtectAdminLevel.LowModerator, MailConsts.Evdokimov, "Low", "Moderator");
        }
    }

    private static int countUsers(ProtectAdminLevel protectAdminLevel) {
        DatabaseConnection dbConnection = new DatabaseConnection("countUsers");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int counter = 0;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT count(*) AS counter " +
                    "FROM AdminUser " +
                    "WHERE AdminUser.AdminRole=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, protectAdminLevel.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                counter = rs.getInt("counter");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return counter;
    }

    public static void addUser(String name, String pass, boolean confirmed, ProtectAdminLevel protectAdminLevel, String mail, String firstName, String lastName) {
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setAdminUserName(name);
        adminUserEntity.setAdminUserPassword(Md5Hash.getMd5Hash(pass));
        adminUserEntity.setConfirmed(confirmed);
        adminUserEntity.setAdminRole(protectAdminLevel.getValue());
        AdminUserAdditionalInfoEntity adminUserAdditionalInfo = new AdminUserAdditionalInfoEntity();
        adminUserAdditionalInfo.setAdminUserEmail(mail);
        adminUserAdditionalInfo.setAdminUserFirstName(firstName);
        adminUserAdditionalInfo.setAdminUserSecondName(lastName);
        adminUserAdditionalInfo.setAdminUserRegistrationTimestamp(TimeManager.currentTime());
        adminUserEntity.setAdminUserAdditionalInfo(adminUserAdditionalInfo);
        AdminUserRequest.addAdminUser(adminUserEntity);
    }

    public static void addSession(String key, String login) {
        AdminUserEntity userEntity = getUserByLogin(login);
        AdminUserSessionEntity adminUserSessionEntity = new AdminUserSessionEntity();
        adminUserSessionEntity.setAdminUser(userEntity);
        adminUserSessionEntity.setSessionKey(key);
        addSession(adminUserSessionEntity);
    }

    public static AdminUserEntity getUserByLogin(String login) {
        DatabaseConnection dbConnection = new DatabaseConnection("getUserByLogin");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * " +
                    "FROM AdminUser " +
                    "JOIN AdminUserAdditionalInfo ON (AdminUserAdditionalInfo.AdminUserAdditionalInfoID=AdminUser.AdminUserAdditionalInfoID) " +
                    "WHERE AdminUser.AdminUserName=?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.first()) {
                AdminUserEntity adminUserEntity = new AdminUserEntity();
                adminUserEntity.setAdminUserName(rs.getString("AdminUser.AdminUserName"));
                adminUserEntity.setAdminUserPassword(rs.getString("AdminUser.AdminUserPassword"));
                adminUserEntity.setAdminUserID(rs.getLong("AdminUser.AdminUserID"));
                adminUserEntity.setConfirmed(rs.getBoolean("AdminUser.Confirmed"));
                adminUserEntity.setAdminRole(rs.getInt("AdminUser.AdminRole"));
                AdminUserAdditionalInfoEntity adminUserAdditionalInfoEntity = new AdminUserAdditionalInfoEntity();
                adminUserAdditionalInfoEntity.setAdminUserRegistrationTimestamp(rs.getTimestamp("AdminUserAdditionalInfo.AdminUserRegistrationTimestamp"));
                adminUserAdditionalInfoEntity.setAdminUserFirstName(rs.getString("AdminUserAdditionalInfo.AdminUserFirstName"));
                adminUserAdditionalInfoEntity.setAdminUserEmail(rs.getString("AdminUserAdditionalInfo.AdminUserEmail"));
                adminUserAdditionalInfoEntity.setAdminUserSecondName(rs.getString("AdminUserAdditionalInfo.AdminUserSecondName"));
                adminUserAdditionalInfoEntity.setAdminUserAdditionalInfoID(rs.getLong("AdminUserAdditionalInfo.AdminUserAdditionalInfoID"));
                adminUserEntity.setAdminUserAdditionalInfo(adminUserAdditionalInfoEntity);
                return adminUserEntity;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    private static boolean addSession(AdminUserSessionEntity adminUserSessionEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(adminUserSessionEntity);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static void registUser(ParsedRegistrationRequest parsedRegistrationRequest) {
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        adminUserEntity.setAdminUserPassword(parsedRegistrationRequest.getPassword());
        adminUserEntity.setConfirmed(false);
        adminUserEntity.setAdminUserName(parsedRegistrationRequest.getLogin());
        adminUserEntity.setAdminRole(ProtectAdminLevel.Registered.getValue());
        AdminUserAdditionalInfoEntity additionalInfoEntity = new AdminUserAdditionalInfoEntity();
        additionalInfoEntity.setAdminUserSecondName(parsedRegistrationRequest.getSecondName());
        additionalInfoEntity.setAdminUserEmail(parsedRegistrationRequest.getEmail());
        additionalInfoEntity.setAdminUserFirstName(parsedRegistrationRequest.getFirstName());
        additionalInfoEntity.setAdminUserRegistrationTimestamp(TimeManager.currentTime());
        adminUserEntity.setAdminUserAdditionalInfo(additionalInfoEntity);
        addAdminUser(adminUserEntity);
    }

    public static String getMd5Hash(String login, String email, String password, String firstName, String secondName) {
        String str = "";
        str += login;
        str += email;
        str += password;
        str += firstName;
        str += secondName;
        str = Md5Hash.getMd5Hash(str);
        return str;
    }

}
