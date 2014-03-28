package model.constants.databaseenumeration;

/**
 * Created by Илья on 27.03.14.
 */
public enum DataBaseEntityType {
    Unknown(0),
    Card(1),
    Text(2),
    Tag(3),
    Coordinate(4),
    Image(5),
    Root(6),
    CardToCardLink(7),
    Menu(8);


    private final int value;

    private DataBaseEntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataBaseEntityType parseInt(int value) {
        DataBaseEntityType[] dataBaseEntityTypes = DataBaseEntityType.values();
        if (value <= 0 || value >= dataBaseEntityTypes.length) {
            return Unknown;
        } else {
            return dataBaseEntityTypes[value];
        }
    }
}
