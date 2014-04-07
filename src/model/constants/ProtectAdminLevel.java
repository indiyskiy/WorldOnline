package model.constants;

/**
 * Created by Илья on 28.03.14.
 */
public enum ProtectAdminLevel {
    Unregistered(0),
    Registered(1),
    Client(2),
    LowModerator(3),
    HeightModerator(4),
    Administrator(5);

    private int value;

    ProtectAdminLevel(int value) {
        this.value = value;
    }


    public static ProtectAdminLevel parseInt(int value) {
        ProtectAdminLevel[] protectAdminLevel = ProtectAdminLevel.values();
        if (value <= 0 || value >= protectAdminLevel.length) {
            return Unregistered;
        } else {
            return protectAdminLevel[value];
        }
    }

    public int getValue() {
        return value;
    }

    public static ProtectAdminLevel parse(String string) {
        return valueOf(string);
    }
}
