package model.constants.databaseenumeration;

public enum ImageType {
    Photo(1, "Фото"),
    ViewImage(2, "Вид города"),
    Tag(3, "Тэг"),
    MenuPhoto(4, "Фото иконки в меню"),
    MenuIcon(5, "Иконка меню");

    private final int value;
    private final String text;

    private ImageType(int value, String text) {
        this.value = value;
        this.text = text;
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
        return text;
    }


    public static ImageType[] cardTypes() {
        return new ImageType[]{Photo, ViewImage, MenuPhoto};
    }
}

