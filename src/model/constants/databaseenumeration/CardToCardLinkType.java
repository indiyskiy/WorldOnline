package model.constants.databaseenumeration;

public enum CardToCardLinkType {
    Metro(1),
    RestaurantChain(2),
    Photographer(3),
    PlaceOnPhoto(4),
    LinkedPlace(5),
    Venue(6);

    private final int value;

    private CardToCardLinkType(int value) {
        this.value = value;
    }

    public static CardToCardLinkType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardToCardLinkType[] cardToCardLinkTypes = CardToCardLinkType.values();
        return cardToCardLinkTypes[value - 1];
    }

    public String getRussianValue() {
        switch (this) {

            case Metro:
                return "Метро";
            case RestaurantChain:
                return "Сеть заведений";
            case Photographer:
                return "Фотограф";
            case PlaceOnPhoto:
                return "Место на фотографии";
            case LinkedPlace:
                return "Связанное место";
            case Venue:
                return "Место проведения";
            default:
                return "Подозрительный тип-сообщите о нём программистам!";
        }
    }

    public int getValue() {
        return value;
    }

}
