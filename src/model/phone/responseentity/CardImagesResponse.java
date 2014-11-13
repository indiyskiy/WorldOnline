package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileCardImagePair;
import model.constants.Status;

import java.util.LinkedList;

public class CardImagesResponse extends MobileResponseEntity {
    private LinkedList<MobileCardImagePair> mobileCardImagePairs = new LinkedList<>();

    public CardImagesResponse() {
        super(Status.OK);
    }

    public LinkedList<MobileCardImagePair> getMobileCardImagePairs() {
        return mobileCardImagePairs;
    }

    public void setMobileCardImagePairs(LinkedList<MobileCardImagePair> mobileCardImagePairs) {
        this.mobileCardImagePairs = mobileCardImagePairs;
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (MobileCardImagePair mobileCardImagePair : mobileCardImagePairs) {
            JsonObject mobileViewImageObject = mobileCardImagePair.toJson();
            jsonArray.add(mobileViewImageObject);
        }
        jsonObject.add("cardImages", jsonArray);
        return jsonObject;
    }
}
