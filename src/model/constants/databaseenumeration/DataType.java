package model.constants.databaseenumeration;

public enum DataType {
    UnknownType(0),
    IntegerType(1),
    DoubleType(2),
    StringType(3),
    LinkType(4),
    EmailType(5),
    PhoneNumberType(6),
    TimestampType(7),
    Percent(8),
    Coast(9);


    private final int value;

    private DataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        DataType[] dataTypes = DataType.values();
        if (value <= 0 || value >= dataTypes.length) {
            return UnknownType;
        } else {
            return dataTypes[value];
        }
    }
}
