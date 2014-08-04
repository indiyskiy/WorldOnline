package model.additionalentity.phone;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MobileText {
    String text;
    private Long textGroupID;
    private long cardParameterTypeID;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("textID", textGroupID);
        jsonObject.addProperty("value", text);
        jsonObject.addProperty("cardParameterTypeID", cardParameterTypeID);
        return jsonObject;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public void setCardParameterTypeID(long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }
}
