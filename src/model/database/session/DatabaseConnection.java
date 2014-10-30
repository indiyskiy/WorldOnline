package model.database.session;

import model.constants.Component;
import model.logger.LoggerFactory;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection = null;
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, DatabaseConnection.class);
    private static long connectionCounter = 0;

    public DatabaseConnection() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env/");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/worldOnLineDB");
            connection = ds.getConnection();
            connectionCounter++;
            if (connectionCounter > 50) {
                loggerFactory.error("connectionCounter overvalued " + connectionCounter);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
            throw new RuntimeException("Cannot connect the database!", e);
        } catch (Exception e) {
            loggerFactory.error(e);
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
            if (connection != null && !connection.isClosed()) {
                connectionCounter--;
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

}

