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
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MenuCardLinkParser {
    private final static String root = ServerConsts.root;
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, MenuCardLinkParser.class);
    public ArrayList<MenuCardLinkEntity> menuCardLinkEntities = new ArrayList<MenuCardLinkEntity>();
    public HashMap<Integer, Integer> menuCardMap = StringFileParser.getIntIntMap(root + "addData/menuCardMap.txt");
    private MainMenuData mainMenuData = new MenuParser().getMainMenuData(root + "MainMenuData.xml");
    private HashMap<String, MenuEntity> menuEntityHashMap = new HashMap<>();

    public MenuCardLinkEntity addLink(CardEntity cardEntity, MenuEntity menuEntity) {
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
        SimpleXmlHelper simpleXmlHelper = new SimpleXmlHelper();
        CardAboutCity cardAboutCity = simpleXmlHelper.getCardAboutCity(root + "card_aboutcity.xml");
        CardHandBook cardHandBook = simpleXmlHelper.getCardHandBook(root + "card_handbook.xml");
        CardHotels cardHotels = simpleXmlHelper.getCardHotels(root + "card_hotels.xml");
        CardMeal cardMeal = simpleXmlHelper.getCardMeal(root + "card_meals.xml");
        CardRelax cardRelax = simpleXmlHelper.getCardRelax(root + "card_relax.xml");
        CardRoute cardRoute = simpleXmlHelper.getCardRoute(root + "card_route.xml");
        CardSight cardSight = simpleXmlHelper.getCardSight(root + "card_sights.xml");
        CardShopping cardShopping = simpleXmlHelper.getCardShopping(root + "card_shopping.xml");
        PeopleAboutCity peopleAboutCity = simpleXmlHelper.getPeopleAboutCity(ServerConsts.root + "people_aboutcity.xml");
        ArrayList<CardEntity> cardEntities = CardRequest.getAllCards();
        for (CardEntity cardEntity : cardEntities) {
            try {
                HashSet<String> names = CardRequest.getAllCardNames(cardEntity.getCardID());
                MenuCardLinkEntity menuCardLinkEntity = null;
                if (cardEntity.getCardType() == (CardType.CardPlace.getValue())) {
                    menuCardLinkEntity = saveCardShopping(cardShopping, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardSight.getValue())) {
                    menuCardLinkEntity = saveCardSight(cardSight, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardRoute.getValue())) {
                    menuCardLinkEntity = saveCardRoute(cardRoute, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardPlace.getValue())) {
                    menuCardLinkEntity = saveCardRelax(cardRelax, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardPlace.getValue())) {
                    menuCardLinkEntity = saveCardMeal(cardMeal, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardPlace.getValue())) {
                    menuCardLinkEntity = saveCardHotels(cardHotels, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardHandBook.getValue())) {
                    menuCardLinkEntity = saveCardHandBook(cardHandBook, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardAboutCity.getValue())) {
                    menuCardLinkEntity = saveCardAboutCity(cardAboutCity, names, cardEntity);
                }
                if (cardEntity.getCardType() == (CardType.CardPerson.getValue())) {
                    menuCardLinkEntity = savePeopleAboutCity(peopleAboutCity, names, cardEntity);
                }
            } catch (Exception e) {
                loggerFactory.error("THE ERROR OF DOOOOM1!!!");
                loggerFactory.error(e);
            }
        }
        try {
            MenuRequest.addMenuCardLink(menuCardLinkEntities);
        } catch (Exception e) {
            loggerFactory.error("THE ERROR OF DOOOOM2!!!");
            loggerFactory.error(e);
        }
    }

    private MenuCardLinkEntity savePeopleAboutCity(PeopleAboutCity peopleAboutCity, HashSet<String> names, CardEntity cardEntity) {
        return new PeopleParser().savePeopleAboutCityMenuLinks(this, peopleAboutCity, names, cardEntity);
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

    public MenuEntity findMenu(String cardID) {
        try {
            if (cardID == null || cardID.isEmpty()) {
                return null;
            }
            if (menuEntityHashMap.containsKey(cardID)) {
                loggerFactory.debug("what it was now?!? 0_o Double cards with same ids");
                return menuEntityHashMap.get(cardID);
            }
            Integer menuID = menuCardMap.get(Integer.parseInt(cardID));
            if (menuID != null && menuID != 0) {
                for (Submenu submenu : mainMenuData.getSubmenus()) {
                    if (submenu != null && submenu.id.equals(String.valueOf(menuID))) {
                        MenuEntity menuEntity = MenuRequest.getMenuByName(submenu.nameEN);
                        if (menuEntity == null) {
                            loggerFactory.error("menu with name " + submenu.nameEN + " not found");
                            menuEntity = MenuRequest.getMenuByName(submenu.nameRU);
                            if (menuEntity == null) {
                                loggerFactory.error("menu with name " + submenu.nameRU + " not found");
                            }
                        } else {
                            loggerFactory.debug("card id=" + cardID +
                                    " menuID=" + menuID +
                                    " submenu id=" + submenu.id +
                                    " submenu name=" + submenu.nameRU +
                                    " menuEntityID=" + menuEntity.getMenuID());
                            menuEntityHashMap.put(cardID, menuEntity);
                            return menuEntity;
                        }
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }


}
