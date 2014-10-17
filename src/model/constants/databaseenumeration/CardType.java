package model.constants.databaseenumeration;

public enum CardType {
    CardAboutCity(1),
    CardHandBook(2),
    CardPlace(3),
    CardRoute(4),
    CardSight(5),
    RestaurantChainCard(6),
    CardPhoto(7),
    CardPerson(8),
    CardNews(9),
    CardInformation(10);

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
        return cardTypes[value - 1];
    }

    public String getText() {
        switch (this) {

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
                return "фото объекта";
            }

            case CardPerson: {
                return "человек";
            }
            case CardNews: {
                return "новость";
            }
            case CardInformation: {
                return "информация";
            }
            default: {
                return "неизвестно";
            }
        }
    }

}
