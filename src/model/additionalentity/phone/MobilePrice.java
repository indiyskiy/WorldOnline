package model.additionalentity.phone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MobilePrice {
    private Long priceID;
    private ArrayList<MobileDish> mobileDishes = new ArrayList<>();
    private String name;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("priceID", priceID);
        jsonObject.addProperty("name", name);
        JsonArray dishes = new JsonArray();
        for (MobileDish mobileDish : mobileDishes) {
            dishes.add(mobileDish.toJson());
        }
        jsonObject.add("dishes", dishes);
        return jsonObject;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public Long getPriceID() {
        return priceID;
    }

    public ArrayList<MobileDish> getMobileDishes() {
        return mobileDishes;
    }

    public void setMobileDishes(ArrayList<MobileDish> mobileDishes) {
        this.mobileDishes = mobileDishes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
