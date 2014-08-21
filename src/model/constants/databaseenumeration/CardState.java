package model.constants.databaseenumeration;

public enum CardState {
    Unknown(0),
    Active(1),
    NotActive(2),
    Deleted(3);

    private final int value;

    private CardState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardState parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardState[] changingTypes = CardState.values();
        if (value <= 0 || value >= changingTypes.length) {
            return Unknown;
        } else {
            return changingTypes[value];
        }
    }

    public String getRussianValue() {
        switch (this) {
            case Active:
                return "Активна";
            case NotActive:
                return "Не активна";
            case Deleted:
                return "Удалена";
            default:
                return "Карточка шрёдингера - расскажите об этом занимательном факте программисту!";
        }
    }
}
