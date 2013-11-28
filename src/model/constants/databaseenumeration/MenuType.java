package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.11.13
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public enum MenuType {
    Unknown(0),
    RootMenu(1),
    StandardMenu(2),
    DropDownMenu(3);

    private final int value;

    private MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuType parseInt(int value) {
        MenuType[] menuTypes = MenuType.values();
        if (value <= 0 || value >=menuTypes.length) {
            return Unknown;
        } else {
            return menuTypes[value];
        }
    }
}
