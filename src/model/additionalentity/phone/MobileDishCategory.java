package model.additionalentity.phone;

import com.google.gson.JsonObject;

public class MobileDishCategory {
    private long dishCategoryID;
    private String name;

    public void setDishCategoryID(long dishCategoryID) {
        this.dishCategoryID = dishCategoryID;
    }

    public long getDishCategoryID() {
        return dishCategoryID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dishCategoryID", dishCategoryID);
        jsonObject.addProperty("name", name);
        return null;
    }
}
