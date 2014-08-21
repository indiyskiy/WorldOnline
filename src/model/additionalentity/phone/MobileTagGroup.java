package model.additionalentity.phone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MobileTagGroup {

    private ArrayList<MobileTag> mobileTags = new ArrayList<>();
    private Long id;
    private Long cardID;
    private String name;
    private int position;
    private int block;
    private int viewType;

    public JsonElement toJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("position", position);
        jsonObject.addProperty("block", block);
        jsonObject.addProperty("viewType", viewType);
        JsonArray jsonArray = new JsonArray();
        for (MobileTag mobileTag : mobileTags) {
            jsonArray.add(mobileTag.toJson());
        }
        jsonObject.add("tags", jsonArray);
        return jsonObject;
    }

    public ArrayList<MobileTag> getMobileTags() {
        return mobileTags;
    }

    public void setMobileTags(ArrayList<MobileTag> mobileTags) {
        this.mobileTags = mobileTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
