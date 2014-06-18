package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public enum CardType {
    Unknown(0),
    CardAboutCity(1),
    CardHandBook(2),
    CardPlace(3),
    CardRoute(4),
    CardSight(5),
    RestaurantChainCard(6),
    CardPhoto(7);

    private final int value;

    private CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        CardType[] cardTypes = CardType.values();
        if (value <= 0 || value >= cardTypes.length) {
            return Unknown;
        } else {
            return cardTypes[value];
        }
    }

    public String getText() {
        switch (this) {
            case Unknown: {
                return "неизвестно";
            }
            case CardAboutCity: {
                return "про город";
            }
            case CardHandBook: {
                return "из справочника";
            }
            case CardPlace: {
                return "место";
            }
            case CardRoute: {
                return "маршрут";
            }
            case CardSight: {
                return "достопремечательность";
            }
            case RestaurantChainCard: {
                return "сеть заведений";
            }
            case CardPhoto: {
                return "Фото объекта";
            }
            default: {
                return "неизвестно";
            }
        }
    }

}
