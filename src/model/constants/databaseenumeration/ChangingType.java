package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public enum ChangingType {
    Unknown(0),
    Create(1),
    Delete(2),
    Edit(3);


    private final int value;

    private ChangingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ChangingType parseInt(int value) {
        ChangingType[] changingTypes = ChangingType.values();
        if (value <= 0 || value >=changingTypes.length) {
            return Unknown;
        } else {
            return changingTypes[value];
        }
    }
}
