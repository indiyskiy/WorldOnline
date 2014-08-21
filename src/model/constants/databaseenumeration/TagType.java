package model.constants.databaseenumeration;

import model.constants.ApplicationBlock;

public enum TagType {
    Cuisine(0, "Кухня", "Cuisine", ApplicationBlock.Cuisine, TagViewType.Icons),
    Categories(1, "Категории", "Categories", ApplicationBlock.AdditionalTags, TagViewType.Icons),
    Ribbons(2, "Ленточки", "Ribbons", ApplicationBlock.AdditionalTags, TagViewType.MenuIcon),
    NoBarriers(3, "БезБарьеров", "No Barriers", ApplicationBlock.AdditionalTags, TagViewType.Icons),
    MiddlePrice(4, "Средний чек", "Middle Price", ApplicationBlock.Cuisine, TagViewType.MenuIcon),
    PetersburgCard(5, "Карта гостя", "", ApplicationBlock.AdditionalTags, TagViewType.Icons),
    NewsTag(6, "", "NewsTag", ApplicationBlock.AdditionalTags, TagViewType.Icons);

    private final int value;
    private final String russianName;
    private final String englishName;
    private final ApplicationBlock applicationBlock;
    private final TagViewType tagViewType;

    private TagType(int value, String russianName, String englishName, ApplicationBlock applicationBlock, TagViewType tagViewType) {
        this.value = value;
        this.russianName = russianName;
        this.englishName = englishName;
        this.applicationBlock = applicationBlock;
        this.tagViewType = tagViewType;
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


    public String getEnglishName() {
        return englishName;
    }

    public ApplicationBlock getApplicationBlock() {
        return applicationBlock;
    }

    public TagViewType getTagViewType() {
        return tagViewType;
    }
}
