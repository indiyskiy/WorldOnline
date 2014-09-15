package model.constants.databaseenumeration;

import model.constants.ApplicationBlock;

public enum CardParameterType {
    Phone(3, DataType.PhoneNumberType, ApplicationBlock.Description, true, false),
    OpenHours(4, DataType.StringType, ApplicationBlock.Description, false, false),
    Site(5, DataType.LinkType, ApplicationBlock.Description, false, false),
    Email(6, DataType.EmailType, ApplicationBlock.Description, false, false),
    Vkcom(7, DataType.LinkType, ApplicationBlock.Social, false, false),
    Fbcom(8, DataType.LinkType, ApplicationBlock.Social, false, false),
    Twitter(9, DataType.LinkType, ApplicationBlock.Social, false, false),
    Frsqr(10, DataType.LinkType, ApplicationBlock.Social, false, false),
    Youtube(11, DataType.LinkType, ApplicationBlock.Details, false, false),
    WifiLogin(12, DataType.StringType, ApplicationBlock.WiFi, false, false),
    WifiPass(13, DataType.StringType, ApplicationBlock.WiFi, false, false),
    LiveJournal(14, DataType.LinkType, ApplicationBlock.Social, false, false),
    AppStore(15, DataType.LinkType, ApplicationBlock.Store, false, false),
    GooglePlay(16, DataType.LinkType, ApplicationBlock.Store, false, false),
    Tripadviser(17, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Instagramm(18, DataType.LinkType, ApplicationBlock.Social, false, false),
    Booking(19, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Billboard(21, DataType.LinkType, ApplicationBlock.Details, false, false),
    Apoi(22, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Ayda(23, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    BlogFcsSpb(24, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    CafeSpb(25, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    DpRu(26, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Flamp(27, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    ImhoNet(28, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    IRecommend(29, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Komandirovka(30, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    MenuRu(31, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Otzovik(32, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    PeterburgRu(33, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Restlook(34, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Restop(35, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Restoran(36, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Restozoom(37, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Spbrestoran(38, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    SzoSpr(39, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    TheVillage(40, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Tourprom(41, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Traveltipz(42, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Tulp(43, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Turbina(44, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Uvoice(45, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Wrestorane(46, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Yell(47, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Zoon(48, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Facts(50, DataType.StringType, ApplicationBlock.Facts, true, false),
    Legends(51, DataType.StringType, ApplicationBlock.Facts, true, false),
    Literature(52, DataType.StringType, ApplicationBlock.Facts, true, false),
    Anecdotes(53, DataType.StringType, ApplicationBlock.Facts, true, true),
    Films(54, DataType.StringType, ApplicationBlock.Facts, true, true),
    FamousPassers(55, DataType.StringType, ApplicationBlock.Facts, true, true),
    Citations(56, DataType.StringType, ApplicationBlock.Facts, true, true),
    BorisStars(57, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Restoclub(58, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Afisha(59, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Timeout(60, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Wikipedia(61, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true),
    Wikitravel(62, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true),
    //    ReservationDiscount(63, DataType.Percent, ApplicationBlock.Store,false,false),
//    ReservationPhone(64, DataType.PhoneNumberType, ApplicationBlock.Store,false,false),
    AllCafe(65, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    WalkSpb(66, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Spbin(67, DataType.LinkType, ApplicationBlock.Reviews, false, false),
    Name(69, DataType.Header, ApplicationBlock.Header, false, true),
    Address(70, DataType.StringType, ApplicationBlock.Header, false, true),
    Description(71, DataType.StringType, ApplicationBlock.Description, false, true),
    News(72, DataType.StringType, ApplicationBlock.Details, false, true),
    Offers(73, DataType.StringType, ApplicationBlock.Details, false, true),
    Biography(74, DataType.StringType, ApplicationBlock.Details, false, true),
    Story(75, DataType.StringType, ApplicationBlock.Details, false, true),
    Recomend(76, DataType.LinkType, ApplicationBlock.Details, false, true),
    Wikimapia(77, DataType.LinkType, ApplicationBlock.Encyclopedia, false, false),
    Encspb(78, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true),
    AfishaBuyTicket(79, DataType.LinkType, ApplicationBlock.Selling, false, false),
    TimeOfEvent(80, DataType.TimestampType, ApplicationBlock.Details, false, false);

    private final int value;
    private final DataType dataType;
    private final ApplicationBlock block;
    private final boolean multiply;
    private final boolean translatable;

    private CardParameterType(int value, DataType dataType, ApplicationBlock applicationBlock, boolean multiply, boolean translatable) {
        this.value = value;
        this.dataType = dataType;
        this.block = applicationBlock;
        this.multiply = multiply;
        this.translatable = translatable;
    }

    public static CardParameterType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardParameterType[] cardParameterTypes = CardParameterType.values();
        return cardParameterTypes[value];
    }

    public int getValue() {
        return value;
    }

    public DataType getDataType() {
        return dataType;
    }

    public ApplicationBlock getBlock() {
        return block;
    }

    public boolean isMultiply() {
        return multiply;
    }

    public boolean isTranslatable() {
        return translatable;
    }

    public String getRussianName() {
        switch (this) {
            case Phone:
                return "Телефон";
            case OpenHours:
                return "Часы работы";
            case Site:
                return "Сайт";
            case Email:
                return "Email";
            case Vkcom:
                return "Вконтакте";
            case Fbcom:
                return "Facebook";
            case Twitter:
                return "Twitter";
            case Frsqr:
                return "Foursquare";
            case Youtube:
                return "Youtube";
            case WifiLogin:
                return "Логин WiFi";
            case WifiPass:
                return "Пароль WiFi";
            case LiveJournal:
                return "LiveJournal";
            case AppStore:
                return "AppStore";
            case GooglePlay:
                return "GooglePlay";
            case Tripadviser:
                return "Tripadviser";
            case Instagramm:
                return "Instagramm";
            case Booking:
                return "Booking";
//            case MiddlePrice:
//                return "Средняя цена";
            case Billboard:
                return "Billboard";
            case Apoi:
                return "Apoi";
            case Ayda:
                return "Ayda";
            case BlogFcsSpb:
                return "BlogFcsSpb";
            case CafeSpb:
                return "CafeSpb";
            case DpRu:
                return "DpRu";
            case Flamp:
                return "Flamp";
            case ImhoNet:
                return "ImhoNet";
            case IRecommend:
                return "IRecommend";
            case Komandirovka:
                return "Komandirovka";
            case MenuRu:
                return "MenuRu";
            case Otzovik:
                return "Otzovik";
            case PeterburgRu:
                return "PeterburgRu";
            case Restlook:
                return "Restlook";
            case Restop:
                return "Restop";
            case Restoran:
                return "Restoran";
            case Restozoom:
                return "Restozoom";
            case Spbrestoran:
                return "Spbrestoran";
            case SzoSpr:
                return "SzoSpr";
            case TheVillage:
                return "TheVillage";
            case Tourprom:
                return "Tourprom";
            case Traveltipz:
                return "Traveltipz";
            case Tulp:
                return "Tulp";
            case Turbina:
                return "Turbina";
            case Uvoice:
                return "Uvoice";
            case Wrestorane:
                return "Wrestorane";
            case Yell:
                return "Yell";
            case Zoon:
                return "Zoon";
//            case FreePass:
//                return "Свободный вход";
            case Facts:
                return "Факты";
            case Legends:
                return "Легенды";
            case Literature:
                return "В литературе";
            case Anecdotes:
                return "Анекдоты";
            case Films:
                return "В фильмах";
            case FamousPassers:
                return "Знаменитые посетители";
            case Citations:
                return "Цитаты";
            case BorisStars:
                return "BorisStars";
            case Restoclub:
                return "Restoclub";
            case Afisha:
                return "Афиша";
            case Timeout:
                return "Timeout";
            case Wikipedia:
                return "Wikipedia";
            case Wikitravel:
                return "Wikitravel";
//            case ReservationDiscount:
//                return "Скидка при бронировании";
//            case ReservationPhone:
//                return "Бронирование столика";
            case AllCafe:
                return "AllCafe";
            case WalkSpb:
                return "WalkSpb";
            case Spbin:
                return "Spbin";
//            case NoBarriers:
//                return "NoBarriers";
            case Name:
                return "Название";
            case Address:
                return "Адрес";
            case Description:
                return "Описание";
            case News:
                return "Новости";
            case Offers:
                return "Акции";
            case Biography:
                return "Биография";
            case Story:
                return "Истории";
            case Recomend:
                return "Recomend";
            case Wikimapia:
                return "Wikimapia";
            case Encspb:
                return "Encspd";
            case AfishaBuyTicket:
                return "Афиша";
            default:
                return "";
        }
    }


    public String getEnglishName() {
        switch (this) {
            case Phone:
                return "Phone number";
            case OpenHours:
                return "Open hours";
            case Site:
                return "Site";
            case Email:
                return "Email";
            case Vkcom:
                return "Vkontakte";
            case Fbcom:
                return "Facebook";
            case Twitter:
                return "Twitter";
            case Frsqr:
                return "Foursquare";
            case Youtube:
                return "Youtube";
            case WifiLogin:
                return "WiFi login";
            case WifiPass:
                return "WiFi password";
            case LiveJournal:
                return "LiveJournal";
            case AppStore:
                return "AppStore";
            case GooglePlay:
                return "GooglePlay";
            case Tripadviser:
                return "Tripadviser";
            case Instagramm:
                return "Instagramm";
            case Booking:
                return "Booking";
//            case MiddlePrice:
//                return "Middle price";
            case Billboard:
                return "Billboard";
            case Apoi:
                return "Apoi";
            case Ayda:
                return "Ayda";
            case BlogFcsSpb:
                return "BlogFcsSpb";
            case CafeSpb:
                return "CafeSpb";
            case DpRu:
                return "DpRu";
            case Flamp:
                return "Flamp";
            case ImhoNet:
                return "ImhoNet";
            case IRecommend:
                return "IRecommend";
            case Komandirovka:
                return "Komandirovka";
            case MenuRu:
                return "MenuRu";
            case Otzovik:
                return "Otzovik";
            case PeterburgRu:
                return "PeterburgRu";
            case Restlook:
                return "Restlook";
            case Restop:
                return "Restop";
            case Restoran:
                return "Restoran";
            case Restozoom:
                return "Restozoom";
            case Spbrestoran:
                return "Spbrestoran";
            case SzoSpr:
                return "SzoSpr";
            case TheVillage:
                return "TheVillage";
            case Tourprom:
                return "Tourprom";
            case Traveltipz:
                return "Traveltipz";
            case Tulp:
                return "Tulp";
            case Turbina:
                return "Turbina";
            case Uvoice:
                return "Uvoice";
            case Wrestorane:
                return "Wrestorane";
            case Yell:
                return "Yell";
            case Zoon:
                return "Zoon";
//            case FreePass:
//                return "Free pass";
            case Facts:
                return "Facts";
            case Legends:
                return "Legends";
            case Literature:
                return "Literature";
            case Anecdotes:
                return "Anecdotes";
            case Films:
                return "Films";
            case FamousPassers:
                return "Famous passers";
            case Citations:
                return "Citations";
            case BorisStars:
                return "BorisStars";
            case Restoclub:
                return "Restoclub";
            case Afisha:
                return "Afisha";
            case Timeout:
                return "Timeout";
            case Wikipedia:
                return "Wikipedia";
            case Wikitravel:
                return "Wikitravel";
//            case ReservationDiscount:
//                return "Reservation discount";
//            case ReservationPhone:
//                return "Reservation phone number";
            case AllCafe:
                return "AllCafe";
            case WalkSpb:
                return "WalkSpb";
            case Spbin:
                return "Spbin";
//            case NoBarriers:
//                return "NoBarriers";
            case Name:
                return "Name";
            case Address:
                return "Address";
            case Description:
                return "Description";
            case News:
                return "News";
            case Offers:
                return "Offers";
            case Biography:
                return "Biography";
            case Story:
                return "Story";
            case Recomend:
                return "Recomend";
            case Wikimapia:
                return "Wikimapia";
            case Encspb:
                return "Encspd";
            case AfishaBuyTicket:
                return "Afisha";
            default:
                return "";
        }
    }
}