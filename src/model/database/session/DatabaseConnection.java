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
import java.util.HashMap;

public class DatabaseConnection {
    private Connection connection = null;
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, DatabaseConnection.class);
    private static long connectionCounter = 0;
    private String callerName;
    private static HashMap<String, Integer> connectionCounterMap = new HashMap<>();

    public DatabaseConnection(String callerName) {
        try {
            this.callerName = callerName;
//            loggerFactory.debug("open connection for " + callerName);
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env/");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/worldOnLineDB");
            connection = ds.getConnection();
            incrementStats(callerName);
            printStats();
        } catch (SQLException e) {
            loggerFactory.error(e);
            throw new RuntimeException("Cannot connect the database!", e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private void printStats() {
        if (connectionCounter > 50) {
            loggerFactory.error("connectionCounter overvalued " + connectionCounter);
            for (String key : connectionCounterMap.keySet()) {
                if (connectionCounterMap.get(key) != 0) {
                    loggerFactory.error("connectionCounterMap " + key + " counter is " + connectionCounterMap.get(key));
                }
            }
        }
    }

    private void incrementStats(String callerName) {
        connectionCounter++;
        if (connectionCounterMap.containsKey(callerName)) {
            connectionCounterMap.put(callerName, connectionCounterMap.get(callerName) + 1);
        } else {
            connectionCounterMap.put(callerName, 1);
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
        } catch (Exception e) {
            loggerFactory.error("db connection is closed - unexpected exception");
            loggerFactory.error(e);
        }
        return connection;
    }

    public void closeConnections(PreparedStatement ps, ResultSet rs) {
        try {
//            if (connection != null && !connection.isClosed()) {
            connectionCounter--;
            connection.close();
//            loggerFactory.debug("connection closerd for caller " + callerName);
//            }
            connectionCounterMap.put(callerName, connectionCounterMap.get(callerName) - 1);
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

