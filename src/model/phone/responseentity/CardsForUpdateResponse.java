package model.phone.responseentity;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.additionalentity.phone.CardUpdateAggregator;
import model.constants.Status;

public class CardsForUpdateResponse extends MobileResponseEntity {

    private CardUpdateAggregator cardUpdateAggregator;

    public CardsForUpdateResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("status", getStatus().getValue());
        JsonArray download = new JsonArray();
        for (Long cardID : cardUpdateAggregator.getCardsForDownload()) {
            download.add(new JsonPrimitive(cardID));
        }
        jsonObject.add("download", download);

        JsonArray update = new JsonArray();
        for (Long cardID : cardUpdateAggregator.getCardsForUpdate()) {
            update.add(new JsonPrimitive(cardID));
        }
        jsonObject.add("update", update);

        JsonArray delete = new JsonArray();
        for (Long cardID : cardUpdateAggregator.getCardsForDelete()) {
            delete.add(new JsonPrimitive(cardID));
        }
        jsonObject.add("delete", delete);
        return jsonObject;
    }

    public void setCardUpdateAggregator(CardUpdateAggregator cardUpdateAggregator) {
        this.cardUpdateAggregator = cardUpdateAggregator;
    }

    public CardUpdateAggregator getCardUpdateAggregator() {
        return cardUpdateAggregator;
    }
}
