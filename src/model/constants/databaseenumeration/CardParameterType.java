package model.constants.databaseenumeration;

import model.constants.ApplicationBlock;

public enum CardParameterType {
    Phone(3, DataType.PhoneNumberType, ApplicationBlock.Description, true, false, "Телефон", "Phone number"),
    OpenHours(4, DataType.StringType, ApplicationBlock.Description, false, false, "Часы работы", "Open hours"),
    Site(5, DataType.LinkType, ApplicationBlock.Description, false, false, "Сайт", "Site"),
    Email(6, DataType.EmailType, ApplicationBlock.Description, false, false, "Email", "Email"),
    Vkcom(7, DataType.LinkType, ApplicationBlock.Social, false, false, "Вконтакте", "Vkontakte"),
    Fbcom(8, DataType.LinkType, ApplicationBlock.Social, false, false, "Facebook", "Facebook"),
    Twitter(9, DataType.LinkType, ApplicationBlock.Social, false, false, "Twitter", "Twitter"),
    Frsqr(10, DataType.LinkType, ApplicationBlock.Social, false, false, "Foursquare", "Foursquare"),
    Youtube(11, DataType.LinkType, ApplicationBlock.Details, false, false, "Youtube", "Youtube"),
    WifiLogin(12, DataType.StringType, ApplicationBlock.WiFi, false, false, "Логин WiFi", "WiFi login"),
    WifiPass(13, DataType.StringType, ApplicationBlock.WiFi, false, false, "Пароль WiFi", "WiFi password"),
    LiveJournal(14, DataType.LinkType, ApplicationBlock.Social, false, false, "LiveJournal", "LiveJournal"),
    AppStore(15, DataType.LinkType, ApplicationBlock.Store, false, false, "AppStore", "AppStore"),
    GooglePlay(16, DataType.LinkType, ApplicationBlock.Store, false, false, "GooglePlay", "GooglePlay"),
    Tripadviser(17, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Tripadviser", "Tripadviser"),
    Instagramm(18, DataType.LinkType, ApplicationBlock.Social, false, false, "Instagramm", "Instagramm"),
    Booking(19, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Booking", "Booking"),
    Billboard(21, DataType.LinkType, ApplicationBlock.Details, false, false, "Billboard", "Billboard"),
    Apoi(22, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Apoi", "Apoi"),
    Ayda(23, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Ayda", "Ayda"),
    BlogFcsSpb(24, DataType.LinkType, ApplicationBlock.Reviews, false, false, "BlogFcsSpb", "BlogFcsSpb"),
    CafeSpb(25, DataType.LinkType, ApplicationBlock.Reviews, false, false, "CafeSpb", "CafeSpb"),
    DpRu(26, DataType.LinkType, ApplicationBlock.Reviews, false, false, "DpRu", "DpRu"),
    Flamp(27, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Flamp", "Flamp"),
    ImhoNet(28, DataType.LinkType, ApplicationBlock.Reviews, false, false, "ImhoNet", "ImhoNet"),
    IRecommend(29, DataType.LinkType, ApplicationBlock.Reviews, false, false, "IRecommend", "IRecommend"),
    Komandirovka(30, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Komandirovka", "Komandirovka"),
    MenuRu(31, DataType.LinkType, ApplicationBlock.Reviews, false, false, "MenuRu", "MenuRu"),
    Otzovik(32, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Otzovik", "Otzovik"),
    PeterburgRu(33, DataType.LinkType, ApplicationBlock.Reviews, false, false, "PeterburgRu", "PeterburgRu"),
    Restlook(34, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Restlook", "Restlook"),
    Restop(35, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Restop", "Restop"),
    Restoran(36, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Restoran", "Restoran"),
    Restozoom(37, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Restozoom", "Restozoom"),
    Spbrestoran(38, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Spbrestoran", "Spbrestoran"),
    SzoSpr(39, DataType.LinkType, ApplicationBlock.Reviews, false, false, "SzoSpr", "SzoSpr"),
    TheVillage(40, DataType.LinkType, ApplicationBlock.Reviews, false, false, "TheVillage", "TheVillage"),
    Tourprom(41, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Tourprom", "Tourprom"),
    Traveltipz(42, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Traveltipz", "Traveltipz"),
    Tulp(43, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Tulp", "Tulp"),
    Turbina(44, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Turbina", "Turbina"),
    Uvoice(45, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Uvoice", "Uvoice"),
    Wrestorane(46, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Wrestorane", "Wrestorane"),
    Yell(47, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Yell", "Yell"),
    Zoon(48, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Zoon", "Zoon"),
    Facts(50, DataType.StringType, ApplicationBlock.Facts, true, false, "Факты", "Facts"),
    Legends(51, DataType.StringType, ApplicationBlock.Facts, true, false, "Легенды", "Legends"),
    Literature(52, DataType.StringType, ApplicationBlock.Facts, true, false, "В литературе", "Literature"),
    Anecdotes(53, DataType.StringType, ApplicationBlock.Facts, true, true, "Анекдоты", "Anecdotes"),
    Films(54, DataType.StringType, ApplicationBlock.Facts, true, true, "В фильмах", "Films"),
    FamousPassers(55, DataType.StringType, ApplicationBlock.Facts, true, true, "Знаменитые посетители", "Famous passers"),
    Citations(56, DataType.StringType, ApplicationBlock.Facts, true, true, "Цитаты", "Citations"),
    BorisStars(57, DataType.LinkType, ApplicationBlock.Reviews, false, false, "BorisStars", "BorisStars"),
    Restoclub(58, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Restoclub", "Restoclub"),
    Afisha(59, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Афиша", "Afisha"),
    Timeout(60, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Timeout", "Timeout"),
    Wikipedia(61, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true, "Wikipedia", "Wikipedia"),
    Wikitravel(62, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true, "Wikitravel", "Wikitravel"),
    AllCafe(65, DataType.LinkType, ApplicationBlock.Reviews, false, false, "AllCafe", "AllCafe"),
    WalkSpb(66, DataType.LinkType, ApplicationBlock.Reviews, false, false, "WalkSpb", "WalkSpb"),
    Spbin(67, DataType.LinkType, ApplicationBlock.Reviews, false, false, "Spbin", "Spbin"),
    Name(69, DataType.Header, ApplicationBlock.Header, false, true, "Название", "Name"),
    Address(70, DataType.StringType, ApplicationBlock.Header, false, true, "Адрес", "Address"),
    Description(71, DataType.StringType, ApplicationBlock.Description, false, true, "Описание", "Description"),
    News(72, DataType.StringType, ApplicationBlock.Details, false, true, "Новости", "News"),
    Offers(73, DataType.StringType, ApplicationBlock.Details, false, true, "Акции", "Offers"),
    Biography(74, DataType.StringType, ApplicationBlock.Details, false, true, "Биография", "Biography"),
    Story(75, DataType.StringType, ApplicationBlock.Details, false, true, "Истории", "Story"),
    Recomend(76, DataType.LinkType, ApplicationBlock.Details, false, true, "Recomend", "Recomend"),
    Wikimapia(77, DataType.LinkType, ApplicationBlock.Encyclopedia, false, false, "Wikimapia", "Wikimapia"),
    Encspb(78, DataType.LinkType, ApplicationBlock.Encyclopedia, false, true, "Encspd", "Encspd"),
    AfishaBuyTicket(79, DataType.LinkType, ApplicationBlock.Selling, false, false, "Афиша", "Afisha"),
    TimeOfEvent(80, DataType.TimestampType, ApplicationBlock.Details, false, false, "Время проведения", "Time of event");

    private final int value;
    private final DataType dataType;
    private final ApplicationBlock block;
    private final boolean multiply;
    private final boolean translatable;
    private final String ruName;
    private final String enName;


    private CardParameterType(int value,
                              DataType dataType,
                              ApplicationBlock applicationBlock,
                              boolean multiply,
                              boolean translatable,
                              String ruName,
                              String enName
    ) {
        this.value = value;
        this.dataType = dataType;
        this.block = applicationBlock;
        this.multiply = multiply;
        this.translatable = translatable;
        this.ruName = ruName;
        this.enName = enName;
    }

  /*  public static CardParameterType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardParameterType[] cardParameterTypes = CardParameterType.values();
        return cardParameterTypes[value];
    }*/

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
        return ruName;
    }

    public String getEnglishName() {
        return enName;
    }
}