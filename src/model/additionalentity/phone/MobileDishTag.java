package model.additionalentity.phone;

import com.google.gson.JsonObject;

public class MobileDishTag {
    private String tagName;
    private Long tagID;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tagName", tagName);
        jsonObject.addProperty("tagID", tagID);
        return jsonObject;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }
}
