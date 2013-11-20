package model.database.session;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 16.10.13
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseConnection {
    private final String url = "jdbc:mysql://localhost:3306/worldonline";
    private final String username = "root";
    private final String password = "Djqysdrjcvjct!!!";
    private Connection connection = null;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
    }

    public Connection getConnection() {
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
            e.printStackTrace();
        }
    }
}

