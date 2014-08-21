package model.additionalentity.phone;

import com.google.gson.JsonObject;
import model.constants.databaseenumeration.ImageType;

public class MobileCardImage {
    private long imageID;
    private ImageType imageType;
    private int imageSize;
    private int imageHeight;
    private int imageWidth;

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("imageID", imageID);
        jsonObject.addProperty("cardImageType", imageType.getValue());
//        jsonObject.addProperty("imageSize", imageSize);
//        jsonObject.addProperty("imageHeight", imageHeight);
//        jsonObject.addProperty("imageWidth", imageWidth);
        return jsonObject;
    }
}
