package model.phone.responseentity;

import model.constants.Status;

import java.util.LinkedList;

/**
 * Created by Илья on 22.05.14.
 */
public class GetViewImagesResponse extends MobileResponseEntity {
    private LinkedList<MobileViewImage> mobileViewImages = new LinkedList<>();

    public GetViewImagesResponse() {
        super(Status.OK);
    }

    public LinkedList<MobileViewImage> getMobileViewImages() {
        return mobileViewImages;
    }

    public void setMobileViewImages(LinkedList<MobileViewImage> mobileViewImages) {
        this.mobileViewImages = mobileViewImages;
    }
}
