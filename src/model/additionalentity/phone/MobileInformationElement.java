package model.additionalentity.phone;

import com.google.gson.JsonObject;

public class MobileInformationElement {
    private String text;
    private Long imageID;
    private int position;
    private long informationElementID;

    public void setText(String text) {
        this.text = text;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("text", text);
        jsonObject.addProperty("imageID", imageID);
        jsonObject.addProperty("position", position);
        jsonObject.addProperty("informationElementID", position);
        return jsonObject;
    }

    public void setInformationElementID(long informationElementID) {
        this.informationElementID = informationElementID;
    }

    public long getInformationElementID() {
        return informationElementID;
    }
}
