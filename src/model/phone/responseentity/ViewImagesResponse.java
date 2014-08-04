package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileViewImage;
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

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (MobileViewImage mobileViewImage : mobileViewImages) {
            JsonObject mobileViewImageObject = mobileViewImage.toJson();
            jsonArray.add(mobileViewImageObject);
        }
        jsonObject.add("mobileViewImages", jsonArray);
        return jsonObject;
    }
}
