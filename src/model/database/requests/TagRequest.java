package model.database.requests;

import helper.StringHelper;
import model.additionalentity.CompleteCardTagInfo;
import model.additionalentity.admin.*;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileTag;
import model.additionalentity.phone.MobileTagGroup;
import model.constants.ApplicationBlock;
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
import java.util.*;

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
    private static final String tagText = "SELECT * FROM Tag " +
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
            session.flush();
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
            tagEntity.setIcon(ImageRequest.getImageByResultSet(rs, "TagIcon"));
            tagEntity.setTagTextGroup(TextRequest.getTextGroupByResultSet(rs));
        }
        return tagEntity;
    }

    public static TagEntity getTag(String tagName) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection("getTag");
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = tagText +
                    "WHERE  Text.Text LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, tagName);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long id = rs.getLong("Tag.TagID");
                return getTag(id);
            }
        } catch (Exception e) {
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
        DatabaseConnection dbConnection = new DatabaseConnection("getTags");
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
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

    public static ArrayList<CompleteTagInfo> getTags() throws SQLException {
        ArrayList<CompleteTagInfo> tags = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        DatabaseConnection dbConnection = new DatabaseConnection("getTags");
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL")
            String sql = tagText +
                    "WHERE " +
                    "(Text.LanguageID=? OR Text.LanguageID IS NULL) " +
                    "AND (TagGroupText.LanguageID=? OR TagGroupText.LanguageID IS NULL) " +
                    "ORDER BY TagGroup.ApplicationBlock, TagGroup.Position, Tag.TagID";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, LanguageType.Russian.getValue());
            ps.setInt(2, LanguageType.Russian.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("Tag.TagID");
                if (id != 0 && !rs.wasNull()) {
                    CompleteTagInfo completeTagInfo = new CompleteTagInfo();
                    completeTagInfo.setTagID(rs.getLong("Tag.TagID"));
                    completeTagInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));
                    Long imageID = rs.getLong("Tag.IconID");
                    if (imageID != 0 && !rs.wasNull()) {
                        completeTagInfo.setIconID(imageID);
                    }
                    completeTagInfo.setTagGroupID(rs.getLong("TagGroup.TagGroupID"));
                    completeTagInfo.setTagGroupName(rs.getString("TagGroupText.Text"));
                    completeTagInfo.setTagName(rs.getString("Text.Text"));
                    tags.add(completeTagInfo);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tags;
    }

    public static TagEntity getTag(long tagID) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
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
            session.flush();
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
            session.flush();
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
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static HashMap<Long, CompleteCardTagInfo> getCompleteCardTags() {
        HashMap<Long, CompleteCardTagInfo> cardTags = new HashMap<Long, CompleteCardTagInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection("getCompleteCardTags");
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
//                        cardTag = cardTags.get(cardTagID);
                    } else {
                        cardTag = new CompleteCardTagInfo(getCardTagByResultSet(rs));
                        cardTags.put(cardTagID, cardTag);
                    }
