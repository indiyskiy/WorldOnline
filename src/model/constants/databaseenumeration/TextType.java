package model.constants.databaseenumeration;

public enum TextType {
    Name(1),
    Address(2),
    Description(3),
    News(4),
    Offers(5),
    Biography(6),
    Story(7),
    Recomend(8),
    Facts(9),
    Legends(10),
    Literature(11),
    Anecdotes(12),
    Films(13),
    FamousPassers(14),
    Citations(15),
    Wikipedia(16),
    Wikimapia(17),
    Encspb(18);


    private final int value;

    private TextType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TextType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        TextType[] textTypes = TextType.values();
        return textTypes[value];
    }

    public CardParameterType getType() {
        return CardParameterType.valueOf(this.toString());
    }

}
