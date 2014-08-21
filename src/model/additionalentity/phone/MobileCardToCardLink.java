package model.additionalentity.phone;


import com.google.gson.JsonObject;

public class MobileCardToCardLink {
    private Long linkID;
    private String cardID;
    private String linkType;

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkType() {
        return linkType;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("linkID", linkID);
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("linkType", linkType);
        return jsonObject;
    }
}
