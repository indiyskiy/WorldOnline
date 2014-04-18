package model.constants.databaseenumeration;


public enum UpdateStatus {
    Unknown(0),
    Stable(1),
    Critical(2),
    Important(3),
    Unimportant(4);

    private final int value;

    private UpdateStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UpdateStatus parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        UpdateStatus[] updateStatuses = UpdateStatus.values();
        if (value <= 0 || value >= updateStatuses.length) {
            return Unknown;
        } else {
            return updateStatuses[value];
        }
    }
}
