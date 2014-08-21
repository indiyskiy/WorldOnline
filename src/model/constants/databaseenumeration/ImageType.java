package model.constants.databaseenumeration;

public enum ImageType {
    Photo(1),
    ViewImage(2),
    Tag(3),
    MenuPhoto(4);

    private final int value;

    private ImageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ImageType parseInt(Integer value) {
        if (value == null) {
            return null;
        }
        ImageType[] imageTypes = ImageType.values();
        return imageTypes[value - 1];
    }

    public String getText() {
        switch (this) {
            case Photo:
                return "Фото";
            case ViewImage:
                return "Вид города";
            case Tag:
                return "Тэг";
            default:
                return "Неизвестно";
        }
    }


}
