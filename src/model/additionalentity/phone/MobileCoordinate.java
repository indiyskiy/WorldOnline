package model.additionalentity.phone;

import com.google.gson.JsonObject;

public class MobileCoordinate {
    private double latitude;
    private double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("latitude", latitude);
        jsonObject.addProperty("longitude", longitude);
        return jsonObject;
    }
}
