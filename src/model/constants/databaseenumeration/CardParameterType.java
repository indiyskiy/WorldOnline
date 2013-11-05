package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public enum CardParameterType {
    Unknown(0),
    Lat(1),
    Lon(2),
    Phone(3),
    OpenHours(4),
    Site(5),
    Email(6),
    Vkcom(7),
    Fbcom(8),
    Twitter(9),
    Frsqr(10),
    Youtube(11),
    WifiLogin(12),
    WifiPass(13),
    LiveJournal(14),
    AppStore(15),
    GooglePlay(16),
    Tripadvisor(17),
    Instagramm(18),
    ParentMenuId(19);

    private final int value;

    private CardParameterType(int value) {
        this.value = value;
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
}
