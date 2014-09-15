package model.database.requests;

import helper.ImageHelper;
import model.constants.databaseenumeration.*;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardToCardLinkEntity;
import model.xmlparser.GlobalXmlParser;

public class NewsRequest {

    public static void addTestNews() {
        if (countNews() == 0) {
            String nameEn = "TestNews";
            String nameRu = "ТестовыеНовости";
            String descriptionRu = "Описание тестовой новости";
            String descriptionEn = "Test news description";
            String offerRu1 = "Тестовое специальное предложение 1";
            String offerEn1 = "Test news offer 1";
            String offerRu2 = "Тестовое специальное предложение 2";
            String offerEn2 = "Test news offer 2";
            String photo = "rainbowDash.jpg";
            String menuPhoto = "LoyaltyHonesty.jpg";
            String placeCardName = "Gogol";
            String venueCardName = "Onegin Art Gallery";

            //лат, лон

            CardEntity cardEntity = new CardEntity(CardType.CardNews, nameEn, CardState.Active);
            GlobalXmlParser.saveText(nameRu, nameEn, nameRu, nameEn, TextType.Name, cardEntity);
            GlobalXmlParser.saveText(descriptionRu, descriptionEn, nameRu, nameEn, TextType.Description, cardEntity);
            GlobalXmlParser.saveText(descriptionRu, descriptionEn, nameRu, nameEn, TextType.Description, cardEntity);
            GlobalXmlParser.saveText(offerRu1, offerEn1, nameRu, nameEn, TextType.Offers, cardEntity);
            GlobalXmlParser.saveText(offerRu2, offerEn2, nameRu, nameEn, TextType.Offers, cardEntity);
            ImageHelper.saveImage(photo, cardEntity, ImageType.Photo);
            ImageHelper.saveImage(menuPhoto, cardEntity, ImageType.MenuPhoto);
            CardEntity linkedPlace = CardRequest.getCardByName(placeCardName);
            CardToCardLinkEntity cardToCardLinkEntity = new CardToCardLinkEntity(cardEntity, linkedPlace, CardToCardLinkType.LinkedPlace);
            LinkRequest.addCardToCardLink(cardToCardLinkEntity);
            CardEntity venue = CardRequest.getCardByName(venueCardName);
            cardToCardLinkEntity = new CardToCardLinkEntity(cardEntity, venue, CardToCardLinkType.Venue);
            LinkRequest.addCardToCardLink(cardToCardLinkEntity);
            GlobalXmlParser.saveParameter("2014-08-22 15:35:00.0", cardEntity, CardParameterType.TimeOfEvent);
            GlobalXmlParser.saveParameter("https://ru-ru.facebook.com/", cardEntity, CardParameterType.Fbcom);
            GlobalXmlParser.saveParameter("http://vk.com/feed", cardEntity, CardParameterType.Vkcom);
            GlobalXmlParser.saveParameter("https://twitter.com/", cardEntity, CardParameterType.Twitter);
            GlobalXmlParser.saveParameter("2128506", cardEntity, CardParameterType.Phone);
            GlobalXmlParser.saveParameter("gmail@gmail.com", cardEntity, CardParameterType.Email);
            GlobalXmlParser.saveParameter("http://www.youtube.com/watch?v=57mkfFdvREw&index=2&list=RDLwCF9cZG60s", cardEntity, CardParameterType.Youtube);
            GlobalXmlParser.saveParameter("javascript:window.parent.ticketManager.placeSchedule(%27ea46b444-07a6-435f-9726-0db1006eb91f%27,2881,%27%7b%22Formats%22%3a%5b%5d%2c%22MaxPrice%22%3anull%2c%22Objects%22%3a%5b%5d%2c%22SaleSupport%22%3anull%2c%22TagID%22%3anull%2c%22TimeStatus%22%3anull%2c%22AllowNonEtickets%22%3afalse%7d%27)", cardEntity, CardParameterType.AfishaBuyTicket);
            TagRequest.addCardTag(NewsTag.free.getNameRu(), cardEntity);
        }
    }

    private static long countNews() {
        return CardRequest.countCard(CardType.CardNews);
    }
}
