package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 03.03.14
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public enum MobilePlatform {
    Unknown(0),
    IPhone(1),
    Android(2),
    WinPhone(3);

    private final int value;

    private MobilePlatform(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MobilePlatform parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        MobilePlatform[] mobilePlatforms = MobilePlatform.values();
        if (value <= 0 || value >= mobilePlatforms.length) {
            return Unknown;
        } else {
            return mobilePlatforms[value];
        }
    }

    public static MobilePlatform parse(String mobilePlatformValue) {
        return MobilePlatform.valueOf(mobilePlatformValue);
    }
}
