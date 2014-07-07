package model.constants.databaseenumeration;

/**
 * Created by Илья on 27.03.14.
 */
public enum DataBaseEntityType {
    Unknown(0),
    Card(0),
    Text(2),
    Parameter(3),
    Image(4),
    Route(5),
    CardToCardLink(6),
    Menu(7),
    Tag(8),
    Coordinate(9);


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
