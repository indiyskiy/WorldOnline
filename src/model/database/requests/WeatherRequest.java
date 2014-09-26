package model.database.requests;

import helper.TimeManager;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.WeatherEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.*;

public class WeatherRequest {
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, WeatherRequest.class);

    public static boolean addWeather(WeatherEntity weather) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(weather);
            session.getTransaction().commit();
            session.flush();
            return true;
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    public static WeatherEntity getCurrentWeather() {
        Timestamp timestamp = TimeManager.currentTime();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT *, " +
                    "ABS(TIMESTAMPDIFF(SECOND, Weather.WeatherTimestamp, ?)) AS timediff " +
                    "FROM Weather " +
                    "ORDER BY timediff, Weather.WeatherID DESC LIMIT 1";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, timestamp);
            rs = ps.executeQuery();
            if (rs.first()) {
                WeatherEntity weatherEntity = new WeatherEntity();
                weatherEntity.setCloudiness(rs.getInt("Weather.Cloudiness"));
                weatherEntity.setHeatMax(rs.getInt("Weather.HeatMax"));
                weatherEntity.setHeatMin(rs.getInt("Weather.HeatMin"));
                weatherEntity.setLightningPower(rs.getInt("Weather.LightningPower"));
                weatherEntity.setMaxTemperature(rs.getInt("Weather.MaxTemperature"));
                weatherEntity.setMinTemperature(rs.getInt("Weather.MinTemperature"));
                weatherEntity.setPrecipitation(rs.getInt("Weather.Precipitation"));
                weatherEntity.setPressureMax(rs.getInt("Weather.PressureMax"));
                weatherEntity.setPressureMin(rs.getInt("Weather.PressureMin"));
                weatherEntity.setRainPower(rs.getInt("Weather.RainPower"));
                weatherEntity.setRelwetMax(rs.getInt("Weather.RelwetMax"));
                weatherEntity.setRelwetMin(rs.getInt("Weather.RelwetMin"));
                weatherEntity.setLightningPower(rs.getInt("Weather.LightningPower"));
                weatherEntity.setWeatherID(rs.getLong("Weather.WeatherID"));
                weatherEntity.setWindMax(rs.getInt("Weather.WindMax"));
                weatherEntity.setWindMin(rs.getInt("Weather.WindMin"));
                weatherEntity.setWindDirection(rs.getInt("Weather.WindDirection"));
                weatherEntity.setWeatherTimestamp(rs.getTimestamp("Weather.WeatherTimestamp"));
                return weatherEntity;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }
}