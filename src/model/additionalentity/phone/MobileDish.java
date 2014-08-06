package model.additionalentity.phone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashSet;

public class MobileDish {
    private Long dishID;
    private double cost;
    private long dishCategoryID;
    private String name;
    private HashSet<Long> tagSet = new HashSet<>();

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dishID", dishID);
        jsonObject.addProperty("cost", cost);
        jsonObject.addProperty("dishCategoryID", dishCategoryID);
        jsonObject.addProperty("name", name);
        JsonArray tags = new JsonArray();
        for (Long tagID : tagSet) {
            tags.add(new JsonPrimitive(tagID));
        }
        jsonObject.add("tags", tags);
        return jsonObject;
    }

    public void setDishID(Long dishID) {
        this.dishID = dishID;
    }

    public Long getDishID() {
        return dishID;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

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

    public HashSet<Long> getTagSet() {
        return tagSet;
    }

    public void setTagSet(HashSet<Long> tagSet) {
        this.tagSet = tagSet;
    }


}
