package model.database.requests;

import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardParameterEntity;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ParameterRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, ParameterRequest.class);

    public static CardParameterEntity getCardParameterByResultSet(ResultSet rs) throws SQLException {
        Long cardParameterEntityID = rs.getLong("CardParameter.CardParameterID");
        CardParameterEntity cardParameterEntity = null;
        if (cardParameterEntityID != 0 && !rs.wasNull()) {
            cardParameterEntity = new CardParameterEntity();
            cardParameterEntity.setCardParameterID(cardParameterEntityID);
            cardParameterEntity.setCardParameterName(rs.getString("CardParameter.CardParameterName"));
            cardParameterEntity.setCardParameterValue(rs.getString("CardParameter.CardParameterValue"));
            cardParameterEntity.setCardParameterType(getCardParameterTypeByResultSet(rs));
        }
        return cardParameterEntity;
    }

    private static CardParameterTypeEntity getCardParameterTypeByResultSet(ResultSet rs) throws SQLException {
        Long cardParameterTypeEntityID = rs.getLong("CardParameterType.CardParameterTypeID");
        CardParameterTypeEntity cardParameterTypeEntity = null;
        if (!rs.wasNull() && cardParameterTypeEntityID != 0) {
            cardParameterTypeEntity = new CardParameterTypeEntity();
            cardParameterTypeEntity.setCardParameterTypeID(cardParameterTypeEntityID);
            cardParameterTypeEntity.setBlock(rs.getInt("CardParameterType.Block"));
            cardParameterTypeEntity.setCardParameterTypeName(TextRequest.getTextGroupByResultSet(rs, "CardParameterTypeTextGroup"));
            cardParameterTypeEntity.setDataType(rs.getInt("CardParameterType.DataType"));
            cardParameterTypeEntity.setImage(ImageRequest.getImageByResultSet(rs, "CardParameterTypeImage"));
            cardParameterTypeEntity.setPosition(rs.getInt("CardParameterType.Position"));
        }
        return cardParameterTypeEntity;
    }

    public static void addCardParameter(CardParameterEntity cardParameterEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardParameterEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addCardParameterType(CardParameterTypeEntity cardParameterTypeEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardParameterTypeEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static CardParameterTypeEntity getParameterTypeByName(String name) {
        loggerFactory.info("getParameterTypeByName");
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Text AS CardParameterTypeText " +
                    "JOIN TextGroup AS CardParameterTypeTextGroup ON (CardParameterTypeText.TextGroupID=CardParameterTypeTextGroup.TextGroupID) " +
                    "JOIN CardParameterType ON (CardParameterType.CardParameterTypeName=CardParameterTypeTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Image AS CardParameterTypeImage ON (CardParameterTypeImage.ImageID=CardParameterType.ImageID) " +
                    "WHERE CardParameterTypeText.Text LIKE ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long cardID = rs.getLong("CardParameterType.CardParameterTypeID");
                if (cardID != 0 && !rs.wasNull()) {
                    CardParameterTypeEntity cardParameterTypeEntity = getCardParameterTypeByResultSet(rs);
                    loggerFactory.info("getParameterTypeByName " + name + " " + cardParameterTypeEntity);
                    if (cardParameterTypeEntity != null) {
                        loggerFactory.info("cardParameterTypeEntity" + cardParameterTypeEntity.getCardParameterTypeID());
                    } else {
                        loggerFactory.info("cardParameterTypeEntity" + "null");
                    }
                    return getCardParameterTypeByResultSet(rs);
                } else {
                    loggerFactory.info("result from rs not found =(");
                }
            } else {
                loggerFactory.info("result set is empty");
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

}
