package model.database.requests;


import model.Test;
import model.additionalentity.*;
import model.constants.databaseenumeration.*;
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

    public static HashMap<Long, CompleteCardInfo> getCompleteCardInfo() {
        HashMap<Long, CompleteCardInfo> cards = new HashMap<Long, CompleteCardInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
                    //card image      \/
                    "LEFT OUTER JOIN CardImage ON(Card.CardID=CardImage.CardID) " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup AS CardImageTextGroup ON (CardImage.ImageDescriptionTextGroupID=CardImageTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS CardImageText ON (CardImageText.TextGroupID=CardImageTextGroup.TextGroupID) " +
                    //card parameter  \/
                    "LEFT OUTER JOIN CardParameter ON (Card.CardID=CardParameter.CardID) " +
                    //card root        \/
                    "LEFT OUTER JOIN CardRoot ON (Card.CardID=CardRoot.CardID) " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup AS RootTextGroup ON(CardRoot.RootDescriptionTextGroupID=RootTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS RootText ON (RootText.TextGroupID=RootTextGroup.TextGroupID) " +
                    //card tag   \/
                    "LEFT OUTER JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
                    "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "LEFT OUTER JOIN TextGroup AS TagTextGroup ON (Tag.TagTextGroupID=TagTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS TagText ON (TagText.TextGroupID=TagTextGroup.TextGroupID) " +
                    //card text
                    "LEFT OUTER JOIN TextCard ON (Card.CardID=TextCard.CardID) " +
                    "LEFT OUTER JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) ";
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery();
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
        } finally {
            dbConnection.closeConnections(ps,rs);
        }
        return cards;
    }

    public static CompleteCardInfo getCompleteCardInfo(long cardID) throws SQLException {
        CompleteCardInfo card = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
                    //card image      \/
                    "LEFT OUTER JOIN CardImage ON(Card.CardID=CardImage.CardID) " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup AS CardImageTextGroup ON (CardImage.ImageDescriptionTextGroupID=CardImageTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS CardImageText ON (CardImageText.TextGroupID=CardImageTextGroup.TextGroupID) " +
                    //card parameter  \/
                    "LEFT OUTER JOIN CardParameter ON (Card.CardID=CardParameter.CardID) " +
                    //card root        \/
                    "LEFT OUTER JOIN CardRoot ON (Card.CardID=CardRoot.CardID) " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup AS RootTextGroup ON(CardRoot.RootDescriptionTextGroupID=RootTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS RootText ON (RootText.TextGroupID=RootTextGroup.TextGroupID) " +
                    //card tag   \/
                    "LEFT OUTER JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
                    "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "LEFT OUTER JOIN TextGroup AS TagTextGroup ON (Tag.TagTextGroupID=TagTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text AS TagText ON (TagText.TextGroupID=TagTextGroup.TextGroupID) " +
                    //card text
                    "LEFT OUTER JOIN TextCard ON (Card.CardID=TextCard.CardID) " +
                    "LEFT OUTER JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    //card to card link
                    "LEFT OUTER JOIN CardToCardLink ON (CardToCardLink.SourceCardID=Card.CardID) " +
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
          dbConnection.closeConnections(ps,rs);
        }
        return card;
    }

    private static CompleteCardInfo getCompleteCardInfo(CompleteCardInfo completeCardInfo, ResultSet rs) throws SQLException {
        //card tag
        Long cardTagID = rs.getLong("CardTag.CardTagID");
        if (!rs.wasNull()) {
            CompleteCardTagInfo cardTagInfo;
            if (completeCardInfo.getCompleteCardTagInfoMap().containsKey(cardTagID)) {
                cardTagInfo = completeCardInfo.getCompleteCardTagInfoMap().get(cardTagID);
            } else {
                cardTagInfo = new CompleteCardTagInfo(TagRequest.getCardTagByResultSet(rs));
                cardTagInfo.getCardTagEntity().setCard(completeCardInfo.getCardEntity());
                completeCardInfo.getCompleteCardTagInfoMap().put(cardTagID, cardTagInfo);
            }
            //tag
            TagRequest.getCompleteCardTag(rs, cardTagInfo, "TagTextGroup", "TagText");
        }
        //card Image
        Long cardImageID = rs.getLong("CardImage.CardImageID");
        if (cardImageID != 0 && !rs.wasNull()) {
            CompleteCardImageInfo completeCardImageInfo;
            if (!completeCardInfo.getCompleteCardImageInfoMap().containsKey(cardImageID) && completeCardInfo.getCompleteCardImageInfoMap().get(cardImageID) != null) {
                completeCardImageInfo = completeCardInfo.getCompleteCardImageInfoMap().get(cardImageID);
            } else {
                CardImageEntity cardImageEntity = ImageRequest.getCardImageByResultSet(rs);
                completeCardImageInfo = new CompleteCardImageInfo(cardImageEntity);
                cardImageEntity.setCard(completeCardInfo.getCardEntity());
                completeCardInfo.getCompleteCardImageInfoMap().put(cardImageID, completeCardImageInfo);
            }
            ImageRequest.getCompleteCardImage(rs, completeCardImageInfo, "CardImageTextGroup", "CardImageText");
        }
        //card parameter
        Long cardParameterID = rs.getLong("CardParameter.CardParameterID");
        if (cardParameterID != 0 && !rs.wasNull()) {
            CardParameterEntity cardParameterEntity;
            HashMap<Long, CardParameterEntity> cardParameterEntityMap = completeCardInfo.getCardParameterEntityMap();
            if (!cardParameterEntityMap.containsKey(cardImageID) || cardParameterEntityMap.get(cardImageID) == null) {
                cardParameterEntity = ParameterRequest.getCardParameterByResultSet(rs);
                cardParameterEntity.setCard(completeCardInfo.getCardEntity());
                cardParameterEntityMap.put(cardParameterID, cardParameterEntity);
            }
        }
        //card root
        Long cardRootID = rs.getLong("CardRoot.CardRootID");
        if (cardRootID != 0 && !rs.wasNull()) {
            CompleteCardRootInfo completeCardRootInfo;
            if (completeCardInfo.getCompleteCardRootInfo() != null) {
                completeCardRootInfo = completeCardInfo.getCompleteCardRootInfo();
            } else {
                completeCardRootInfo = new CompleteCardRootInfo(RootRequest.getCardRootByResultSet(rs));
                RootRequest.getCardRootByResultSet(rs);
                completeCardInfo.setCompleteCardRootInfo(completeCardRootInfo);
            }
            //root element
            RootRequest.getCompleteCardRoot(rs, completeCardRootInfo, "RootCard", "TextGroup", "RootText");
        }
        Long cardCoordinate = rs.getLong("CardCoordinate.CardCoordinateID");
        if (cardCoordinate != 0 && !rs.wasNull()) {
            if (completeCardInfo.getCardCoordinateEntity() == null) {
                CardCoordinateEntity cardCoordinateEntity = RootRequest.getCardCoordinateByResultSet(rs);
                cardCoordinateEntity.setCard(completeCardInfo.getCardEntity());
                completeCardInfo.setCardCoordinateEntity(cardCoordinateEntity);
            }
        }
        return completeCardInfo;
    }

    public static CardEntity getCardFromResultSet(ResultSet rs) throws SQLException {
        return getCardFromResultSet(rs, "Card");
    }

    public static CardEntity getCardFromResultSet(ResultSet rs, String card) throws SQLException {
        CardEntity cardEntity = null;
        if (rs.getLong(card + ".CardID") != 0 && !rs.wasNull()) {
            cardEntity = new CardEntity();
            cardEntity.setCardID(rs.getLong(card + ".CardID"));
            cardEntity.setCardName(rs.getString(card + ".CardName"));
            cardEntity.setCardType(rs.getInt(card + ".CardType"));
            cardEntity.setCardVersion(rs.getInt(card + ".CardVersion"));
            cardEntity.setCreationTimestamp(rs.getTimestamp(card + ".CreationTimestamp"));
            cardEntity.setLastUpdateTimestamp(rs.getTimestamp(card + ".LastUpdateTimestamp"));
            cardEntity.setCardState(rs.getInt(card+".CardState"));
        }
        return cardEntity;
    }



    public static void printInfo(CompleteCardInfo completeCardInfo) throws SQLException {
        System.out.println("Card name = " + completeCardInfo.getCardEntity().getCardName());
        if (completeCardInfo.getCardCoordinateEntity() != null) {
            CardCoordinateEntity cardCoordinateEntity = completeCardInfo.getCardCoordinateEntity();
            System.out.println("card coordinate = " + cardCoordinateEntity.getCardCoordinateID() + " " + cardCoordinateEntity.getLatitude() + "-" + cardCoordinateEntity.getLongitude());
        }
        Collection<CompleteCardTagInfo> cardTagInfos = completeCardInfo.getCompleteCardTagInfoMap().values();
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
        HashMap<Long, CompleteCardImageInfo> completeCardImageInfoMap = completeCardInfo.getCompleteCardImageInfoMap();
        if (completeCardImageInfoMap != null) {
            for (CompleteCardImageInfo completeCardImageInfo : completeCardImageInfoMap.values()) {
                CardImageEntity cardImageEntity = completeCardImageInfo.getCardImageEntity();
                System.out.println("card image id " + cardImageEntity.getCardImageID());
                System.out.println("card image image " + cardImageEntity.getImage().getImageID());
                CompleteTextGroupInfo completeTextGroupInfo = completeCardImageInfo.getCompleteTextGroupInfo();
                if (completeTextGroupInfo != null) {
                    System.out.println("image text Group id " + completeTextGroupInfo.getTextGroup().getTextGroupID());
                    System.out.println("image text Group name " + completeTextGroupInfo.getTextGroup().getTextGroupName());
                    for (TextEntity textEntity : completeTextGroupInfo.getTextEntityMap().values()) {
                        System.out.println("image text " + textEntity.getText());
                    }
                }
            }
        }
        HashMap<Long, CardParameterEntity> cardParameterEntityMap = completeCardInfo.getCardParameterEntityMap();
        if (cardParameterEntityMap != null) {
            Collection<CardParameterEntity> cardParameterEntities = cardParameterEntityMap.values();
            for (CardParameterEntity cardParameterEntity : cardParameterEntities) {
                System.out.println("card parameter id = " + cardParameterEntity.getCardParameterID());
                System.out.println("card parameter value = " + cardParameterEntity.getCardParameterValue());
            }
        }
        if (completeCardInfo.getCompleteCardRootInfo() != null) {
            CompleteCardRootInfo completeCardRootInfo = completeCardInfo.getCompleteCardRootInfo();
            if (completeCardRootInfo.getCardRootEntity() != null) {
                CardRootEntity cardRootEntity = completeCardRootInfo.getCardRootEntity();
                System.out.println("card root id =  " + cardRootEntity.getCardRootID());
                System.out.println("card root name =  " + cardRootEntity.getCardRootName());
            }
            if (completeCardRootInfo.getRootElementEntityMap() != null) {
                Collection<RootElementEntity> rootElementEntities = completeCardRootInfo.getRootElementEntityMap().values();
                for (RootElementEntity rootElementEntity : rootElementEntities) {
                    System.out.println("root element ID = " + rootElementEntity.getRootElementID());
                    System.out.println("root element name = " + rootElementEntity.getPlaceCard().getCardName());
                }
            }
            CompleteTextGroupInfo completeTextGroupInfo = completeCardRootInfo.getCompleteTextGroupInfo();
            if (completeTextGroupInfo != null) {
                System.out.println("root text Group id " + completeTextGroupInfo.getTextGroup().getTextGroupID());
                System.out.println("root text Group name " + completeTextGroupInfo.getTextGroup().getTextGroupName());
                for (TextEntity textEntity : completeTextGroupInfo.getTextEntityMap().values()) {
                    System.out.println("root text " + textEntity.getText());
                }
            }
        }
        Collection<CompleteTextCardInfo> completeTextCardInfos = completeCardInfo.getCompleteTextCardInfoMap().values();
        for (CompleteTextCardInfo completeTextCardInfo : completeTextCardInfos) {
            if (completeTextCardInfo.getTextCardEntity() != null) {
                System.out.println("text card id = " + completeTextCardInfo.getTextCardEntity().getTextCardID());
            }
        }
    }
}
