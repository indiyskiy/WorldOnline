package model.constants.databaseenumeration;

public enum CardEventType {
    TestEvent0(0),
    TestEvent1(1);

    private final int value;

    private CardEventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardEventType parseInt(Integer value) {
        CardEventType[] cardEventTypes = CardEventType.values();
        return cardEventTypes[value];
    }

}
