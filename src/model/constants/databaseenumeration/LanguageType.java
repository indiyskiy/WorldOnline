package model.constants.databaseenumeration;

public enum LanguageType {
    Russian(1, "Русский"),
    English(2, "Английский");

    private final int value;
    private final String stringValue;

    private LanguageType(int value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public int getValue() {
        return value;
    }

    public static LanguageType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        LanguageType[] languageTypes = LanguageType.values();
        return languageTypes[value - 1];

    }

    public static LanguageType parse(String languageValue) {
        return LanguageType.valueOf(languageValue);
    }

    public String getStringValue() {
        return stringValue;
    }

}
