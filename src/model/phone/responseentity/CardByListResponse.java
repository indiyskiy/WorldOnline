package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Status;

import java.util.LinkedList;

public class CardByListResponse extends MobileResponseEntity {
    private LinkedList<MobileCardInfo> mobileCards;

    public CardByListResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray cards = new JsonArray();
        for (MobileCardInfo mobileCardInfo : mobileCards) {
            cards.add(mobileCardInfo.toJson());
        }
        jsonObject.add("cards", cards);
        return jsonObject;
    }

    public void setMobileCards(LinkedList<MobileCardInfo> mobileCards) {
        this.mobileCards = mobileCards;
    }

    public LinkedList<MobileCardInfo> getMobileCards() {
        return mobileCards;
    }
}
