package model.constants.databaseenumeration;

/**
 * Created by Илья on 17.04.14.
 */
public enum ChangingDataType {
    Card(0),
    Text(2),
    Parameter(3),
    Image(4),
    Root(5),
    CardToCardLink(6),
    Menu(7),
    Tag(8),
    Coordinate(9);

    private final int value;

    private ChangingDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ChangingDataType parseInt(Integer value) {
        if (value == null) {
            return Card;
        }
        ChangingDataType[] changingDataTypes = ChangingDataType.values();
        if (value <= 0 || value >= changingDataTypes.length) {
            return Card;
        } else {
            return changingDataTypes[value];
        }
    }
}
