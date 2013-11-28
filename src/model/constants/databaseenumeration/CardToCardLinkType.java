package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 25.11.13
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public enum CardToCardLinkType {
    Unknown(0),
    Metro(1);

    private final int value;

    private CardToCardLinkType(int value) {
        this.value = value;
    }

    public static CardToCardLinkType parseInt(int value) {
        CardToCardLinkType[] cardToCardLinkTypes = CardToCardLinkType.values();
        if (value <= 0 || value >=cardToCardLinkTypes.length) {
            return Unknown;
        } else {
            return cardToCardLinkTypes[value];
        }
    }

    public int getValue() {
        return value;
    }

}
