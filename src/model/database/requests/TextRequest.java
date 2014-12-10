package model.database.requests;

import helper.StringHelper;
import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.CompleteTextInfo;
import model.additionalentity.admin.CardText;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileText;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.exception.DatabaseException;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            session.flush();
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


    public static TextGroupEntity getTextGroupByResultSet(ResultSet rs) throws SQLException {
        return getTextGroupByResultSet(rs, "TextGroup");
    }

    public static TextEntity getTextByResultSet(ResultSet rs) throws SQLException {
        return getTextByResultSet(rs, "Text");
    }

    public static void addTextCard(TextCardEntity textCardEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(textCardEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public static void addTextGroup(TextGroupEntity textGroup) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(textGroup);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static TextEntity findTextByText(String text) {
        TextEntity textEntity = null;
        DatabaseConnection dbConnection = new DatabaseConnection("findTextByText");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return textEntity;
    }

    public static CompleteTextGroupInfo getCompleteTextGroupInfo(long textGroupID) {
        CompleteTextGroupInfo completeTextGroupInfo = null;
        DatabaseConnection dbConnection = new DatabaseConnection("getCompleteTextGroupInfo");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT TextGroup.TextGroupID, " +
                    "TextGroup.TextGroupName, " +
                    "Text.TextID, " +
                    "Text.Text, " +
                    "Text.LanguageID " +
                    "FROM TextGroup " +
                    "LEFT OUTER JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE TextGroup.TextGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textGroupID);
            rs = ps.executeQuery();
            if (rs.first()) {
                completeTextGroupInfo = new CompleteTextGroupInfo();
                completeTextGroupInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));
                completeTextGroupInfo.setTextGroupName(rs.getString("TextGroup.TextGroupName"));
                Long textID = rs.getLong("Text.TextID");
                if (textID != 0 && !rs.wasNull()) {
                    CompleteTextInfo completeTextInfo = new CompleteTextInfo();
                    completeTextInfo.setLanguageType(LanguageType.parseInt(rs.getInt("Text.LanguageID")));
                    completeTextInfo.setText(rs.getString("Text.Text"));
                    completeTextInfo.setTextID(rs.getLong("Text.TextID"));
                    completeTextGroupInfo.getCompleteTextInfos().add(completeTextInfo);
                }
                while (rs.next()) {
                    textID = rs.getLong("Text.TextID");
                    if (textID != 0 && !rs.wasNull()) {
                        CompleteTextInfo completeTextInfo = new CompleteTextInfo();
                        completeTextInfo.setLanguageType(LanguageType.parseInt(rs.getInt("Text.LanguageID")));
                        completeTextInfo.setText(rs.getString("Text.Text"));
                        completeTextInfo.setTextID(rs.getLong("Text.TextID"));
                        completeTextGroupInfo.getCompleteTextInfos().add(completeTextInfo);
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return completeTextGroupInfo;
    }

    public static boolean isTranslated(String text, LanguageType language) {
        DatabaseConnection dbConnection = new DatabaseConnection("isTranslated");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static void setCardTexts(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardTexts");
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
//                            "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "JOIN CardParameterType ON (TextCard.CardParameterTypeID = CardParameterType.CardParameterTypeID) " +
                            "JOIN TextGroup AS ParameterTypeTextGroup " +
                            "ON (ParameterTypeTextGroup.TextGroupID = CardParameterType.CardParameterTypeName) " +
                            "JOIN Text AS ParameterTypeText ON (ParameterTypeText.TextGroupID=ParameterTypeTextGroup.TextGroupID) " +
                            "WHERE Card.CardID = ? " +
                            "AND ParameterTypeText.LanguageID=" + LanguageType.Russian.getValue() + " " +
//                            "AND Text.LanguageID=" + LanguageType.Russian.getValue() + " " +
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
        } catch (Exception e) {
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
            session.flush();
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
        DatabaseConnection dbConnection = new DatabaseConnection("deleteTextCard");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM TextCard WHERE TextCard.TextCardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textCardEntity.getTextCardID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }


    public static void deleteTextGroup(TextGroupEntity textGroupEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteTextGroup");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM Text WHERE Text.TextGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textGroupEntity.getTextGroupID());
            ps.executeUpdate();
            sql = "DELETE FROM TextGroup WHERE TextGroup.TextGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, textGroupEntity.getTextGroupID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static boolean isCardTextExist(long cardID, long cardParameterTypeID) {
        DatabaseConnection dbConnection = new DatabaseConnection("isCardTextExist");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static boolean areRepeatingTextsExists(long cardID, HashSet<Long> cardParameterTypeIDs) {
        if (cardParameterTypeIDs.isEmpty()) {
            return false;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("areRepeatingTextsExists");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String stringIDs = StringHelper.getStringFromCollection(cardParameterTypeIDs);
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT TextGroup.TextGroupID FROM TextGroup " +
                    "JOIN TextCard ON (TextCard.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Card ON (Card.CardID=TextCard.CardID) " +
                    "JOIN CardParameterType ON (TextCard.CardParameterTypeID=CardParameterType.CardParameterTypeID)" +
                    "WHERE Card.CardID=? AND CardParameterType.CardParameterTypeID IN (" + stringIDs + ") AND (NOT CardParameterType.Multiply)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            }
        } catch (Exception e) {
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

    public static ArrayList<CompleteTextInfo> getFullTextMap(HashMap<Integer, CompleteTextInfo> textMap) {
        ArrayList<CompleteTextInfo> completeTextInfos = new ArrayList<>();
        for (LanguageType languageType : LanguageType.values()) {
            if (textMap.containsKey(languageType.getValue())) {
                completeTextInfos.add(textMap.get(languageType.getValue()));
            } else {
                CompleteTextInfo completeTextInfo = new CompleteTextInfo();
                completeTextInfo.setLanguageType(languageType);
                completeTextInfos.add(completeTextInfo);
            }
        }
        return completeTextInfos;
    }

    public static TextGroupEntity getTextGroup(Long textGroupID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (TextGroupEntity) session.get(TextGroupEntity.class, textGroupID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void deleteText(ArrayList<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("deleteText");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM Text " +
                    "WHERE Text.TextID IN (" + StringHelper.getStringFromArray((List) ids) + ")";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void addText(ArrayList<TextEntity> textEntities) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            for (TextEntity text : textEntities) {
                if (text.getTextGroup() == null) {
                    throw new DatabaseException("TextGroup is null");
                }
                session.save(text);
            }
            transaction.commit();
            session.flush();
        } catch (DatabaseException e) {
            loggerFactory.error(e.getMessage());
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void setMobileTexts(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs, LanguageType languageType) {
        //texts
//            "Text.Text, " +
//            "TextCard.CardParameterTypeID, " +
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileTexts");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "TextCard.CardID, " +
                    "Text.Text, " +
                    "TextCard.CardParameterTypeID, " +
                    "Text.TextGroupID " +
                    "FROM TextCard " +
                    "JOIN Text ON (Text.TextGroupID=TextCard.TextGroupID) " +
                    "WHERE Text.LanguageID=" + languageType.getValue() + " " +
                    "AND TextCard.CardID IN (" + cardIDs + ") ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("TextCard.CardID"));
                MobileText mobileText = new MobileText();
                mobileText.setText(rs.getString("Text.Text"));
                mobileText.setCardParameterTypeID(rs.getLong("TextCard.CardParameterTypeID"));
                mobileText.setTextGroupID(rs.getLong("Text.TextGroupID"));
                mobileCardInfo.getMobileTexts().add(mobileText);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static void addTextsToCard(long cardID, HashSet<Long> cardTextParameterTypeIDs) {
//        loggerFactory.debug("start");
        if (cardTextParameterTypeIDs.isEmpty()) {
//            loggerFactory.debug("end 1");
            return;
        }
        ArrayList<Long> textGroupIDs = new ArrayList<>(cardTextParameterTypeIDs.size());
        for (Long cardTextParameterTypeID : cardTextParameterTypeIDs) {
            TextGroupEntity textGroupEntity = new TextGroupEntity("cardText " + cardID + "-" + cardTextParameterTypeID);
            addTextGroup(textGroupEntity);
            textGroupIDs.add(textGroupEntity.getTextGroupID());
        }

        DatabaseConnection dbConnection = new DatabaseConnection("addTextsToCard");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO TextCard (CardID,CardParameterTypeID,TextGroupID) " +
                    "VALUES ";
            Iterator<Long> it = cardTextParameterTypeIDs.iterator();
            for (long textGroupID : textGroupIDs) {
                sql += "(" + cardID + "," + it.next() + "," + textGroupID + "),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }

//        for (int i = 0; i < textGroupIDs.size(); i++) {
//            deleteTextGroup(getTextGroup(textGroupIDs.get(i)));
//        }
//        loggerFactory.debug("end 2");
    }
}
