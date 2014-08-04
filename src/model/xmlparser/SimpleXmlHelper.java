package model.xmlparser;

import model.constants.Component;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.card.cardaboutcity.CardAboutCity;
import model.xmlparser.xmlview.card.cardhandbook.CardHandBook;
import model.xmlparser.xmlview.card.cardhotel.CardHotels;
import model.xmlparser.xmlview.card.cardmeal.CardMeal;
import model.xmlparser.xmlview.card.cardrelax.CardRelax;
import model.xmlparser.xmlview.card.cardroute.CardRoute;
import model.xmlparser.xmlview.card.cardshopping.CardShopping;
import model.xmlparser.xmlview.card.cardsights.CardSight;
import model.xmlparser.xmlview.menu.MenuXML;
import model.xmlparser.xmlview.menu.TagsXML;
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.FileInputStream;
import java.io.IOException;

public class SimpleXmlHelper {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, SimpleXmlHelper.class);

    public TagsXML getTagsXML(String root) {
        return (TagsXML) getXML(root, TagsXML.class);
    }

    public CardAboutCity getCardAboutCity(String root) {
        return (CardAboutCity) getXML(root, CardAboutCity.class);
    }

    public CardHandBook getCardHandBook(String root) {
        return (CardHandBook) getXML(root, CardHandBook.class);
    }

    public CardHotels getCardHotels(String root) {
        return (CardHotels) getXML(root, CardHotels.class);
    }

    public CardMeal getCardMeal(String root) {
        return (CardMeal) getXML(root, CardMeal.class);
    }

    public CardRelax getCardRelax(String root) {
        return (CardRelax) getXML(root, CardRelax.class);
    }

    public CardRoute getCardRoute(String root) {
        return (CardRoute) getXML(root, CardRoute.class);
    }

    public CardShopping getCardShopping(String root) {
        return (CardShopping) getXML(root, CardShopping.class);
    }

    public CardSight getCardSight(String root) {
        return (CardSight) getXML(root, CardSight.class);
    }

    public Object getXML(String root, Class objClass) {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream(root);
//            Persister serializer = new Persister();
            Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
            return serializer.read(objClass, reader, false);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    loggerFactory.error(e);
                }
            }
        }
        return null;
    }


    public PeopleAboutCity getPeopleAboutCity(String root) {
        return (PeopleAboutCity) getXML(root, PeopleAboutCity.class);
    }

    public MenuXML getMenuXML(String root) {
        return (MenuXML) getXML(root, MenuXML.class);
    }
}
