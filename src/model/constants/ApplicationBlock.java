package model.constants;

public enum ApplicationBlock {
    Main(0),
    Social(1),
    Reviews(2),
    AdditionalInformation(3),
    Details(4),
    WiFi(5),
    Facts(6),
    Encyclopedia(7),
    Header(8),
    Selling(9),
    Description(10),
    Cuisine(11),
    AdditionalTags(12);

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

    public String getRusText() {
        switch (this) {
            case Main:
                return "основной";
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
                return "Инцеклопедия";
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

}
