package model.constants.databaseenumeration;

public enum UpdateType {
    Unknown(0),
    Create(1),
    Update(2),
    Delete(3);

    private final int value;

    private UpdateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UpdateType parseInt(Integer value) {
        if (value == null) {
            return Create;
        }
        UpdateType[] updateTypes = UpdateType.values();
        if (value <= 0 || value >= updateTypes.length) {
            return Unknown;
        } else {
            return updateTypes[value];
        }
    }
}
