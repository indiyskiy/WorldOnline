package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileDishTag;
import model.constants.Status;

import java.util.ArrayList;

public class AllDishTagsResponse extends MobileResponseEntity {

    private ArrayList<MobileDishTag> allDishTags;

    public AllDishTagsResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (MobileDishTag mobileDishTag : allDishTags) {
            jsonArray.add(mobileDishTag.toJson());
        }
        jsonObject.add("dishTags", jsonArray);
        return jsonObject;
    }

    public void setAllDishTags(ArrayList<MobileDishTag> allDishTags) {
        this.allDishTags = allDishTags;
    }

    public ArrayList<MobileDishTag> getAllDishTags() {
        return allDishTags;
    }
}
