package model.database.requests;

import model.additionalentity.CompleteCardTagInfo;
import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.admin.CompleteTagInfo;
import model.additionalentity.admin.SimpleTagGroup;
import model.additionalentity.phone.MobileTag;
import model.additionalentity.phone.MobileTagGroup;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TagRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, TagRequest.class);
    @Language("MySQL")
    private static final String cardTagText = "SELECT * FROM  CardTag " +
            "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
            "LEFT OUTER JOIN TextGroup AS TagIcon ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
            "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
            "LEFT OUTER JOIN Image AS TagIcon ON (TagIcon.ImageID=Tag.IconID) " +
            "LEFT OUTER JOIN TagGroup ON (Tag.TagGroupID=TagGroup.TagGroupID) " +
            "LEFT OUTER JOIN TextGroup AS TagGroupTextGroup ON (TagGroupTextGroup.TextGroupID=TagGroup.TagGroupTextGroupID) " +
            "LEFT OUTER JOIN Text AS TagGroupText ON (TagGroupText.TextGroupID=TagGroupTextGroup.TextGroupID) ";

    @Language("MySQL")
    private static final String tagText = "SELECT * FROM Tag  " +
            "LEFT OUTER JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
            "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
            "LEFT OUTER JOIN Image AS TagIcon ON (TagIcon.ImageID=Tag.IconID) " +
            "LEFT OUTER JOIN TagGroup ON (Tag.TagGroupID=TagGroup.TagGroupID) " +
            "LEFT OUTER JOIN TextGroup AS TagGroupTextGroup ON (TagGroupTextGroup.TextGroupID=TagGroup.TagGroupTextGroupID) " +
            "LEFT OUTER JOIN Text AS TagGroupText ON (TagGroupText.TextGroupID=TagGroupTextGroup.TextGroupID) ";

    public static CardTagEntity getCardTagByResultSet(ResultSet rs) throws SQLException {
        CardTagEntity cardTagEntity = null;
        if (rs.getLong("CardTag.CardTagID") != 0 && !rs.wasNull()) {
            cardTagEntity = new CardTagEntity();
            cardTagEntity.setCardTagID(rs.getLong("CardTag.CardTagID"));
        }
        return cardTagEntity;
    }


    public static void addTagGroup(TagGroupEntity tagGroupEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(tagGroupEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static TagEntity getTagByResultSet(ResultSet rs) throws SQLException {
        TagEntity tagEntity = null;
        Long tagID = rs.getLong("Tag.TagID");
        if (tagID != 0 && !rs.wasNull()) {
            tagEntity = new TagEntity();
            tagEntity.setTagID(tagID);
            tagEntity.setTagName(rs.getString("Tag.TagName"));
            tagEntity.setIcon(ImageRequest.getImageByResultSet(rs, "TagIcon"));
            tagEntity.setTagTextGroup(TextRequest.getTextGroupByResultSet(rs));
        }
        return tagEntity;
    }

    public static TagEntity getTag(String tagName) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = tagText +
                    "WHERE  Tag.TagName LIKE ?";
            ps = connection.prepareStatement(sql);
//            ps.setLong(1, tagGroupID);
            ps.setString(1, tagName);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long id = rs.getLong("Tag.TagID");
                return getTag(id);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static ArrayList<TagEntity> getTags(Long tagGroupID) throws SQLException {
        ArrayList<TagEntity> tags = new ArrayList<TagEntity>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = tagText +
                    "WHERE Tag.TagGroupID=?";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagGroupID);
            rs = ps.executeQuery();
            while (rs.next()) {
                TagEntity tag = getTagByResultSet(rs);
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                tag.setTagTextGroup(textGroupEntity);
                tags.add(tag);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

    public static ArrayList<TagEntity> getTags() throws SQLException {
        ArrayList<TagEntity> tags = new ArrayList<TagEntity>();
        HashMap<Long, TagEntity> hashMap = new HashMap<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = tagText +
                    "ORDER BY Tag.TagGroupID DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("Tag.TagID");
                if (id != 0 && !rs.wasNull() && !hashMap.containsKey(id)) {
                    TagEntity tag = getTagByResultSet(rs);
                    TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs);
                    tag.setTagTextGroup(textGroupEntity);
                    tags.add(tag);
                    hashMap.put(id, tag);
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

  /*  public static CardTagEntity getCardTag(long cardTagID) throws SQLException {
        CardTagEntity cardTag = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = cardTagText +
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardTag;
    }*/

    public static TagEntity getTag(long tagID) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (TagEntity) session.get(TagEntity.class, tagID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addCardTag(CardTagEntity cardTagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardTagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addTag(TagEntity tagEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(tagEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
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
            if (session != null && session.isOpen()) {
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
            @Language("MySQL") String sql = cardTagText;
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
            loggerFactory.error(e);
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
        Long cardID = rs.getLong("Card.CardID");
        if (cardID != 0 && !rs.wasNull() && cardTagInfo.getCardTagEntity().getCard() == null) {
            CardEntity cardEntity = CardRequest.getCardFromResultSet(rs);
            cardTagInfo.getCardTagEntity().setCard(cardEntity);
        }
    }

   /* public static HashMap<Long, CompleteTagInfo> getCompleteTags() {
        HashMap<Long, CompleteTagInfo> tags = new HashMap<Long, CompleteTagInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = tagText;
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }*/

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
            @Language("MySQL") String sql = tagText +
                    "WHERE Tag.TagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagID);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long tagID2 = rs.getLong("Tag.TagID");
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tag;
    }

    public static ArrayList<SimpleTagGroup> getAllSimpleTagGroups(LanguageType languageType) {
        ArrayList<SimpleTagGroup> simpleTagGroups = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT TagGroup.TagGroupID," +
                    "Text.Text " +
                    "FROM TagGroup " +
                    "JOIN TextGroup ON (TagGroup.TagGroupTextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Text.LanguageID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, languageType.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleTagGroup simpleTagGroup = new SimpleTagGroup();
                simpleTagGroup.setID(rs.getLong("TagGroup.TagGroupID"));
                simpleTagGroup.setText(rs.getString("Text.Text"));
                simpleTagGroups.add(simpleTagGroup);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleTagGroups;
    }

    public static ArrayList<MobileTagGroup> getMobileTagGroups(Long userID) throws SQLException {
        ArrayList<MobileTagGroup> mobileTagGroups = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Long, MobileTagGroup> mobileTagGroupHashMap = new HashMap<>();
        HashMap<Long, MobileTag> mobileTagHashMap = new HashMap<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT " +
                    "TagGroup.TagGroupID," +
                    "TagGroupText.Text," +
                    "TagGroup.CardID," +
                    "TagGroup.Position, " +
                    "Tag.TagID," +
                    "Tag.IconID," +
                    "Text.Text " +
                    "FROM Tag " +
                    "LEFT OUTER JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Image AS TagIcon ON (TagIcon.ImageID=Tag.IconID) " +
                    "LEFT OUTER JOIN TagGroup ON (Tag.TagGroupID=TagGroup.TagGroupID) " +
                    "LEFT OUTER JOIN TextGroup AS TagGroupTextGroup ON (TagGroupTextGroup.TextGroupID=TagGroup.TagGroupTextGroupID) " +
                    "LEFT OUTER JOIN Text AS TagGroupText ON (TagGroupText.TextGroupID=TagGroupTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN User ON (User.UserID=?) " +
                    "LEFT OUTER JOIN UserPersonalData ON (User.UserPersonalDataID=UserPersonalData.UserPersonalDataID) " +
                    "WHERE (Text.LanguageID=UserPersonalData.UserLanguage OR Text.LanguageID IS NULL) AND " +
                    "(TagGroupText.LanguageID=UserPersonalData.UserLanguage OR TagGroupText.LanguageID IS NULL)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long mobileTagGroupID = rs.getLong("TagGroup.TagGroupID");
                if (mobileTagGroupID != 0 && !rs.wasNull()) {
                    MobileTagGroup mobileTagGroup;
                    if (mobileTagGroupHashMap.containsKey(mobileTagGroupID)) {
                        mobileTagGroup = mobileTagGroupHashMap.get(mobileTagGroupID);
                    } else {
                        mobileTagGroup = new MobileTagGroup();
                        mobileTagGroup.setId(mobileTagGroupID);
                        Long cardID = rs.getLong("TagGroup.CardID");
                        if (!rs.wasNull() && cardID != 0) {
                            mobileTagGroup.setCardID(cardID);
                        }
                        String text = rs.getString("TagGroupText.Text");
                        if (text != null && !text.isEmpty() && !rs.wasNull()) {
                            mobileTagGroup.setName(text);
                        }
                        Integer position = rs.getInt("TagGroup.Position");
                        if (position != 0 && !rs.wasNull()) {
                            mobileTagGroup.setPosition(position);
                        }
                        mobileTagGroups.add(mobileTagGroup);
                        mobileTagGroupHashMap.put(mobileTagGroupID, mobileTagGroup);
                    }
                    Long tagID = rs.getLong("Tag.TagID");
                    if (!rs.wasNull() && tagID != 0) {
                        if (!mobileTagHashMap.containsKey(tagID)) {
                            MobileTag mobileTag = new MobileTag();
                            mobileTag.setTagID(tagID);
                            mobileTag.setIconID(rs.getLong("Tag.IconID"));
                            mobileTag.setName(rs.getLong("Text.Text"));
                            mobileTagHashMap.put(tagID, mobileTag);
                            mobileTagGroup.getMobileTags().add(mobileTag);
                        }
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
            throw e;
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileTagGroups;
    }
}