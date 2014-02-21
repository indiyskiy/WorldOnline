package model.xmlparser;

import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.card.cardaboutcity.AboutCity;
import model.xmlparser.xmlview.card.cardaboutcity.CardAboutCity;
import model.xmlparser.xmlview.card.cardhandbook.CardHandBook;
import model.xmlparser.xmlview.card.cardhandbook.HandBook;
import model.xmlparser.xmlview.card.cardhotel.CardHotels;
import model.xmlparser.xmlview.card.cardhotel.Hotel;
import model.xmlparser.xmlview.card.cardmeal.CardMeal;
import model.xmlparser.xmlview.card.cardmeal.Meal;
import model.xmlparser.xmlview.card.cardrelax.CardRelax;
import model.xmlparser.xmlview.card.cardrelax.Relax;
import model.xmlparser.xmlview.card.cardroute.CardRoute;
import model.xmlparser.xmlview.card.cardroute.Route;
import model.xmlparser.xmlview.card.cardshopping.CardShopping;
import model.xmlparser.xmlview.card.cardshopping.Shopping;
import model.xmlparser.xmlview.card.cardsights.CardSight;
import model.xmlparser.xmlview.card.cardsights.Sight;
import model.xmlparser.xmlview.mainmenudata.MainMenuData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 04.02.14
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class MenuCardLinkParser {
    private final static String root = ServerConsts.root;
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, MenuCardLinkParser.class);
    private final HashMap<String, MenuEntity> menuEntityHashMap;
    public ArrayList<MenuCardLinkEntity> menuCardLinkEntities = new ArrayList<MenuCardLinkEntity>();
    private MainMenuData mainMenuData = new MenuParser().getMainMenuData(root + "MainMenuData.xml");

    public MenuCardLinkParser(HashMap<String, MenuEntity> menuEntityHashMap) {
        for (Map.Entry<String, MenuEntity> entry : menuEntityHashMap.entrySet()) {
            loggerFactory.info("map iterate " + entry.getKey() + " ; " + entry.getValue().getMenuID());
        }
        this.menuEntityHashMap = menuEntityHashMap;
    }

    private MenuCardLinkEntity addLink(CardEntity cardEntity, MenuEntity menuEntity) {
        if (menuEntity != null) {
            MenuCardLinkEntity menuCardLinkEntity = new MenuCardLinkEntity();
            menuCardLinkEntity.setCard(cardEntity);
            menuCardLinkEntity.setMenu(menuEntity);
            menuCardLinkEntities.add(menuCardLinkEntity);
//            MenuRequest.addMenuCardLink(menuCardLinkEntity);
            return menuCardLinkEntity;
        }
        return null;
    }

    public void parseMenuCardLink() {
        menuCardLinkEntities.clear();
        CardsParser cardsParser = new CardsParser();
        CardAboutCity cardAboutCity = cardsParser.getCardAboutCity(root + "card_aboutcity.xml");
        CardHandBook cardHandBook = cardsParser.getCardHandBook(root + "card_handbook.xml");
        CardHotels cardHotels = cardsParser.getCardHotels(root + "card_hotels.xml");
        CardMeal cardMeal = cardsParser.getCardMeal(root + "card_meals.xml");
        CardRelax cardRelax = cardsParser.getCardRelax(root + "card_relax.xml");
        CardRoute cardRoute = cardsParser.getCardRoute(root + "card_route.xml");
        CardSight cardSight = cardsParser.getCardSight(root + "card_sights.xml");
        CardShopping cardShopping = cardsParser.getCardShopping(root + "card_shopping.xml");
        ArrayList<CardEntity> cardEntities = CardRequest.getAllCards();
        for (CardEntity cardEntity : cardEntities) {
            try {
                HashSet<String> names = CardRequest.getAllCardNames(cardEntity.getCardID());
                MenuCardLinkEntity menuCardLinkEntity = null;
                if (cardEntity.getCardType() == (CardType.CardShopping.getValue())) {
                    menuCardLinkEntity = saveCardShopping(cardShopping, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardSight.getValue())) {
                    menuCardLinkEntity = saveCardSight(cardSight, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardRoute.getValue())) {
                    menuCardLinkEntity = saveCardRoute(cardRoute, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardRelax.getValue())) {
                    menuCardLinkEntity = saveCardRelax(cardRelax, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardMeal.getValue())) {
                    menuCardLinkEntity = saveCardMeal(cardMeal, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardHotel.getValue())) {
                    menuCardLinkEntity = saveCardHotels(cardHotels, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardHandBook.getValue())) {
                    menuCardLinkEntity = saveCardHandBook(cardHandBook, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardAboutCity.getValue())) {
                    menuCardLinkEntity = saveCardAboutCity(cardAboutCity, names, cardEntity);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        try {
            MenuRequest.addMenuCardLink(menuCardLinkEntities);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private MenuCardLinkEntity saveCardAboutCity(CardAboutCity cardAboutCity, HashSet<String> names, CardEntity cardEntity) {
        for (AboutCity aboutCity : cardAboutCity.aboutCities) {
            String nameEN = aboutCity.nameEN;
            String nameRU = aboutCity.nameRU;
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(aboutCity.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardHotels(CardHotels cardHotels, HashSet<String> names, CardEntity cardEntity) {
        for (Hotel hotel : cardHotels.hotels) {
            String nameEN = hotel.nameEN;
            String nameRU = hotel.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(hotel.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardMeal(CardMeal cardMeal, HashSet<String> names, CardEntity cardEntity) {
        for (Meal meal : cardMeal.meals) {
            String nameEN = meal.nameEN;
            String nameRU = meal.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(meal.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardRelax(CardRelax cardRelax, HashSet<String> names, CardEntity cardEntity) {
        for (Relax relax : cardRelax.relaxes) {
            String nameEN = relax.nameEN;
            String nameRU = relax.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(relax.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardRoute(CardRoute cardRoute, HashSet<String> names, CardEntity cardEntity) {
        for (Route route : cardRoute.routes) {
            String nameEN = route.nameEN;
            String nameRU = route.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(route.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardSight(CardSight cardSight, HashSet<String> names, CardEntity cardEntity) {
        for (Sight sight : cardSight.sights) {
            String nameEN = sight.nameEN;
            String nameRU = sight.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(sight.parentMenuID);
                if (menuEntity != null) {

                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardShopping(CardShopping cardShopping, HashSet<String> names, CardEntity cardEntity) {
        for (Shopping shopping : cardShopping.shoppings) {
            String nameEN = shopping.nameEN;
            String nameRU = shopping.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(shopping.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardHandBook(CardHandBook cardHandBook, HashSet<String> names, CardEntity cardEntity) {
        for (HandBook handBook : cardHandBook.handBooks) {
            String nameEN = handBook.nameEN;
            String nameRU = handBook.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
            if (names.contains(nameEN) || names.contains(nameRU)) {
                MenuEntity menuEntity = findMenu(handBook.parentMenuID);
                if (menuEntity != null) {
                    return addLink(cardEntity, menuEntity);
                } else {
//                    loggerFactory.info("~menu entity for card " + cardEntity.getCardID());
                }
            }
        }
        return null;
    }

    private MenuEntity findMenu(String parentMenuID) {
        MenuEntity menuEntity = menuEntityHashMap.get(parentMenuID);
        loggerFactory.info(parentMenuID + " " + menuEntity.getMenuID());
        return menuEntity;
    }


}