//                    getCompleteCardTag(rs, cardTag, "TextGroup", "Text");
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardTags;
    }


    public static CompleteTagInfo getCompleteTag(long tagID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getCompleteTag");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = tagText +
                    "WHERE Tag.TagID=? " +
                    "AND (Text.LanguageID=? OR Text.LanguageID IS NULL) " +
                    "AND (TagGroupText.LanguageID=? OR TagGroupText.LanguageID IS NULL)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagID);
            ps.setLong(2, LanguageType.Russian.getValue());
            ps.setLong(3, LanguageType.Russian.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                CompleteTagInfo completeTagInfo = new CompleteTagInfo();
                completeTagInfo.setTagID(rs.getLong("Tag.TagID"));
                completeTagInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));
                completeTagInfo.setIconID(rs.getLong("Tag.IconID"));
                completeTagInfo.setTagGroupID(rs.getLong("TagGroup.TagGroupID"));
                completeTagInfo.setTagGroupName(rs.getString("TagGroupText.Text"));
                completeTagInfo.setTagName(rs.getString("Text.Text"));
                completeTagInfo.setCards(CardRequest.getAllCardsByTag(completeTagInfo.getTagID()));
                return completeTagInfo;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static ArrayList<SimpleTagGroup> getAllSimpleTagGroups(LanguageType languageType) {
        ArrayList<SimpleTagGroup> simpleTagGroups = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection("getAllSimpleTagGroups");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT TagGroup.TagGroupID," +
                    "Text.Text, " +
                    "TagGroup.ApplicationBlock, " +
                    "TagGroup.Position " +
                    "FROM TagGroup " +
                    "JOIN TextGroup ON (TagGroup.TagGroupTextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Text.LanguageID=? ORDER BY TagGroup.ApplicationBlock,TagGroup.Position";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, languageType.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleTagGroup simpleTagGroup = new SimpleTagGroup();
                simpleTagGroup.setTagGroupID(rs.getLong("TagGroup.TagGroupID"));
                simpleTagGroup.setName(rs.getString("Text.Text"));
                simpleTagGroup.setBlock(ApplicationBlock.parseInt(rs.getInt("TagGroup.ApplicationBlock")));
                simpleTagGroup.setPosition(rs.getInt("TagGroup.Position"));
                simpleTagGroups.add(simpleTagGroup);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleTagGroups;
    }

    public static ArrayList<MobileTagGroup> getMobileTagGroups(Long userID) throws SQLException {
        ArrayList<MobileTagGroup> mobileTagGroups = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection("getMobileTagGroups");
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
                    "TagGroup.ApplicationBlock, " +
                    "TagGroup.TagViewType, " +
                    "Tag.TagID," +
                    "Tag.IconID, " +
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
                        Integer block = rs.getInt("TagGroup.ApplicationBlock");
                        if (block != 0 && !rs.wasNull()) {
                            mobileTagGroup.setBlock(block);
                        }
                        Integer viewType = rs.getInt("TagGroup.TagViewType");
                        if (block != 0 && !rs.wasNull()) {
                            mobileTagGroup.setViewType(viewType);
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
                            mobileTag.setName(rs.getString("Text.Text"));
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

    public static void addCardTag(String tagName, CardEntity cardEntity) {
        try {
            TagEntity tagEntity = getTag(tagName);
            if (tagEntity != null) {
                addCardTag(tagEntity, cardEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private static void addCardTag(TagEntity tagEntity, CardEntity cardEntity) {
        CardTagEntity cardTagEntity = new CardTagEntity(cardEntity, tagEntity);
        addCardTag(cardTagEntity);
    }

    public static void setCardTags(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardTags");
        ArrayList<CardTag> cardTags = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT TagGT.Text, " +
                            "Text.Text, " +
                            "Tag.TagID, " +
                            "CardTag.CardTagID, " +
                            "TagGroup.TagGroupID " +
                            "FROM Card " +
                            "JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
                            "JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                            "JOIN TextGroup ON (Tag.TagTextGroupID=TextGroup.TextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "JOIN TagGroup ON (TagGroup.TagGroupID=Tag.TagGroupID) " +
                            "JOIN TextGroup AS TagGG ON (TagGG.TextGroupID=TagGroup.TagGroupTextGroupID) " +
                            "JOIN Text AS TagGT ON (TagGT.TextGroupID=TagGG.TextGroupID) " +
                            "WHERE Card.CardID=? " +
                            "AND Text.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "AND TagGT.LanguageID=" + LanguageType.Russian.getValue() + " " +
                            "ORDER BY TagGroup.TagGroupID";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardTag cardTag = new CardTag();
                cardTag.setTagGroup(rs.getString("TagGT.Text"));
                cardTag.setTagName(rs.getString("Text.Text"));
                cardTag.setTagID(rs.getLong("Tag.TagID"));
                cardTag.setCardTagID(rs.getLong("CardTag.CardTagID"));
                cardTag.setTagGroupID(rs.getLong("TagGroup.TagGroupID"));
                cardTag.setAdded(true);
                cardTags.add(cardTag);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardTagArrayList(cardTags);
    }

    public static Collection<TagGroup> getAllTagGroups(long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getAllTagGroups");
        HashMap<Long, TagGroup> tagGroups = new HashMap<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT " +
                            "Tag.TagID, " +
                            "TagGroup.TagGroupID, " +
                            "GroupText.Text AS GroupName, " +
                            "Text.Text      AS TagName, " +
                            "T.tagID " +
                            "FROM TagGroup " +
                            "JOIN TextGroup AS GroupTextGroup ON (TagGroup.TagGroupTextGroupID = GroupTextGroup.TextGroupID) " +
                            "JOIN Text AS GroupText ON (GroupText.TextGroupID = GroupTextGroup.TextGroupID) " +
                            "JOIN Tag ON (Tag.TagGroupID = TagGroup.TagGroupID) " +
                            "JOIN TextGroup ON (Tag.TagTextGroupID = TextGroup.TextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID = TextGroup.TextGroupID) " +
                            "LEFT OUTER JOIN ( " +
                            "SELECT Tag.TagID AS tagID FROM Tag " +
                            "LEFT OUTER JOIN CardTag ON (CardTag.TagID = Tag.TagID) " +
                            "LEFT OUTER JOIN Card ON (CardTag.CardID = Card.CardID) " +
                            "WHERE Card.CardID=? " +
                            ") AS T ON (T.tagID=Tag.TagID) " +
                            "WHERE " +
                            "Text.LanguageID = " + LanguageType.Russian.getValue() + " " +
                            "AND GroupText.LanguageID = " + LanguageType.Russian.getValue() + " " +
                            "ORDER BY GroupText.Text, Text.Text";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long tagGroupID = rs.getLong("TagGroup.TagGroupID");
                TagGroup tagGroup;
                if (tagGroups.containsKey(tagGroupID)) {
                    tagGroup = tagGroups.get(tagGroupID);
                } else {
                    tagGroup = new TagGroup();
                    tagGroup.setName(rs.getString("GroupName"));
                    tagGroups.put(tagGroupID, tagGroup);
                    tagGroup.setTagGroupID(tagGroupID);
                }
                ArrayList<CardTag> cardTags = tagGroup.getTagList();
                Long tagID = rs.getLong("Tag.TagID");
                CardTag cardTag = new CardTag();
                cardTag.setTagID(tagID);
                cardTag.setTagName(rs.getString("TagName"));
                cardTag.setTagGroup(tagGroup.getName());
                cardTag.setTagGroupID(tagGroup.getTagGroupID());
                long t = rs.getLong("T.tagID");
                if (rs.wasNull() || t == 0L) {
                    cardTag.setAdded(false);
                } else {
                    cardTag.setAdded(true);
                }
                cardTags.add(cardTag);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tagGroups.values();
    }

    public static HashSet<Long> getCardTagIDs(long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getCardTagIDs");
        HashSet<Long> tagIDs = new HashSet<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT Tag.TagID FROM Tag " +
                            "JOIN CardTag ON (Tag.TagID=CardTag.TagID) " +
                            "JOIN Card ON (CardTag.CardID=Card.CardID) " +
                            "WHERE Card.CardID=?";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                tagIDs.add(rs.getLong("Tag.TagID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return tagIDs;
    }

    public static void deleteTagsFromCard(long cardID, ArrayList<Long> idsToDelete) {
        if (idsToDelete.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("deleteTagsFromCard");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            String ids = StringHelper.getStringFromArray((List) idsToDelete);
            @Language("MySQL") String sqlString =
                    "DELETE FROM CardTag " +
                            "WHERE CardTag.CardID=? AND CardTag.TagID IN(" + ids + ")";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            ps.executeUpdate();

        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static void addTagsToCard(long cardID, ArrayList<Long> idsToAdd) {
        if (idsToAdd.isEmpty()) {
            return;
        }
        try {
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            if (cardEntity != null) {
                for (Long tagId : idsToAdd) {
                    TagEntity tagEntity = TagRequest.getTag(tagId);
                    if (tagEntity != null) {
                        CardTagEntity cardTagEntity = new CardTagEntity(cardEntity, tagEntity);
                        TagRequest.addCardTag(cardTagEntity);
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public static CardTagEntity getCardTag(Long cardTagID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardTagEntity) session.get(CardTagEntity.class, cardTagID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void deleteCardTag(CardTagEntity cardTagEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteCardTag");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM CardTag WHERE CardTag.CardTagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardTagEntity.getCardTagID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static ArrayList<SimpleTagGroup> getAllSimpleTagGroups() {
        return getAllSimpleTagGroups(LanguageType.Russian);
    }

    public static CompleteTagGroupInfo getCompleteTagGroupInfo(Long tagGroupID) {
        CompleteTagGroupInfo completeTagGroupInfo = getSimplePartOfCompleteTagGroupInfo(tagGroupID);
        completeTagGroupInfo.setTagCard(CardRequest.getTagGroupCard(tagGroupID));
        completeTagGroupInfo.setSimpleTags(getTagGroupTags(tagGroupID));
        return completeTagGroupInfo;
    }

    private static ArrayList<SimpleTag> getTagGroupTags(Long tagGroupID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getTagGroupTags");
        ArrayList<SimpleTag> simpleTags = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT Tag.TagID,Text.Text FROM TagGroup " +
                            "JOIN Tag ON (Tag.TagGroupID=TagGroup.TagGroupID) " +
                            "JOIN TextGroup ON (TextGroup.TextGroupID=Tag.TagTextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "WHERE TagGroup.TagGroupID=? " +
                            "AND Text.LanguageID=" + LanguageType.Russian.getValue();
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, tagGroupID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleTag simpleTag = new SimpleTag();
                simpleTag.setName(rs.getString("Text.Text"));
                simpleTag.setTagID(rs.getLong("Tag.TagID"));
                simpleTags.add(simpleTag);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleTags;
    }

    private static CompleteTagGroupInfo getSimplePartOfCompleteTagGroupInfo(Long tagGroupID) {
        DatabaseConnection dbConnection = new DatabaseConnection("getSimplePartOfCompleteTagGroupInfo");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT TagGroup.TagGroupID, " +
                            "Text.Text, " +
                            "TextGroup.TextGroupID " +
                            "FROM TagGroup " +
                            "JOIN TextGroup ON (TextGroup.TextGroupID=TagGroup.TagGroupTextGroupID) " +
                            "JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                            "WHERE TagGroup.TagGroupID=? " +
                            "AND Text.LanguageID=" + LanguageType.Russian.getValue();
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, tagGroupID);
            rs = ps.executeQuery();
            if (rs.first()) {
                CompleteTagGroupInfo completeTagGroupInfo = new CompleteTagGroupInfo();
                completeTagGroupInfo.setName(rs.getString("Text.Text"));
                completeTagGroupInfo.setTagGroupID(rs.getLong("TagGroup.TagGroupID"));
                completeTagGroupInfo.setTextGroupID(rs.getLong("TextGroup.TextGroupID"));
                return completeTagGroupInfo;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static void setTagGroupCard(TagGroupEntity tagGroup, CardEntity card) {
        DatabaseConnection dbConnection = new DatabaseConnection("setTagGroupCard");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE TagGroup " +
                    "SET TagGroup.CardID=? " +
                    "WHERE TagGroup.TagGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, card.getCardID());
            ps.setLong(2, tagGroup.getTagGroupID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void deleteTagGroupCard(TagGroupEntity tagGroup) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteTagGroupCard");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE TagGroup " +
                    "SET TagGroup.CardID=NULL " +
                    "WHERE TagGroup.TagGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagGroup.getTagGroupID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static TagGroupEntity getTagGroup(Long tagGroupID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (TagGroupEntity) session.get(TagGroupEntity.class, tagGroupID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static Integer getMaxApplicationBlockPosition(ApplicationBlock applicationBlock) {
        DatabaseConnection dbConnection = new DatabaseConnection("getMaxApplicationBlockPosition");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT MAX(TagGroup.Position) AS maxValue " +
                            "FROM TagGroup " +
                            "WHERE TagGroup.ApplicationBlock=? ";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, applicationBlock.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getInt("maxValue");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0;
    }

    public static void updateTagIcon(TagEntity tagEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("updateTagIcon");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "UPDATE Tag " +
                    "SET Tag.IconID=? " +
                    "WHERE Tag.TagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagEntity.getIcon().getImageID());
            ps.setLong(2, tagEntity.getTagID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void setMobileTags(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileTags");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language(value = "MySQL") String sql = "SELECT CardTag.CardID, CardTag.TagID FROM CardTag " +
                    "WHERE CardTag.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("CardTag.CardID"));
                mobileCardInfo.getTagIDs().add(rs.getLong("CardTag.TagID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }
}