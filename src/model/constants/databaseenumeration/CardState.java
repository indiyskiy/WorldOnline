package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
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

    public static CardState parseInt(int value) {
        CardState[] changingTypes = CardState.values();
        if (value <= 0 || value > changingTypes.length) {
            return Unknown;
        } else {
            return changingTypes[value];
        }
    }
}
