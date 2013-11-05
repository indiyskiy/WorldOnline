package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 30.10.13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public enum LanguageType {
    Unknown(0),
    Russian(1),
    English(2);

    private final int value;

    private LanguageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LanguageType parseInt(int value) {
        LanguageType[] languageTypes = LanguageType.values();
        if (value <= 0 || value > languageTypes.length) {
            return Unknown;
        } else {
            return languageTypes[value];
        }
    }
}
