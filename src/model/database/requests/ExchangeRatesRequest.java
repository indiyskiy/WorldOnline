package model.database.requests;

import helper.TimeManager;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.ExchangeRatesEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.*;

public class ExchangeRatesRequest {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, ExchangeRatesRequest.class);

    public static void saveExchangeRates(ExchangeRatesEntity exchangeRatesEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(exchangeRatesEntity);
            transaction.commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static ExchangeRatesEntity getActualExchangeRates() {
        Timestamp timestamp = TimeManager.currentTime();
        DatabaseConnection dbConnection = new DatabaseConnection("getActualExchangeRates");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT ExchangeRates.ActualDayTimestamp, " +
                    "ExchangeRates.EUR," +
                    "ExchangeRates.USD, " +
                    "ExchangeRates.DownloadTimestamp," +
                    "ExchangeRates.ExchangeRatesID, " +
                    "ABS(TIMESTAMPDIFF(SECOND, ExchangeRates.DownloadTimestamp, ?)) AS timediff " +
                    "FROM ExchangeRates " +
                    "ORDER BY timediff, ExchangeRates.ExchangeRatesID DESC LIMIT 1";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, timestamp);
            rs = ps.executeQuery();
            if (rs.first()) {
                ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
                exchangeRatesEntity.setActualDayTimestamp(rs.getTimestamp("ExchangeRates.ActualDayTimestamp"));
                exchangeRatesEntity.setDownloadTimestamp(rs.getTimestamp("ExchangeRates.DownloadTimestamp"));
                exchangeRatesEntity.setUsd(rs.getDouble("ExchangeRates.USD"));
                exchangeRatesEntity.setEur(rs.getDouble("ExchangeRates.EUR"));
                exchangeRatesEntity.setExchangeRatesID(rs.getLong("ExchangeRates.ExchangeRatesID"));
                return exchangeRatesEntity;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }
}
