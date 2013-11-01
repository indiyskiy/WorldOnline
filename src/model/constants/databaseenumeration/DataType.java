package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public enum DataType {
    Unknown(0),
    Integer(1),
    Double(2),
    String(3),
    Link(4),
    Email(5),
    PhoneNumber(6),
    Timestamp(7);


    private final int value;

    private DataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataType parseInt(int value) {
        DataType[] dataTypes = DataType.values();
        if (value <= 0 || value > dataTypes.length) {
            return Unknown;
        } else {
            return dataTypes[value];
        }
    }
}
