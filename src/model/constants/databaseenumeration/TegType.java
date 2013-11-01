package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public enum TegType {
    Unknown(0),
    Cuisine(1),
    Categories(2),
    Ribbons(3);

    private final int value;

    private TegType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TegType parseInt(int value) {
        TegType[] tegTypes = TegType.values();
        if (value <= 0 || value > tegTypes.length) {
            return Unknown;
        } else {
            return tegTypes[value];
        }
    }
}
