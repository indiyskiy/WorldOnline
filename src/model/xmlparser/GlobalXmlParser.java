package model.xmlparser;

import model.FileReader;
import model.constants.databaseenumeration.*;
import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.requests.TagRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.*;
import model.textparser.StringFileParser;
import model.textparser.StringIntPair;
import model.xmlparser.xmlview.card.cardaboutcity.AboutCity;
import model.xmlparser.xmlview.card.cardaboutcity.CardAboutCity;
import model.xmlparser.xmlview.card.cardhandbook.CardHandBook;
import model.xmlparser.xmlview.card.cardhotel.CardHotels;
import model.xmlparser.xmlview.card.cardmeal.CardMeal;
import model.xmlparser.xmlview.card.cardrelax.CardRelax;
import model.xmlparser.xmlview.card.cardroute.CardRoute;
import model.xmlparser.xmlview.card.cardshopping.CardShopping;
import model.xmlparser.xmlview.card.cardsights.CardSight;
import model.xmlparser.xmlview.mainmenudata.MainMenuData;
import model.xmlparser.xmlview.people.peopleaboutcity.PeopleAboutCity;
import model.xmlparser.xmlview.photo.photocard.PhotoCard;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;

import java.io.IOException;
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
        String root = "\\cardsdata\\";
        ArrayList<StringIntPair> categories = StringFileParser.parseStandartStringIntPair(FileReader.readFileAsString(root + "app_data\\Categories.txt"));
        saveTags(categories, TagType.Categories);
        ArrayList<StringIntPair> kitchens = StringFileParser.parseStandartStringIntPair(FileReader.readFileAsString(root + "app_data\\Kitchens.txt"));
        saveTags(kitchens, TagType.Cuisine);
        ArrayList<StringIntPair> ribbons = StringFileParser.parseStandartStringIntPair(FileReader.readFileAsString(root + "app_data\\Ribbons.txt"));
        saveTags(ribbons, TagType.Ribbons);
        //cards
        CardsParser cardsParser = new CardsParser();
        CardAboutCity cardAboutCity = cardsParser.getCardAboutCity(root + "card_aboutcity.xml");
        saveCardsAboutCity(cardAboutCity);
        CardHandBook cardHandBook = cardsParser.getCardHandBook(root + "card_handbook.xml");
        CardHotels cardHotels = cardsParser.getCardHotels(root + "card_hotels.xml");
        CardMeal cardMeal = cardsParser.getCardMeal(root + "card_meals.xml");
        CardRelax cardRelax = cardsParser.getCardRelax(root + "card_relax.xml");
        CardRoute cardRoute = cardsParser.getCardRoute(root + "card_route.xml");
        CardShopping cardShopping = cardsParser.getCardShopping(root + "card_shopping.xml");
        CardSight cardSight = cardsParser.getCardSight(root + "card_sights.xml");
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

    private void saveCardsAboutCity(CardAboutCity cardAboutCity) throws SQLException {
        List<AboutCity> aboutCityList = cardAboutCity.aboutCities;
        for (AboutCity aboutCity : aboutCityList) {
            CardState cardState;
            if (aboutCity.notShow.equals("0")) {
                cardState = CardState.NotActive;
            } else {
                cardState = CardState.Active;
            }
            CardEntity aboutCityCard = new CardEntity(CardType.CardAboutCity, aboutCity.nameEN, cardState);
            CardRequest.addCard(aboutCityCard);
            saveText(aboutCity.addrRU, aboutCity.addrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Address, aboutCityCard);
            saveText(aboutCity.nameRU, aboutCity.nameEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Name, aboutCityCard);
            saveText(aboutCity.descrRU, aboutCity.descrEN, aboutCity.nameRU, aboutCity.nameEN, TextType.Description, aboutCityCard);
            saveText(aboutCity.newsRu, aboutCity.newsEn, aboutCity.nameRU, aboutCity.nameEN, TextType.News, aboutCityCard);
            saveText(aboutCity.offersRu, aboutCity.offersEn, aboutCity.nameRU, aboutCity.nameEN, TextType.Offers, aboutCityCard);

            saveParameter(aboutCity.lat, aboutCityCard, CardParameterType.Lat);
            saveParameter(aboutCity.lon, aboutCityCard, CardParameterType.Lon);
            saveParameter(aboutCity.phone, aboutCityCard, CardParameterType.Phone);
            saveParameter(aboutCity.openHours, aboutCityCard, CardParameterType.OpenHours);
            saveParameter(aboutCity.site, aboutCityCard, CardParameterType.Site);
            saveParameter(aboutCity.email, aboutCityCard, CardParameterType.Email);
            saveParameter(aboutCity.vKcom, aboutCityCard, CardParameterType.Vkcom);
            saveParameter(aboutCity.fBcom, aboutCityCard, CardParameterType.Fbcom);
            saveParameter(aboutCity.twitter, aboutCityCard, CardParameterType.Twitter);
            saveParameter(aboutCity.frsqr, aboutCityCard, CardParameterType.Frsqr);
            saveParameter(aboutCity.wifiLogin, aboutCityCard, CardParameterType.WifiLogin);
            saveParameter(aboutCity.wifiPass, aboutCityCard, CardParameterType.WifiPass);
            saveParameter(aboutCity.liveJournal, aboutCityCard, CardParameterType.LiveJournal);
            saveParameter(aboutCity.appStore, aboutCityCard, CardParameterType.AppStore);
            saveParameter(aboutCity.googlePlay, aboutCityCard, CardParameterType.GooglePlay);
            saveParameter(aboutCity.tripadviser, aboutCityCard, CardParameterType.Tripadviser);
            saveParameter(aboutCity.instagramm, aboutCityCard, CardParameterType.Instagramm);
            saveParameter(aboutCity.booking, aboutCityCard, CardParameterType.Booking);
            saveParameter(aboutCity.middlePrice, aboutCityCard, CardParameterType.MiddlePrice);
            saveParameter(aboutCity.youtube, aboutCityCard, CardParameterType.Youtube);
            saveParameter(aboutCity.youtube, aboutCityCard, CardParameterType.Youtube);
            saveParameter(aboutCity.billboard, aboutCityCard, CardParameterType.Billboard);

            saveCardTags(aboutCity.kitchen, aboutCityCard, TagType.Cuisine);
            saveCardTags(aboutCity.categories, aboutCityCard, TagType.Categories);
            saveCardTags(aboutCity.ribbons,aboutCityCard,TagType.Ribbons);
        }
    }

    private void saveCardTags(String stringOfTags, CardEntity card, TagType tagType) throws SQLException {
        if(stringOfTags==null || stringOfTags.equals("") || stringOfTags.equals("null")){
           return;
        }
        ArrayList<StringIntPair> stringIntPairArrayList = StringFileParser.parseStandartStringIntPair(stringOfTags);
        for (StringIntPair stringIntPair : stringIntPairArrayList) {
           TagEntity tagEntity= TagRequest.getTag(tagType, stringIntPair.getString());
           if (tagEntity!=null){
              CardTagEntity cardTagEntity=new CardTagEntity(card,tagEntity);
               TagRequest.addCardTag(cardTagEntity);
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
            textGroupEntity = new TextGroupEntity(name + "_" + "address");
            TextEntity textEntity = new TextEntity(LanguageType.English, textEn, textGroupEntity);
            TextRequest.addText(textEntity);
        }
        if (textRu != null && !textRu.isEmpty()) {
            if (textGroupEntity == null) {
                textGroupEntity = new TextGroupEntity(name + "_" + "address");
            }
            TextEntity textEntity = new TextEntity(LanguageType.Russian, textRu, textGroupEntity);
            TextRequest.addText(textEntity);
        }
        if (textGroupEntity != null) {
            TextCardEntity textCardEntity = new TextCardEntity(textGroupEntity, card, textType);
            TextRequest.addTextCard(textCardEntity);
        }
    }

    private void saveTags(ArrayList<StringIntPair> categoriesList, TagType categories) {
        for (StringIntPair tagItem : categoriesList) {
            TextGroupEntity textGroup = new TextGroupEntity(categories + "_" + tagItem.getString());
            TagEntity tagEntity = new TagEntity(textGroup, categories, tagItem.getString());
            tagEntity.setTagID((long) tagItem.getAnInt());
            TagRequest.addTag(tagEntity);
        }
    }
}
/*



public String priceFile;
public String photo;
public String panoramaToList;
public String panorama;
public String cardImage;

public String metro;
*/
