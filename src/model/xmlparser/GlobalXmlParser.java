package model.xmlparser;

import helper.FileHelper;
import helper.ImageHelper;
import helper.ParameterValidator;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.*;
import model.database.requests.*;
import model.database.worldonlinedb.*;
import model.exception.DataIsEmptyException;
import model.logger.LoggerFactory;
import model.textparser.StringFileParser;
import model.textparser.StringIntPair;
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
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;
import model.xmlparser.xmlview.photo.photocard.Photo;
import model.xmlparser.xmlview.photo.photocard.PhotoCard;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalXmlParser implements Runnable {

    public static HashMap<Integer, CardEntity> restaurantChainMap;
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, GlobalXmlParser.class);
    private HashMap<Long, CardEntity> cardEntityHashMap = new HashMap<>();

    public static void parse() {
        new Thread(new GlobalXmlParser()).start();
    }

    public void globalParse() throws IOException, SQLException {
        try {
            //tags
            TagParser.parseTags();
            //main menu
            MenuParser menuParser = new MenuParser();
            menuParser.saveMenu();
//            HashMap<String, MenuEntity> menuEntityHashMap = menuParser.getMenuEntityHashMap();
            //parameter types
            ParameterParser.saveTypes();
            //cards
            SimpleXmlHelper simpleXmlHelper = new SimpleXmlHelper();
            CardAboutCity cardAboutCity = simpleXmlHelper.getCardAboutCity(ServerConsts.root + "card_aboutcity.xml");
            saveCardsAboutCity(cardAboutCity);
            CardHandBook cardHandBook = simpleXmlHelper.getCardHandBook(ServerConsts.root + "card_handbook.xml");
            saveCardHandBook(cardHandBook);
            CardHotels cardHotels = simpleXmlHelper.getCardHotels(ServerConsts.root + "card_hotels.xml");
            saveCardHotels(cardHotels);
            CardMeal cardMeal = simpleXmlHelper.getCardMeal(ServerConsts.root + "card_meals.xml");
            saveCardMeal(cardMeal);
            CardRelax cardRelax = simpleXmlHelper.getCardRelax(ServerConsts.root + "card_relax.xml");
            saveCardRelax(cardRelax);
            CardRoute cardRoute = simpleXmlHelper.getCardRoute(ServerConsts.root + "card_route.xml");
            saveCardRoute(cardRoute);
            CardSight cardSight = simpleXmlHelper.getCardSight(ServerConsts.root + "card_sights.xml");
            saveCardSight(cardSight);
            CardShopping cardShopping = simpleXmlHelper.getCardShopping(ServerConsts.root + "card_shopping.xml");
            saveCardShopping(cardShopping);

            //people
            PeopleParser peopleParser = new PeopleParser();
            PeopleAboutCity peopleAboutCity = peopleParser.getPeopleAboutCity(ServerConsts.root + "people_aboutcity.xml");
            peopleParser.savePeopleAboutCity(this, peopleAboutCity);
            //photo
            PhotoParser photoParser = new PhotoParser();
            PhotoCard photoCard = photoParser.getPhotoCard(ServerConsts.root + "photocards.xml");
            savePhotoCards(photoCard);
            //route
            RouteParser routeParser = new RouteParser();
            RouteRoute routeRoute = routeParser.getRouteRoute(ServerConsts.root + "route_routes.xml");
            new RouteParser().saveRoute(this, routeRoute);
            //menu card link
            MenuCardLinkParser menuCardLinkParser = new MenuCardLinkParser();
            menuCardLinkParser.parseMenuCardLink();
            new DishParser().saveDishes(cardEntityHashMap);
            NewsRequest.addTestNews();
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }


    private void savePhotoCards(PhotoCard photoCard) {
        List<Photo> photos = photoCard.photos;
        for (Photo photo : photos) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(photo.nameRu);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(photo.id), oldCard);
                } else {
                    CardState cardState = CardState.Active;
                    CardEntity card = new CardEntity(CardType.CardPhoto, photo.nameEn, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(photo.id), card);
                    saveText(photo.nameRu, photo.nameEn, photo.nameRu, photo.nameEn, TextType.Name, card);
                    try {
                        ImageHelper.saveImages(photo.fileName, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photocard image on card " + photo.nameRu + "[" + photo.id + "]");
                        loggerFactory.error(e);
                    }
                    if (photo.lat != null && !photo.lat.replaceAll(" ", "").isEmpty() && photo.lon != null && !photo.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(photo.lat, photo.lon, card);
                    }
                    if (cardEntityHashMap.containsKey(Long.parseLong(photo.peopleCard))) {
                        addCardToCardLink(cardEntityHashMap.get(Long.parseLong(photo.peopleCard)), card, CardToCardLinkType.Photographer);
                    }
                    if (cardEntityHashMap.containsKey(Long.parseLong(photo.peopleCard))) {
                        addCardToCardLink(cardEntityHashMap.get(Long.parseLong(photo.peopleCard)), card, CardToCardLinkType.PlaceOnPhoto);
                    }
                    MenuEntity menuEntity = MenuRequest.getPhotoMenu();
                    if (menuEntity != null) {
                        MenuCardLinkEntity menuCardLinkEntity = new MenuCardLinkEntity();
                        menuCardLinkEntity.setCard(card);
                        menuCardLinkEntity.setMenu(menuEntity);
                        MenuRequest.addMenuCardLinkSafe(menuCardLinkEntity);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void saveCardShopping(CardShopping cardShopping) throws IOException, SQLException {
        List<Shopping> shoppingList = cardShopping.shoppings;
        for (Shopping shopping : shoppingList) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(shopping.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(shopping.id), oldCard);
                } else {
                    CardState cardState;
                    if (shopping.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardPlace, shopping.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(shopping.id), card);
                    saveText(shopping.nameRU, shopping.nameEN, shopping.nameRU, shopping.nameEN, TextType.Name, card);
                    saveText(shopping.addrRU, shopping.addrEN, shopping.nameRU, shopping.nameEN, TextType.Address, card);
                    saveText(shopping.descrRU, shopping.descrEN, shopping.nameRU, shopping.nameEN, TextType.Description, card);
                    saveText(shopping.newsRu, shopping.newsEn, shopping.nameRU, shopping.nameEN, TextType.News, card);
                    saveText(shopping.offersRu, shopping.offersEn, shopping.nameRU, shopping.nameEN, TextType.Offers, card);
                    saveMultiplyText(shopping.facts, shopping.factsEn, shopping.nameRU, shopping.nameEN, TextType.Facts, card);
                    saveMultiplyText(shopping.legends, shopping.legendsEn, shopping.nameRU, shopping.nameEN, TextType.Legends, card);
                    saveMultiplyText(shopping.literature, shopping.literatureEn, shopping.nameRU, shopping.nameEN, TextType.Literature, card);
                    saveMultiplyText(shopping.anecdotes, shopping.anecdotesEn, shopping.nameRU, shopping.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(shopping.films, shopping.filmsEn, shopping.nameRU, shopping.nameEN, TextType.Films, card);
                    saveMultiplyText(shopping.famousPassers, shopping.famousPassersEn, shopping.nameRU, shopping.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(shopping.citations, shopping.citationsEn, shopping.nameRU, shopping.nameEN, TextType.Citations, card);
                    saveText(shopping.wikipedia, shopping.wikipediaEn, shopping.nameRU, shopping.nameEN, TextType.Wikipedia, card);
                    saveText(shopping.wikimapiaRu, shopping.wikimapiaEn, shopping.nameRU, shopping.nameEN, TextType.Wikimapia, card);
                    saveText(shopping.encspbRu, shopping.encspbEn, shopping.nameRU, shopping.nameEN, TextType.Encspb, card);


                    if (shopping.lat != null && !shopping.lat.replaceAll(" ", "").isEmpty() && shopping.lon != null && !shopping.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(shopping.lat, shopping.lon, card);
                    }

                    saveParameter(shopping.phone, card, CardParameterType.Phone);
                    saveParameter(shopping.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(shopping.openHours, card, CardParameterType.OpenHours);
                    saveParameter(shopping.site, card, CardParameterType.Site);
                    saveParameter(shopping.email, card, CardParameterType.Email);
                    saveParameter(shopping.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(shopping.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(shopping.twitter, card, CardParameterType.Twitter);
                    saveParameter(shopping.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(shopping.booking, card, CardParameterType.Booking);
                    saveParameter(shopping.youtube, card, CardParameterType.Youtube);
                    saveParameter(shopping.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(shopping.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(shopping.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(shopping.appStore, card, CardParameterType.AppStore);
                    saveParameter(shopping.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(shopping.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(shopping.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(shopping.billboard, card, CardParameterType.Billboard);

                    saveParameter(shopping.apoi, card, CardParameterType.Apoi);
                    saveParameter(shopping.ayda, card, CardParameterType.Ayda);
                    saveParameter(shopping.blogFcsSpb, card, CardParameterType.BlogFcsSpb);
                    saveParameter(shopping.cafeSpb, card, CardParameterType.CafeSpb);
                    saveParameter(shopping.dpRu, card, CardParameterType.DpRu);
                    saveParameter(shopping.flamp, card, CardParameterType.Flamp);
                    saveParameter(shopping.imhoNet, card, CardParameterType.ImhoNet);
                    saveParameter(shopping.iRecommend, card, CardParameterType.IRecommend);
                    saveParameter(shopping.komandirovka, card, CardParameterType.Komandirovka);
                    saveParameter(shopping.menuRu, card, CardParameterType.MenuRu);
                    saveParameter(shopping.otzovik, card, CardParameterType.Otzovik);
                    saveParameter(shopping.peterburgRu, card, CardParameterType.PeterburgRu);
                    saveParameter(shopping.restlook, card, CardParameterType.Restlook);
                    saveParameter(shopping.restop, card, CardParameterType.Restop);
                    saveParameter(shopping.restoran, card, CardParameterType.Restoran);
                    saveParameter(shopping.restozoom, card, CardParameterType.Restozoom);
                    saveParameter(shopping.spbrestoran, card, CardParameterType.Spbrestoran);
                    saveParameter(shopping.szoSpr, card, CardParameterType.SzoSpr);
                    saveParameter(shopping.theVillage, card, CardParameterType.TheVillage);
                    saveParameter(shopping.tourprom, card, CardParameterType.Tourprom);
                    saveParameter(shopping.traveltipz, card, CardParameterType.Traveltipz);
                    saveParameter(shopping.tulp, card, CardParameterType.Tulp);
                    saveParameter(shopping.turbina, card, CardParameterType.Turbina);
                    saveParameter(shopping.uvoice, card, CardParameterType.Uvoice);
                    saveParameter(shopping.wrestorane, card, CardParameterType.Wrestorane);
                    saveParameter(shopping.yell, card, CardParameterType.Yell);
                    saveParameter(shopping.zoon, card, CardParameterType.Zoon);

                    String freePass = "0";
                    if ("yes".equalsIgnoreCase(shopping.freePass)) {
                        freePass = "100";
                    }

                    saveParameter(shopping.borisStars, card, CardParameterType.BorisStars);
                    saveParameter(shopping.restoclub, card, CardParameterType.Restoclub);
                    saveParameter(shopping.afisha, card, CardParameterType.Afisha);
                    saveParameter(shopping.timeout, card, CardParameterType.Timeout);
                    saveParameter(shopping.wikitravel, card, CardParameterType.Wikitravel);
                    saveParameter(shopping.allCafe, card, CardParameterType.AllCafe);
                    saveParameter(shopping.walkspb, card, CardParameterType.WalkSpb);
                    saveParameter(shopping.spbin, card, CardParameterType.Spbin);


                    TagParser.saveCardTags(shopping.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(shopping.categories, card, TagType.Categories);
                    TagParser.saveCardTags(shopping.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(shopping.freePass, card);
                    TagParser.saveCardMiddlePrice(shopping.middlePrice, card);
                    TagParser.saveCardNoBarrier(shopping.noBarriers, card);
                    try {
                        ImageHelper.saveImages(shopping.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + shopping.nameRU + "[" + shopping.id + "]");
                        loggerFactory.error(e);
                    }
          /*  try {
                ImageHelper.saveImages(shopping.panoramaToList, card, ImageType.PanoramaToList);
            } catch (DataIsEmptyException e) {
                loggerFactory.error("no panoramaToList image on card " + shopping.nameRU + "[" + shopping.id + "]");
                loggerFactory.error(e);
            }*/
           /* try {
                ImageHelper.saveImages(shopping.panorama, card, ImageType.Panorama);
            } catch (DataIsEmptyException e) {
                loggerFactory.error("no panorama image on card " + shopping.nameRU + "[" + shopping.id + "]");
                loggerFactory.error(e);
            }*/
          /*  try {
                ImageHelper.saveImages(shopping.cardImage, card, ImageType.CardImage);
            } catch (DataIsEmptyException e) {
                loggerFactory.error("no cardImage image on card " + shopping.nameRU + "[" + shopping.id + "]");
                loggerFactory.error(e);
            }*/
                    addRestarauntChainLink(shopping.restaurantChain, card);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }


    private void saveCardSight(CardSight cardSight) throws IOException, SQLException {
        List<Sight> sights = cardSight.sights;
        for (Sight sight : sights) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(sight.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(sight.id), oldCard);
                } else {
                    CardState cardState;
                    if (sight.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardSight, sight.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(sight.id), card);
                    saveText(sight.nameRU, sight.nameEN, sight.nameRU, sight.nameEN, TextType.Name, card);
                    saveText(sight.addrRU, sight.addrEN, sight.nameRU, sight.nameEN, TextType.Address, card);
                    saveText(sight.descrRU, sight.descrEN, sight.nameRU, sight.nameEN, TextType.Description, card);
                    saveText(sight.newsRu, sight.newsEn, sight.nameRU, sight.nameEN, TextType.News, card);
                    saveText(sight.offersRu, sight.offersEn, sight.nameRU, sight.nameEN, TextType.Offers, card);
                    saveMultiplyText(sight.facts, sight.factsEn, sight.nameRU, sight.nameEN, TextType.Facts, card);
                    saveMultiplyText(sight.legends, sight.legendsEn, sight.nameRU, sight.nameEN, TextType.Legends, card);
                    saveMultiplyText(sight.literature, sight.literatureEn, sight.nameRU, sight.nameEN, TextType.Literature, card);
                    saveMultiplyText(sight.anecdotes, sight.anecdotesEn, sight.nameRU, sight.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(sight.films, sight.filmsEn, sight.nameRU, sight.nameEN, TextType.Films, card);
                    saveMultiplyText(sight.famousPassers, sight.famousPassersEn, sight.nameRU, sight.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(sight.citations, sight.citationsEn, sight.nameRU, sight.nameEN, TextType.Citations, card);
                    saveText(sight.wikipedia, sight.wikipediaEn, sight.nameRU, sight.nameEN, TextType.Wikipedia, card);
                    saveText(sight.wikimapiaRu, sight.wikimapiaEn, sight.nameRU, sight.nameEN, TextType.Wikimapia, card);
                    saveText(sight.encspbRu, sight.encspbEn, sight.nameRU, sight.nameEN, TextType.Encspb, card);

                    if (sight.lat != null && !sight.lat.replaceAll(" ", "").isEmpty() && sight.lon != null && !sight.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(sight.lat, sight.lon, card);
                    }

                    saveParameter(sight.phone, card, CardParameterType.Phone);
                    saveParameter(sight.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(sight.openHours, card, CardParameterType.OpenHours);
                    saveParameter(sight.site, card, CardParameterType.Site);
                    saveParameter(sight.email, card, CardParameterType.Email);
                    saveParameter(sight.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(sight.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(sight.twitter, card, CardParameterType.Twitter);
                    saveParameter(sight.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(sight.booking, card, CardParameterType.Booking);
                    saveParameter(sight.youtube, card, CardParameterType.Youtube);
                    saveParameter(sight.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(sight.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(sight.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(sight.appStore, card, CardParameterType.AppStore);
                    saveParameter(sight.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(sight.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(sight.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(sight.billboard, card, CardParameterType.Billboard);

                    saveParameter(sight.apoi, card, CardParameterType.Apoi);
                    saveParameter(sight.ayda, card, CardParameterType.Ayda);
                    saveParameter(sight.blogFcsSpb, card, CardParameterType.BlogFcsSpb);
                    saveParameter(sight.cafeSpb, card, CardParameterType.CafeSpb);
                    saveParameter(sight.dpRu, card, CardParameterType.DpRu);
                    saveParameter(sight.flamp, card, CardParameterType.Flamp);
                    saveParameter(sight.imhoNet, card, CardParameterType.ImhoNet);
                    saveParameter(sight.iRecommend, card, CardParameterType.IRecommend);
                    saveParameter(sight.komandirovka, card, CardParameterType.Komandirovka);
                    saveParameter(sight.menuRu, card, CardParameterType.MenuRu);
                    saveParameter(sight.otzovik, card, CardParameterType.Otzovik);
                    saveParameter(sight.peterburgRu, card, CardParameterType.PeterburgRu);
                    saveParameter(sight.restlook, card, CardParameterType.Restlook);
                    saveParameter(sight.restop, card, CardParameterType.Restop);
                    saveParameter(sight.restoran, card, CardParameterType.Restoran);
                    saveParameter(sight.restozoom, card, CardParameterType.Restozoom);
                    saveParameter(sight.spbrestoran, card, CardParameterType.Spbrestoran);
                    saveParameter(sight.szoSpr, card, CardParameterType.SzoSpr);
                    saveParameter(sight.theVillage, card, CardParameterType.TheVillage);
                    saveParameter(sight.tourprom, card, CardParameterType.Tourprom);
                    saveParameter(sight.traveltipz, card, CardParameterType.Traveltipz);
                    saveParameter(sight.tulp, card, CardParameterType.Tulp);
                    saveParameter(sight.turbina, card, CardParameterType.Turbina);
                    saveParameter(sight.uvoice, card, CardParameterType.Uvoice);
                    saveParameter(sight.wrestorane, card, CardParameterType.Wrestorane);
                    saveParameter(sight.yell, card, CardParameterType.Yell);
                    saveParameter(sight.zoon, card, CardParameterType.Zoon);

                    String freePass = "0";
                    if ("yes".equalsIgnoreCase(sight.freePass)) {
                        freePass = "100";
                    }

                    saveParameter(sight.borisStars, card, CardParameterType.BorisStars);
                    saveParameter(sight.restoclub, card, CardParameterType.Restoclub);
                    saveParameter(sight.afisha, card, CardParameterType.Afisha);
                    saveParameter(sight.timeout, card, CardParameterType.Timeout);
                    saveParameter(sight.wikitravel, card, CardParameterType.Wikitravel);
                    saveParameter(sight.allCafe, card, CardParameterType.AllCafe);

                    TagParser.saveCardTags(sight.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(sight.categories, card, TagType.Categories);
                    TagParser.saveCardTags(sight.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(sight.freePass, card);
                    TagParser.saveCardMiddlePrice(sight.middlePrice, card);
                    TagParser.saveCardNoBarrier(sight.noBarriers, card);

                    try {
                        ImageHelper.saveImages(sight.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + sight.nameRU + "[" + sight.id + "]");
                        loggerFactory.error(e);
                    }
                    addRestarauntChainLink(sight.restaurantChain, card);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }

//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void saveCardRoute(CardRoute cardRoute) throws IOException, SQLException {
        List<Route> routes = cardRoute.routes;
        for (Route route : routes) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(route.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(route.id), oldCard);
                } else {
                    CardState cardState;
                    if (route.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardRoute, route.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(route.id), card);
                    saveText(route.nameRU, route.nameEN, route.nameRU, route.nameEN, TextType.Name, card);
                    saveText(route.addrRU, route.addrEN, route.nameRU, route.nameEN, TextType.Address, card);
                    saveText(route.descrRU, route.descrEN, route.nameRU, route.nameEN, TextType.Description, card);
                    saveText(route.newsRu, route.newsEn, route.nameRU, route.nameEN, TextType.News, card);
                    saveText(route.offersRu, route.offersEn, route.nameRU, route.nameEN, TextType.Offers, card);
                    saveMultiplyText(route.facts, route.factsEn, route.nameRU, route.nameEN, TextType.Facts, card);
                    saveMultiplyText(route.legends, route.legendsEn, route.nameRU, route.nameEN, TextType.Legends, card);
                    saveMultiplyText(route.literature, route.literatureEn, route.nameRU, route.nameEN, TextType.Literature, card);
                    saveMultiplyText(route.anecdotes, route.anecdotesEn, route.nameRU, route.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(route.films, route.filmsEn, route.nameRU, route.nameEN, TextType.Films, card);
                    saveMultiplyText(route.famousPassers, route.famousPassersEn, route.nameRU, route.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(route.citations, route.citationsEn, route.nameRU, route.nameEN, TextType.Citations, card);
                    saveText(route.wikipedia, route.wikipediaEn, route.nameRU, route.nameEN, TextType.Wikipedia, card);
                    saveText(route.wikimapiaRu, route.wikimapiaEn, route.nameRU, route.nameEN, TextType.Wikimapia, card);
                    saveText(route.encspbRu, route.encspbEn, route.nameRU, route.nameEN, TextType.Encspb, card);

                    if (route.lat != null && !route.lat.replaceAll(" ", "").isEmpty() && route.lon != null && !route.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(route.lat, route.lon, card);
                    }

                    saveParameter(route.phone, card, CardParameterType.Phone);
                    saveParameter(route.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(route.openHours, card, CardParameterType.OpenHours);
                    saveParameter(route.site, card, CardParameterType.Site);
                    saveParameter(route.email, card, CardParameterType.Email);
                    saveParameter(route.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(route.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(route.twitter, card, CardParameterType.Twitter);
                    saveParameter(route.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(route.booking, card, CardParameterType.Booking);
                    saveParameter(route.youtube, card, CardParameterType.Youtube);
                    saveParameter(route.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(route.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(route.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(route.appStore, card, CardParameterType.AppStore);
                    saveParameter(route.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(route.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(route.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(route.billboard, card, CardParameterType.Billboard);

                    saveParameter(route.apoi, card, CardParameterType.Apoi);
                    saveParameter(route.ayda, card, CardParameterType.Ayda);
                    saveParameter(route.blogFcsSpb, card, CardParameterType.BlogFcsSpb);
                    saveParameter(route.cafeSpb, card, CardParameterType.CafeSpb);
                    saveParameter(route.dpRu, card, CardParameterType.DpRu);
                    saveParameter(route.flamp, card, CardParameterType.Flamp);
                    saveParameter(route.imhoNet, card, CardParameterType.ImhoNet);
                    saveParameter(route.iRecommend, card, CardParameterType.IRecommend);
                    saveParameter(route.komandirovka, card, CardParameterType.Komandirovka);
                    saveParameter(route.menuRu, card, CardParameterType.MenuRu);
                    saveParameter(route.otzovik, card, CardParameterType.Otzovik);
                    saveParameter(route.peterburgRu, card, CardParameterType.PeterburgRu);
                    saveParameter(route.restlook, card, CardParameterType.Restlook);
                    saveParameter(route.restop, card, CardParameterType.Restop);
                    saveParameter(route.restoran, card, CardParameterType.Restoran);
                    saveParameter(route.restozoom, card, CardParameterType.Restozoom);
                    saveParameter(route.spbrestoran, card, CardParameterType.Spbrestoran);
                    saveParameter(route.szoSpr, card, CardParameterType.SzoSpr);
                    saveParameter(route.theVillage, card, CardParameterType.TheVillage);
                    saveParameter(route.tourprom, card, CardParameterType.Tourprom);
                    saveParameter(route.traveltipz, card, CardParameterType.Traveltipz);
                    saveParameter(route.tulp, card, CardParameterType.Tulp);
                    saveParameter(route.turbina, card, CardParameterType.Turbina);
                    saveParameter(route.uvoice, card, CardParameterType.Uvoice);
                    saveParameter(route.wrestorane, card, CardParameterType.Wrestorane);
                    saveParameter(route.yell, card, CardParameterType.Yell);
                    saveParameter(route.zoon, card, CardParameterType.Zoon);

                    String freePass = "0";
                    if ("yes".equalsIgnoreCase(route.freePass)) {
                        freePass = "100";
                    }
                    saveParameter(route.borisStars, card, CardParameterType.BorisStars);
                    saveParameter(route.restoclub, card, CardParameterType.Restoclub);
                    saveParameter(route.afisha, card, CardParameterType.Afisha);
                    saveParameter(route.timeout, card, CardParameterType.Timeout);
                    saveParameter(route.wikitravel, card, CardParameterType.Wikitravel);
                    saveParameter(route.allCafe, card, CardParameterType.AllCafe);

                    TagParser.saveCardTags(route.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(route.categories, card, TagType.Categories);
                    TagParser.saveCardTags(route.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(route.freePass, card);
                    TagParser.saveCardMiddlePrice(route.middlePrice, card);
                    TagParser.saveCardNoBarrier(route.noBarriers, card);

                    try {
                        ImageHelper.saveImages(route.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + route.nameRU + "[" + route.id + "]");
                        loggerFactory.error(e);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void saveCardRelax(CardRelax cardRelax) throws IOException, SQLException {
        List<Relax> relaxes = cardRelax.relaxes;
        for (Relax relax : relaxes) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(relax.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(relax.id), oldCard);
                } else {
                    CardState cardState;
                    if (relax.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardPlace, relax.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(relax.id), card);
                    saveText(relax.nameRU, relax.nameEN, relax.nameRU, relax.nameEN, TextType.Name, card);
                    saveText(relax.addrRU, relax.addrEN, relax.nameRU, relax.nameEN, TextType.Address, card);
                    saveText(relax.descrRU, relax.descrEN, relax.nameRU, relax.nameEN, TextType.Description, card);
                    saveText(relax.newsRu, relax.newsEn, relax.nameRU, relax.nameEN, TextType.News, card);
                    saveText(relax.offersRu, relax.offersEn, relax.nameRU, relax.nameEN, TextType.Offers, card);
                    saveMultiplyText(relax.facts, relax.factsEn, relax.nameRU, relax.nameEN, TextType.Facts, card);
                    saveMultiplyText(relax.legends, relax.legendsEn, relax.nameRU, relax.nameEN, TextType.Legends, card);
                    saveMultiplyText(relax.literature, relax.literatureEn, relax.nameRU, relax.nameEN, TextType.Literature, card);
                    saveMultiplyText(relax.anecdotes, relax.anecdotesEn, relax.nameRU, relax.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(relax.films, relax.filmsEn, relax.nameRU, relax.nameEN, TextType.Films, card);
                    saveMultiplyText(relax.famousPassers, relax.famousPassersEn, relax.nameRU, relax.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(relax.citations, relax.citationsEn, relax.nameRU, relax.nameEN, TextType.Citations, card);
                    saveText(relax.wikipedia, relax.wikipediaEn, relax.nameRU, relax.nameEN, TextType.Wikipedia, card);
                    saveText(relax.wikimapiaRu, relax.wikimapiaEn, relax.nameRU, relax.nameEN, TextType.Wikimapia, card);
                    saveText(relax.encspbRu, relax.encspbEn, relax.nameRU, relax.nameEN, TextType.Encspb, card);

                    if (relax.lat != null && !relax.lat.replaceAll(" ", "").isEmpty() && relax.lon != null && !relax.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(relax.lat, relax.lon, card);
                    }

                    saveParameter(relax.phone, card, CardParameterType.Phone);
                    saveParameter(relax.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(relax.openHours, card, CardParameterType.OpenHours);
                    saveParameter(relax.site, card, CardParameterType.Site);
                    saveParameter(relax.email, card, CardParameterType.Email);
                    saveParameter(relax.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(relax.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(relax.twitter, card, CardParameterType.Twitter);
                    saveParameter(relax.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(relax.booking, card, CardParameterType.Booking);
                    saveParameter(relax.youtube, card, CardParameterType.Youtube);
                    saveParameter(relax.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(relax.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(relax.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(relax.appStore, card, CardParameterType.AppStore);
                    saveParameter(relax.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(relax.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(relax.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(relax.billboard, card, CardParameterType.Billboard);

                    saveParameter(relax.apoi, card, CardParameterType.Apoi);
                    saveParameter(relax.ayda, card, CardParameterType.Ayda);
                    saveParameter(relax.blogFcsSpb, card, CardParameterType.BlogFcsSpb);
                    saveParameter(relax.cafeSpb, card, CardParameterType.CafeSpb);
                    saveParameter(relax.dpRu, card, CardParameterType.DpRu);
                    saveParameter(relax.flamp, card, CardParameterType.Flamp);
                    saveParameter(relax.imhoNet, card, CardParameterType.ImhoNet);
                    saveParameter(relax.iRecommend, card, CardParameterType.IRecommend);
                    saveParameter(relax.komandirovka, card, CardParameterType.Komandirovka);
                    saveParameter(relax.menuRu, card, CardParameterType.MenuRu);
                    saveParameter(relax.otzovik, card, CardParameterType.Otzovik);
                    saveParameter(relax.peterburgRu, card, CardParameterType.PeterburgRu);
                    saveParameter(relax.restlook, card, CardParameterType.Restlook);
                    saveParameter(relax.restop, card, CardParameterType.Restop);
                    saveParameter(relax.restoran, card, CardParameterType.Restoran);
                    saveParameter(relax.restozoom, card, CardParameterType.Restozoom);
                    saveParameter(relax.spbrestoran, card, CardParameterType.Spbrestoran);
                    saveParameter(relax.szoSpr, card, CardParameterType.SzoSpr);
                    saveParameter(relax.theVillage, card, CardParameterType.TheVillage);
                    saveParameter(relax.tourprom, card, CardParameterType.Tourprom);
                    saveParameter(relax.traveltipz, card, CardParameterType.Traveltipz);
                    saveParameter(relax.tulp, card, CardParameterType.Tulp);
                    saveParameter(relax.turbina, card, CardParameterType.Turbina);
                    saveParameter(relax.uvoice, card, CardParameterType.Uvoice);
                    saveParameter(relax.wrestorane, card, CardParameterType.Wrestorane);
                    saveParameter(relax.yell, card, CardParameterType.Yell);
                    saveParameter(relax.zoon, card, CardParameterType.Zoon);

                    String freePass = "0";
                    if ("yes".equalsIgnoreCase(relax.freePass)) {
                        freePass = "100";
                    }
                    saveParameter(relax.borisStars, card, CardParameterType.BorisStars);
                    saveParameter(relax.restoclub, card, CardParameterType.Restoclub);
                    saveParameter(relax.afisha, card, CardParameterType.Afisha);
                    saveParameter(relax.timeout, card, CardParameterType.Timeout);
                    saveParameter(relax.wikitravel, card, CardParameterType.Wikitravel);
                    saveParameter(relax.allCafe, card, CardParameterType.AllCafe);

                    TagParser.saveCardTags(relax.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(relax.categories, card, TagType.Categories);
                    TagParser.saveCardTags(relax.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(relax.freePass, card);
                    TagParser.saveCardMiddlePrice(relax.middlePrice, card);
                    TagParser.saveCardNoBarrier(relax.noBarriers, card);

                    try {
                        ImageHelper.saveImages(relax.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + relax.nameRU + "[" + relax.id + "]");
                        loggerFactory.error(e);
                    }
                    addRestarauntChainLink(relax.restaurantChain, card);
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void saveCardMeal(CardMeal cardMeal) throws SQLException, IOException {
        List<Meal> meals = cardMeal.meals;
        for (Meal meal : meals) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(meal.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(meal.id), oldCard);
                } else {
                    CardState cardState;
                    if (meal.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardPlace, meal.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(meal.id), card);
                    saveText(meal.nameRU, meal.nameEN, meal.nameRU, meal.nameEN, TextType.Name, card);
                    saveText(meal.addrRU, meal.addrEN, meal.nameRU, meal.nameEN, TextType.Address, card);
                    saveText(meal.descrRU, meal.descrEN, meal.nameRU, meal.nameEN, TextType.Description, card);
                    saveText(meal.newsRu, meal.newsEn, meal.nameRU, meal.nameEN, TextType.News, card);
                    saveText(meal.offersRu, meal.offersEn, meal.nameRU, meal.nameEN, TextType.Offers, card);
                    saveMultiplyText(meal.facts, meal.factsEn, meal.nameRU, meal.nameEN, TextType.Facts, card);
                    saveMultiplyText(meal.legends, meal.legendsEn, meal.nameRU, meal.nameEN, TextType.Legends, card);
                    saveMultiplyText(meal.literature, meal.literatureEn, meal.nameRU, meal.nameEN, TextType.Literature, card);
                    saveMultiplyText(meal.anecdotes, meal.anecdotesEn, meal.nameRU, meal.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(meal.films, meal.filmsEn, meal.nameRU, meal.nameEN, TextType.Films, card);
                    saveMultiplyText(meal.famousPassers, meal.famousPassersEn, meal.nameRU, meal.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(meal.citations, meal.citationsEn, meal.nameRU, meal.nameEN, TextType.Citations, card);
                    saveText(meal.wikipedia, meal.wikipediaEn, meal.nameRU, meal.nameEN, TextType.Wikipedia, card);
                    saveText(meal.wikimapiaRu, meal.wikimapiaEn, meal.nameRU, meal.nameEN, TextType.Wikimapia, card);
                    saveText(meal.encspbRu, meal.encspbEn, meal.nameRU, meal.nameEN, TextType.Encspb, card);

                    if (meal.lat != null && !meal.lat.replaceAll(" ", "").isEmpty() && meal.lon != null && !meal.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(meal.lat, meal.lon, card);
                    }

                    saveParameter(meal.phone, card, CardParameterType.Phone);
                    saveParameter(meal.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(meal.openHours, card, CardParameterType.OpenHours);
                    saveParameter(meal.site, card, CardParameterType.Site);
                    saveParameter(meal.email, card, CardParameterType.Email);
                    saveParameter(meal.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(meal.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(meal.twitter, card, CardParameterType.Twitter);
                    saveParameter(meal.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(meal.booking, card, CardParameterType.Booking);
                    saveParameter(meal.youtube, card, CardParameterType.Youtube);
                    saveParameter(meal.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(meal.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(meal.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(meal.appStore, card, CardParameterType.AppStore);
                    saveParameter(meal.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(meal.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(meal.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(meal.billboard, card, CardParameterType.Billboard);

                    saveParameter(meal.apoi, card, CardParameterType.Apoi);
                    saveParameter(meal.ayda, card, CardParameterType.Ayda);
                    saveParameter(meal.blogFcsSpb, card, CardParameterType.BlogFcsSpb);
                    saveParameter(meal.cafeSpb, card, CardParameterType.CafeSpb);
                    saveParameter(meal.dpRu, card, CardParameterType.DpRu);
                    saveParameter(meal.flamp, card, CardParameterType.Flamp);
                    saveParameter(meal.imhoNet, card, CardParameterType.ImhoNet);
                    saveParameter(meal.iRecommend, card, CardParameterType.IRecommend);
                    saveParameter(meal.komandirovka, card, CardParameterType.Komandirovka);
                    saveParameter(meal.menuRu, card, CardParameterType.MenuRu);
                    saveParameter(meal.otzovik, card, CardParameterType.Otzovik);
                    saveParameter(meal.peterburgRu, card, CardParameterType.PeterburgRu);
                    saveParameter(meal.restlook, card, CardParameterType.Restlook);
                    saveParameter(meal.restop, card, CardParameterType.Restop);
                    saveParameter(meal.restoran, card, CardParameterType.Restoran);
                    saveParameter(meal.restozoom, card, CardParameterType.Restozoom);
                    saveParameter(meal.spbrestoran, card, CardParameterType.Spbrestoran);
                    saveParameter(meal.szoSpr, card, CardParameterType.SzoSpr);
                    saveParameter(meal.theVillage, card, CardParameterType.TheVillage);
                    saveParameter(meal.tourprom, card, CardParameterType.Tourprom);
                    saveParameter(meal.traveltipz, card, CardParameterType.Traveltipz);
                    saveParameter(meal.tulp, card, CardParameterType.Tulp);
                    saveParameter(meal.turbina, card, CardParameterType.Turbina);
                    saveParameter(meal.uvoice, card, CardParameterType.Uvoice);
                    saveParameter(meal.wrestorane, card, CardParameterType.Wrestorane);
                    saveParameter(meal.yell, card, CardParameterType.Yell);
                    saveParameter(meal.zoon, card, CardParameterType.Zoon);

                    String freePass = "0";
                    if ("yes".equalsIgnoreCase(meal.freePass)) {
                        freePass = "100";
                    }
                    saveParameter(meal.borisStars, card, CardParameterType.BorisStars);
                    saveParameter(meal.restoclub, card, CardParameterType.Restoclub);
                    saveParameter(meal.afisha, card, CardParameterType.Afisha);
                    saveParameter(meal.timeout, card, CardParameterType.Timeout);
                    saveParameter(meal.wikitravel, card, CardParameterType.Wikitravel);
                    saveParameter(meal.allCafe, card, CardParameterType.AllCafe);

                    TagParser.saveCardTags(meal.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(meal.categories, card, TagType.Categories);
                    TagParser.saveCardTags(meal.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(meal.freePass, card);
                    TagParser.saveCardMiddlePrice(meal.middlePrice, card);
                    TagParser.saveCardNoBarrier(meal.noBarriers, card);

                    try {
                        ImageHelper.saveImages(meal.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + meal.nameRU + "[" + meal.id + "]");
                        loggerFactory.error(e);
                    }
                    addRestarauntChainLink(meal.restaurantChain, card);
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void saveCardHotels(CardHotels cardHotels) throws SQLException, IOException {
        List<Hotel> hotels = cardHotels.hotels;
        for (Hotel hotel : hotels) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(hotel.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(hotel.id), oldCard);
                } else {
                    CardState cardState;
                    if (hotel.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardPlace, hotel.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(hotel.id), card);
                    saveText(hotel.nameRU, hotel.nameEN, hotel.nameRU, hotel.nameEN, TextType.Name, card);
                    saveText(hotel.addrRU, hotel.addrEN, hotel.nameRU, hotel.nameEN, TextType.Address, card);
                    saveText(hotel.descrRU, hotel.descrEN, hotel.nameRU, hotel.nameEN, TextType.Description, card);
                    saveText(hotel.newsRu, hotel.newsEn, hotel.nameRU, hotel.nameEN, TextType.News, card);
                    saveText(hotel.offersRu, hotel.offersEn, hotel.nameRU, hotel.nameEN, TextType.Offers, card);
                    saveMultiplyText(hotel.facts, hotel.factsEn, hotel.nameRU, hotel.nameEN, TextType.Facts, card);
                    saveMultiplyText(hotel.legends, hotel.legendsEn, hotel.nameRU, hotel.nameEN, TextType.Legends, card);
                    saveMultiplyText(hotel.literature, hotel.literatureEn, hotel.nameRU, hotel.nameEN, TextType.Literature, card);
                    saveMultiplyText(hotel.anecdotes, hotel.anecdotesEn, hotel.nameRU, hotel.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(hotel.films, hotel.filmsEn, hotel.nameRU, hotel.nameEN, TextType.Films, card);
                    saveMultiplyText(hotel.famousPassers, hotel.famousPassersEn, hotel.nameRU, hotel.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(hotel.citations, hotel.citationsEn, hotel.nameRU, hotel.nameEN, TextType.Citations, card);
                    saveText(hotel.wikipedia, hotel.wikipediaEn, hotel.nameRU, hotel.nameEN, TextType.Wikipedia, card);
                    saveText(hotel.wikimapiaRu, hotel.wikimapiaEn, hotel.nameRU, hotel.nameEN, TextType.Wikimapia, card);
                    saveText(hotel.encspbRu, hotel.encspbEn, hotel.nameRU, hotel.nameEN, TextType.Encspb, card);

                    if (hotel.lat != null && !hotel.lat.replaceAll(" ", "").isEmpty() && hotel.lon != null && !hotel.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(hotel.lat, hotel.lon, card);
                    }

                    saveParameter(hotel.phone, card, CardParameterType.Phone);
                    saveParameter(hotel.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(hotel.openHours, card, CardParameterType.OpenHours);
                    saveParameter(hotel.site, card, CardParameterType.Site);
                    saveParameter(hotel.email, card, CardParameterType.Email);
                    saveParameter(hotel.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(hotel.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(hotel.twitter, card, CardParameterType.Twitter);
                    saveParameter(hotel.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(hotel.booking, card, CardParameterType.Booking);
                    saveParameter(hotel.youtube, card, CardParameterType.Youtube);
                    saveParameter(hotel.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(hotel.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(hotel.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(hotel.appStore, card, CardParameterType.AppStore);
                    saveParameter(hotel.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(hotel.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(hotel.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(hotel.billboard, card, CardParameterType.Billboard);

                    TagParser.saveCardTags(hotel.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(hotel.categories, card, TagType.Categories);
                    TagParser.saveCardTags(hotel.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(hotel.freePass, card);
                    TagParser.saveCardMiddlePrice(hotel.middlePrice, card);
                    TagParser.saveCardNoBarrier(hotel.noBarriers, card);

                    try {
                        ImageHelper.saveImages(hotel.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + hotel.nameRU + "[" + hotel.id + "]");
                        loggerFactory.error(e);
                    }
                    addRestarauntChainLink(hotel.restaurantChain, card);
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void saveMultiplyText(String multiplyTextEn, String multiplyTextRu, String nameRU, String nameEN, TextType textType, CardEntity card) {
        String[] arrayRu;
        String[] arrayEn;
        if (multiplyTextRu != null) {
            arrayRu = multiplyTextRu.split("\\*\\*\\*");
        } else {
            return;
        }
        if (multiplyTextEn != null) {
            arrayEn = multiplyTextEn.split("\\*\\*\\*");
        } else {
            loggerFactory.error("card " + nameEN + " has untranslated " + textType);
            return;
        }
        if (arrayRu.length == arrayEn.length) {
            for (int i = 0; i < arrayEn.length; i++) {
                saveText(arrayEn[i], arrayRu[i], nameRU, nameEN, textType, card);
            }
        } else {
            loggerFactory.error("card " + nameEN + " has untranslated " + textType);
        }
    }

    private void saveCardHandBook(CardHandBook cardHandBook) throws SQLException, IOException {
        List<HandBook> handBooks = cardHandBook.handBooks;
        for (HandBook handBook : handBooks) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(handBook.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(handBook.id), oldCard);
                } else {
                    CardState cardState;
                    if (handBook.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardHandBook, handBook.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(handBook.id), card);
                    saveText(handBook.nameRU, handBook.nameEN, handBook.nameRU, handBook.nameEN, TextType.Name, card);
                    saveText(handBook.addrRU, handBook.addrEN, handBook.nameRU, handBook.nameEN, TextType.Address, card);
                    saveText(handBook.descrRU, handBook.descrEN, handBook.nameRU, handBook.nameEN, TextType.Description, card);
                    saveText(handBook.newsRu, handBook.newsEn, handBook.nameRU, handBook.nameEN, TextType.News, card);
                    saveText(handBook.offersRu, handBook.offersEn, handBook.nameRU, handBook.nameEN, TextType.Offers, card);
                    saveMultiplyText(handBook.facts, handBook.factsEn, handBook.nameRU, handBook.nameEN, TextType.Facts, card);
                    saveMultiplyText(handBook.legends, handBook.legendsEn, handBook.nameRU, handBook.nameEN, TextType.Legends, card);
                    saveMultiplyText(handBook.literature, handBook.literatureEn, handBook.nameRU, handBook.nameEN, TextType.Literature, card);
                    saveMultiplyText(handBook.anecdotes, handBook.anecdotesEn, handBook.nameRU, handBook.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(handBook.films, handBook.filmsEn, handBook.nameRU, handBook.nameEN, TextType.Films, card);
                    saveMultiplyText(handBook.famousPassers, handBook.famousPassersEn, handBook.nameRU, handBook.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(handBook.citations, handBook.citationsEn, handBook.nameRU, handBook.nameEN, TextType.Citations, card);
                    saveText(handBook.wikipedia, handBook.wikipediaEn, handBook.nameRU, handBook.nameEN, TextType.Wikipedia, card);
                    saveText(handBook.wikimapiaRu, handBook.wikimapiaEn, handBook.nameRU, handBook.nameEN, TextType.Wikimapia, card);
                    saveText(handBook.encspbRu, handBook.encspbEn, handBook.nameRU, handBook.nameEN, TextType.Encspb, card);

                    if (handBook.lat != null && !handBook.lat.replaceAll(" ", "").isEmpty() && handBook.lon != null && !handBook.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(handBook.lat, handBook.lon, card);
                    }

                    saveParameter(handBook.phone, card, CardParameterType.Phone);
                    saveParameter(handBook.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(handBook.openHours, card, CardParameterType.OpenHours);
                    saveParameter(handBook.site, card, CardParameterType.Site);
                    saveParameter(handBook.email, card, CardParameterType.Email);
                    saveParameter(handBook.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(handBook.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(handBook.twitter, card, CardParameterType.Twitter);
                    saveParameter(handBook.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(handBook.booking, card, CardParameterType.Booking);
                    saveParameter(handBook.youtube, card, CardParameterType.Youtube);
                    saveParameter(handBook.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(handBook.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(handBook.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(handBook.appStore, card, CardParameterType.AppStore);
                    saveParameter(handBook.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(handBook.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(handBook.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(handBook.billboard, card, CardParameterType.Billboard);

                    TagParser.saveCardTags(handBook.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(handBook.categories, card, TagType.Categories);
                    TagParser.saveCardTags(handBook.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(handBook.freePass, card);
                    TagParser.saveCardMiddlePrice(handBook.middlePrice, card);
                    TagParser.saveCardNoBarrier(handBook.noBarriers, card);

                    try {
                        ImageHelper.saveImages(handBook.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + handBook.nameRU + "[" + handBook.id + "]");
                        loggerFactory.error(e);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    private void saveCardsAboutCity(CardAboutCity cardAboutCity) throws SQLException, IOException {
        List<AboutCity> aboutCityList = cardAboutCity.aboutCities;
        for (AboutCity aboutCity : aboutCityList) {
            try {
                CardEntity oldCard = CardRequest.getCardByName(aboutCity.nameRU);
                if (oldCard != null) {
                    cardEntityHashMap.put(Long.parseLong(aboutCity.id), oldCard);
                } else {
                    CardState cardState;
                    if (aboutCity.notShow.equals("1")) {
                        cardState = CardState.NotActive;
                    } else {
                        cardState = CardState.Active;
                    }
                    CardEntity card = new CardEntity(CardType.CardAboutCity, aboutCity.nameEN, cardState);
                    CardRequest.addCardSafe(card);
                    cardEntityHashMap.put(Long.parseLong(aboutCity.id), card);
                    saveText(aboutCity.addrRU, aboutCity.addrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Address, card);
                    saveText(aboutCity.nameRU, aboutCity.nameEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Name, card);
                    saveText(aboutCity.descrRU, aboutCity.descrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Description, card);
                    saveText(aboutCity.newsRu, aboutCity.newsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.News, card);
                    saveText(aboutCity.offersRu, aboutCity.offersEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Offers, card);
                    saveMultiplyText(aboutCity.facts, aboutCity.factsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Facts, card);
                    saveMultiplyText(aboutCity.legends, aboutCity.legendsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Legends, card);
                    saveMultiplyText(aboutCity.literature, aboutCity.literatureEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Literature, card);
                    saveMultiplyText(aboutCity.anecdotes, aboutCity.anecdotesEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Anecdotes, card);
                    saveMultiplyText(aboutCity.films, aboutCity.filmsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Films, card);
                    saveMultiplyText(aboutCity.famousPassers, aboutCity.famousPassersEn, aboutCity.nameRU, aboutCity.nameEN, TextType.FamousPassers, card);
                    saveMultiplyText(aboutCity.citations, aboutCity.citationsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Citations, card);
                    saveText(aboutCity.wikipedia, aboutCity.wikipediaEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Wikipedia, card);
                    saveText(aboutCity.wikimapiaRu, aboutCity.wikimapiaEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Wikimapia, card);
                    saveText(aboutCity.wikimapiaRu, aboutCity.wikimapiaEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Wikimapia, card);
                    saveText(aboutCity.encspbRu, aboutCity.encspbEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Encspb, card);

                    if (aboutCity.lat != null && !aboutCity.lat.replaceAll(" ", "").isEmpty() && aboutCity.lon != null && !aboutCity.lon.replaceAll(" ", "").isEmpty()) {
                        saveCoordinate(aboutCity.lat, aboutCity.lon, card);
                    }

                    saveParameter(aboutCity.phone, card, CardParameterType.Phone);
                    saveParameter(aboutCity.additionalPhone, card, CardParameterType.Phone);
                    saveParameter(aboutCity.openHours, card, CardParameterType.OpenHours);
                    saveParameter(aboutCity.site, card, CardParameterType.Site);
                    saveParameter(aboutCity.email, card, CardParameterType.Email);
                    saveParameter(aboutCity.vkCom, card, CardParameterType.Vkcom);
                    saveParameter(aboutCity.fbCom, card, CardParameterType.Fbcom);
                    saveParameter(aboutCity.twitter, card, CardParameterType.Twitter);
                    saveParameter(aboutCity.frsqr, card, CardParameterType.Frsqr);
                    saveParameter(aboutCity.wifiLogin, card, CardParameterType.WifiLogin);
                    saveParameter(aboutCity.wifiPass, card, CardParameterType.WifiPass);
                    saveParameter(aboutCity.liveJournal, card, CardParameterType.LiveJournal);
                    saveParameter(aboutCity.appStore, card, CardParameterType.AppStore);
                    saveParameter(aboutCity.googlePlay, card, CardParameterType.GooglePlay);
                    saveParameter(aboutCity.tripadviser, card, CardParameterType.Tripadviser);
                    saveParameter(aboutCity.instagramm, card, CardParameterType.Instagramm);
                    saveParameter(aboutCity.booking, card, CardParameterType.Booking);
                    saveParameter(aboutCity.youtube, card, CardParameterType.Youtube);
                    saveParameter(aboutCity.billboard, card, CardParameterType.Billboard);

                    TagParser.saveCardTags(aboutCity.kitchen, card, TagType.Cuisine);
                    TagParser.saveCardTags(aboutCity.categories, card, TagType.Categories);
                    TagParser.saveCardTags(aboutCity.ribbons, card, TagType.Ribbons);
                    TagParser.saveCardFreePass(aboutCity.freePass, card);
                    TagParser.saveCardMiddlePrice(aboutCity.middlePrice, card);
                    TagParser.saveCardNoBarrier(aboutCity.noBarriers, card);

                    try {
                        ImageHelper.saveImages(aboutCity.photo, card, ImageType.Photo);
                    } catch (DataIsEmptyException e) {
                        loggerFactory.error("no photo image on card " + aboutCity.nameRU + "[" + aboutCity.id + "]");
                        loggerFactory.error(e);
                    }
                }
            } catch (Exception e) {
                loggerFactory.error(e);
            }
        }
    }

    public static void saveParameter(String parameter, CardEntity card, CardParameterType cardParameterType) {
        if (parameter != null
                && !parameter.replaceAll(" ", "").isEmpty()
                && !parameter.equals("null")) {
            String validatedParameter = ParameterValidator.isValidParameter(parameter, cardParameterType.getDataType());
            if (validatedParameter != null) {
                CardParameterTypeEntity cardParameterTypeEntity = ParameterRequest.getParameterTypeByName(cardParameterType.getEnglishName());
                CardParameterEntity cardParameterEntity = new CardParameterEntity(card, cardParameterTypeEntity, validatedParameter);
                ParameterRequest.addCardParameter(cardParameterEntity);
            } else {
                loggerFactory.error("invalid parameter " + parameter + " " + cardParameterType.getEnglishName() + " on card " + card.getCardName());
            }
        }
    }


    public static void saveText(String textRu, String textEn, String nameRu, String nameEn, TextType textType, CardEntity card) {
        if ("null".equals(textEn)) {
            textEn = null;
        }
        if ("null".equals(textRu)) {
            textRu = null;
        }

        if ((textRu == null || textRu.isEmpty()) && (textEn == null || textEn.isEmpty())) {
            return;
        }
        String name;
        if (nameEn != null) {
            name = nameEn;
        } else {
            if (nameRu != null) {
                name = nameRu;
            } else {
                name = "";
            }
        }
        TextGroupEntity textGroupEntity = new TextGroupEntity(name + textType);
        if (textRu != null && !textRu.isEmpty()) {
            TextEntity textERu = new TextEntity(LanguageType.Russian, textRu, textGroupEntity);
            TextRequest.addText(textERu);
        }
        if (textEn != null && !textEn.isEmpty()) {
            TextEntity textEEn = new TextEntity(LanguageType.English, textEn, textGroupEntity);
            TextRequest.addText(textEEn);
        }
        if (textGroupEntity.getTextGroupID() != null) {
            TextCardEntity textCardEntity = new TextCardEntity(textGroupEntity, card, ParameterRequest.getParameterTypeByName(textType.toString()));
            TextRequest.addTextCard(textCardEntity);
        }
    }


    public void saveCoordinate(String lat, String lon, CardEntity card) {
        try {
            String validLat = ParameterValidator.isValidParameter(lat, DataType.DoubleType);
            String validLon = ParameterValidator.isValidParameter(lon, DataType.DoubleType);

            if (validLat != null && validLon != null) {
                double doubleLat = Double.parseDouble(validLat);
                double doubleLon = Double.parseDouble(validLon);
                CardCoordinateEntity cardCoordinateEntity = new CardCoordinateEntity(card, doubleLon, doubleLat);
                CoordinateRequest.addCardCoordinate(cardCoordinateEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }


    @Override
    public void run() {
        try {
            restaurantChainMap = new HashMap<>();
            GlobalXmlParser globalXmlParser = new GlobalXmlParser();
            globalXmlParser.globalParse();
        } catch (Exception e) {
            loggerFactory.error("global base parse is dead");
            loggerFactory.error(e);
        } finally {
            restaurantChainMap = null;
        }
    }

    private void addRestarauntChainLink(String restaurantChain, CardEntity card) {
        try {
            if (restaurantChain != null && !restaurantChain.replaceAll(" ", "").isEmpty() && !restaurantChain.equals("0")) {
                Integer restChainID = Integer.parseInt(restaurantChain);
                CardEntity targetCard;
                if (restChainID > 0) {
                    if (restaurantChainMap.containsKey(restChainID) && restaurantChainMap.get(restChainID) != null) {
                        targetCard = restaurantChainMap.get(restChainID);
                    } else {
                        targetCard = new CardEntity(CardType.RestaurantChainCard, CardType.RestaurantChainCard + "_" + restChainID, CardState.Active);
                        restaurantChainMap.put(restChainID, targetCard);
                    }
                    addCardToCardLink(targetCard, card, CardToCardLinkType.RestaurantChain);
                }
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private void addCardToCardLink(CardEntity targetCard, CardEntity card, CardToCardLinkType cardToCardLinkType) {
        CardToCardLinkEntity cardToCardLinkEntity = new CardToCardLinkEntity(card, targetCard, cardToCardLinkType);
        LinkRequest.addCardToCardLinkRequest(cardToCardLinkEntity);
    }

    public HashMap<Long, CardEntity> getCardEntityHashMap() {
        return cardEntityHashMap;
    }


}

/*
priceFile;
metro;
*/
