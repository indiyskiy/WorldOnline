package model.constants.databaseenumeration;

public enum CardState {
    Unknown(0),
    Active(1),
    Hidden(2),
    NotActive(3),
    Deleted(4);

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
}
