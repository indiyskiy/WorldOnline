package model.constants;

public enum ApplicationBlock {
    Main(0),
    Social(1),
    Reviews(2),
    Description(3),
    Details(4),
    WiFi(5),
    Facts(6),
    Encyclopedia(7),
    Header(8);

    private final int value;

    private ApplicationBlock(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplicationBlock parseInt(Integer value) {
        if (value == null) {
            return Main;
        }
        ApplicationBlock[] applicationBlocks = ApplicationBlock.values();
        return applicationBlocks[value];
    }

}
