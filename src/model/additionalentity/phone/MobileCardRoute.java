package model.additionalentity.phone;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MobileCardRoute {
    private Long routeID;
    private ArrayList<MobileRouteElement> mobileRouteElements = new ArrayList<>();
    private ArrayList<MobileRouteCoordinate> mobileRouteCoordinates = new ArrayList<>();

    public ArrayList<MobileRouteElement> getMobileRouteElements() {
        return mobileRouteElements;
    }

    public void setMobileRouteElements(ArrayList<MobileRouteElement> mobileRouteElements) {
        this.mobileRouteElements = mobileRouteElements;
    }

    public ArrayList<MobileRouteCoordinate> getMobileRouteCoordinates() {
        return mobileRouteCoordinates;
    }

    public void setMobileRouteCoordinates(ArrayList<MobileRouteCoordinate> mobileRouteCoordinates) {
        this.mobileRouteCoordinates = mobileRouteCoordinates;
    }

    public Long getRouteID() {
        return routeID;
    }

    public void setRouteID(Long routeID) {
        this.routeID = routeID;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("routeID", routeID);
        JsonArray routeJson = new JsonArray();
        for (MobileRouteElement element : mobileRouteElements) {
            routeJson.add(element.toJson());
        }
        jsonObject.add("elements", routeJson);
        JsonArray pointsJson = new JsonArray();
        for (MobileRouteCoordinate coordinate : mobileRouteCoordinates) {
            pointsJson.add(coordinate.toJson());
        }
        jsonObject.add("points", pointsJson);
        return jsonObject;
    }
}
