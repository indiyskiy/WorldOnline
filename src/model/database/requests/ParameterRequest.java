package model.database.requests;

import controller.phone.entity.AllCardParameterTypesRequest;
import helper.ParameterValidator;
import model.additionalentity.admin.CardParameter;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.admin.ParameterType;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileParameter;
import model.additionalentity.phone.MobileParameterType;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardEntity;
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
import java.util.HashMap;


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
            cardParameterTypeEntity.setMultiply(rs.getBoolean(cardParameterType + ".Multiply"));
            cardParameterTypeEntity.setTranslatable(rs.getBoolean(cardParameterType + ".Translatable"));
        }
        return cardParameterTypeEntity;
    }

    public static CardParameterTypeEntity getCardParameterTypeByResultSet(ResultSet rs) throws SQLException {
        return getCardParameterTypeByResultSet("CardParameterType", rs);
    }

    public static void addCardParameter(CardParameterEntity cardParameterEntity) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            boolean isValid = ParameterValidator.isValidParameter(cardParameterEntity.getCardParameterValue(), DataType.parseInt(cardParameterEntity.getCardParameterType().getDataType()));
            if (isValid) {
                session.beginTransaction();
                session.save(cardParameterEntity);
                session.getTransaction().commit();
                session.flush();
            }
        } catch (Exception e) {
            loggerFactory.error(e);
            throw e;
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
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardParameterTypeEntity getParameterTypeByName(String name) {
        DatabaseConnection dbConnection = new DatabaseConnection("getParameterTypeByName");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        CardParameterTypeEntity cardParameterTypeEntity = null;
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
                    cardParameterTypeEntity = getCardParameterTypeByResultSet(rs);
                    return getCardParameterTypeByResultSet(rs);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardParameterTypeEntity;
    }

    public static ArrayList<MobileParameterType> getAllMobileParameterTypes(AllCardParameterTypesRequest allCardParameterTypesRequest) {
        Long userID = allCardParameterTypesRequest.getUserID();
        DatabaseConnection dbConnection = new DatabaseConnection("getAllMobileParameterTypes");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileParameterTypes;
    }

    public static void setCardParameters(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardParameters");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardParameterArrayList(cardParameters);
    }

    public static CardParameterEntity getCardParameter(Long parameterID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardParameterEntity) session.get(CardParameterEntity.class, parameterID);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public static boolean updateCardParameter(CardParameterEntity cardParameter) throws Exception {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            boolean isValid = ParameterValidator.isValidParameter(cardParameter.getCardParameterValue(), DataType.parseInt(cardParameter.getCardParameterType().getDataType()));
            if (isValid) {
                session.beginTransaction();
                session.update(cardParameter);
                session.getTransaction().commit();
                session.flush();
            }
        } catch (Exception e) {
            loggerFactory.error(e);
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }

    public static void deleteCardParameter(CardParameterEntity cardParameterEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteCardParameter");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM CardParameter WHERE CardParameter.CardParameterID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardParameterEntity.getCardParameterID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }

    }

    public static ArrayList<ParameterType> getAllTypes() {
        DatabaseConnection dbConnection = new DatabaseConnection("getAllTypes");
        ArrayList<ParameterType> parameterTypes = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT Text.Text, " +
                            "CardParameterType.Block," +
                            "CardParameterType.CardParameterTypeID," +
                            "CardParameterType.Translatable," +
                            "CardParameterType.Multiply " +
                            "FROM CardParameterType " +
                            "JOIN TextGroup ON (CardParameterType.CardParameterTypeName =TextGroup.TextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "WHERE Text.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "ORDER BY CardParameterType.Block";
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            while (rs.next()) {
                ParameterType parameterType = new ParameterType();
                parameterType.setParameterTypeID(rs.getLong("CardParameterType.CardParameterTypeID"));
                parameterType.setName(rs.getString("Text.Text"));
                parameterType.setBlock(ApplicationBlock.parseInt(rs.getInt("CardParameterType.Block")));
                parameterType.setMultiply(rs.getBoolean("CardParameterType.Multiply"));
                parameterType.setTranslatable(rs.getBoolean("CardParameterType.Translatable"));
                parameterTypes.add(parameterType);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return parameterTypes;
    }

    public static CardParameterTypeEntity getCardParameterType(long cardParameterTypeID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardParameterTypeEntity) session.get(CardParameterTypeEntity.class, cardParameterTypeID);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    public static boolean isCardParameterExist(long cardID, long cardParameterTypeID) {
        DatabaseConnection dbConnection = new DatabaseConnection("isCardParameterExist");
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT CardParameter.CardParameterID FROM CardParameter " +
                    "JOIN Card ON (Card.CardID=CardParameter.CardID) " +
                    "JOIN CardParameterType ON (CardParameter.CardParameterTypeID=CardParameterType.CardParameterTypeID)" +
                    "WHERE Card.CardID=? AND CardParameterType.CardParameterTypeID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            ps.setLong(2, cardParameterTypeID);
            rs = ps.executeQuery();
            if (rs.first()) {
                b = true;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return b;
    }

    public static void addEmptyCardParameter(CardEntity cardEntity, CardParameterTypeEntity cardParameterTypeEntity) throws Exception {
        CardParameterEntity cardParameter = new CardParameterEntity(cardEntity, cardParameterTypeEntity, "");
        addCardParameter(cardParameter);
    }

    public static void setMobileParameters(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileParameters");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT  " +
                    "CardParameter.CardParameterValue, " +
                    "CardParameter.CardParameterID, " +
                    "CardParameter.CardParameterTypeID, " +
                    "CardParameter.CardID " +
                    "FROM  CardParameter " +
                    "WHERE CardParameter.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("CardParameter.CardID"));
                MobileParameter mobileParameter = new MobileParameter();
                mobileParameter.setValue(rs.getString("CardParameter.CardParameterValue"));
                mobileParameter.setParameterID(rs.getLong("CardParameter.CardParameterID"));
                mobileParameter.setParameterTypeID(rs.getLong("CardParameter.CardParameterTypeID"));
                mobileCardInfo.getMobileParameters().add(mobileParameter);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }
}
