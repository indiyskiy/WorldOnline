package model.constants.databaseenumeration;

public enum LanguageType {
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
        return languageTypes[value - 1];

    }

    public static LanguageType parse(String languageValue) {
        return LanguageType.valueOf(languageValue);
    }
}
