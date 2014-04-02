package model.database.requests;

import model.Md5Hash;
import model.additionalentity.LoginRequest;
import model.constants.Component;
import model.constants.ProtectAdminLevel;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, AdminUserRequest.class);

    public static ProtectAdminLevel getProtectedLevel(String key) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT AdminRole.AdminRoleName " +
                    "FROM AdminRole " +
                    "JOIN AdminUser ON (AdminUser.AdminRoleID=AdminRole.AdminRoleID) " +
                    "JOIN AdminUserSession ON (AdminUserSession.AdminUserID=AdminUser.AdminUserID) " +
                    "WHERE AdminUserSession.SessionKey=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            if (rs.first()) {
                return ProtectAdminLevel.parse(rs.getString("AdminRole.AdminRoleName"));
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return ProtectAdminLevel.Unregistered;
    }

    public static String getKey(LoginRequest loginRequest) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
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
                return Md5Hash.getMd5Hash(login + " @#$%^ " + password);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static boolean addAdminRole(AdminRoleEntity adminRole) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(adminRole);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean addAdminUser(AdminUserEntity adminUser) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(adminUser);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Long countUsers() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(AdminUserEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public static Long countRoles() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(AdminRoleEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public static void addRootAdminUser() {
        if (AdminUserRequest.countUsers() == 0 && AdminUserRequest.countRoles() == 0) {
            AdminUserEntity adminUserEntity = new AdminUserEntity();
            adminUserEntity.setAdminUserName("root");
            adminUserEntity.setAdminUserPassword(Md5Hash.getMd5Hash("eva01hashin"));
            for (ProtectAdminLevel protectAdminLevel : ProtectAdminLevel.values()) {
                AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
                adminRoleEntity.setAdminRoleName(protectAdminLevel.toString());
                AdminUserRequest.addAdminRole(adminRoleEntity);
                if (protectAdminLevel == ProtectAdminLevel.Administrator) {
                    adminUserEntity.setAdminRole(adminRoleEntity);
                }
            }
            AdminUserRequest.addAdminUser(adminUserEntity);
        }
    }

    public static void addSession(String key, String login) {
        loggerFactory.debug("addSession");
        AdminUserEntity userEntity = getUserByLogin(login);
        AdminUserSessionEntity adminUserSessionEntity = new AdminUserSessionEntity();
        adminUserSessionEntity.setAdminUser(userEntity);
        adminUserSessionEntity.setSessionKey(key);
        addSession(adminUserSessionEntity);
    }

    private static AdminUserEntity getUserByLogin(String login) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * " +
                    "FROM AdminUser " +
                    "JOIN AdminRole ON (AdminRole.AdminRoleID=AdminUser.AdminRoleID) " +
                    "WHERE AdminUserName=?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.first()) {
                AdminUserEntity adminUserEntity = new AdminUserEntity();
                login = rs.getString("AdminUser.AdminUserName");
                String password = rs.getString("AdminUser.AdminUserPassword");
                adminUserEntity.setAdminUserName(login);
                adminUserEntity.setAdminUserPassword(password);
                adminUserEntity.setAdminUserID(rs.getLong("AdminUser.AdminUserID"));
                AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
                adminRoleEntity.setAdminRoleName(rs.getString("AdminRole.AdminRoleName"));
                adminRoleEntity.setAdminRoleID(rs.getLong("AdminRole.AdminRoleID"));
                adminUserEntity.setAdminRole(adminRoleEntity);
                return adminUserEntity;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    private static boolean addSession(AdminUserSessionEntity adminUserSessionEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(adminUserSessionEntity);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
