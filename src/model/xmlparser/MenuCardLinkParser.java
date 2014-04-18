package model.xmlparser;

import model.additionalentity.CompleteMenuInfo;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import model.textparser.StringFileParser;
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
import model.xmlparser.xmlview.mainmenudata.Submenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
    public ArrayList<MenuCardLinkEntity> menuCardLinkEntities = new ArrayList<MenuCardLinkEntity>();
    public HashMap<Integer, Integer> menuCardMap = StringFileParser.getIntIntMap(root + "addData/menuCardMap.txt");
    private MainMenuData mainMenuData = new MenuParser().getMainMenuData(root + "MainMenuData.xml");

    private MenuCardLinkEntity addLink(CardEntity cardEntity, MenuEntity menuEntity) {
        if (menuEntity != null && cardEntity != null) {
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
            try {
                String nameEN = aboutCity.nameEN;
                String nameRU = aboutCity.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(aboutCity.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardHotels(CardHotels cardHotels, HashSet<String> names, CardEntity cardEntity) {
        for (Hotel hotel : cardHotels.hotels) {
            try {
                String nameEN = hotel.nameEN;
                String nameRU = hotel.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(hotel.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardMeal(CardMeal cardMeal, HashSet<String> names, CardEntity cardEntity) {
        for (Meal meal : cardMeal.meals) {
            try {
                String nameEN = meal.nameEN;
                String nameRU = meal.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(meal.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardRelax(CardRelax cardRelax, HashSet<String> names, CardEntity cardEntity) {
        for (Relax relax : cardRelax.relaxes) {
            try {
                String nameEN = relax.nameEN;
                String nameRU = relax.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(relax.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardRoute(CardRoute cardRoute, HashSet<String> names, CardEntity cardEntity) {
        for (Route route : cardRoute.routes) {
            try {

                String nameEN = route.nameEN;
                String nameRU = route.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(route.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardSight(CardSight cardSight, HashSet<String> names, CardEntity cardEntity) {
        for (Sight sight : cardSight.sights) {
            try {
                String nameEN = sight.nameEN;
                String nameRU = sight.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(sight.id);
                    if (menuEntity != null) {

                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardShopping(CardShopping cardShopping, HashSet<String> names, CardEntity cardEntity) {
        for (Shopping shopping : cardShopping.shoppings) {
            try {
                String nameEN = shopping.nameEN;
                String nameRU = shopping.nameRU;
//            loggerFactory.info(nameEN + " " + nameRU);
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(shopping.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuCardLinkEntity saveCardHandBook(CardHandBook cardHandBook, HashSet<String> names, CardEntity cardEntity) {
        for (HandBook handBook : cardHandBook.handBooks) {
            try {
                String nameEN = handBook.nameEN;
                String nameRU = handBook.nameRU;
                if (names.contains(nameEN) || names.contains(nameRU)) {
                    MenuEntity menuEntity = findMenu(handBook.id);
                    if (menuEntity != null) {
                        return addLink(cardEntity, menuEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
        return null;
    }

    private MenuEntity findMenu(String cardID) {
        Integer menuID = menuCardMap.get(Integer.parseInt(cardID));
        for (Submenu submenu : mainMenuData.getSubmenus()) {
            if (submenu.id.equals(String.valueOf(menuID))) {
                CompleteMenuInfo completeMenuInfo = MenuRequest.getMenuByName(submenu.nameEN);
                MenuEntity menuEntity = completeMenuInfo.getMenuEntity();
                return menuEntity;
            }
        }
        return null;
    }


}
