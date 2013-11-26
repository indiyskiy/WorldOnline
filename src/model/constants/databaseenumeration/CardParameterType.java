package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public enum CardParameterType {
    Unknown(0,DataType.UnknownType),
    Lat(1,DataType.IntegerType),
    Lon(2,DataType.IntegerType),
    Phone(3,DataType.PhoneNumberType),
    OpenHours(4,DataType.StringType),
    Site(5,DataType.LinkType),
    Email(6,DataType.EmailType),
    Vkcom(7,DataType.LinkType),
    Fbcom(8,DataType.LinkType),
    Twitter(9,DataType.LinkType),
    Frsqr(10,DataType.LinkType),
    Youtube(11,DataType.LinkType),
    WifiLogin(12,DataType.StringType),
    WifiPass(13,DataType.StringType),
    LiveJournal(14,DataType.LinkType),
    AppStore(15,DataType.LinkType),
    GooglePlay(16,DataType.LinkType),
    Tripadviser(17,DataType.LinkType),
    Instagramm(18,DataType.LinkType),
    Booking(19,DataType.LinkType),
    MiddlePrice(20,DataType.IntegerType),
    Billboard(21,DataType.LinkType);

    private final int value;
    private final DataType dataType;

    private CardParameterType(int value,DataType dataType) {
        this.value = value;
        this.dataType=dataType;
    }

    public int getValue() {
        return value;
    }

    public static CardParameterType parseInt(int value) {
        CardParameterType[] cardParameterTypes = CardParameterType.values();
        if (value <= 0 || value > cardParameterTypes.length) {
            return Unknown;
        } else {
            return cardParameterTypes[value];
        }
    }

    public DataType getDataType() {
        return dataType;
    }
}
