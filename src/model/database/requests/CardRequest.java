package model.database.requests;


import model.additionalentity.*;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.TagType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 01.11.13
 * Time: 5:12
 * To change this template use File | Settings | File Templates.
 */
public class CardRequest {

    public static ArrayList<CardEntity> getAllCards() {
        ArrayList<CardEntity> cardEntities = new ArrayList<CardEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardEntities = (ArrayList<CardEntity>) session.createCriteria(CardEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cardEntities;
    }


    public static CardEntity getCardByID(Long cardID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardEntity) session.get(CardEntity.class, cardID);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean addCard(CardEntity card) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(card);
            session.getTransaction().commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean contains(CardEntity card) {
        if (card != null) {
            CardEntity cardFromBase = getCardByID(card.getCardID());
            if (cardFromBase != null && card.equals(cardFromBase)) {
                return true;
            }
        }
        return false;
    }

    public static void addCard(List<CardEntity> cards) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for (CardEntity card : cards) {
                session.save(card);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static CardEntity getCardFromResultSet(ResultSet rs) throws SQLException {
        CardEntity cardEntity = null;
        if (rs.getLong("Card.CardID") != 0 && !rs.wasNull()) {
            cardEntity = new CardEntity();
            cardEntity.setCardID(rs.getLong("Card.CardID"));
            cardEntity.setCardName(rs.getString("Card.CardName"));
            cardEntity.setCardType(rs.getInt("Card.CardType"));
            cardEntity.setCardVersion(rs.getInt("Card.CardVersion"));
            cardEntity.setCreationTimestamp(rs.getTimestamp("Card.CreationTimestamp"));
            cardEntity.setLastUpdateTimestamp(rs.getTimestamp("Card.LastUpdateTimestamp"));
        }
        return cardEntity;
    }

    public static HashMap<Long, CompleteCardInfo> getCompleteCardInfo() {
        HashMap<Long, CompleteCardInfo> cards = new HashMap<Long, CompleteCardInfo>();
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
                    //card image
                    "LEFT OUTER JOIN CardImage ON(Card.CardID=CardImage.CardID) " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    //card parameter
                    "LEFT OUTER JOIN CardParameter ON (Card.CardID=CardParameter.CardID) " +
                    //card root
                    "LEFT OUTER JOIN CardRoot ON (Card.CardID=CardRoot.CardID) " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.CardRootID) " +
                    //card tag
                    "LEFT OUTER JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
                    "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "LEFT OUTER JOIN TextGroup AS TagTextGroup ON (Tag.TagTextGroupID=TagTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS TagText ON (TagText.TextGroupID=TagTextGroup.TextGroupID) " +
                    //card text
                    "LEFT OUTER JOIN TextCard ON (Card.CardID=TextCard.CardID) " +
                    "LEFT OUTER JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardInfo card;
                Long cardID = rs.getLong("Card.CardID");
                if (cards.containsKey(cardID) && cards.get(cardID) != null && !rs.wasNull()) {
                    card = cards.get(cardID);
                } else {
                    card = new CompleteCardInfo(getCardFromResultSet(rs));
                    cards.put(cardID, card);
                }
                getCompleteCardInfo(card, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static CompleteCardInfo getCompleteCardInfo(long cardID) throws SQLException {
        CompleteCardInfo card = null;
        DatabaseConnection dbConnection = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            dbConnection = new DatabaseConnection();
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
                    //card image
                    "LEFT OUTER JOIN CardImage ON(Card.CardID=CardImage.CardID) " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup AS CardImageTextGroup ON (CardImage.ImageDescriptionTextGroupID=CardImageTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS CardImageText ON (CardImageText.TextGroupID=CardImageTextGroup.TextGroupID) " +
                    //card parameter
                    "LEFT OUTER JOIN CardParameter ON (Card.CardID=CardParameter.CardID) " +
                    //card root
                    "LEFT OUTER JOIN CardRoot ON (Card.CardID=CardRoot.CardID) " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.CardRootID) " +
                    //card tag
                    "LEFT OUTER JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
                    "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "LEFT OUTER JOIN TextGroup AS TagTextGroup ON (Tag.TagTextGroupID=TagTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS TagText ON (TagText.TextGroupID=TagTextGroup.TextGroupID) " +
                    //card text
                    "LEFT OUTER JOIN TextCard ON (Card.CardID=TextCard.CardID) " +
                    "LEFT OUTER JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE Card.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                CardEntity cardEntity = getCardFromResultSet(rs);
                card = new CompleteCardInfo(cardEntity);
                card = getCompleteCardInfo(card, rs);
                while (rs.next()) {
                    card = getCompleteCardInfo(card, rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return card;
    }

    private static CompleteCardInfo getCompleteCardInfo(CompleteCardInfo card, ResultSet rs) throws SQLException {
        //card tag
        Long cardTagID = rs.getLong("CardTag.CardTagID");
        if (!rs.wasNull()) {
            CompleteCardTagInfo cardTagInfo;
            if (card.getCompleteCardTagInfoMap().containsKey(cardTagID)) {
                cardTagInfo = card.getCompleteCardTagInfoMap().get(cardTagID);
            } else {
                cardTagInfo = new CompleteCardTagInfo(TagRequest.getCardTagByResultSet(rs));
                cardTagInfo.getCardTagEntity().setCard(card.getCardEntity());
                card.getCompleteCardTagInfoMap().put(cardTagID, cardTagInfo);
            }
            //tag
            Long tagID = rs.getLong("Tag.TagID");
            if (!rs.wasNull()) {
                CompleteTagInfo tagInfo;
                if (cardTagInfo.getCompleteTagInfo() == null) {
                    tagInfo = new CompleteTagInfo(TagRequest.getTagByResultSet(rs));
                    cardTagInfo.setCompleteTagInfo(tagInfo);
                    cardTagInfo.getCardTagEntity().setTag(tagInfo.getTagEntity());
                } else {
                    tagInfo = cardTagInfo.getCompleteTagInfo();
                }
                //tag text group
                Long tagTextGroupID = rs.getLong("TagTextGroup.TextGroupID");
                if (!rs.wasNull()) {
                    CompleteTextGroupInfo textGroup;
                    if (tagInfo.getCompleteTextGroupInfoMap().containsKey(tagTextGroupID) && tagInfo.getCompleteTextGroupInfoMap().get(tagTextGroupID) != null) {
                        textGroup = tagInfo.getCompleteTextGroupInfoMap().get(tagTextGroupID);
                    } else {
                        textGroup = new CompleteTextGroupInfo(TextRequest.getTextGroupByResultSet(rs, "TagTextGroup"));
                        tagInfo.getTagEntity().setTagTextGroup(textGroup.getTextGroup());
                        tagInfo.getCompleteTextGroupInfoMap().put(tagTextGroupID, textGroup);
                    }
                    //tag text
                    Long textID = rs.getLong("TagText.TextID");
                    if (!rs.wasNull()) {
                        if (!textGroup.getTextEntityMap().containsKey(textID)) {
                            TextEntity text = TextRequest.getTextByResultSet(rs, "TagText");
                            text.setTextGroup(textGroup.getTextGroup());
                            textGroup.getTextEntityMap().put(textID, text);
                        }
                    }
                }
            }
        }
        //card Image
        Long cardImageID = rs.getLong("CardImage.CardImageID");
        if (cardImageID != 0 && !rs.wasNull()) {
            CompleteCardImageInfo completeCardImageInfo;
            if (!card.getCompleteCardImageInfoMap().containsKey(cardImageID) && card.getCompleteCardImageInfoMap().get(cardImageID) != null) {
                completeCardImageInfo = card.getCompleteCardImageInfoMap().get(cardImageID);
            } else {
                CardImageEntity cardImageEntity = ImageRequest.gerCardImageByResultSet(rs);
                completeCardImageInfo = new CompleteCardImageInfo(cardImageEntity);
                cardImageEntity.setCard(card.getCardEntity());
                card.getCompleteCardImageInfoMap().put(cardImageID, completeCardImageInfo);
                completeCardImageInfo = new CompleteCardImageInfo(cardImageEntity);
            }
            if (completeCardImageInfo.getImageEntity() == null) {
                //card image
                Long imageID = rs.getLong("Image.ImageID");
                if (rs.getLong("Image.ImageID") != 0 && !rs.wasNull())
                    if (imageID != 0 && !rs.wasNull()) {
                        ImageEntity imageEntity = ImageRequest.getImageFromResultSet(rs);
                        completeCardImageInfo.setImageEntity(imageEntity);
                    }
            }
            Long imageTextGroup = rs.getLong("CardImageTextGroup.TextGroupID");
            if (imageTextGroup != 0 && !rs.wasNull()) {
                if (completeCardImageInfo.getCompleteTextGroupInfo() == null) {
                    TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, "CardImageTextGroup");
                    CompleteTextGroupInfo completeTextGroupInfo = new CompleteTextGroupInfo(textGroupEntity);
//                    if()
                }
            }
        }
        return card;
    }


    public static void main(String[] args) {
        try {
            CardEntity card = new CardEntity(CardType.CardRoute, "rootCard1");
            addCard(card);
            String tagName = "some random cuisine name";
            TagType tagType = TagType.Cuisine;
            TagEntity tag = TagRequest.getTag(tagType, tagName);
            if (tag == null) {
                TextGroupEntity tagTextGroup = new TextGroupEntity("random cuisine tag text group");
                TextEntity textEntytyEng = new TextEntity(LanguageType.English, "ololo", tagTextGroup);
                TextRequest.addText(textEntytyEng);
                TextEntity textEntytyRus = new TextEntity(LanguageType.Russian, "ололо", tagTextGroup);
                TextRequest.addText(textEntytyRus);
                tag = new TagEntity(tagTextGroup, tagType, tagName);
            }
            CardTagEntity cardTagEntity = new CardTagEntity(card, tag);
            TagRequest.addCardTag(cardTagEntity);
            CompleteCardInfo value = getCompleteCardInfo(1);
            System.out.println("Card name = " + value.getCardEntity().getCardName());
            Collection<CompleteCardTagInfo> cardTagInfos = value.getCompleteCardTagInfoMap().values();
            for (CompleteCardTagInfo cardTagInfo : cardTagInfos) {
                System.out.println("Card tag id = " + cardTagInfo.getCardTagEntity().getCardTagID());
                System.out.println("tag id= " + cardTagInfo.getCompleteTagInfo().getTagEntity().getTagID());

                for (CompleteTextGroupInfo textInfo : cardTagInfo.getCompleteTagInfo().getCompleteTextGroupInfoMap().values()) {
                    System.out.println("text Group id " + textInfo.getTextGroup().getTextGroupID());
                    System.out.println("text Group name " + textInfo.getTextGroup().getTextGroupName());
                    for (TextEntity textEntity : textInfo.getTextEntityMap().values()) {
                        System.out.println("text " + textEntity.getText());
                    }
                }
            }
            /*HashMap<Long, CompleteCardInfo> completeCardInfo = getCompleteCardInfo();
            Collection<CompleteCardInfo> values = completeCardInfo.values();
            for (CompleteCardInfo value2 : values) {
                System.out.println("card name = "+value2.getCardEntity().getCardName());
                Collection<CompleteCardTagInfo> tagInfos2 = value2.getCompleteCardTagInfoMap().values();
                for (CompleteCardTagInfo tagInfo : tagInfos2) {
                    System.out.println("Card tag = "+tagInfo.getCardTagEntity().getCardTagID());
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
