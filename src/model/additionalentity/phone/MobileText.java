package model.additionalentity.phone;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.constants.databaseenumeration.TextType;

/**
 * Created by Илья on 18.04.14.
 */
public class MobileText {
    String text;
    TextType textType;
    private Long textGroupID;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextType getTextType() {
        return textType;
    }

    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("textID", textGroupID);
        jsonObject.addProperty("text", text);
        jsonObject.addProperty("textType", textType.getValue());
        return jsonObject;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }
}
