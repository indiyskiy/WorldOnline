package model.additionalentity.phone;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.constants.databaseenumeration.CardType;

import javax.swing.*;
import java.util.ArrayList;

public class MobileCardInfo {
    private Long cardID;
    private ArrayList<MobileText> mobileTexts = new ArrayList<>();
    private ArrayList<Long> menuIDs = new ArrayList<>();
    private CardType cardType;
    private ArrayList<MobileCardImage> images = new ArrayList<>();
    private ArrayList<MobileParameter> mobileParameters = new ArrayList<>();
    private MobileCoordinate coordinate;
    private int order;
    private ArrayList<Long> tagIDs = new ArrayList<>();
    private Long priceID;
    private ArrayList<MobileInformationElement> informationElements = new ArrayList<>();
    private ArrayList<MobileCardToCardLink> sourceLinks = new ArrayList<>();
    private ArrayList<MobileCardRoute> mobileCardRoutes = new ArrayList<>();

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

    public ArrayList<Long> getMenuIDs() {
        return menuIDs;
    }

    public ArrayList<MobileParameter> getMobileParameters() {
        return mobileParameters;
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

    public ArrayList<Long> getTagIDs() {
        return tagIDs;
    }

    public ArrayList<MobileInformationElement> getInformationElements() {
        return informationElements;
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
        if (priceID != null && priceID != 0) {
            jsonObject.addProperty("priceID", priceID);
        }
        JsonArray sourceLinksJson = new JsonArray();
        for (MobileCardToCardLink sourceLink : sourceLinks) {
            sourceLinksJson.add(sourceLink.toJson());
        }
        jsonObject.add("sourceLinks", sourceLinksJson);

        JsonArray informationJson = new JsonArray();
        for (MobileInformationElement informationElement : informationElements) {
            informationJson.add(informationElement.toJson());
        }
        jsonObject.add("information", informationJson);
        return jsonObject;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public ArrayList<MobileCardToCardLink> getSourceLinks() {
        return sourceLinks;
    }


    public ArrayList<MobileCardRoute> getMobileCardRoutes() {
        return mobileCardRoutes;
    }

    public void setMobileCardRoutes(ArrayList<MobileCardRoute> mobileCardRoutes) {
        this.mobileCardRoutes = mobileCardRoutes;
    }
}
