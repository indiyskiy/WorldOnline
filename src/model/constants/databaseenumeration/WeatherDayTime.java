package model.constants.databaseenumeration;

/**
 * Created by Илья on 13.05.14.
 */
public enum WeatherDayTime {
    Night(0),
    Morning(1),
    Day(2),
    Evening(3);

    private final int value;

    private WeatherDayTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WeatherDayTime parseInt(Integer value) {
        WeatherDayTime[] weatherDayTimes = WeatherDayTime.values();
        return weatherDayTimes[value];
    }
}
