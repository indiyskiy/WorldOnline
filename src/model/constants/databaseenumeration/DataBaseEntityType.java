package model.constants.databaseenumeration;


public enum DataBaseEntityType {
    Unknown(0),
    Card(1),
    Menus(2),
    Price(3),
    Tags(4),
    DishTags(5),
    ParameterTypes(6),
    DishCats(7);

    private final int value;

    private DataBaseEntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataBaseEntityType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        DataBaseEntityType[] dataBaseEntityTypes = DataBaseEntityType.values();
        if (value <= 0 || value >= dataBaseEntityTypes.length) {
            return Unknown;
        } else {
            return dataBaseEntityTypes[value];
        }
    }
}
