package model.constants;

public enum Month {
    January(1, "январь"),
    February(2, "февраль "),
    March(3, "март"),
    April(4, "апрель"),
    May(5, "май"),
    June(6, "июнь"),
    July(7, "июль"),
    August(8, "август"),
    September(9, "сентябрь"),
    October(10, "октябрь"),
    November(11, "ноябрь"),
    December(12, "декабрь");

    private final String name;
    private final int value;

    Month(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Month parseInt(int i) {
        return Month.values()[i - 1];
    }
}
