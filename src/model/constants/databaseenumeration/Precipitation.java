package model.constants.databaseenumeration;

/**
 * Created by Илья on 13.05.14.
 */
public enum Precipitation {
    Rain(4),
    Shower(5),
    Snow(6),
    Snow2(7),
    Lightning(8),
    NoData(9),
    Clear(10);


    private final int value;

    private Precipitation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Precipitation parseInt(Integer value) {
        Precipitation[] precipitations = Precipitation.values();
        return precipitations[value];
    }
}
