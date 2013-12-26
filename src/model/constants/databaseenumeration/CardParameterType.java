package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public enum CardParameterType {
    Unknown(0, DataType.UnknownType),
    Phone(3, DataType.PhoneNumberType),      //main info
    OpenHours(4, DataType.StringType),       //main info
    Site(5, DataType.LinkType),              //main info
    Email(6, DataType.EmailType),            //main info
    Vkcom(7, DataType.LinkType),             //links
    Fbcom(8, DataType.LinkType),             //links
    Twitter(9, DataType.LinkType),           //links
    Frsqr(10, DataType.LinkType),            //links
    Youtube(11, DataType.LinkType),          //links
    WifiLogin(12, DataType.StringType),      //add info
    WifiPass(13, DataType.StringType),       //add info
    LiveJournal(14, DataType.LinkType),      //links
    AppStore(15, DataType.LinkType),         //application
    GooglePlay(16, DataType.LinkType),       //application
    Tripadviser(17, DataType.LinkType),      //reviews \/
    Instagramm(18, DataType.LinkType),       //links
    Booking(19, DataType.LinkType),          //reviews \/
    MiddlePrice(20, DataType.IntegerType),   //add info
    Billboard(21, DataType.LinkType),        //links
    Apoi(22, DataType.LinkType),             //reviews \/
    Ayda(23, DataType.LinkType),             //reviews \/
    BlogFcsSpb(24, DataType.LinkType),       //reviews \/
    CafeSpb(25, DataType.LinkType),          //reviews \/
    DpRu(26, DataType.LinkType),             //reviews \/
    Flamp(27, DataType.LinkType),            //reviews \/
    ImhoNet(28, DataType.LinkType),          //reviews \/
    IRecommend(29, DataType.LinkType),       //reviews \/
    Komandirovka(30, DataType.LinkType),     //reviews \/
    MenuRu(31, DataType.LinkType),           //reviews \/
    Otzovik(32, DataType.LinkType),          //reviews \/
    PeterburgRu(33, DataType.LinkType),      //reviews \/
    Restlook(34, DataType.LinkType),         //reviews \/
    Restop(35, DataType.LinkType),           //reviews \/
    Restoran(36, DataType.LinkType),         //reviews \/
    Restozoom(37, DataType.LinkType),        //reviews \/
    Spbrestoran(38, DataType.LinkType),      //reviews \/
    SzoSpr(39, DataType.LinkType),           //reviews \/
    TheVillage(40, DataType.LinkType),       //reviews \/
    Tourprom(41, DataType.LinkType),         //reviews \/
    Traveltipz(42, DataType.LinkType),       //reviews \/
    Tulp(43, DataType.LinkType),             //reviews \/
    Turbina(44, DataType.LinkType),          //reviews \/
    Uvoice(45, DataType.LinkType),           //reviews \/
    Wrestorane(46, DataType.LinkType),       //reviews \/
    Yell(47, DataType.LinkType),             //reviews \/
    Zoon(48, DataType.LinkType),             //reviews \/
    FreePass(49, DataType.Percent),          //add info
    Facts(50, DataType.StringType),          //facts \/
    Legends(51, DataType.StringType),        //facts \/
    Literature(52, DataType.StringType),     //facts \/
    Anecdotes(53, DataType.StringType),      //facts \/
    Films(54, DataType.StringType),          //facts \/
    FamousPassers(55, DataType.StringType),  //facts \/
    Citations(56, DataType.StringType),      //facts \/
    BorisStars(57, DataType.LinkType),       //reviews \/
    Restoclub(58, DataType.LinkType),        //reviews \/
    Afisha(59, DataType.LinkType),           //reviews \/
    Timeout(60, DataType.LinkType),          //reviews \/
    Wikipedia(61, DataType.LinkType),        //links
    Wikitravel(62, DataType.LinkType),       //links
    ReservationDiscount(63, DataType.Percent),//add info
    ReservationPhone(64, DataType.PhoneNumberType);//add info
    public static final CardParameterType[] reviews = new CardParameterType[]{
            Tripadviser, Booking, Apoi, Ayda,
            BlogFcsSpb, CafeSpb, DpRu, Flamp,
            ImhoNet, IRecommend, Komandirovka, MenuRu,
            Otzovik, PeterburgRu, Restlook, Restop,
            Restoran, Restozoom, Spbrestoran, SzoSpr,
            TheVillage, Tourprom, Traveltipz, Tulp,
            Turbina, Uvoice, Wrestorane, Yell, Zoon,
            BorisStars, Restoclub, Afisha, Timeout};
    public static final CardParameterType[] mainInfo = new CardParameterType[]{
            Phone, OpenHours, Site, Email};
    public static final CardParameterType[] facts = new CardParameterType[]{
            Facts, Legends, Literature, Anecdotes, Films, FamousPassers, Citations};
    public static final CardParameterType[] links = new CardParameterType[]{
            Vkcom, Fbcom, Twitter, Frsqr, Youtube, LiveJournal, Instagramm, Billboard, Wikipedia, Wikitravel};
    private final int value;
    private final DataType dataType;

    private CardParameterType(int value, DataType dataType) {
        this.value = value;
        this.dataType = dataType;
    }

    public static CardParameterType parseInt(int value) {
        CardParameterType[] cardParameterTypes = CardParameterType.values();
        if (value <= 0 || value >= cardParameterTypes.length) {
            return Unknown;
        } else {
            return cardParameterTypes[value];
        }
    }

    public static String getStringForSql(CardParameterType[] types) {
        String result = "";
        if (types.length > 0) {
            result += types[0];
        }
        if (types.length > 1) {
            for (int i = 1; i < types.length; i++) {
                result += ", " + types[i].getValue();
            }
        }
        return result;
    }

    public static boolean isInArray(Integer value, CardParameterType[] types) {
        if (value == null) {
            return false;
        }
        for (CardParameterType type : types) {
            if (type.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInArray(CardParameterType value, CardParameterType[] types) {
        return isInArray(value.getValue(), types);
    }

    public int getValue() {
        return value;
    }

    public DataType getDataType() {
        return dataType;
    }
}