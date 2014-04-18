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
    Phone(3, DataType.PhoneNumberType),
    OpenHours(4, DataType.StringType),
    Site(5, DataType.LinkType),
    Email(6, DataType.EmailType),
    Vkcom(7, DataType.LinkType),
    Fbcom(8, DataType.LinkType),
    Twitter(9, DataType.LinkType),
    Frsqr(10, DataType.LinkType),
    Youtube(11, DataType.LinkType),
    WifiLogin(12, DataType.StringType),
    WifiPass(13, DataType.StringType),
    LiveJournal(14, DataType.LinkType),
    AppStore(15, DataType.LinkType),
    GooglePlay(16, DataType.LinkType),
    Tripadviser(17, DataType.LinkType),
    Instagramm(18, DataType.LinkType),
    Booking(19, DataType.LinkType),
    MiddlePrice(20, DataType.IntegerType),
    Billboard(21, DataType.LinkType),
    Apoi(22, DataType.LinkType),
    Ayda(23, DataType.LinkType),
    BlogFcsSpb(24, DataType.LinkType),
    CafeSpb(25, DataType.LinkType),
    DpRu(26, DataType.LinkType),
    Flamp(27, DataType.LinkType),
    ImhoNet(28, DataType.LinkType),
    IRecommend(29, DataType.LinkType),
    Komandirovka(30, DataType.LinkType),
    MenuRu(31, DataType.LinkType),
    Otzovik(32, DataType.LinkType),
    PeterburgRu(33, DataType.LinkType),
    Restlook(34, DataType.LinkType),
    Restop(35, DataType.LinkType),
    Restoran(36, DataType.LinkType),
    Restozoom(37, DataType.LinkType),
    Spbrestoran(38, DataType.LinkType),
    SzoSpr(39, DataType.LinkType),
    TheVillage(40, DataType.LinkType),
    Tourprom(41, DataType.LinkType),
    Traveltipz(42, DataType.LinkType),
    Tulp(43, DataType.LinkType),
    Turbina(44, DataType.LinkType),
    Uvoice(45, DataType.LinkType),
    Wrestorane(46, DataType.LinkType),
    Yell(47, DataType.LinkType),
    Zoon(48, DataType.LinkType),
    FreePass(49, DataType.Percent),
    Facts(50, DataType.StringType),
    Legends(51, DataType.StringType),
    Literature(52, DataType.StringType),
    Anecdotes(53, DataType.StringType),
    Films(54, DataType.StringType),
    FamousPassers(55, DataType.StringType),
    Citations(56, DataType.StringType),
    BorisStars(57, DataType.LinkType),
    Restoclub(58, DataType.LinkType),
    Afisha(59, DataType.LinkType),
    Timeout(60, DataType.LinkType),
    Wikipedia(61, DataType.LinkType),
    Wikitravel(62, DataType.LinkType),
    ReservationDiscount(63, DataType.Percent),
    ReservationPhone(64, DataType.PhoneNumberType);
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

    public static CardParameterType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
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