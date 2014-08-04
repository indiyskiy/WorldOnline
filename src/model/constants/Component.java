package model.constants;

public enum Component {
    Global(0),
    Admin(1),
    Database(2),
    Parser(3),
    Phone(4),
    Test(5);

    private final int value;

    private Component(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Component parseInt(int value) {
        Component[] changingTypes = Component.values();
        if (value <= 0 || value >= changingTypes.length) {
            return Global;
        } else {
            return changingTypes[value];
        }
    }
}
