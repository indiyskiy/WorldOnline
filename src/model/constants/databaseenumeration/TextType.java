package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public enum TextType {
    Unknown(0),
    Name(1),
    Address(2),
    Description(3),
    News(4),
    Offers(5);


    private final int value;

    private TextType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TextType parseInt(int value) {
        TextType[] textTypes = TextType.values();
        if (value <= 0 || value >= textTypes.length) {
            return Unknown;
        } else {
            return textTypes[value];
        }
    }
}
