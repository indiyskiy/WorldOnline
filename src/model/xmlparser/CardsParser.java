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
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 18.10.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class CardsParser {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, CardsParser.class);

    public CardAboutCity getCardAboutCity(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardAboutCity.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardHandBook getCardHandBook(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardHandBook.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardHotels getCardHotels(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardHotels.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardMeal getCardMeal(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardMeal.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardRelax getCardRelax(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardRelax.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardRoute getCardRoute(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardRoute.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardShopping getCardShopping(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardShopping.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public CardSight getCardSight(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(CardSight.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }
}
