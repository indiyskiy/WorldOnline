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
        return precipitations[value - 4];
    }

    public String toStringHTML() {
        switch (this) {
            case Rain:
                return "дождь";
            case Shower:
                return "ливень";
            case Snow:
                return "снег";
            case Snow2:
                return "снег";
            case Lightning:
                return "гроза";
            case NoData:
                return "неизвестно";
            case Clear:
                return "без осадков";
            default:
                return "неизвестно";
        }
    }

    public String toStringHTML(int rpow, int spow) {
        switch (this) {
            case Rain:
                if (rpow == 1) {
                    return toStringHTML();
                } else {
                    return "возможен " + toStringHTML();
                }
            case Shower:
                if (rpow == 1) {
                    return toStringHTML();
                } else {
                    return "возможен " + toStringHTML();
                }
            case Snow:
                if (rpow == 1) {
                    return toStringHTML();
                } else {
                    return "возможен " + toStringHTML();
                }
            case Snow2:
                if (rpow == 1) {
                    return toStringHTML();
                } else {
                    return "возможен " + toStringHTML();
                }
            case Lightning:
                if (spow == 1) {
                    return toStringHTML();
                } else {
                    return "возможно " + toStringHTML();
                }
            case NoData:
                return "неизвестно";
            case Clear:
                return "без осадков";
            default:
                return "неизвестно";
        }
    }
}
