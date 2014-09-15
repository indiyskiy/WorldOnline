package model.database.requests;

import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.admin.CardText;
import model.additionalentity.admin.CompleteCardInfo;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.exception.DatabaseException;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TextRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, TextRequest.class);

    public static void addText(TextEntity text) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            if (text.getTextGroup() == null) {
                throw new DatabaseException("TextGroup is null");
            }
            session.beginTransaction();
            session.save(text);
            session.getTransaction().commit();
        } catch (DatabaseException e) {
            loggerFactory.error(e.getMessage());
            loggerFactory.error(e);
        } catch (NullPointerException e) {
            loggerFactory.error("text not found " + text);
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static TextGroupEntity getTextGroupByResultSet(ResultSet rs, String tagTextGroup) throws SQLException {
        TextGroupEntity textGroup = null;
        if (rs.getLong(tagTextGroup + ".TextGroupID") != 0 && !rs.wasNull()) {
            textGroup = new TextGroupEntity();
            textGroup.setTextGroupID(rs.getLong(tagTextGroup + ".TextGroupID"));
            textGroup.setTextGroupName(rs.getString(tagTextGroup + ".TextGroupName"));
        }
        return textGroup;
    }

    public static TextEntity getTextByResultSet(ResultSet rs, String tagText) throws SQLException {
        TextEntity textEntity = null;
        if (rs.getLong(tagText + ".TextID") != 0 && !rs.wasNull()) {
            textEntity = new TextEntity();
            textEntity.setTextID(rs.getLong(tagText + ".TextID"));
            textEntity.setLanguageID(rs.getInt(tagText + ".LanguageID"));
            textEntity.setText(rs.getString(tagText + ".Text"));
        }
        return textEntity;
    }

    public static TextCardEntity getTextCardByResultSet(ResultSet rs, String textCard, String cardParameterType) throws SQLException {
        Long textCardID = rs.getLong(textCard + ".TextCardID");
        if (textCardID == 0 || rs.wasNull()) {
            return null;
        }
        TextCardEntity textCardEntity = new TextCardEntity();
        textCardEntity.setTextCardID(textCardID);
        textCardEntity.setCardParameterType(ParameterRequest.getCardParameterTypeByResultSet(cardParameterType, rs));
        return textCardEntity;
    }

    public static TextCardEntity getTextCardByResultSet(ResultSet rs) throws SQLException {
        return getTextCardByResultSet(rs, "TextCard", "TextCardParameterType");
    }

    public static TextGroupEntity getTextGroupByResultSet(ResultSet rs) throws SQLException {
        return getTextGroupByResultSet(rs, "TextGroup");
    }

    public static TextEntity getTextByResultSet(ResultSet rs) throws SQLException {
        return getTextByResultSet(rs, "Text");
    }

    public static void addTextCard(TextCardEntity textCardEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(textCardEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void getCompleteTextGroupInfo(ResultSet rs, CompleteTextGroupInfo textGroupInfo, String text) throws SQLException {
        Long textID = rs.getLong(text + ".TextID");
        if (textID != 0 && !rs.wasNull()) {
            if (!textGroupInfo.getTextEntityMap().containsKey(textID) || textGroupInfo.getTextEntityMap().get(textID) == null) {
                TextEntity textEntity = TextRequest.getTextByResultSet(rs, text);
                textGroupInfo.getTextEntityMap().put(textID, textEntity);
            }
        }
    }

    public static void addTextGroup(TextGroupEntity textGroup) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(textGroup);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static TextEntity findTextByText(String text) {
        TextEntity textEntity = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM TextGroup " +
                    "JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE Text.Text LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, text);
            rs = ps.executeQuery();
            if (rs.first()) {
                textEntity = getTextByResultSet(rs);
                TextGroupEntity textGroupEntity = getTextGroupByResultSet(rs);
                textEntity.setTextGroup(textGroupEntity);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return textEntity;
    }

    public static CompleteTextGroupInfo getCompleteTextGroupInfo(long textGroupID) {
        CompleteTextGroupInfo completeTextGroupInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM TextGroup " +
                    "JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE TextGroup.TextGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textGroupID);
            rs = ps.executeQuery();
            if (rs.first()) {
                completeTextGroupInfo = new CompleteTextGroupInfo(getTextGroupByResultSet(rs));
                getCompleteTextGroupInfo(rs, completeTextGroupInfo, "Text");
                while (rs.next()) {
                    getCompleteTextGroupInfo(rs, completeTextGroupInfo, "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return completeTextGroupInfo;
    }

    public static boolean isTranslated(String text, LanguageType language) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Text AS defText " +
                    "JOIN TextGroup ON (TextGroup.TextGroupID=defText.TextGroupID) " +
                    "JOIN Text AS targetText ON(TextGroup.TextGroupID=targetText.TextGroupID) " +
                    "WHERE defText.Text LIKE ? AND targetText.LanguageID=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, text);
            ps.setLong(2, language.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static void setCardTexts(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        ArrayList<CardText> cardTexts = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * " +
                            "FROM Card " +
                            "JOIN TextCard ON (TextCard.CardID = Card.CardID) " +
                            "JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID)" +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "JOIN CardParameterType ON (TextCard.CardParameterTypeID = CardParameterType.CardParameterTypeID) " +
                            "JOIN TextGroup AS ParameterTypeTextGroup " +
                            "ON (ParameterTypeTextGroup.TextGroupID = CardParameterType.CardParameterTypeName) " +
                            "JOIN Text AS ParameterTypeText ON (ParameterTypeText.TextGroupID=ParameterTypeTextGroup.TextGroupID) " +
                            "WHERE Card.CardID = ? " +
                            "AND ParameterTypeText.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "AND Text.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "ORDER BY CardParameterType.Position";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardText cardText = new CardText();
                cardText.setTextID(rs.getLong("TextGroup.TextGroupID"));
                cardText.setBlock(rs.getInt("CardParameterType.Block"));
                cardText.setTypeName(rs.getString("ParameterTypeText.Text"));
                cardText.setName(rs.getString("TextGroup.TextGroupName"));
                cardText.setTextCardID(rs.getLong("TextCard.TextCardID"));
                cardTexts.add(cardText);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardTextArrayList(cardTexts);
    }

    public static TextEntity getText(Long textID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (TextEntity) session.get(TextEntity.class, textID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean updateText(TextEntity textEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.update(textEntity);
            session.getTransaction().commit();
            b = true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static TextCardEntity getTextCard(Long textCardID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (TextCardEntity) session.get(TextCardEntity.class, textCardID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void deleteTextCard(TextCardEntity textCardEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM TextCard WHERE TextCard.TextCardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textCardEntity.getTextCardID());
            ps.executeUpdate();
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static boolean isCardTextExist(long cardID, long cardParameterTypeID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT TextGroup.TextGroupID FROM TextGroup " +
                    "JOIN TextCard ON (TextCard.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Card ON (Card.CardID=TextCard.CardID) " +
                    "JOIN CardParameterType ON (TextCard.CardParameterTypeID=CardParameterType.CardParameterTypeID)" +
                    "WHERE Card.CardID=? AND CardParameterType.CardParameterTypeID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            ps.setLong(2, cardParameterTypeID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static TextCardEntity addEmptyCardText(CardEntity cardEntity, CardParameterTypeEntity cardParameterTypeEntity) {
        TextGroupEntity textGroupEntity = new TextGroupEntity(cardParameterTypeEntity.getCardParameterTypeName().getTextGroupName() + cardEntity.getCardName());
        TextCardEntity textCardEntity = new TextCardEntity(textGroupEntity, cardEntity, cardParameterTypeEntity);
        addTextGroup(textGroupEntity);
        addTextCard(textCardEntity);
        for (LanguageType languageType : LanguageType.values()) {
            TextEntity textEntity = new TextEntity(languageType, "", textGroupEntity);
            addText(textEntity);
        }
        return textCardEntity;
    }
}
