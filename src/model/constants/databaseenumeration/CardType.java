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
    CardHotel(3),
    CardMeal(4),
    CardRelax(5),
    CardRoute(6),
    CardShopping(7),
    CardSight(8),
    RestaurantChainCard(9);

    private final int value;

    private CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardType parseInt(int value) {
        CardType[] cardTypes = CardType.values();
        if (value <= 0 || value >=cardTypes.length) {
            return Unknown;
        } else {
            return cardTypes[value];
        }
    }

}
