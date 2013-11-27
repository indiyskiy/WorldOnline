package model;

import model.additionalentity.CompleteCardInfo;
import model.constants.databaseenumeration.*;
import model.database.requests.*;
import model.database.worldonlinedb.*;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 19.11.13
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void addManyTags() {
        TagType[] types = TagType.values();
        for (TagType type : types) {
            for (int k = 0; k < 6; k++) {
                String groupName = "textGroupOfTag_" + type + "_" + k;
                String tagName = "tag_" + type + "_" + k;
                TextGroupEntity textGroupEntity = new TextGroupEntity(groupName);
                TagRequest.addTag(new TagEntity(textGroupEntity, type, tagName));
            }
        }
    }

    public static void addTestData() throws SQLException {
        //add info
        //card
        CardEntity card = new CardEntity(CardType.CardRoute, "rootCard1",CardState.Active);
        CardRequest.addCard(card);
        //coordinate
        CardCoordinateEntity cardCoordinateEntity = new CardCoordinateEntity(card, 18.0, 77.5);
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
        Long fileSize = 100500l;
        String md5Hash = "qwiwdrhqn4wevrmw4458m3nr0tr9imib34";
        String url = "http:\\\\www.RandomFileServer.ru?imgID=1";
        ImageEntity imageEntity = new ImageEntity(url, 1920, 1080, fileSize, md5Hash);
        ImageRequest.addImage(imageEntity);
        //card image
        CardImageEntity cardImageEntity = new CardImageEntity(card, imageEntity, ImageType.CardImage);
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
        CardParameterEntity cardParameterEntity = new CardParameterEntity(card, CardParameterType.Youtube, DataType.LinkType, cardParameterValue);
        ParameterRequest.addCardParameter(cardParameterEntity);
        //card root text group
        TextGroupEntity rootTextGroup = new TextGroupEntity("random root text group");
        TextEntity rootTextEntityEng = new TextEntity(LanguageType.English, "ololo root", rootTextGroup);
        TextRequest.addText(rootTextEntityEng);
        TextEntity rootTextEntityRus = new TextEntity(LanguageType.Russian, "ололо рут", rootTextGroup);
        TextRequest.addText(rootTextEntityRus);
        //card root
        CardRootEntity cardRootEntity = new CardRootEntity("прогулки по rootCard1", card);
        cardRootEntity.setRootDescriptionTextGroup(rootTextGroup);
        RootRequest.addCardRoot(cardRootEntity);
        //place card
        CardEntity placeCard = new CardEntity(CardType.CardAboutCity, "some place card",CardState.Active);
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
        //card to card link
        CardToCardLinkEntity cardToCardLinkEntity=new CardToCardLinkEntity(card,card,CardToCardLinkType.Unknown);
        LinkRequest.addCardToCardLinkRequest(cardToCardLinkEntity);
    }

    public static void main(String[] args) {
        try {
            Test.addTestData();
            //card
            CompleteCardInfo completeCardInfo = CardRequest.getCompleteCardInfo(1);
            CardRequest.printInfo(completeCardInfo);
            //todo other requests
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
