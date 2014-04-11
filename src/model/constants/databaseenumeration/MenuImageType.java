package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 17.01.14
 * Time: 18:08
 * To change this template use File | Settings | File Templates.
 */
public enum MenuImageType {
    Unknown(0),
    Simple(1),
    Pushed(2);
    private final int value;

    MenuImageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static MenuImageType parseInt(int value) {
        MenuImageType[] menuImageTypes = MenuImageType.values();
        if (value <= 0 || value >= menuImageTypes.length) {
            return Unknown;
        } else {
            return menuImageTypes[value];
        }
    }
}
