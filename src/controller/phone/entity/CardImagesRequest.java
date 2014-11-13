package controller.phone.entity;

import model.constants.databaseenumeration.ImageType;

public class CardImagesRequest extends MobileRequest {

    private ImageType imageType;

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }
}
