package model.database.requests;

import model.additionalentity.CompleteCardTagInfo;
import model.additionalentity.CompleteTagInfo;
import model.additionalentity.CompleteTextGroupInfo;
import model.constants.databaseenumeration.TagType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardTagEntity;
import model.database.worldonlinedb.TagEntity;
import model.database.worldonlinedb.TextGroupEntity;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 01.11.13
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class TagRequest {
    public static CardTagEntity getCardTagByResultSet(ResultSet rs) throws SQLException {
        CardTagEntity cardTagEntity = null;
        if (rs.getLong("CardTag.CardTagID") != 0 && !rs.wasNull()) {
            cardTagEntity = new CardTagEntity();
            cardTagEntity.setCardTagID(rs.getLong("CardTag.CardTagID"));
        }
        return cardTagEntity;
    }

    public static TagEntity getTagByResultSet(ResultSet rs) throws SQLException {
        TagEntity tagEntity = null;
        if (rs.getLong("Tag.TagID") != 0 && !rs.wasNull()) {
            tagEntity = new TagEntity();
            tagEntity.setTagID(rs.getLong("Tag.TagID"));
            tagEntity.setTagName(rs.getString("Tag.TagName"));
            tagEntity.setTagType(rs.getInt("Tag.TagType"));
        }
        return tagEntity;
    }

    public static TagEntity getTag(TagType tagType, String tagName) throws SQLException {
        TagEntity tag = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = "SELECT * FROM Tag " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Tag.TagType=? AND Tag.TagName=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, tagType.getValue());
            ps.setString(2, tagName);
            rs = ps.executeQuery();
            if (rs.first()) {
                tag = getTagByResultSet(rs);
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                tag.setTagTextGroup(textGroupEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tag;
    }

    public static ArrayList<TagEntity> getTags(TagType tagType) throws SQLException {
        ArrayList<TagEntity> tags = new ArrayList<TagEntity>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = "SELECT * FROM Tag " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Tag.TagType=?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, tagType.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                TagEntity tag = getTagByResultSet(rs);
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                tag.setTagTextGroup(textGroupEntity);
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

    public static CardTagEntity getCardTag(long cardTagID) throws SQLException {
        CardTagEntity cardTag = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = "SELECT * FROM CardTag " +
                    "JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardTag.CardTagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardTagID);
            rs = ps.executeQuery();
            if (rs.first()) {
                cardTag = getCardTagByResultSet(rs);
                TagEntity tag = getTagByResultSet(rs);
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                tag.setTagTextGroup(textGroupEntity);
                cardTag.setTag(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardTag;
    }

    public static TagEntity getTag(long tagID) throws SQLException {
        TagEntity tag = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = "SELECT * FROM Tag " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Tag.TagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagID);
            rs = ps.executeQuery();
            if (rs.first()) {
                tag = getTagByResultSet(rs);
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                tag.setTagTextGroup(textGroupEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tag;
    }

    public static void addCardTag(CardTagEntity cardTagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardTagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addTag(TagEntity tagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(tagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void editTag(TagEntity tagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(tagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static HashMap<Long, CompleteCardTagInfo> getCompleteCardTags() {
        HashMap<Long, CompleteCardTagInfo> cardTags = new HashMap<Long, CompleteCardTagInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM CardTag " +
                    "JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardTagInfo cardTag;
                Long cardTagID = rs.getLong("CardTag.CardTagID");
                if (cardTagID != 0 && !rs.wasNull()) {
                    if (cardTags.containsKey(cardTagID) && cardTags.get(cardTagID) != null) {
                        cardTag = cardTags.get(cardTagID);
                    } else {
                        cardTag = new CompleteCardTagInfo(getCardTagByResultSet(rs));
                        cardTags.put(cardTagID, cardTag);
                    }
                    getCompleteCardTag(rs, cardTag, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardTags;
    }

    public static void getCompleteCardTag(ResultSet rs, CompleteCardTagInfo cardTagInfo, String textGroupName, String textName) throws SQLException {
        Long tagID = rs.getLong("Tag.TagID");
        if (tagID != 0 && !rs.wasNull()) {
            CompleteTagInfo tagInfo;
            if (cardTagInfo.getCompleteTagInfo() == null) {
                tagInfo = new CompleteTagInfo(TagRequest.getTagByResultSet(rs));
                cardTagInfo.setCompleteTagInfo(tagInfo);
                cardTagInfo.getCardTagEntity().setTag(tagInfo.getTagEntity());
            } else {
                tagInfo = cardTagInfo.getCompleteTagInfo();
            }
            //tag text group
            getCompleteTag(rs, tagInfo, textGroupName, textName);
        }
    }

    public static HashMap<Long, CompleteTagInfo> getCompleteTags() {
        HashMap<Long, CompleteTagInfo> tags = new HashMap<Long, CompleteTagInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Tag " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteTagInfo tag;
                Long tagID = rs.getLong("Tag.TagID");
                if (tagID != 0 && !rs.wasNull()) {
                    if (tags.containsKey(tagID) && tags.get(tagID) != null) {
                        tag = tags.get(tagID);
                    } else {
                        tag = new CompleteTagInfo(getTagByResultSet(rs));
                        tags.put(tagID, tag);
                    }
                    getCompleteTag(rs, tag, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

    public static void getCompleteTag(ResultSet rs, CompleteTagInfo tagInfo, String textGroupName, String textName) throws SQLException {
        Long tagTextGroupID = rs.getLong(textGroupName + ".TextGroupID");
        if (!rs.wasNull()) {
            CompleteTextGroupInfo textGroup;
            if (tagInfo.getCompleteTextGroupInfoMap().containsKey(tagTextGroupID) && tagInfo.getCompleteTextGroupInfoMap().get(tagTextGroupID) != null) {
                textGroup = tagInfo.getCompleteTextGroupInfoMap().get(tagTextGroupID);
            } else {
                textGroup = new CompleteTextGroupInfo(TextRequest.getTextGroupByResultSet(rs, textGroupName));
                tagInfo.getTagEntity().setTagTextGroup(textGroup.getTextGroup());
                tagInfo.getCompleteTextGroupInfoMap().put(tagTextGroupID, textGroup);
            }
            //tag text
            TextRequest.getCompleteTextGroupInfo(rs, textGroup, textName);
        }
    }

    public static CompleteTagInfo getCompleteTag(long tagID) {
        CompleteTagInfo tag = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Tag " +
                    "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Tag.TagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagID);
            rs = ps.executeQuery();
            System.out.println("0-" + tagID);
            if (rs.first()) {
                Long tagID2 = rs.getLong("Tag.TagID");
                System.out.println("1-" + tagID2);
                if (tagID2 != 0 && tagID == tagID2 && !rs.wasNull()) {
                    tag = new CompleteTagInfo(getTagByResultSet(rs));
                    getCompleteTag(rs, tag, "TextGroup", "Text");
                } else {
                    return null;
                }
                while (rs.next()) {
                    getCompleteTag(rs, tag, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tag;
    }
}