package model.xmlparser.xmlview.card.cardaboutcity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 18.10.13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@Root(name="Aboutcity")
public class AboutCity {
    @Attribute(name = "id")
    //1
    public String id;
    @Element(name = "ParentMenuID")
    //2
    public String parentMenuID;
    @Element(name = "NameRU")
    //3
    public String nameRU;
    @Element(name = "NameEN")
    //4
    public String nameEN;
    @Element(name = "Lat",required = false)
    //5
    public String lat;
    @Element(name = "Lon",required = false)
    //6
    public String lon;
    @Element(name = "PriceFile",required = false)
    //7
    public String priceFile;
    @Element(name = "Phone",required = false)
    //8
    public String phone;
    @Element(name = "AddrRU",required = false)
    //9
    public String addrRU;
    @Element(name = "AddrEN",required = false)
    //10
    public String addrEN;
    @Element(name = "OpenHours",required = false)
    //11
    public String openHours;
    @Element(name = "Photo",required = false)
    //12
    public String photo;
    @Element(name = "DescrRU",required = false)
    //13
    public String descrRU;
    @Element(name = "DescrEN",required = false)
    //14
    public String descrEN;
    @Element(name = "Site",required = false)
    //15
    public String site;
    @Element(name = "Email",required = false)
    //16
    public String email;
    @Element(name = "VKcom",required = false)
    //17
    public String vKcom;
    @Element(name = "FBcom",required = false)
    //18
    public String fBcom;
    @Element(name = "Twitter",required = false)
    //19
    public String twitter;
    @Element(name = "Frsqr",required = false)
    //20
    public String frsqr;
    @Element(name = "PanoramaToList",required = false)
    //21
    public String panoramaToList;
    @Element(name = "Booking",required = false)
    //22
    public String booking;
    @Element(name = "MiddlePrice",required = false)
    //23
    public String middlePrice;
    @Element(name = "Youtube",required = false)
    //24
    public String youtube;
    @Element(name = "Panorama",required = false)
    //25
    public String panorama;
    @Element(name = "Billboard",required = false)
    //26
    public String billboard;
    @Element(name = "Metro",required = false)
    //27
    public String metro;
    @Element(name = "WifiLogin",required = false)
    //28
    public String wifiLogin;
    @Element(name = "WifiPass",required = false)
    //29
    public String wifiPass;
    @Element(name = "Kitchen",required = false)
    //30
    public String kitchen;
    @Element(name = "Categories",required = false)
    //31
    public String categories;
    @Element(name = "NotShow")
    //32
    public String notShow;
    @Element(name = "Ribbons",required = false)
    //33
    public String ribbons;
    @Element(name = "CardImage",required = false)
    //34
    public String cardImage;
    @Element(name = "LiveJournal",required = false)
    //35
    public String liveJournal;
    @Element(name = "AppStore",required = false)
    //36
    public String appStore;
    @Element(name = "GooglePlay",required = false)
    //37
    public String googlePlay;
    @Element(name = "Tripadviser",required = false)
    //38
    public String tripadviser;
    @Element(name = "NewsRu",required = false)
    //39
    public String newsRu;
    @Element(name = "NewsEn",required = false)
    //40
    public String newsEn;
    @Element(name = "OffersRu",required = false)
    //41
    public String offersRu;
    @Element(name = "OffersEn",required = false)
    //42
    public String offersEn;
    @Element(name = "Instagramm",required = false)
    //43
    public String instagramm;
}
