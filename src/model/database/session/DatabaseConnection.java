package model.database.session;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 16.10.13
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseConnection {
    private final String url = "jdbc:mysql://127.0.0.1:3306/worldonline?autoReconnect=true";
    private final String username = "root";
    private final String password = "Djqysdrjcvjct!!!";
    private Connection connection = null;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Database, DatabaseConnection.class);

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                loggerFactory.error("db connection is closed or null");
            }
        } catch (SQLException e) {
            loggerFactory.error("db connection is closed");
            loggerFactory.error(e);
        }
        return connection;
    }

    public void closeConnections(PreparedStatement ps, ResultSet rs) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        }
    }
}

