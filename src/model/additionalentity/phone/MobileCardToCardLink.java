package model.additionalentity.phone;


import com.google.gson.JsonObject;
import model.constants.databaseenumeration.CardToCardLinkType;

public class MobileCardToCardLink {
    private Long linkID;
    private Long cardID;
    private CardToCardLinkType linkType;

    public void setLinkID(Long linkID) {
        this.linkID = linkID;
    }

    public Long getLinkID() {
        return linkID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public Long getCardID() {
        return cardID;
    }

    public void setLinkType(CardToCardLinkType linkType) {
        this.linkType = linkType;
    }

    public CardToCardLinkType getLinkType() {
        return linkType;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("linkID", linkID);
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("linkType", linkType.getValue());
        return jsonObject;
    }
}
