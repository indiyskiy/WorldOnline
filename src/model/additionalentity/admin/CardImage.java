package model.additionalentity.admin;

import model.constants.databaseenumeration.ImageType;

public class CardImage {
    private long cardImageID;
    private long imageID;
    private ImageType imageType;
    private String imageName;

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public long getCardImageID() {
        return cardImageID;
    }

    public void setCardImageID(long cardImageID) {
        this.cardImageID = cardImageID;
    }
}
