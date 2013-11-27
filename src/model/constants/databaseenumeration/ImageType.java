package model.constants.databaseenumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 24.10.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public enum ImageType {
    Unknown(0),
    CardImage(1),
    Photo(2),
    PanoramaToList(3),
    Panorama(4);

    private final int value;

    private ImageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ImageType parseInt(int value) {
        ImageType[] imageTypes = ImageType.values();
        if (value <= 0 || value > imageTypes.length) {
            return Unknown;
        } else {
            return imageTypes[value];
        }
    }
}
