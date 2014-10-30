package model.additionalentity.phone;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MobileRouteElement {

    private int number;
    private long routeElementID;
    private long placeCardID;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setRouteElementID(long routeElementID) {
        this.routeElementID = routeElementID;
    }

    public long getRouteElementID() {
        return routeElementID;
    }

    public void setPlaceCardID(long placeCardID) {
        this.placeCardID = placeCardID;
    }

    public long getPlaceCardID() {
        return placeCardID;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("position", number);
        jsonObject.addProperty("placeCardID", placeCardID);
        return jsonObject;
    }
}
