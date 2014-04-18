package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 05.11.13
 * Time: 4:48
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

    public static LanguageType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        LanguageType[] languageTypes = LanguageType.values();
        if (value <= 0 || value >= languageTypes.length) {
            return Unknown;
        } else {
            return languageTypes[value];
        }
    }

    public static LanguageType parse(String languageValue) {
        return LanguageType.valueOf(languageValue);
    }
}
