package model.constants.databaseenumeration;

public enum CardImageType {
    Photo(1),
    ViewImage(2);

    private final int value;

    private CardImageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardImageType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardImageType[] cardImageTypes = CardImageType.values();
        return cardImageTypes[value - 1];
    }

    public String getText() {
        switch (this) {
            case Photo:
                return "Фото";
            case ViewImage:
                return "Вид города";
            default:
                return "Неизвестно";
        }
    }


}
