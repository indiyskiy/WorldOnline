package model.constants.databaseenumeration;

/**
 * Created by Илья on 19.05.14.
 */
public enum WindDirection {
    North(1),
    NorthEast(2),
    East(3),
    SouthEast(4),
    South(5),
    SouthWest(6),
    West(7),
    NorthWest(8);

    private final int value;

    WindDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WindDirection parseInt(int windDirection) {
        return values()[windDirection - 1];
    }

    public String toString() {
        switch (this) {
            case North:
                return "северный";
            case NorthEast:
                return "северо-восточный";
            case East:
                return "восточный";
            case SouthEast:
                return "юго-восточный";
            case South:
                return "южный";
            case SouthWest:
                return "юго-западный";
            case West:
                return "западный";
            case NorthWest:
                return "северо-западный";
            default:
                return "\'unknown_value\'";
        }
    }
}
