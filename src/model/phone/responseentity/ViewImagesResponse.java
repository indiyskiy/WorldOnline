package model.phone.responseentity;

import model.constants.Status;

import java.util.LinkedList;

public class ViewImagesResponse extends MobileResponseEntity {
    private LinkedList<MobileViewImage> mobileViewImages = new LinkedList<>();

    public ViewImagesResponse() {
        super(Status.OK);
    }

    public LinkedList<MobileViewImage> getMobileViewImages() {
        return mobileViewImages;
    }

    public void setMobileViewImages(LinkedList<MobileViewImage> mobileViewImages) {
        this.mobileViewImages = mobileViewImages;
    }
}
