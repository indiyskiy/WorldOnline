package model.xmlparser;

import model.FileReader;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.*;
import model.database.requests.*;
import model.database.worldonlinedb.*;
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
import model.xmlparser.xmlview.photo.photocard.PhotoCard;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 21.11.13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class GlobalXmlParser implements Runnable {

    public static HashMap<Integer, CardEntity> restaurantChainMap;
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, GlobalXmlParser.class);

    public static void parse() {
        new Thread(new GlobalXmlParser()).start();
    }

    public void globalParse() throws IOException, SQLException {
        ArrayList<StringIntPair> categories = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(ServerConsts.root + "app_data/Categories.txt"), ",");
        saveTags(categories, TagType.Categories);
        ArrayList<StringIntPair> kitchens = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(ServerConsts.root + "app_data/Kitchens.txt"), ",");
        saveTags(kitchens, TagType.Cuisine);
        ArrayList<StringIntPair> ribbons = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(ServerConsts.root + "app_data/Ribbons.txt"), ",");
        saveTags(ribbons, TagType.Ribbons);
        //cards
        CardsParser cardsParser = new CardsParser();
        CardAboutCity cardAboutCity = cardsParser.getCardAboutCity(ServerConsts.root + "card_aboutcity.xml");
        saveCardsAboutCity(cardAboutCity);
        CardHandBook cardHandBook = cardsParser.getCardHandBook(ServerConsts.root + "card_handbook.xml");
        saveCardHandBook(cardHandBook);
        CardHotels cardHotels = cardsParser.getCardHotels(ServerConsts.root + "card_hotels.xml");
        saveCardHotels(cardHotels);
        CardMeal cardMeal = cardsParser.getCardMeal(ServerConsts.root + "card_meals.xml");
        saveCardMeal(cardMeal);
        CardRelax cardRelax = cardsParser.getCardRelax(ServerConsts.root + "card_relax.xml");
        saveCardRelax(cardRelax);
        CardRoute cardRoute = cardsParser.getCardRoute(ServerConsts.root + "card_route.xml");
        saveCardRoute(cardRoute);
        CardSight cardSight = cardsParser.getCardSight(ServerConsts.root + "card_sights.xml");
        saveCardSight(cardSight);
        CardShopping cardShopping = cardsParser.getCardShopping(ServerConsts.root + "card_shopping.xml");
        saveCardShopping(cardShopping);
        //main menu
        MenuParser menuParser=new MenuParser();
        menuParser.saveMenu();
        HashMap<String,MenuEntity> menuEntityHashMap=menuParser.getMenuEntityHashMap();

        //people
        PeopleParser peopleParser = new PeopleParser();
        PeopleAboutCity peopleAboutCity = peopleParser.getPeopleAboutCity(ServerConsts.root + "people_aboutcity.xml");
        //photo
        PhotoParser photoParser = new PhotoParser();
        PhotoCard photoCard = photoParser.getPhotoCard(ServerConsts.root + "photocards.xml");
        //root
        RouteParser routeParser = new RouteParser();
        RouteRoute routeRoute = routeParser.getRouteRoute(ServerConsts.root + "route_routes.xml");
        //menu card link
        MenuCardLinkParser menuCardLinkParser=new MenuCardLinkParser();
        menuCardLinkParser.parseMenuCardLink();
    }

    private void saveCardShopping(CardShopping cardShopping) throws IOException, SQLException {
        List<Shopping> shoppingList = cardShopping.shoppings;
        for (Shopping shopping : shoppingList) {
            CardState cardState;
            if (shopping.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardShopping, shopping.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(shopping.nameRU, shopping.nameEN, shopping.nameRU, shopping.nameEN, TextType.Name, card);
            saveText(shopping.addrRU, shopping.addrEN, shopping.nameRU, shopping.nameEN, TextType.Address, card);
            saveText(shopping.descrRU, shopping.descrEN, shopping.nameRU, shopping.nameEN, TextType.Description, card);
            saveText(shopping.newsRu, shopping.newsEn, shopping.nameRU, shopping.nameEN, TextType.News, card);
            saveText(shopping.offersRu, shopping.offersEn, shopping.nameRU, shopping.nameEN, TextType.Offers, card);

            if (shopping.lat != null && !shopping.lat.replaceAll(" ", "").isEmpty() && shopping.lon != null && !shopping.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(shopping.lat, shopping.lon, card);
            }

            saveParameter(shopping.phone, card, CardParameterType.Phone);
            saveParameter(shopping.openHours, card, CardParameterType.OpenHours);
            saveParameter(shopping.site, card, CardParameterType.Site);
            saveParameter(shopping.email, card, CardParameterType.Email);
            saveParameter(shopping.vkCom, card, CardParameterType.Vkcom);
            saveParameter(shopping.fbCom, card, CardParameterType.Fbcom);
            saveParameter(shopping.twitter, card, CardParameterType.Twitter);
            saveParameter(shopping.frsqr, card, CardParameterType.Frsqr);
            saveParameter(shopping.booking, card, CardParameterType.Booking);
            saveParameter(shopping.middlePrice, card, CardParameterType.MiddlePrice);
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
            saveParameter(freePass, card, CardParameterType.FreePass);

            saveParameter(shopping.facts, card, CardParameterType.Facts);
            saveParameter(shopping.legends, card, CardParameterType.Legends);
            saveParameter(shopping.literature, card, CardParameterType.Literature);
            saveParameter(shopping.anecdotes, card, CardParameterType.Anecdotes);
            saveParameter(shopping.films, card, CardParameterType.Films);
            saveParameter(shopping.famousPassers, card, CardParameterType.FamousPassers);
            saveParameter(shopping.citations, card, CardParameterType.Citations);
            saveParameter(shopping.borisStars, card, CardParameterType.BorisStars);
            saveParameter(shopping.restoclub, card, CardParameterType.Restoclub);
            saveParameter(shopping.afisha, card, CardParameterType.Afisha);
            saveParameter(shopping.timeout, card, CardParameterType.Timeout);
            saveParameter(shopping.wikipedia, card, CardParameterType.Wikipedia);
            saveParameter(shopping.wikitravel, card, CardParameterType.Wikitravel);
            saveParameter(shopping.reservationDiscount, card, CardParameterType.ReservationDiscount);
            saveParameter(shopping.reservationPhone, card, CardParameterType.ReservationPhone);

            saveCardTags(shopping.kitchen, card, TagType.Cuisine);
            saveCardTags(shopping.categories, card, TagType.Categories);
            saveCardTags(shopping.ribbons, card, TagType.Ribbons);

            saveImages(shopping.photo, card, ImageType.Photo);
            saveImages(shopping.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(shopping.panorama, card, ImageType.Panorama);
            saveImages(shopping.cardImage, card, ImageType.CardImage);

            addRestarauntChainLink(shopping.restaurantChain, card);
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void addRestarauntChainLink(String restaurantChain, CardEntity card) {
        if (restaurantChain != null && !restaurantChain.replaceAll(" ", "").isEmpty()) {
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
    }

    private void addCardToCardLink(CardEntity targetCard, CardEntity card, CardToCardLinkType cardToCardLinkType) {
        CardToCardLinkEntity cardToCardLinkEntity = new CardToCardLinkEntity(card, targetCard, cardToCardLinkType);
        LinkRequest.addCardToCardLinkRequest(cardToCardLinkEntity);
    }

    private void saveCardSight(CardSight cardSight) throws IOException, SQLException {
        List<Sight> sights = cardSight.sights;
        for (Sight sight : sights) {
            CardState cardState;
            if (sight.notShow.equals("1")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardSight, sight.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(sight.nameRU, sight.nameEN, sight.nameRU, sight.nameEN, TextType.Name, card);
            saveText(sight.addrRU, sight.addrEN, sight.nameRU, sight.nameEN, TextType.Address, card);
            saveText(sight.descrRU, sight.descrEN, sight.nameRU, sight.nameEN, TextType.Description, card);
            saveText(sight.newsRu, sight.newsEn, sight.nameRU, sight.nameEN, TextType.News, card);
            saveText(sight.offersRu, sight.offersEn, sight.nameRU, sight.nameEN, TextType.Offers, card);

            if (sight.lat != null && !sight.lat.replaceAll(" ", "").isEmpty() && sight.lon != null && !sight.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(sight.lat, sight.lon, card);
            }

            saveParameter(sight.phone, card, CardParameterType.Phone);
            saveParameter(sight.openHours, card, CardParameterType.OpenHours);
            saveParameter(sight.site, card, CardParameterType.Site);
            saveParameter(sight.email, card, CardParameterType.Email);
            saveParameter(sight.vkCom, card, CardParameterType.Vkcom);
            saveParameter(sight.fbCom, card, CardParameterType.Fbcom);
            saveParameter(sight.twitter, card, CardParameterType.Twitter);
            saveParameter(sight.frsqr, card, CardParameterType.Frsqr);
            saveParameter(sight.booking, card, CardParameterType.Booking);
            saveParameter(sight.middlePrice, card, CardParameterType.MiddlePrice);
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
            saveParameter(freePass, card, CardParameterType.FreePass);

            saveParameter(sight.facts, card, CardParameterType.Facts);
            saveParameter(sight.legends, card, CardParameterType.Legends);
            saveParameter(sight.literature, card, CardParameterType.Literature);
            saveParameter(sight.anecdotes, card, CardParameterType.Anecdotes);
            saveParameter(sight.films, card, CardParameterType.Films);
            saveParameter(sight.famousPassers, card, CardParameterType.FamousPassers);
            saveParameter(sight.citations, card, CardParameterType.Citations);
            saveParameter(sight.borisStars, card, CardParameterType.BorisStars);
            saveParameter(sight.restoclub, card, CardParameterType.Restoclub);
            saveParameter(sight.afisha, card, CardParameterType.Afisha);
            saveParameter(sight.timeout, card, CardParameterType.Timeout);
            saveParameter(sight.wikipedia, card, CardParameterType.Wikipedia);
            saveParameter(sight.wikitravel, card, CardParameterType.Wikitravel);
            saveParameter(sight.reservationDiscount, card, CardParameterType.ReservationDiscount);
            saveParameter(sight.reservationPhone, card, CardParameterType.ReservationPhone);

            saveCardTags(sight.kitchen, card, TagType.Cuisine);
            saveCardTags(sight.categories, card, TagType.Categories);
            saveCardTags(sight.ribbons, card, TagType.Ribbons);

            saveImages(sight.photo, card, ImageType.Photo);
            saveImages(sight.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(sight.panorama, card, ImageType.Panorama);
            saveImages(sight.cardImage, card, ImageType.CardImage);

            addRestarauntChainLink(sight.restaurantChain, card);
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void saveCardRoute(CardRoute cardRoute) throws IOException, SQLException {
        List<Route> routes = cardRoute.routes;
        for (Route route : routes) {
            CardState cardState;
            if (route.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardRoute, route.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(route.nameRU, route.nameEN, route.nameRU, route.nameEN, TextType.Name, card);
            saveText(route.addrRU, route.addrEN, route.nameRU, route.nameEN, TextType.Address, card);
            saveText(route.descrRU, route.descrEN, route.nameRU, route.nameEN, TextType.Description, card);
            saveText(route.newsRu, route.newsEn, route.nameRU, route.nameEN, TextType.News, card);
            saveText(route.offersRu, route.offersEn, route.nameRU, route.nameEN, TextType.Offers, card);

            if (route.lat != null && !route.lat.replaceAll(" ", "").isEmpty() && route.lon != null && !route.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(route.lat, route.lon, card);
            }

            saveParameter(route.phone, card, CardParameterType.Phone);
            saveParameter(route.openHours, card, CardParameterType.OpenHours);
            saveParameter(route.site, card, CardParameterType.Site);
            saveParameter(route.email, card, CardParameterType.Email);
            saveParameter(route.vkCom, card, CardParameterType.Vkcom);
            saveParameter(route.fbCom, card, CardParameterType.Fbcom);
            saveParameter(route.twitter, card, CardParameterType.Twitter);
            saveParameter(route.frsqr, card, CardParameterType.Frsqr);
            saveParameter(route.booking, card, CardParameterType.Booking);
            saveParameter(route.middlePrice, card, CardParameterType.MiddlePrice);
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
            saveParameter(freePass, card, CardParameterType.FreePass);

            saveParameter(route.facts, card, CardParameterType.Facts);
            saveParameter(route.legends, card, CardParameterType.Legends);
            saveParameter(route.literature, card, CardParameterType.Literature);
            saveParameter(route.anecdotes, card, CardParameterType.Anecdotes);
            saveParameter(route.films, card, CardParameterType.Films);
            saveParameter(route.famousPassers, card, CardParameterType.FamousPassers);
            saveParameter(route.citations, card, CardParameterType.Citations);
            saveParameter(route.borisStars, card, CardParameterType.BorisStars);
            saveParameter(route.restoclub, card, CardParameterType.Restoclub);
            saveParameter(route.afisha, card, CardParameterType.Afisha);
            saveParameter(route.timeout, card, CardParameterType.Timeout);
            saveParameter(route.wikipedia, card, CardParameterType.Wikipedia);
            saveParameter(route.wikitravel, card, CardParameterType.Wikitravel);
            saveParameter(route.reservationDiscount, card, CardParameterType.ReservationDiscount);
            saveParameter(route.reservationPhone, card, CardParameterType.ReservationPhone);

            saveCardTags(route.kitchen, card, TagType.Cuisine);
            saveCardTags(route.categories, card, TagType.Categories);
            saveCardTags(route.ribbons, card, TagType.Ribbons);

            saveImages(route.photo, card, ImageType.Photo);
            saveImages(route.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(route.panorama, card, ImageType.Panorama);
            saveImages(route.cardImage, card, ImageType.CardImage);
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void saveCardRelax(CardRelax cardRelax) throws IOException, SQLException {
        List<Relax> relaxes = cardRelax.relaxes;
        for (Relax relax : relaxes) {
            CardState cardState;
            if (relax.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardRelax, relax.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(relax.nameRU, relax.nameEN, relax.nameRU, relax.nameEN, TextType.Name, card);
            saveText(relax.addrRU, relax.addrEN, relax.nameRU, relax.nameEN, TextType.Address, card);
            saveText(relax.descrRU, relax.descrEN, relax.nameRU, relax.nameEN, TextType.Description, card);
            saveText(relax.newsRu, relax.newsEn, relax.nameRU, relax.nameEN, TextType.News, card);
            saveText(relax.offersRu, relax.offersEn, relax.nameRU, relax.nameEN, TextType.Offers, card);

            if (relax.lat != null && !relax.lat.replaceAll(" ", "").isEmpty() && relax.lon != null && !relax.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(relax.lat, relax.lon, card);
            }

            saveParameter(relax.phone, card, CardParameterType.Phone);
            saveParameter(relax.openHours, card, CardParameterType.OpenHours);
            saveParameter(relax.site, card, CardParameterType.Site);
            saveParameter(relax.email, card, CardParameterType.Email);
            saveParameter(relax.vkCom, card, CardParameterType.Vkcom);
            saveParameter(relax.fbCom, card, CardParameterType.Fbcom);
            saveParameter(relax.twitter, card, CardParameterType.Twitter);
            saveParameter(relax.frsqr, card, CardParameterType.Frsqr);
            saveParameter(relax.booking, card, CardParameterType.Booking);
            saveParameter(relax.middlePrice, card, CardParameterType.MiddlePrice);
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
            saveParameter(freePass, card, CardParameterType.FreePass);

            saveParameter(relax.facts, card, CardParameterType.Facts);
            saveParameter(relax.legends, card, CardParameterType.Legends);
            saveParameter(relax.literature, card, CardParameterType.Literature);
            saveParameter(relax.anecdotes, card, CardParameterType.Anecdotes);
            saveParameter(relax.films, card, CardParameterType.Films);
            saveParameter(relax.famousPassers, card, CardParameterType.FamousPassers);
            saveParameter(relax.citations, card, CardParameterType.Citations);
            saveParameter(relax.borisStars, card, CardParameterType.BorisStars);
            saveParameter(relax.restoclub, card, CardParameterType.Restoclub);
            saveParameter(relax.afisha, card, CardParameterType.Afisha);
            saveParameter(relax.timeout, card, CardParameterType.Timeout);
            saveParameter(relax.wikipedia, card, CardParameterType.Wikipedia);
            saveParameter(relax.wikitravel, card, CardParameterType.Wikitravel);
            saveParameter(relax.reservationDiscount, card, CardParameterType.ReservationDiscount);
            saveParameter(relax.reservationPhone, card, CardParameterType.ReservationPhone);

            saveCardTags(relax.kitchen, card, TagType.Cuisine);
            saveCardTags(relax.categories, card, TagType.Categories);
            saveCardTags(relax.ribbons, card, TagType.Ribbons);

            saveImages(relax.photo, card, ImageType.Photo);
            saveImages(relax.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(relax.panorama, card, ImageType.Panorama);
            saveImages(relax.cardImage, card, ImageType.CardImage);

            addRestarauntChainLink(relax.restaurantChain, card);
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
        }
    }

    private void saveCardMeal(CardMeal cardMeal) throws SQLException, IOException {
        List<Meal> meals = cardMeal.meals;
        for (Meal meal : meals) {
            CardState cardState;
            if (meal.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardMeal, meal.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(meal.nameRU, meal.nameEN, meal.nameRU, meal.nameEN, TextType.Name, card);
            saveText(meal.addrRU, meal.addrEN, meal.nameRU, meal.nameEN, TextType.Address, card);
            saveText(meal.descrRU, meal.descrEN, meal.nameRU, meal.nameEN, TextType.Description, card);
            saveText(meal.newsRu, meal.newsEn, meal.nameRU, meal.nameEN, TextType.News, card);
            saveText(meal.offersRu, meal.offersEn, meal.nameRU, meal.nameEN, TextType.Offers, card);

            if (meal.lat != null && !meal.lat.replaceAll(" ", "").isEmpty() && meal.lon != null && !meal.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(meal.lat, meal.lon, card);
            }

            saveParameter(meal.phone, card, CardParameterType.Phone);
            saveParameter(meal.openHours, card, CardParameterType.OpenHours);
            saveParameter(meal.site, card, CardParameterType.Site);
            saveParameter(meal.email, card, CardParameterType.Email);
            saveParameter(meal.vkCom, card, CardParameterType.Vkcom);
            saveParameter(meal.fbCom, card, CardParameterType.Fbcom);
            saveParameter(meal.twitter, card, CardParameterType.Twitter);
            saveParameter(meal.frsqr, card, CardParameterType.Frsqr);
            saveParameter(meal.booking, card, CardParameterType.Booking);
            saveParameter(meal.middlePrice, card, CardParameterType.MiddlePrice);
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
            saveParameter(freePass, card, CardParameterType.FreePass);

            saveParameter(meal.facts, card, CardParameterType.Facts);
            saveParameter(meal.legends, card, CardParameterType.Legends);
            saveParameter(meal.literature, card, CardParameterType.Literature);
            saveParameter(meal.anecdotes, card, CardParameterType.Anecdotes);
            saveParameter(meal.films, card, CardParameterType.Films);
            saveParameter(meal.famousPassers, card, CardParameterType.FamousPassers);
            saveParameter(meal.citations, card, CardParameterType.Citations);
            saveParameter(meal.borisStars, card, CardParameterType.BorisStars);
            saveParameter(meal.restoclub, card, CardParameterType.Restoclub);
            saveParameter(meal.afisha, card, CardParameterType.Afisha);
            saveParameter(meal.timeout, card, CardParameterType.Timeout);
            saveParameter(meal.wikipedia, card, CardParameterType.Wikipedia);
            saveParameter(meal.wikitravel, card, CardParameterType.Wikitravel);
            saveParameter(meal.reservationDiscount, card, CardParameterType.ReservationDiscount);
            saveParameter(meal.reservationPhone, card, CardParameterType.ReservationPhone);

            saveCardTags(meal.kitchen, card, TagType.Cuisine);
            saveCardTags(meal.categories, card, TagType.Categories);
            saveCardTags(meal.ribbons, card, TagType.Ribbons);

            saveImages(meal.photo, card, ImageType.Photo);
            saveImages(meal.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(meal.panorama, card, ImageType.Panorama);
            saveImages(meal.cardImage, card, ImageType.CardImage);

            addRestarauntChainLink(meal.restaurantChain, card);

//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
        }
    }

    private void saveCardHotels(CardHotels cardHotels) throws SQLException, IOException {
        List<Hotel> hotels = cardHotels.hotels;
        for (Hotel hotel : hotels) {
            CardState cardState;
            if (hotel.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardHotel, hotel.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(hotel.nameRU, hotel.nameEN, hotel.nameRU, hotel.nameEN, TextType.Name, card);
            saveText(hotel.addrRU, hotel.addrEN, hotel.nameRU, hotel.nameEN, TextType.Address, card);
            saveText(hotel.descrRu, hotel.descrEN, hotel.nameRU, hotel.nameEN, TextType.Description, card);
            saveText(hotel.newsRu, hotel.newsEn, hotel.nameRU, hotel.nameEN, TextType.News, card);
            saveText(hotel.offersRu, hotel.offersEn, hotel.nameRU, hotel.nameEN, TextType.Offers, card);

            if (hotel.lat != null && !hotel.lat.replaceAll(" ", "").isEmpty() && hotel.lon != null && !hotel.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(hotel.lat, hotel.lon, card);
            }

            saveParameter(hotel.phone, card, CardParameterType.Phone);
            saveParameter(hotel.openHours, card, CardParameterType.OpenHours);
            saveParameter(hotel.site, card, CardParameterType.Site);
            saveParameter(hotel.email, card, CardParameterType.Email);
            saveParameter(hotel.vkCom, card, CardParameterType.Vkcom);
            saveParameter(hotel.fbCom, card, CardParameterType.Fbcom);
            saveParameter(hotel.twitter, card, CardParameterType.Twitter);
            saveParameter(hotel.frsqr, card, CardParameterType.Frsqr);
            saveParameter(hotel.booking, card, CardParameterType.Booking);
            saveParameter(hotel.middlePrice, card, CardParameterType.MiddlePrice);
            saveParameter(hotel.youtube, card, CardParameterType.Youtube);
            saveParameter(hotel.wifiLogin, card, CardParameterType.WifiLogin);
            saveParameter(hotel.wifiPass, card, CardParameterType.WifiPass);
            saveParameter(hotel.liveJournal, card, CardParameterType.LiveJournal);
            saveParameter(hotel.appStore, card, CardParameterType.AppStore);
            saveParameter(hotel.googlePlay, card, CardParameterType.GooglePlay);
            saveParameter(hotel.tripadviser, card, CardParameterType.Tripadviser);
            saveParameter(hotel.instagramm, card, CardParameterType.Instagramm);
            saveParameter(hotel.billboard, card, CardParameterType.Billboard);

            saveCardTags(hotel.kitchen, card, TagType.Cuisine);
            saveCardTags(hotel.categories, card, TagType.Categories);
            saveCardTags(hotel.ribbons, card, TagType.Ribbons);

            saveImages(hotel.photo, card, ImageType.Photo);
            saveImages(hotel.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(hotel.panorama, card, ImageType.Panorama);

            addRestarauntChainLink(hotel.restaurantChain, card);

        }
    }

    private void saveCardHandBook(CardHandBook cardHandBook) throws SQLException, IOException {
        List<HandBook> handBooks = cardHandBook.handBooks;
        for (HandBook handBook : handBooks) {
            CardState cardState;
            if (handBook.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardHandBook, handBook.nameEN, cardState);
            CardRequest.addCard(card);

            saveText(handBook.nameRU, handBook.nameEN, handBook.nameRU, handBook.nameEN, TextType.Name, card);
            saveText(handBook.addrRU, handBook.addrEN, handBook.nameRU, handBook.nameEN, TextType.Address, card);
            saveText(handBook.descrRU, handBook.descrEN, handBook.nameRU, handBook.nameEN, TextType.Description, card);
            saveText(handBook.newsRu, handBook.newsEn, handBook.nameRU, handBook.nameEN, TextType.News, card);
            saveText(handBook.offersRu, handBook.offersEn, handBook.nameRU, handBook.nameEN, TextType.Offers, card);

            if (handBook.lat != null && !handBook.lat.replaceAll(" ", "").isEmpty() && handBook.lon != null && !handBook.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(handBook.lat, handBook.lon, card);
            }

            saveParameter(handBook.phone, card, CardParameterType.Phone);
            saveParameter(handBook.openHours, card, CardParameterType.OpenHours);
            saveParameter(handBook.site, card, CardParameterType.Site);
            saveParameter(handBook.email, card, CardParameterType.Email);
            saveParameter(handBook.vkCom, card, CardParameterType.Vkcom);
            saveParameter(handBook.fbCom, card, CardParameterType.Fbcom);
            saveParameter(handBook.twitter, card, CardParameterType.Twitter);
            saveParameter(handBook.frsqr, card, CardParameterType.Frsqr);
            saveParameter(handBook.booking, card, CardParameterType.Booking);
            saveParameter(handBook.middlePrice, card, CardParameterType.MiddlePrice);
            saveParameter(handBook.youtube, card, CardParameterType.Youtube);
            saveParameter(handBook.wifiLogin, card, CardParameterType.WifiLogin);
            saveParameter(handBook.wifiPass, card, CardParameterType.WifiPass);
            saveParameter(handBook.liveJournal, card, CardParameterType.LiveJournal);
            saveParameter(handBook.appStore, card, CardParameterType.AppStore);
            saveParameter(handBook.googlePlay, card, CardParameterType.GooglePlay);
            saveParameter(handBook.tripadviser, card, CardParameterType.Tripadviser);
            saveParameter(handBook.instagramm, card, CardParameterType.Instagramm);
            saveParameter(handBook.billboard, card, CardParameterType.Billboard);

            saveCardTags(handBook.kitchen, card, TagType.Cuisine);
            saveCardTags(handBook.categories, card, TagType.Categories);
            saveCardTags(handBook.ribbons, card, TagType.Ribbons);

            saveImages(handBook.photo, card, ImageType.Photo);
            saveImages(handBook.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(handBook.panorama, card, ImageType.Panorama);
            saveImages(handBook.cardImage, card, ImageType.CardImage);

//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
        }
    }

    private void saveCardsAboutCity(CardAboutCity cardAboutCity) throws SQLException, IOException {
        List<AboutCity> aboutCityList = cardAboutCity.aboutCities;
        for (AboutCity aboutCity : aboutCityList) {
            CardState cardState;
            if (aboutCity.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity card = new CardEntity(CardType.CardAboutCity, aboutCity.nameEN, cardState);
            CardRequest.addCard(card);
            saveText(aboutCity.addrRU, aboutCity.addrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Address, card);
            saveText(aboutCity.nameRU, aboutCity.nameEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Name, card);
            saveText(aboutCity.descrRU, aboutCity.descrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Description, card);
            saveText(aboutCity.newsRu, aboutCity.newsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.News, card);
            saveText(aboutCity.offersRu, aboutCity.offersEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Offers, card);

            if (aboutCity.lat != null && !aboutCity.lat.replaceAll(" ", "").isEmpty() && aboutCity.lon != null && !aboutCity.lon.replaceAll(" ", "").isEmpty()) {
                saveCoordinate(aboutCity.lat, aboutCity.lon, card);
            }

            saveParameter(aboutCity.phone, card, CardParameterType.Phone);
            saveParameter(aboutCity.openHours, card, CardParameterType.OpenHours);
            saveParameter(aboutCity.site, card, CardParameterType.Site);
            saveParameter(aboutCity.email, card, CardParameterType.Email);
            saveParameter(aboutCity.vKcom, card, CardParameterType.Vkcom);
            saveParameter(aboutCity.fBcom, card, CardParameterType.Fbcom);
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
            saveParameter(aboutCity.middlePrice, card, CardParameterType.MiddlePrice);
            saveParameter(aboutCity.youtube, card, CardParameterType.Youtube);
            saveParameter(aboutCity.billboard, card, CardParameterType.Billboard);

            saveCardTags(aboutCity.kitchen, card, TagType.Cuisine);
            saveCardTags(aboutCity.categories, card, TagType.Categories);
            saveCardTags(aboutCity.ribbons, card, TagType.Ribbons);

            saveImages(aboutCity.photo, card, ImageType.Photo);
            saveImages(aboutCity.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(aboutCity.panorama, card, ImageType.Panorama);
            saveImages(aboutCity.cardImage, card, ImageType.CardImage);

        }
    }

    private void saveImages(String imageNames, CardEntity card, ImageType imageType) {
        if (imageNames == null || imageNames.isEmpty()) {
            return;
        }
        String[] imageNameArray = imageNames.split(";");
        for (String imageName : imageNameArray) {
            saveImage(imageName, card, imageType);
        }
    }

    private void saveCardTags(String stringOfTags, CardEntity card, TagType tagType) throws SQLException, IOException {
        if (stringOfTags == null || stringOfTags.equals("") || stringOfTags.equals("null")) {
            return;
        }
        ArrayList<Integer> integers = StringFileParser.getIntegerListByString(stringOfTags, ",");
        String fileText = "";
        if (tagType.equals(TagType.Cuisine)) {
            fileText = FileReader.readFileAsString(ServerConsts.root + "app_data/Kitchens.txt");
        } else {
            if (tagType.equals(TagType.Categories)) {
                fileText = FileReader.readFileAsString(ServerConsts.root + "app_data/Categories.txt");
            } else {
                fileText = FileReader.readFileAsString(ServerConsts.root + "app_data/Ribbons.txt");
            }
        }
        ArrayList<StringIntPair> stringIntPairs = StringFileParser.parseStandardStringIntPair(fileText, ",");
        for (Integer integer : integers) {
            for (StringIntPair stringIntPair : stringIntPairs) {
                if (integer == stringIntPair.getAnInt()) {
                    TagEntity tagEntity = TagRequest.getTag(tagType, stringIntPair.getString());
                    if (tagEntity != null) {
                        CardTagEntity cardTagEntity = new CardTagEntity(card, tagEntity);
                        TagRequest.addCardTag(cardTagEntity);
                    }
                }
            }
        }
    }

    private void saveParameter(String parameter, CardEntity card, CardParameterType cardParameterType) {
        if (isValidParameter(parameter, cardParameterType.getDataType())) {
            CardParameterEntity cardParameterEntity = new CardParameterEntity(card, cardParameterType, cardParameterType.getDataType(), parameter);
            ParameterRequest.addCardParameter(cardParameterEntity);
        }
    }

    private boolean isValidParameter(String parameter, DataType cardParameterDataType) {
        try {
            if (parameter == null || parameter.isEmpty()) {
                return false;
            }
            switch (cardParameterDataType) {
                case DoubleType: {
                    Double.parseDouble(parameter);
                    break;
                }
                case EmailType: {
                    //todo validator
//                    InternetAddress emailAddr = new InternetAddress(parameter);
//                    emailAddr.validate();
                    break;
                }
                case IntegerType: {
                    Integer.parseInt(parameter);
                    break;
                }
                case LinkType: {
                    //todo validator
                    break;
                }
                case PhoneNumberType: {
                    if (parameter.startsWith("+")) {
                        parameter = parameter.substring(1, parameter.length());
                    }
                    parameter = parameter.replaceAll("-", "");
                    parameter = parameter.replaceAll(" ", "");
                    parameter = parameter.replaceAll("\\(", "");
                    parameter = parameter.replaceAll("\\)", "");
                    if (parameter.length() < 3) {
                        return false;
                    }
                    Long.parseLong(parameter);
                    break;
                }
                case StringType: {
                    break;
                }
                case UnknownType: {
                    break;
                }
                default: {
                    break;
                }
            }

        } catch (Exception e) {
            loggerFactory.error(e);
            return false;
        }
        return true;
    }

    private void saveText(String textRu, String textEn, String nameRu, String nameEn, TextType textType, CardEntity card) {
        TextGroupEntity textGroupEntity = null;
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
        if (textEn != null && !textEn.isEmpty()) {
            textGroupEntity = new TextGroupEntity(name + "_" + textType);
            TextEntity textEntity = TextRequest.findTextByText(textEn);
            if (textEntity == null) {
                textEntity = new TextEntity(LanguageType.English, textEn, textGroupEntity);
                TextRequest.addText(textEntity);
            } else {
                textGroupEntity = textEntity.getTextGroup();
            }
        }
        boolean translated = false;
        if (textEn != null && !textEn.isEmpty()) {
            translated = TextRequest.isTranslated(textEn, LanguageType.Russian);
        }
        if (!translated)
            if (textRu != null && !textRu.isEmpty()) {
                {
                    if (textGroupEntity == null) {
                        textGroupEntity = new TextGroupEntity(name + "_" + textType);
                    }
                    TextEntity textEntity = TextRequest.findTextByText(textRu);
                    if (textEntity == null) {
                        textEntity = new TextEntity(LanguageType.Russian, textRu, textGroupEntity);
                        TextRequest.addText(textEntity);
                    } else {
                        if (textGroupEntity.getTextGroupID() == null) {
                            textGroupEntity = textEntity.getTextGroup();
                        }
                    }
                }
            }
        if (textGroupEntity != null && textGroupEntity.getTextGroupID() == null) {
            TextRequest.addTextGroup(textGroupEntity);
        }
        if (textGroupEntity != null) {
            TextCardEntity textCardEntity = new TextCardEntity(textGroupEntity, card, textType);
            TextRequest.addTextCard(textCardEntity);
        }
    }

    private void saveTags(ArrayList<StringIntPair> tagList, TagType tagType) {
        try {
            for (StringIntPair tagItem : tagList) {
                TextGroupEntity textGroup = new TextGroupEntity(tagType + "_" + tagItem.getString());
                TagEntity tagEntity = new TagEntity(textGroup, tagType, tagItem.getString());
                tagEntity.setTagID((long) tagItem.getAnInt());
                TagRequest.addTag(tagEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private void saveCoordinate(String lat, String lon, CardEntity card) {
        try {
            if (isValidParameter(lat, DataType.DoubleType) && isValidParameter(lon, DataType.DoubleType)) {
                double doubleLat = Double.parseDouble(lat);
                double doubleLon = Double.parseDouble(lon);
                CardCoordinateEntity cardCoordinateEntity = new CardCoordinateEntity(card, doubleLon, doubleLat);
                CoordinateRequest.addCardCoordinate(cardCoordinateEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
//            loggerFactory.error(e);
        }
    }

    private void saveImage(String imageName, CardEntity card, ImageType imageType) {
        try {
            if (imageName == null || imageName.isEmpty()) {
                return;
            }
            File imageFile = new File(ServerConsts.oldImageRoot + imageName);
            if (!imageFile.exists()) {
                return;
            }
            BufferedImage bimg = ImageIO.read(imageFile);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            long size = imageFile.length();
            String hash = getMd5Hash(imageFile);
            ImageEntity imageEntity = ImageRequest.getImageByHash(hash);
            if (imageEntity == null) {
                saveFile(imageFile, imageName, ServerConsts.imageFolder + imageType);
                String path = ServerConsts.imageFolder + imageType.toString() + "/" + imageName;
                imageEntity = new ImageEntity(path, height, width, size, hash);
                ImageRequest.addImage(imageEntity);
            }
            CardImageEntity cardImageEntity = new CardImageEntity(card, imageEntity, imageType);
            cardImageEntity.setCardImageName(imageName);
            ImageRequest.addCardImage(cardImageEntity);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    private void saveFile(File file,  String fileName, String path) throws IOException {
        try {
            String mainRoot = path;
            File outFolder = new File(mainRoot);
            if (!outFolder.exists()) {
                outFolder.mkdirs();
            }
            File out = new File(mainRoot+"/"+fileName);
            FileCopyUtils.copy(file, out);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public String getMd5Hash(File file) {
        String checksum = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16);
        } catch (IOException e) {
            loggerFactory.error(e);
        } catch (NoSuchAlgorithmException e) {
            loggerFactory.error(e);
        }
        return checksum;
    }

    @Override
    public void run() {
        try {
            restaurantChainMap = new HashMap<Integer, CardEntity>();
            GlobalXmlParser globalXmlParser = new GlobalXmlParser();
            globalXmlParser.globalParse();
//            CompleteCardInfo completeCardInfo = CardRequest.getCompleteCardInfo(1);
        } catch (IOException e) {
            loggerFactory.error(e);
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            restaurantChainMap = null;
        }
    }

    public static void main(String[] args) {
        GlobalXmlParser globalXmlParser=new GlobalXmlParser();
        globalXmlParser.run();
    }
}



/*
priceFile;
metro;
parentMenuID
*/
