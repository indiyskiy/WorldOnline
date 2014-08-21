package model.additionalentity.phone;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MobileTag {
    private Long iconID;
    private Long tagID;
    private String name;

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("iconID", iconID);
        jsonObject.addProperty("tagID", tagID);
        jsonObject.addProperty("name", name);
        return jsonObject;
    }

    public Long getIconID() {
        return iconID;
    }

    public void setIconID(Long iconID) {
        this.iconID = iconID;
    }

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
