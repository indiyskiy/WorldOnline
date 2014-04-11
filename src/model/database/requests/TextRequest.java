package model.database.requests;

import model.additionalentity.CompleteTextGroupInfo;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.TextCardEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 2:49
 * To change this template use File | Settings | File Templates.
 */
public class TextRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, TextRequest.class);

    public static void addText(TextEntity text) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(text);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
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

    public static TextCardEntity getTextCardByResultSet(ResultSet rs, String textCard) throws SQLException {
        Long textCardID = rs.getLong(textCard + ".TextCardID");
        if (textCardID == 0 || rs.wasNull()) {
            return null;
        }
        TextCardEntity textCardEntity = new TextCardEntity();
        textCardEntity.setTextCardID(textCardID);
        textCardEntity.setCardTextType(rs.getInt(textCard + ".CardTextType"));
        return textCardEntity;
    }

    public static TextCardEntity getTextCardByResultSet(ResultSet rs) throws SQLException {
        return getTextCardByResultSet(rs, "TextCard");
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
            if (session != null) {
                session.close();
            }
        }
    }

    public static HashMap<Long, CompleteTextGroupInfo> getCompleteTextGroupInfos() {
        HashMap<Long, CompleteTextGroupInfo> textGroupInfos = new HashMap<Long, CompleteTextGroupInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM TextGroup " +
                    "JOIN Text ON (TextGroup.TextGroupID=Text.TextGroupID)";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteTextGroupInfo textGroupInfo;
                Long textGroupInfoID = rs.getLong("TextGroup.TextGroupID");
                if (textGroupInfoID != 0 && !rs.wasNull()) {
                    if (textGroupInfos.containsKey(textGroupInfoID) && textGroupInfos.get(textGroupInfoID) != null) {
                        textGroupInfo = textGroupInfos.get(textGroupInfoID);
                    } else {
                        textGroupInfo = new CompleteTextGroupInfo(getTextGroupByResultSet(rs));
                        textGroupInfos.put(textGroupInfoID, textGroupInfo);
                    }
                    getCompleteTextGroupInfo(rs, textGroupInfo, "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return textGroupInfos;
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
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(textGroup);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
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
                    "WHERE defText.Text like ? AND targetText.LanguageID=?";
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
}
