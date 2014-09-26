package model.constants;

public enum ApplicationBlock {
    Store(0, 10),
    Social(1, 4),
    Reviews(2, 9),
    AdditionalInformation(3, 3),
    Details(4, 6),
    WiFi(5, 2),
    Facts(6, 7),
    Encyclopedia(7, 8),
    Header(8, 0),
    Selling(9, 11),
    Description(10, 1),
    Cuisine(11, 5),
    AdditionalTags(12, 12);

    private final int value;
    private final int position;

    private ApplicationBlock(int value, int position) {
        this.value = value;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public static ApplicationBlock parseInt(Integer value) {
        if (value == null) {
            return Store;
        }
        ApplicationBlock[] applicationBlocks = ApplicationBlock.values();
        return applicationBlocks[value];
    }

    public String getRusText() {
        switch (this) {
            case Store:
                return "Ссылки на приложения";
            case Social:
                return "Социальные сети";
            case Reviews:
                return "Отзывы";
            case AdditionalInformation:
                return "Дополнительная информация";
            case Details:
                return "Детали";
            case WiFi:
                return "ВайФай";
            case Facts:
                return "Факты";
            case Encyclopedia:
                return "Энциклопедия";
            case Header:
                return "Заголовок";
            case Selling:
                return "Продажи";
            case Description:
                return "Описание";
            case Cuisine:
                return "Кухня";
            case AdditionalTags:
                return "Дополнительные теги";
            default:
                return "Неведомый блок, знаменующий поиск багов";
        }
    }

    public static ApplicationBlock[] getTagBlocks() {
        return new ApplicationBlock[]{Cuisine, AdditionalTags};
    }
}
