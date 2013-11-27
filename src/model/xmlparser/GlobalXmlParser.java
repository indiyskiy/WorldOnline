package model.xmlparser;

import model.FileReader;
import model.constants.databaseenumeration.*;
import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardParameterEntity;
import model.database.worldonlinedb.CardTagEntity;
import model.database.worldonlinedb.TagEntity;
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
import model.xmlparser.xmlview.mainmenudata.MainMenuData;
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;
import model.xmlparser.xmlview.photo.photocard.PhotoCard;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 21.11.13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class GlobalXmlParser {
    private final String root = "\\cardsdata\\";
    private final String imageRoot = "\\imageData\\";
    private final String fileRoot = "FileBase\\";

    public static void main(String[] args) {
        try {
            GlobalXmlParser globalXmlParser = new GlobalXmlParser();
            globalXmlParser.globalParse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void globalParse() throws IOException, SQLException {

        ArrayList<StringIntPair> categories = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(root + "app_data\\Categories.txt"), ",");
        saveTags(categories, TagType.Categories);
        ArrayList<StringIntPair> kitchens = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(root + "app_data\\Kitchens.txt"), ",");
        saveTags(kitchens, TagType.Cuisine);
        ArrayList<StringIntPair> ribbons = StringFileParser.parseStandardStringIntPair(FileReader.readFileAsString(root + "app_data\\Ribbons.txt"), ",");
        saveTags(ribbons, TagType.Ribbons);
        //cards
        CardsParser cardsParser = new CardsParser();
        CardAboutCity cardAboutCity = cardsParser.getCardAboutCity(root + "card_aboutcity.xml");
        saveCardsAboutCity(cardAboutCity);
        CardHandBook cardHandBook = cardsParser.getCardHandBook(root + "card_handbook.xml");
        saveCardHandBook(cardHandBook);
        CardHotels cardHotels = cardsParser.getCardHotels(root + "card_hotels.xml");
        saveCardHotels(cardHotels);
        CardMeal cardMeal = cardsParser.getCardMeal(root + "card_meals.xml");
        saveCardMeal(cardMeal);
        CardRelax cardRelax = cardsParser.getCardRelax(root + "card_relax.xml");
        saveCardRelax(cardRelax);
        CardRoute cardRoute = cardsParser.getCardRoute(root + "card_route.xml");
        saveCardRoute(cardRoute);
        CardSight cardSight = cardsParser.getCardSight(root + "card_sights.xml");
        saveCardSight(cardSight);
        CardShopping cardShopping = cardsParser.getCardShopping(root + "card_shopping.xml");
        saveCardShopping(cardShopping);
        //main menu
        MenuParser menuParser = new MenuParser();
        MainMenuData mainMenuData = menuParser.getMainMenuData(root + "MainMenuData.xml");
        //people
        PeopleParser peopleParser = new PeopleParser();
        PeopleAboutCity peopleAboutCity = peopleParser.getPeopleAboutCity(root + "people_aboutcity.xml");
        //photo
        PhotoParser photoParser = new PhotoParser();
        PhotoCard photoCard = photoParser.getPhotoCard(root + "photocards.xml");
        //root
        RouteParser routeParser = new RouteParser();
        RouteRoute routeRoute = routeParser.getRouteRoute(root + "route_routes.xml");
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

            saveParameter(shopping.lat, card, CardParameterType.Lat);
            saveParameter(shopping.lon, card, CardParameterType.Lon);
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

            saveCardTags(shopping.kitchen, card, TagType.Cuisine);
            saveCardTags(shopping.categories, card, TagType.Categories);
            saveCardTags(shopping.ribbons, card, TagType.Ribbons);

            saveImages(shopping.photo, card, ImageType.Photo);
            saveImages(shopping.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(shopping.panorama, card, ImageType.Panorama);
            saveImages(shopping.cardImage, card, ImageType.CardImage);
        }
//            handBook.priceFile);
//            handBook.metro);
//            handBook.parentMenuID);
    }

    private void saveCardSight(CardSight cardSight) throws IOException, SQLException {
        List<Sight> sights = cardSight.sights;
        for (Sight sight : sights) {
            CardState cardState;
            if (sight.notShow.equals("0")) {
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

            saveParameter(sight.lat, card, CardParameterType.Lat);
            saveParameter(sight.lon, card, CardParameterType.Lon);
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

            saveCardTags(sight.kitchen, card, TagType.Cuisine);
            saveCardTags(sight.categories, card, TagType.Categories);
            saveCardTags(sight.ribbons, card, TagType.Ribbons);

            saveImages(sight.photo, card, ImageType.Photo);
            saveImages(sight.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(sight.panorama, card, ImageType.Panorama);
            saveImages(sight.cardImage, card, ImageType.CardImage);
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

            saveParameter(route.lat, card, CardParameterType.Lat);
            saveParameter(route.lon, card, CardParameterType.Lon);
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

            saveParameter(relax.lat, card, CardParameterType.Lat);
            saveParameter(relax.lon, card, CardParameterType.Lon);
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

            saveCardTags(relax.kitchen, card, TagType.Cuisine);
            saveCardTags(relax.categories, card, TagType.Categories);
            saveCardTags(relax.ribbons, card, TagType.Ribbons);

            saveImages(relax.photo, card, ImageType.Photo);
            saveImages(relax.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(relax.panorama, card, ImageType.Panorama);
            saveImages(relax.cardImage, card, ImageType.CardImage);

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

            saveParameter(meal.lat, card, CardParameterType.Lat);
            saveParameter(meal.lon, card, CardParameterType.Lon);
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

            saveCardTags(meal.kitchen, card, TagType.Cuisine);
            saveCardTags(meal.categories, card, TagType.Categories);
            saveCardTags(meal.ribbons, card, TagType.Ribbons);

            saveImages(meal.photo, card, ImageType.Photo);
            saveImages(meal.panoramaToList, card, ImageType.PanoramaToList);
            saveImages(meal.panorama, card, ImageType.Panorama);
            saveImages(meal.cardImage, card, ImageType.CardImage);

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

            saveParameter(hotel.lat, card, CardParameterType.Lat);
            saveParameter(hotel.lon, card, CardParameterType.Lon);
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

            saveParameter(handBook.lat, card, CardParameterType.Lat);
            saveParameter(handBook.lon, card, CardParameterType.Lon);
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

            saveParameter(aboutCity.lat, card, CardParameterType.Lat);
            saveParameter(aboutCity.lon, card, CardParameterType.Lon);
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
//        System.out.println(imageNames+" "+imageType);
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
            fileText = FileReader.readFileAsString(root + "app_data\\Kitchens.txt");
        } else {
            if (tagType.equals(TagType.Categories)) {
                fileText = FileReader.readFileAsString(root + "app_data\\Categories.txt");
            } else {
                fileText = FileReader.readFileAsString(root + "app_data\\Ribbons.txt");
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
//        if (isValidParameter(parameter, cardParameterType.getDataType())) {
//            CardParameterEntity cardParameterEntity = new CardParameterEntity(card, cardParameterType, cardParameterType.getDataType(), parameter);
//            ParameterRequest.addCardParameter(cardParameterEntity);
//        }
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
            return false;
        }
        return true;
    }

    private void saveText(String textRu, String textEn, String nameRu, String nameEn, TextType textType, CardEntity card) {
//        TextGroupEntity textGroupEntity = null;
//        String name;
//        if (nameEn != null) {
//            name = nameEn;
//        } else {
//            if (nameRu != null) {
//                name = nameRu;
//            } else {
//                name = "";
//            }
//        }
//        if (textEn != null && !textEn.isEmpty()) {
//            textGroupEntity = new TextGroupEntity(name + "_" + textType);
//            TextEntity textEntity = new TextEntity(LanguageType.English, textEn, textGroupEntity);
//            TextRequest.addText(textEntity);
//        }
//        if (textRu != null && !textRu.isEmpty()) {
//            if (textGroupEntity == null) {
//                textGroupEntity = new TextGroupEntity(name + "_" + textType);
//            }
//            TextEntity textEntity = new TextEntity(LanguageType.Russian, textRu, textGroupEntity);
//            TextRequest.addText(textEntity);
//        }
//        if (textGroupEntity != null) {
//            TextRequest.addTextGroup(textGroupEntity);
//            TextCardEntity textCardEntity = new TextCardEntity(textGroupEntity, card, textType);
//            TextRequest.addTextCard(textCardEntity);
//        }
    }

    private void saveTags(ArrayList<StringIntPair> tagList, TagType tagType) {
//        for (StringIntPair tagItem : tagList) {
//            TextGroupEntity textGroup = new TextGroupEntity(tagType + "_" + tagItem.getString());
//            TagEntity tagEntity = new TagEntity(textGroup, tagType, tagItem.getString());
//            tagEntity.setTagID((long) tagItem.getAnInt());
//            TagRequest.addTag(tagEntity);
//        }
    }

    private void saveImage(String imageName, CardEntity card, ImageType imageType) {
//        try {
//            if (imageName == null || imageName.isEmpty()) {
//                return;
//            }
//            File imageFile = new File(imageRoot + imageName);
//            if (!imageFile.exists()) {
//                System.out.println(imageRoot + " : " + imageName + " FROM " + imageType);
//                return;
//            }
//            BufferedImage bimg = ImageIO.read(imageFile);
//            int width = bimg.getWidth();
//            int height = bimg.getHeight();
//            long size = imageFile.length();
//            String hash = getMd5Hash(imageFile);
//            saveFile(imageFile, imageName, imageType.toString());
//            String someUrl = ServerConsts.ServerURL + ServerConsts.getWorldOnlineFileModule + ServerConsts.getImageServlet;
//            ImageEntity imageEntity = ImageRequest.getImageByHash(hash);
//            if (imageEntity == null) {
//                imageEntity = new ImageEntity(someUrl + "?fileName=" + imageName, height, width, size, hash);
//            }
//            ImageRequest.addImage(imageEntity);
//            CardImageEntity cardImageEntity = new CardImageEntity(card, imageEntity, imageType);
//            cardImageEntity.setCardImageName(imageName);
//            ImageRequest.addCardImage(cardImageEntity);
//        } catch (Exception e) {
//            System.out.println("Exception on " + imageRoot + " : " + imageName + " FROM " + imageType);
//            e.printStackTrace();
//        }
    }

    private void saveFile(File file, String fileName, String subRoot) throws IOException {
        File outFolder = new File("..\\..\\" + fileRoot + subRoot);
        if (!outFolder.exists()) {
            outFolder.mkdirs();
        }
        File out = new File("..\\..\\" + fileRoot + subRoot + "\\" + fileName);
        FileCopyUtils.copy(file, out);
    }

    public String getMd5Hash(File file) {
        String checksum = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Using MessageDigest update() method to provide input
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16); //don't use this, truncates leading zero
        } catch (IOException ex) {
//            logger.log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
//            logger.log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
//        System.out.println(checksum);
        return checksum;
    }
}

/*
public String priceFile;
public String metro;
parentMenuID
*/
