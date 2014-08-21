package model.constants.databaseenumeration;

import model.constants.ApplicationBlock;

public enum CardParameterType {
    Phone(3, DataType.PhoneNumberType, ApplicationBlock.Description),
    OpenHours(4, DataType.StringType, ApplicationBlock.Description),
    Site(5, DataType.LinkType, ApplicationBlock.Description),
    Email(6, DataType.EmailType, ApplicationBlock.Description),
    Vkcom(7, DataType.LinkType, ApplicationBlock.Social),
    Fbcom(8, DataType.LinkType, ApplicationBlock.Social),
    Twitter(9, DataType.LinkType, ApplicationBlock.Social),
    Frsqr(10, DataType.LinkType, ApplicationBlock.Social),
    Youtube(11, DataType.LinkType, ApplicationBlock.Details),
    WifiLogin(12, DataType.StringType, ApplicationBlock.WiFi),
    WifiPass(13, DataType.StringType, ApplicationBlock.WiFi),
    LiveJournal(14, DataType.LinkType, ApplicationBlock.Social),
    AppStore(15, DataType.LinkType, ApplicationBlock.Main),
    GooglePlay(16, DataType.LinkType, ApplicationBlock.Main),
    Tripadviser(17, DataType.LinkType, ApplicationBlock.Reviews),
    Instagramm(18, DataType.LinkType, ApplicationBlock.Social),
    Booking(19, DataType.LinkType, ApplicationBlock.Reviews),
    Billboard(21, DataType.LinkType, ApplicationBlock.Details),
    Apoi(22, DataType.LinkType, ApplicationBlock.Reviews),
    Ayda(23, DataType.LinkType, ApplicationBlock.Reviews),
    BlogFcsSpb(24, DataType.LinkType, ApplicationBlock.Reviews),
    CafeSpb(25, DataType.LinkType, ApplicationBlock.Reviews),
    DpRu(26, DataType.LinkType, ApplicationBlock.Reviews),
    Flamp(27, DataType.LinkType, ApplicationBlock.Reviews),
    ImhoNet(28, DataType.LinkType, ApplicationBlock.Reviews),
    IRecommend(29, DataType.LinkType, ApplicationBlock.Reviews),
    Komandirovka(30, DataType.LinkType, ApplicationBlock.Reviews),
    MenuRu(31, DataType.LinkType, ApplicationBlock.Reviews),
    Otzovik(32, DataType.LinkType, ApplicationBlock.Reviews),
    PeterburgRu(33, DataType.LinkType, ApplicationBlock.Reviews),
    Restlook(34, DataType.LinkType, ApplicationBlock.Reviews),
    Restop(35, DataType.LinkType, ApplicationBlock.Reviews),
    Restoran(36, DataType.LinkType, ApplicationBlock.Reviews),
    Restozoom(37, DataType.LinkType, ApplicationBlock.Reviews),
    Spbrestoran(38, DataType.LinkType, ApplicationBlock.Reviews),
    SzoSpr(39, DataType.LinkType, ApplicationBlock.Reviews),
    TheVillage(40, DataType.LinkType, ApplicationBlock.Reviews),
    Tourprom(41, DataType.LinkType, ApplicationBlock.Reviews),
    Traveltipz(42, DataType.LinkType, ApplicationBlock.Reviews),
    Tulp(43, DataType.LinkType, ApplicationBlock.Reviews),
    Turbina(44, DataType.LinkType, ApplicationBlock.Reviews),
    Uvoice(45, DataType.LinkType, ApplicationBlock.Reviews),
    Wrestorane(46, DataType.LinkType, ApplicationBlock.Reviews),
    Yell(47, DataType.LinkType, ApplicationBlock.Reviews),
    Zoon(48, DataType.LinkType, ApplicationBlock.Reviews),
    Facts(50, DataType.StringType, ApplicationBlock.Facts),
    Legends(51, DataType.StringType, ApplicationBlock.Facts),
    Literature(52, DataType.StringType, ApplicationBlock.Facts),
    Anecdotes(53, DataType.StringType, ApplicationBlock.Facts),
    Films(54, DataType.StringType, ApplicationBlock.Facts),
    FamousPassers(55, DataType.StringType, ApplicationBlock.Facts),
    Citations(56, DataType.StringType, ApplicationBlock.Facts),
    BorisStars(57, DataType.LinkType, ApplicationBlock.Reviews),
    Restoclub(58, DataType.LinkType, ApplicationBlock.Reviews),
    Afisha(59, DataType.LinkType, ApplicationBlock.Reviews),
    Timeout(60, DataType.LinkType, ApplicationBlock.Reviews),
    Wikipedia(61, DataType.LinkType, ApplicationBlock.Encyclopedia),
    Wikitravel(62, DataType.LinkType, ApplicationBlock.Encyclopedia),
    //    ReservationDiscount(63, DataType.Percent, ApplicationBlock.Main),
//    ReservationPhone(64, DataType.PhoneNumberType, ApplicationBlock.Main),
    AllCafe(65, DataType.LinkType, ApplicationBlock.Reviews),
    WalkSpb(66, DataType.LinkType, ApplicationBlock.Reviews),
    Spbin(67, DataType.LinkType, ApplicationBlock.Reviews),
    Name(69, DataType.Header, ApplicationBlock.Header),
    Address(70, DataType.StringType, ApplicationBlock.Header),
    Description(71, DataType.StringType, ApplicationBlock.Description),
    News(72, DataType.StringType, ApplicationBlock.Details),
    Offers(73, DataType.StringType, ApplicationBlock.Details),
    Biography(74, DataType.StringType, ApplicationBlock.Details),
    Story(75, DataType.StringType, ApplicationBlock.Details),
    Recomend(76, DataType.LinkType, ApplicationBlock.Details),
    Wikimapia(77, DataType.LinkType, ApplicationBlock.Encyclopedia),
    Encspb(78, DataType.LinkType, ApplicationBlock.Encyclopedia),
    AfishaBuyTicket(79, DataType.LinkType, ApplicationBlock.Selling),
    TimeOfEvent(80, DataType.TimestampType, ApplicationBlock.Details);

    private final int value;
    private final DataType dataType;
    private ApplicationBlock block;

    private CardParameterType(int value, DataType dataType, ApplicationBlock applicationBlock) {
        this.value = value;
        this.dataType = dataType;
        this.block = applicationBlock;
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
                return "Name";
            case Address:
                return "Address";
            case Description:
                return "Description";
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
                return "Адрес";
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