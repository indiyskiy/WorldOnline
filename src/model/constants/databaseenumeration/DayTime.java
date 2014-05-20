package model.constants.databaseenumeration;

/**
 * Created by Илья on 13.05.14.
 */
public enum DayTime {
    Night(0),
    Morning(1),
    Day(2),
    Evening(3);

    private final int value;

    private DayTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DayTime parseInt(Integer value) {
        DayTime[] dayTimes = DayTime.values();
        return dayTimes[value];
    }

}
