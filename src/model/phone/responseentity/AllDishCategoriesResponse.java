package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileDishCategory;
import model.constants.Status;

import java.util.ArrayList;

public class AllDishCategoriesResponse extends MobileResponseEntity {
    private ArrayList<MobileDishCategory> mobileDishCategories;

    public AllDishCategoriesResponse() {
        super(Status.OK);
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray categories = new JsonArray();
        for (MobileDishCategory mobileDishCategory : mobileDishCategories) {
            categories.add(mobileDishCategory.toJson());
        }
        jsonObject.add("dishCategories", categories);
        return jsonObject;
    }

    public void setMobileDishCategories(ArrayList<MobileDishCategory> mobileDishCategories) {
        this.mobileDishCategories = mobileDishCategories;
    }

    public ArrayList<MobileDishCategory> getMobileDishCategories() {
        return mobileDishCategories;
    }
}
