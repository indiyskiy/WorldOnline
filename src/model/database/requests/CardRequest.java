package model.database.requests;


import controller.parser.edit.adminparser.AllCardParser;
import model.additionalentity.*;
import model.constants.Component;
import model.constants.databaseenumeration.TextType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, CardRequest.class);

    public static ArrayList<CardEntity> getAllCards(int firstElem, int maxElems) {
        ArrayList<CardEntity> cardEntities = new ArrayList<CardEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardEntities = (ArrayList<CardEntity>) session.createCriteria(CardEntity.class).setFirstResult(firstElem).setMaxResults(maxElems).list();
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
//        Session session = new HibernateUtil().getSessionFactory().openSession();
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
        Transaction transaction = null;
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
//        Session session = new HibernateUtil().getSessionFactory().openSession();
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
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
                    "LEFT OUTER JOIN Card AS TargetCard ON (CardToCardLink.TargetCardID=TargetCard.CardID) " +
                    //linket at
                    "LEFT OUTER JOIN CardToCardLink AS CardToCardLinkedOn ON (CardToCardLinkedOn.TargetCardID=Card.CardID) " +
                    "LEFT OUTER JOIN Card AS SourceCard ON (CardToCardLinkedOn.SourceCardID=SourceCard.CardID) " +
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
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
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
        Long textCardID = rs.getLong("TextCard.TextCardID");
        if (textCardID != 0 && !rs.wasNull()) {
            HashMap<Long, CompleteTextCardInfo> cardInfoHashMap = completeCardInfo.getCompleteTextCardInfoMap();
            CompleteTextCardInfo completeTextCardInfo = cardInfoHashMap.get(textCardID);
            if (completeTextCardInfo == null) {
                TextCardEntity textCardEntity = TextRequest.getTextCardByResultSet(rs);
                completeTextCardInfo = new CompleteTextCardInfo(textCardEntity);
                cardInfoHashMap.put(textCardID, completeTextCardInfo);
            }
            if (completeTextCardInfo.getCompleteTextGroupInfo() == null) {
                CompleteTextGroupInfo completeTextGroupInfo = new CompleteTextGroupInfo(TextRequest.getTextGroupByResultSet(rs));
                completeTextCardInfo.setCompleteTextGroupInfo(completeTextGroupInfo);
            }
            TextRequest.getCompleteTextGroupInfo(rs, completeTextCardInfo.getCompleteTextGroupInfo(), "Text");
        }
        //card links
        Long cardToCardLinkID = rs.getLong("CardToCardLink.CardToCardLinkID");
        if (cardToCardLinkID != 0 && !rs.wasNull()) {
            HashMap<Long, CardToCardLinkEntity> cardToCardLinkEntityMap = completeCardInfo.getCardToCardLinkEntityMap();
            CardToCardLinkEntity cardToCardLinkEntity = cardToCardLinkEntityMap.get(cardToCardLinkID);
            if (cardToCardLinkEntity == null) {
                cardToCardLinkEntity = LinkRequest.getCardToCardLinkByResultSet(rs, "CardToCardLink");
                cardToCardLinkEntityMap.put(cardToCardLinkID, cardToCardLinkEntity);
                cardToCardLinkEntity.setSourceCard(completeCardInfo.getCardEntity());
            }
            if (cardToCardLinkEntity.getTargetCard() == null) {
                Long targetCardID = rs.getLong("TargetCard.CardID");
                if (targetCardID != 0 && !rs.wasNull()) {
                    CardEntity cardEntity = getCardFromResultSet(rs, "TargetCard");
                    cardToCardLinkEntity.setTargetCard(cardEntity);
                }
            }
        }
        //card linked on
        Long cardToCardLinkedOnID = rs.getLong("CardToCardLinkedOn.CardToCardLinkID");
        if (cardToCardLinkedOnID != 0 && !rs.wasNull()) {
            HashMap<Long, CardToCardLinkEntity> cardToCardLinkedOnEntityMap = completeCardInfo.getCardToCardLinkedOnEntityMap();
            CardToCardLinkEntity cardToCardLinkedOnEntity = cardToCardLinkedOnEntityMap.get(cardToCardLinkedOnID);
            if (cardToCardLinkedOnEntity == null) {
                cardToCardLinkedOnEntity = LinkRequest.getCardToCardLinkByResultSet(rs, "CardToCardLinkedOn");
                cardToCardLinkedOnEntityMap.put(cardToCardLinkedOnID, cardToCardLinkedOnEntity);
                cardToCardLinkedOnEntity.setTargetCard(completeCardInfo.getCardEntity());
            }
            if (cardToCardLinkedOnEntity.getSourceCard() == null) {
                Long targetCardID = rs.getLong("SourceCard.CardID");
                if (targetCardID != 0 && !rs.wasNull()) {
                    CardEntity cardEntity = getCardFromResultSet(rs, "SourceCard");
                    cardToCardLinkedOnEntity.setSourceCard(cardEntity);
                }
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
            cardEntity.setCardState(rs.getInt(card + ".CardState"));
        }
        return cardEntity;
    }

    public static void printInfo(CompleteCardInfo completeCardInfo) throws SQLException {
        loggerFactory.info("Card name = " + completeCardInfo.getCardEntity().getCardName());
        if (completeCardInfo.getCardCoordinateEntity() != null) {
            CardCoordinateEntity cardCoordinateEntity = completeCardInfo.getCardCoordinateEntity();
            loggerFactory.info("card coordinate = " + cardCoordinateEntity.getCardCoordinateID() + " " + cardCoordinateEntity.getLatitude() + "-" + cardCoordinateEntity.getLongitude());
        }
        Collection<CompleteCardTagInfo> cardTagInfos = completeCardInfo.getCompleteCardTagInfoMap().values();
        for (CompleteCardTagInfo cardTagInfo : cardTagInfos) {
            loggerFactory.info("Card tag id = " + cardTagInfo.getCardTagEntity().getCardTagID());
            loggerFactory.info("tag id= " + cardTagInfo.getCompleteTagInfo().getTagEntity().getTagID());

            for (CompleteTextGroupInfo textInfo : cardTagInfo.getCompleteTagInfo().getCompleteTextGroupInfoMap().values()) {
                loggerFactory.info("text Group id " + textInfo.getTextGroup().getTextGroupID());
                loggerFactory.info("text Group name " + textInfo.getTextGroup().getTextGroupName());
                for (TextEntity textEntity : textInfo.getTextEntityMap().values()) {
                    loggerFactory.info("text " + textEntity.getText());
                }
            }
        }
        HashMap<Long, CompleteCardImageInfo> completeCardImageInfoMap = completeCardInfo.getCompleteCardImageInfoMap();
        if (completeCardImageInfoMap != null) {
            for (CompleteCardImageInfo completeCardImageInfo : completeCardImageInfoMap.values()) {
                CardImageEntity cardImageEntity = completeCardImageInfo.getCardImageEntity();
                loggerFactory.info("card image id " + cardImageEntity.getCardImageID());
                loggerFactory.info("card image image " + cardImageEntity.getImage().getImageID());
                CompleteTextGroupInfo completeTextGroupInfo = completeCardImageInfo.getCompleteTextGroupInfo();
                if (completeTextGroupInfo != null) {
                    loggerFactory.info("image text Group id " + completeTextGroupInfo.getTextGroup().getTextGroupID());
                    loggerFactory.info("image text Group name " + completeTextGroupInfo.getTextGroup().getTextGroupName());
                    for (TextEntity textEntity : completeTextGroupInfo.getTextEntityMap().values()) {
                        loggerFactory.info("image text " + textEntity.getText());
                    }
                }
            }
        }
        HashMap<Long, CardParameterEntity> cardParameterEntityMap = completeCardInfo.getCardParameterEntityMap();
        if (cardParameterEntityMap != null) {
            Collection<CardParameterEntity> cardParameterEntities = cardParameterEntityMap.values();
            for (CardParameterEntity cardParameterEntity : cardParameterEntities) {
                loggerFactory.info("card parameter id = " + cardParameterEntity.getCardParameterID());
                loggerFactory.info("card parameter value = " + cardParameterEntity.getCardParameterValue());
            }
        }
        if (completeCardInfo.getCompleteCardRootInfo() != null) {
            CompleteCardRootInfo completeCardRootInfo = completeCardInfo.getCompleteCardRootInfo();
            if (completeCardRootInfo.getCardRootEntity() != null) {
                CardRootEntity cardRootEntity = completeCardRootInfo.getCardRootEntity();
                loggerFactory.info("card root id =  " + cardRootEntity.getCardRootID());
                loggerFactory.info("card root name =  " + cardRootEntity.getCardRootName());
            }
            if (completeCardRootInfo.getRootElementEntityMap() != null) {
                Collection<RootElementEntity> rootElementEntities = completeCardRootInfo.getRootElementEntityMap().values();
                for (RootElementEntity rootElementEntity : rootElementEntities) {
                    loggerFactory.info("root element ID = " + rootElementEntity.getRootElementID());
                    loggerFactory.info("root element name = " + rootElementEntity.getPlaceCard().getCardName());
                }
            }
            CompleteTextGroupInfo completeTextGroupInfo = completeCardRootInfo.getCompleteTextGroupInfo();
            if (completeTextGroupInfo != null) {
                loggerFactory.info("root text Group id " + completeTextGroupInfo.getTextGroup().getTextGroupID());
                loggerFactory.info("root text Group name " + completeTextGroupInfo.getTextGroup().getTextGroupName());
                for (TextEntity textEntity : completeTextGroupInfo.getTextEntityMap().values()) {
                    loggerFactory.info("root text " + textEntity.getText());
                }
            }
        }

        Collection<CompleteTextCardInfo> completeTextCardInfos = completeCardInfo.getCompleteTextCardInfoMap().values();
        for (CompleteTextCardInfo completeTextCardInfo : completeTextCardInfos) {
            if (completeTextCardInfo.getTextCardEntity() != null) {
                loggerFactory.info(TextType.parseInt(completeTextCardInfo.getTextCardEntity().getCardTextType()) + " " + completeTextCardInfo.getCompleteTextGroupInfo().getTextGroup().getTextGroupName());
            }
        }
    }

    public static ArrayList<CardEntity> getAllCards(AllCardParser parser) {
        ArrayList<CardEntity> cards = new ArrayList<CardEntity>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT * FROM Card " +
                    "WHERE 1=1";
            if (parser.getCardID() != null) {
                sql += " AND Card.CardID=?";
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += " AND Card.CardName=?";
            }
            if (parser.getCardType() != null) {
                sql += " AND Card.CardType=?";
            }
            sql += " LIMIT " + parser.getFirstElem() + ", " + parser.getMaxItems();
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (parser.getCardID() != null) {
                ps.setLong(i, parser.getCardID());
                i++;
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                ps.setString(i, parser.getCardName());
                i++;
            }
            if (parser.getCardType() != null) {
                ps.setInt(i, parser.getCardType().getValue());
                i++;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                CardEntity cardEntity = getCardFromResultSet(rs);
                cards.add(cardEntity);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cards;
    }

    public static Long countCard() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(CardEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public static Long countCard(AllCardParser parser) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT count(1) as results FROM Card " +
                    "WHERE 1=1";
            if (parser.getCardID() != null) {
                sql += " AND Card.CardID=?";
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += " AND Card.CardName=?";
            }
            if (parser.getCardType() != null) {
                sql += " AND Card.CardType=?";
            }
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (parser.getCardID() != null) {
                ps.setLong(i, parser.getCardID());
                i++;
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                ps.setString(i, parser.getCardName());
                i++;
            }
            if (parser.getCardType() != null) {
                ps.setInt(i, parser.getCardType().getValue());
                i++;
            }
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getLong("results");
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0L;
    }
}
