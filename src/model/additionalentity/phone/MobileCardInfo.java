package model.additionalentity.phone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.constants.databaseenumeration.CardType;

import java.util.ArrayList;
import java.util.HashSet;

public class MobileCardInfo {
    private Long cardID;
    private ArrayList<MobileText> mobileTexts = new ArrayList<>();
    private HashSet<Long> menuIDs = new HashSet<>();
    private CardType cardType;

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public ArrayList<MobileText> getMobileTexts() {
        return mobileTexts;
    }

    public void setMobileTexts(ArrayList<MobileText> mobileTexts) {
        this.mobileTexts = mobileTexts;
    }

    public HashSet<Long> getMenuIDs() {
        return menuIDs;
    }

    public void setMenuIDs(HashSet<Long> menuIDs) {
        this.menuIDs = menuIDs;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("cardType", cardType.toString());
        JsonArray textArray = new JsonArray();
        for (MobileText mobileText : mobileTexts) {
            textArray.add(mobileText.toJson());
        }
        jsonObject.add("cardTexts", textArray);
        JsonArray menus = new JsonArray();
        for (long menuID : menuIDs) {
            menus.add(new JsonPrimitive(menuID));
        }
        jsonObject.add("menus", menus);
        return jsonObject;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }
}
