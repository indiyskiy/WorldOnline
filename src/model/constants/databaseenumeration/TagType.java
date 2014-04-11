package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 04.11.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public enum TagType {
    Unknown(0),
    Cuisine(1),
    Categories(2),
    Ribbons(3);

    private final int value;

    private TagType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TagType parseInt(int value) {
        TagType[] tagTypes = TagType.values();
        if (value <= 0 || value >= tagTypes.length) {
            return Unknown;
        } else {
            return tagTypes[value];
        }
    }
}
