package model.constants.databaseenumeration;

/**
 * Created by Илья on 13.05.14.
 */
public enum Cloudiness {
    Clear(0),
    FewCloudy(1),
    Cloudy(2),
    VeryCloudy(3);
    private final int value;

    private Cloudiness(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Cloudiness parseInt(Integer value) {
        Cloudiness[] cloudiness = Cloudiness.values();
        return cloudiness[value];
    }

    public String toStringHTML() {
        switch (this) {
            case Clear:
                return "ясно";
            case FewCloudy:
                return "малооблачно";
            case Cloudy:
                return "облачно";
            case VeryCloudy:
                return "пасмурно";
            default:
                return "\"unknown value\"";
        }
    }

}
