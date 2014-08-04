package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Status;

import java.util.LinkedList;

public class AllCardsResponse extends MobileResponseEntity {
    private LinkedList<MobileCardInfo> cardList;

    public AllCardsResponse() {
        super(Status.OK);
    }

    public void setCardList(LinkedList<MobileCardInfo> cardList) {
        this.cardList = cardList;
    }

    public LinkedList<MobileCardInfo> getCardList() {
        return cardList;
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (MobileCardInfo mobileCardInfo : getCardList()) {
            jsonArray.add(mobileCardInfo.toJson());
        }
        jsonObject.add("allCards", jsonArray);
        return jsonObject;
    }
}
