package model.database.requests;

import controller.phone.entity.AllCardParameterTypesRequest;
import model.additionalentity.admin.CardParameter;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.phone.MobileParameterType;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.constants.databaseenumeration.LanguageType;
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
import java.util.ArrayList;


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

    public static CardParameterTypeEntity getCardParameterTypeByResultSet(String cardParameterType, ResultSet rs) throws SQLException {
        Long cardParameterTypeEntityID = rs.getLong(cardParameterType + ".CardParameterTypeID");
        CardParameterTypeEntity cardParameterTypeEntity = null;
        if (!rs.wasNull() && cardParameterTypeEntityID != 0) {
            cardParameterTypeEntity = new CardParameterTypeEntity();
            cardParameterTypeEntity.setCardParameterTypeID(cardParameterTypeEntityID);
            cardParameterTypeEntity.setBlock(rs.getInt(cardParameterType + ".Block"));
            cardParameterTypeEntity.setCardParameterTypeName(TextRequest.getTextGroupByResultSet(rs, cardParameterType + "TextGroup"));
            cardParameterTypeEntity.setDataType(rs.getInt(cardParameterType + ".DataType"));
            cardParameterTypeEntity.setImage(ImageRequest.getImageByResultSet(rs, "CardParameterTypeImage"));
            cardParameterTypeEntity.setPosition(rs.getInt(cardParameterType + ".Position"));
        }
        return cardParameterTypeEntity;
    }

    public static CardParameterTypeEntity getCardParameterTypeByResultSet(ResultSet rs) throws SQLException {
        return getCardParameterTypeByResultSet("CardParameterType", rs);
    }

    public static void addCardParameter(CardParameterEntity cardParameterEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardParameterEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
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
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardParameterTypeEntity getParameterTypeByName(String name) {
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
                    return getCardParameterTypeByResultSet(rs);
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static ArrayList<MobileParameterType> getAllMobileParameterTypes(AllCardParameterTypesRequest allCardParameterTypesRequest) {
        Long userID = allCardParameterTypesRequest.getUserID();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<MobileParameterType> mobileParameterTypes = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "CardParameterType.CardParameterTypeID, " +
                    "CardParameterType.Block, " +
                    "CardParameterType.DataType," +
                    "CardParameterType.Position," +
                    "Text.Text," +
                    "Image.ImageID " +
                    "FROM CardParameterType " +
                    "LEFT OUTER JOIN TextGroup ON (CardParameterType.CardParameterTypeName=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "LEFT OUTER JOIN UserPersonalData ON (UserPersonalData.UserLanguage=Text.LanguageID) " +
                    "LEFT OUTER JOIN User ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "LEFT OUTER JOIN Image ON (CardParameterType.ImageID=Image.ImageID) " +
                    "WHERE User.UserID=?";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long parameterTypeID = rs.getLong("CardParameterType.CardParameterTypeID");
                if (parameterTypeID != 0 && !rs.wasNull()) {
                    MobileParameterType mobileParameterType = new MobileParameterType();
                    mobileParameterType.setCardParameterTypeID(parameterTypeID);
                    mobileParameterType.setBlock(ApplicationBlock.parseInt(rs.getInt("CardParameterType.Block")));
                    mobileParameterType.setDataType(DataType.parseInt(rs.getInt("CardParameterType.DataType")));
                    mobileParameterType.setPosition(rs.getInt("CardParameterType.Position"));
                    mobileParameterType.setName(rs.getString("Text.Text"));
                    mobileParameterType.setIconID(rs.getLong("Image.ImageID"));
                    mobileParameterTypes.add(mobileParameterType);
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileParameterTypes;
    }

    public static void setCardParameters(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        ArrayList<CardParameter> cardParameters = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT " +
                            "CardParameter.CardParameterID, " +
                            "CardParameter.CardParameterValue, " +
                            "CardParameterType.Block, " +
                            "ParameterTypeText.Text " +
                            "FROM Card " +
                            "JOIN CardParameter ON (CardParameter.CardID = Card.CardID) " +
                            "JOIN CardParameterType ON (CardParameter.CardParameterTypeID = CardParameterType.CardParameterTypeID) " +
                            "JOIN TextGroup AS ParameterTypeTextGroup " +
                            "ON (ParameterTypeTextGroup.TextGroupID = CardParameterType.CardParameterTypeName) " +
                            "JOIN Text AS ParameterTypeText ON (ParameterTypeText.TextGroupID=ParameterTypeTextGroup.TextGroupID) " +
                            "WHERE Card.CardID = ? AND ParameterTypeText.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "ORDER BY CardParameterType.Position";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardParameter cardParameter = new CardParameter();
                cardParameter.setParameterID(rs.getLong("CardParameter.CardParameterID"));
                cardParameter.setBlock(rs.getInt("CardParameterType.Block"));
                cardParameter.setName(rs.getString("ParameterTypeText.Text"));
                cardParameter.setValue(rs.getString("CardParameter.CardParameterValue"));
                cardParameters.add(cardParameter);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardParameterArrayList(cardParameters);
    }
}
