package model.xmlparser.xmlview.card.cardshopping;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 20.10.13
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "Shopping")
public class Shopping {
    @Attribute(name = "id", required = true)
    public String id;

    @Element(name = "ParentMenuID", required = false)
    public String parentMenuID;
    @Element(name = "NameRU", required = false)
    public String nameRU;
    @Element(name = "NameEN", required = false)
    public String  nameEN;
    @Element(name = "Lat", required = false)
    public String lat;
    @Element(name = "Lon", required = false)
    public String  lon;
    @Element(name = "PriceFile", required = false)
    public String priceFile;
    @Element(name = "Phone", required = false)
    public String  phone;
    @Element(name = "AddrRU", required = false)
    public String   addrRU;
    @Element(name = "AddrEN", required = false)
    public String  addrEN;
    @Element(name = "OpenHours", required = false)
    public String   openHours;
    @Element(name = "Photo", required = false)
    public String    photo;
    @Element(name = "DescrRU", required = false)
    public String  descrRU;
    @Element(name = "DescrEN", required = false)
    public String   descrEN;
    @Element(name = "Site", required = false)
    public String   site;
    @Element(name = "Email", required = false)
    public String  email;
    @Element(name = "Vkcom", required = false)
    public String  vkCom;
    @Element(name = "Fbcom", required = false)
    public String   fbCom;
    @Element(name = "Twitter", required = false)
    public String    twitter;
    @Element(name = "Frsqr", required = false)
    public String    frsqr;
    @Element(name = "PanoramaToList", required = false)
    public String   panoramaToList;
    @Element(name = "Booking", required = false)
    public String  booking;
    @Element(name = "MiddlePrice", required = false)
    public String    middlePrice;
    @Element(name = "Youtube", required = false)
    public String    youtube;
    @Element(name = "Panorama", required = false)
    public String   panorama;
    @Element(name = "Billboard", required = false)
    public String  billboard;
    @Element(name = "Metro", required = false)
    public String  metro;
    @Element(name = "WifiLogin", required = false)
    public String  wifiLogin;
    @Element(name = "WifiPass", required = false)
    public String   wifiPass;
    @Element(name = "Kitchen", required = false)
    public String    kitchen;
    @Element(name = "Categories", required = false)
    public String    categories;
    @Element(name = "NotShow", required = false)
    public String  notShow;
    @Element(name = "Ribbons", required = false)
    public String   ribbons;
    @Element(name = "CardImage", required = false)
    public String   cardImage;
    @Element(name = "LiveJournal", required = false)
    public String liveJournal;
    @Element(name = "AppStore", required = false)
    public String    appStore;
    @Element(name = "GooglePlay", required = false)
    public String    googlePlay;
    @Element(name = "Tripadviser", required = false)
    public String    tripadviser;
    @Element(name = "NewsRu", required = false)
    public String    newsRu;
    @Element(name = "NewsEn", required = false)
    public String   newsEn;
    @Element(name = "OffersRu", required = false)
    public String    offersRu;
    @Element(name = "OffersEn", required = false)
    public String   offersEn;
    @Element(name = "Instagramm", required = false)
    public String instagramm;
}
