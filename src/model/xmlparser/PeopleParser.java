package model.xmlparser;

import helper.ImageHelper;
import model.constants.Component;
import model.constants.databaseenumeration.*;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.exception.DataIsEmptyException;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.people.peopleaboutcity.AboutCity;
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.util.HashSet;

public class PeopleParser {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, PeopleParser.class);

    public PeopleAboutCity getPeopleAboutCity(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(PeopleAboutCity.class, reader, false);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public void savePeopleAboutCity(GlobalXmlParser globalXmlParser, PeopleAboutCity peopleAboutCity) {
        for (AboutCity aboutCity : peopleAboutCity.aboutCities) {
            CardEntity oldCard = CardRequest.getCardByName(aboutCity.nameRu);
            if (oldCard != null) {
                globalXmlParser.getCardEntityHashMap().put(Long.parseLong(aboutCity.id), oldCard);
            } else {
                CardEntity card = new CardEntity(CardType.CardPerson, aboutCity.nameEn, CardState.Active);
                CardRequest.addCardSafe(card);
                globalXmlParser.getCardEntityHashMap().put(Long.parseLong(aboutCity.id), card);
                globalXmlParser.saveText(aboutCity.nameRu, aboutCity.nameEn, aboutCity.nameRu, aboutCity.nameEn, TextType.Name, card);
                globalXmlParser.saveText(aboutCity.biographyRu, aboutCity.biographyEn, aboutCity.nameRu, aboutCity.nameEn, TextType.Biography, card);
                globalXmlParser.saveText(aboutCity.storyRu, aboutCity.storyEn, aboutCity.nameRu, aboutCity.nameEn, TextType.Story, card);
                globalXmlParser.saveText(aboutCity.recommendRu, aboutCity.recommendEn, aboutCity.nameRu, aboutCity.nameEn, TextType.Recomend, card);

                globalXmlParser.saveParameter(aboutCity.phone, card, CardParameterType.Phone);
                globalXmlParser.saveParameter(aboutCity.site, card, CardParameterType.Site);
                globalXmlParser.saveParameter(aboutCity.email, card, CardParameterType.Email);
                globalXmlParser.saveParameter(aboutCity.twitter, card, CardParameterType.Twitter);
                globalXmlParser.saveParameter(aboutCity.frsqr, card, CardParameterType.Frsqr);
                globalXmlParser.saveParameter(aboutCity.liveJournal, card, CardParameterType.LiveJournal);
                globalXmlParser.saveParameter(aboutCity.vkCom, card, CardParameterType.Vkcom);
                globalXmlParser.saveParameter(aboutCity.fbCom, card, CardParameterType.Fbcom);

                try {
                    ImageHelper.saveImages(aboutCity.photo, card, ImageType.Photo);
                } catch (DataIsEmptyException e) {
                    loggerFactory.error("no photo image on card " + aboutCity.nameRu + "[" + aboutCity.id + "]");
                    loggerFactory.error(e);
                }
            }
        }


    }

    public MenuCardLinkEntity savePeopleAboutCityMenuLinks(MenuCardLinkParser menuCardLinkParser, PeopleAboutCity peopleAboutCity, HashSet<String> names, CardEntity cardEntity) {
        for (AboutCity aboutCity : peopleAboutCity.aboutCities) {
            try {
                String nameEN = aboutCity.nameEn;
                String nameRU = aboutCity.nameRu;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = menuCardLinkParser.findMenu(aboutCity.id);
                    if (menuEntity != null) {
                        return menuCardLinkParser.addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }
}
