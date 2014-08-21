package model.constants.databaseenumeration;

public enum MiddlePrice implements TagEnum {
    small(0, "до 500р", "cheaper 500 rubles", null),
    middle(1, "500-1000р", "500-1000 rubles", null),
    big(2, "1500-2500р", "1500-2500 rubles", null),
    large(3, "больше 2500р", "2500 rubles and more", null);

    private final String nameRu;
    private final String nameEn;
    private final int value;
    private final String imageName;

    MiddlePrice(int value, String nameRu, String nameEn, String imageName) {
        this.nameRu = nameRu;
        this.value = value;
        this.nameEn = nameEn;
        this.imageName = imageName;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public int getValue() {
        return value;
    }

    public String getImageName() {
        return imageName;
    }

    public MiddlePrice setByValue(String value) {
        return MiddlePrice.values()[Integer.parseInt(value)];
    }

    public TagType getTagType() {
        return TagType.MiddlePrice;
    }

    @Override
    public TagEnum[] allValues() {
        return values();
    }

}
