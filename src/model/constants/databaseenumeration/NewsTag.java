package model.constants.databaseenumeration;

public enum NewsTag implements TagEnum {
    free(0, "Вход свободный", "Free admission", null);

    private final int value;
    private final String nameRu;
    private final String nameEn;
    private final String imageName;

    private NewsTag(int value, String nameRu, String nameEN, String imageName) {
        this.value = value;
        this.nameRu = nameRu;
        this.nameEn = nameEN;
        this.imageName = imageName;
    }

    public int getValue() {
        return value;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public TagEnum setByValue(String value) {
        return MiddlePrice.values()[Integer.parseInt(value)];
    }

    @Override
    public TagType getTagType() {
        return TagType.NewsTag;
    }

    @Override
    public TagEnum[] allValues() {
        return values();
    }
}
