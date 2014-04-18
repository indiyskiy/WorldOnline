package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public enum DataType {
    UnknownType(0),
    IntegerType(1),
    DoubleType(2),
    StringType(3),
    LinkType(4),
    EmailType(5),
    PhoneNumberType(6),
    TimestampType(7),
    Percent(8);


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
