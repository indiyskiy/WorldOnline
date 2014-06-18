package model.constants.databaseenumeration;

public enum MenuType {
    Unknown(0),
    RootMenu(1),
    StandardMenu(2),
    MainScreenMenu(3);

    private final int value;

    private MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        MenuType[] menuTypes = MenuType.values();
        if (value <= 0 || value >= menuTypes.length) {
            return Unknown;
        } else {
            return menuTypes[value];
        }
    }
}
