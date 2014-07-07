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
    private ArrayList<MobileCardImage> images = new ArrayList<>();
    private ArrayList<MobileParameter> mobileParameters = new ArrayList<>();
    private MobileCoordinate coordinate;
    private int order;
    private HashSet<Long> tagIDs = new HashSet<>();

    public MobileCoordinate getCoordinate() {
        return coordinate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public ArrayList<MobileText> getMobileTexts() {
        return mobileTexts;
    }

    public HashSet<Long> getMenuIDs() {
        return menuIDs;
    }

    public void setMobileTexts(ArrayList<MobileText> mobileTexts) {
        this.mobileTexts = mobileTexts;
    }

    public void setMenuIDs(HashSet<Long> menuIDs) {
        this.menuIDs = menuIDs;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setImages(ArrayList<MobileCardImage> images) {
        this.images = images;
    }

    public ArrayList<MobileParameter> getMobileParameters() {
        return mobileParameters;
    }

    public void setMobileParameters(ArrayList<MobileParameter> mobileParameters) {
        this.mobileParameters = mobileParameters;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public ArrayList<MobileCardImage> getImages() {
        return images;
    }

    public void setCoordinate(MobileCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public HashSet<Long> getTagIDs() {
        return tagIDs;
    }

    public void setTagIDs(HashSet<Long> tagIDs) {
        this.tagIDs = tagIDs;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cardID", cardID);
        jsonObject.addProperty("cardType", cardType.getValue());
        jsonObject.addProperty("order", order);
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
        JsonArray tags = new JsonArray();
        for (long tagId : tagIDs) {
            tags.add(new JsonPrimitive(tagId));
        }
        jsonObject.add("tags", tags);
        JsonArray imagesJson = new JsonArray();
        for (MobileCardImage mobileCardImage : images) {
            imagesJson.add(mobileCardImage.toJson());
        }
        jsonObject.add("images", imagesJson);
        JsonArray parametersJson = new JsonArray();
        for (MobileParameter mobileParameter : mobileParameters) {
            parametersJson.add(mobileParameter.toJson());
        }
        jsonObject.add("parameters", parametersJson);
        if (coordinate != null) {
            JsonObject coordinateJson = coordinate.toJson();
            jsonObject.add("coordinate", coordinateJson);
        }
        return jsonObject;
    }
}
