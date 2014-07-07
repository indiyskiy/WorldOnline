package model.constants.databaseenumeration;

public enum TagType {
    Cuisine(0, "Кухня"),
    Categories(1, "Категории"),
    Ribbons(2, "Ленточки");

    private final int value;
    private String russianName;

    private TagType(int value, String russianName) {
        this.value = value;
        this.russianName = russianName;
    }

    public int getValue() {
        return value;
    }

    public static TagType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        TagType[] tagTypes = TagType.values();
        return tagTypes[value];
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }
}
