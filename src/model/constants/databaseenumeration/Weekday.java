package model.constants.databaseenumeration;

public enum Weekday {
    Sunday(0),
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6);

    private final int value;

    private Weekday(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Weekday parseInt(Integer value) {
        Weekday[] weekdays = Weekday.values();
        return weekdays[value];
    }
}
