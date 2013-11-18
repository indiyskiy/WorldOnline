package model.database.requests;


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
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
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
                    TextRequest.getCompleteTextGroupInfo(rs,textGroup,"TagText");
                }
            }
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
            ImageRequest.getCompleteCardImage(rs,completeCardImageInfo,"CardImageTextGroup","CardImageText");
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
           RootRequest.getCompleteCardRoot(rs,completeCardRootInfo,"RootCard","TextGroup","RootText");
        }
        Long cardCoordinate=rs.getLong("CardCoordinate.CardCoordinateID");
        if(cardCoordinate!=0 && !rs.wasNull()){
            if(completeCardInfo.getCardCoordinateEntity()==null){
                CardCoordinateEntity cardCoordinateEntity =  RootRequest.getCardCoordinateByResultSet(rs);
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
        }
        return cardEntity;
    }

    public static void main(String[] args) {
        try {
//            addData
            addData();
            CompleteCardInfo completeCardInfo = getCompleteCardInfo(1);
            //get info
            getInfo(completeCardInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getInfo( CompleteCardInfo completeCardInfo) throws SQLException {
        System.out.println("Card name = " + completeCardInfo.getCardEntity().getCardName());
        if(completeCardInfo.getCardCoordinateEntity()!=null){
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
                System.out.println("text card id = "+completeTextCardInfo.getTextCardEntity().getTextCardID());
            }
        }
    }

    private static void addData() throws SQLException {
        //add info
        //card
        CardEntity card = new CardEntity(CardType.CardRoute, "rootCard1");
        addCard(card);
        //coordinate
        CardCoordinateEntity cardCoordinateEntity=new CardCoordinateEntity(card,18.0,77.5);
        RootRequest.addCardCoordinate(cardCoordinateEntity);
        //tag
        String tagName = "some random cuisine name";
        TagType tagType = TagType.Cuisine;
        TagEntity tag = TagRequest.getTag(tagType, tagName);
        if (tag == null) {
            //tag text
            TextGroupEntity tagTextGroup = new TextGroupEntity("random cuisine tag text group");
            TextEntity textEntityEng = new TextEntity(LanguageType.English, "ololo tag", tagTextGroup);
            TextRequest.addText(textEntityEng);
            TextEntity textEntityRus = new TextEntity(LanguageType.Russian, "ололо тэг", tagTextGroup);
            TextRequest.addText(textEntityRus);
            tag = new TagEntity(tagTextGroup, tagType, tagName);
        }
        CardTagEntity cardTagEntity = new CardTagEntity(card, tag);
        TagRequest.addCardTag(cardTagEntity);
        //image
        int fileSize = 100500;
        String md5Hash="qwiwdrhqn4wevrmw4458m3nr0tr9imib34";
        String url="http:\\\\www.RandomFileServer.ru?imgID=1";
        ImageEntity imageEntity = new ImageEntity(url,1920, 1080, fileSize,md5Hash);
        ImageRequest.addImage(imageEntity);
        //card image
        CardImageEntity cardImageEntity = new CardImageEntity(card,imageEntity,ImageType.CardImage);
        //image text group
        TextGroupEntity imageTextGroupEntity = new TextGroupEntity("random image text group");
        cardImageEntity.setImageDescriptionTextGroup(imageTextGroupEntity);
        TextEntity imageTextEntityEng = new TextEntity(LanguageType.English, "ololo image", imageTextGroupEntity);
        TextRequest.addText(imageTextEntityEng);
        TextEntity imageTextEntityRus = new TextEntity(LanguageType.Russian, "ололо картинка", imageTextGroupEntity);
        TextRequest.addText(imageTextEntityRus);
        ImageRequest.addCardImage(cardImageEntity);
        //card parameter
        String cardParameterValue = "http:\\\\www.youtobe.com";
        CardParameterEntity cardParameterEntity = new CardParameterEntity(card,CardParameterType.Youtube,DataType.Link,cardParameterValue);
        ParameterRequest.addCardParameter(cardParameterEntity);
        //card root text group
        TextGroupEntity rootTextGroup = new TextGroupEntity("random root text group");
        TextEntity rootTextEntityEng = new TextEntity(LanguageType.English, "ololo root", rootTextGroup);
        TextRequest.addText(rootTextEntityEng);
        TextEntity rootTextEntityRus = new TextEntity(LanguageType.Russian, "ололо рут", rootTextGroup);
        TextRequest.addText(rootTextEntityRus);
        //card root
        CardRootEntity cardRootEntity = new CardRootEntity("прогулки по rootCard1",card);
        cardRootEntity.setRootDescriptionTextGroup(rootTextGroup);
        RootRequest.addCardRoot(cardRootEntity);
        //place card
        CardEntity placeCard = new CardEntity(CardType.CardAboutCity, "some place card");
        //root element
        RootElementEntity rootElementEntity = new RootElementEntity();
        rootElementEntity.setCardRoot(cardRootEntity);
        rootElementEntity.setRootElementNumber(0);
        rootElementEntity.setPlaceCard(placeCard);
        RootRequest.addRootElement(rootElementEntity);
        //textCard
        TextCardEntity textCardEntity = new TextCardEntity();
        textCardEntity.setCard(card);
        textCardEntity.setCardTextType(TextType.Address.getValue());
        TextGroupEntity textGroup = new TextGroupEntity("random card adress");
        textCardEntity.setTextGroup(textGroup);
        TextRequest.addTextCard(textCardEntity);
        TextEntity textEntityEng = new TextEntity(LanguageType.English, "ololo sadovaya 113", textGroup);
        TextRequest.addText(textEntityEng);
        TextEntity textEntityRus = new TextEntity(LanguageType.Russian, "ололо садовая 113", textGroup);
        TextRequest.addText(textEntityRus);
    }
}
