package model.xmlparser.xmlview.card.cardroute;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 20.10.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "Route")
public class Route {
    @Attribute(name = "id", required = true)
    public String id;

    @Element(name = "ParentMenuID", required = false)
    public String parentMenuID;
    @Element(name = "NameRU", required = false)
    public String nameRU;
    @Element(name = "NameEN", required = false)
    public String nameEN;
    @Element(name = "Lat", required = false)
    public String lat;
    @Element(name = "Lon", required = false)
    public String lon;
    @Element(name = "PriceFile", required = false)
    public String priceFile;
    @Element(name = "Phone", required = false)
    public String phone;
    @Element(name = "AddrRU", required = false)
    public String addrRU;
    @Element(name = "AddrEN", required = false)
    public String addrEN;
    @Element(name = "OpenHours", required = false)
    public String openHours;
    @Element(name = "Photo", required = false)
    public String photo;
    @Element(name = "DescrRU", required = false)
    public String descrRU;
    @Element(name = "DescrEN", required = false)
    public String descrEN;
    @Element(name = "Site", required = false)
    public String site;
    @Element(name = "Email", required = false)
    public String email;
    @Element(name = "Vkcom", required = false)
    public String vkCom;
    @Element(name = "Fbcom", required = false)
    public String fbCom;
    @Element(name = "Twitter", required = false)
    public String twitter;
    @Element(name = "Frsqr", required = false)
    public String frsqr;
    @Element(name = "PanoramaToList", required = false)
    public String panoramaToList;
    @Element(name = "Booking", required = false)
    public String booking;
    @Element(name = "MiddlePrice", required = false)
    public String middlePrice;
    @Element(name = "Youtube", required = false)
    public String youtube;
    @Element(name = "Panorama", required = false)
    public String panorama;
    @Element(name = "Billboard", required = false)
    public String billboard;
    @Element(name = "Metro", required = false)
    public String metro;
    @Element(name = "WifiLogin", required = false)
    public String wifiLogin;
    @Element(name = "WifiPass", required = false)
    public String wifiPass;
    @Element(name = "Kitchen", required = false)
    public String kitchen;
    @Element(name = "Categories", required = false)
    public String categories;
    @Element(name = "NotShow", required = false)
    public String notShow;
    @Element(name = "Ribbons", required = false)
    public String ribbons;
    @Element(name = "CardImage", required = false)
    public String cardImage;
    @Element(name = "LiveJournal", required = false)
    public String liveJournal;
    @Element(name = "AppStore", required = false)
    public String appStore;
    @Element(name = "GooglePlay", required = false)
    public String googlePlay;
    @Element(name = "Tripadviser", required = false)
    public String tripadviser;
    @Element(name = "NewsRu", required = false)
    public String newsRu;
    @Element(name = "NewsEn", required = false)
    public String newsEn;
    @Element(name = "OffersRu", required = false)
    public String offersRu;
    @Element(name = "OffersEn", required = false)
    public String offersEn;
    @Element(name = "Instagramm", required = false)
    public String instagramm;
    @Element(name = "Apoi", required = false)
    public String apoi;
    @Element(name = "Ayda", required = false)
    public String ayda;
    @Element(name = "BlogFcsSpb", required = false)
    public String blogFcsSpb;
    @Element(name = "CafeSpb", required = false)
    public String cafeSpb;
    @Element(name = "DpRu", required = false)
    public String dpRu;
    @Element(name = "Flamp", required = false)
    public String flamp;
    @Element(name = "ImhoNet", required = false)
    public String imhoNet;
    @Element(name = "IRecommend", required = false)
    public String iRecommend;
    @Element(name = "Komandirovka", required = false)
    public String komandirovka;
    @Element(name = "MenuRu", required = false)
    public String menuRu;
    @Element(name = "Otzovik", required = false)
    public String otzovik;
    @Element(name = "PeterburgRu", required = false)
    public String peterburgRu;
    @Element(name = "Restlook", required = false)
    public String restlook;
    @Element(name = "Restop", required = false)
    public String restop;
    @Element(name = "Restoran", required = false)
    public String restoran;
    @Element(name = "Restozoom", required = false)
    public String restozoom;
    @Element(name = "Spbrestoran", required = false)
    public String spbrestoran;
    @Element(name = "SzoSpr", required = false)
    public String szoSpr;
    @Element(name = "TheVillage", required = false)
    public String theVillage;
    @Element(name = "Tourprom", required = false)
    public String tourprom;
    @Element(name = "Traveltipz", required = false)
    public String traveltipz;
    @Element(name = "Tulp", required = false)
    public String tulp;
    @Element(name = "Turbina", required = false)
    public String turbina;
    @Element(name = "Uvoice", required = false)
    public String uvoice;
    @Element(name = "Wrestorane", required = false)
    public String wrestorane;
    @Element(name = "Yell", required = false)
    public String yell;
    @Element(name = "Zoon", required = false)
    public String zoon;
    @Element(name = "FreePass", required = false)
    public String freePass;
    @Element(name = "Facts", required = false)
    public String facts;
    @Element(name = "Legends", required = false)
    public String legends;
    @Element(name = "Literature", required = false)
    public String literature;
    @Element(name = "Anecdotes", required = false)
    public String anecdotes;
    @Element(name = "Films", required = false)
    public String films;
    @Element(name = "FamousPassers", required = false)
    public String famousPassers;
    @Element(name = "Citations", required = false)
    public String citations;
    @Element(name = "BorisStars", required = false)
    public String borisStars;
    @Element(name = "Restoclub", required = false)
    public String restoclub;
    @Element(name = "Afisha", required = false)
    public String afisha;
    @Element(name = "Timeout", required = false)
    public String timeout;
    @Element(name = "Wikipedia", required = false)
    public String wikipedia;
    @Element(name = "Wikitravel", required = false)
    public String wikitravel;
    @Element(name = "ReservationDiscount", required = false)
    public String reservationDiscount;
    @Element(name = "ReservationPhone", required = false)
    public String reservationPhone;
    @Element(name = "RestaurantChain", required = false)
    public String restaurantChain;
}
