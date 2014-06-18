package model.phone.responseentity;

import com.google.gson.JsonObject;

/**
 * Created by Илья on 21.05.14.
 */
public class MobileViewImage {
    private long cardID;
    private long imageID;

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("imageID", imageID);
        return jsonObject;
    }
}
