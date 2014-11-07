package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.additionalentity.phone.PricesUpdateAggregator;
import model.constants.Status;


public class PricesForUpdateResponse extends MobileResponseEntity {

    private PricesUpdateAggregator pricesUpdateAggregator;

    public PricesForUpdateResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray download = new JsonArray();
        for (Long priceID : pricesUpdateAggregator.getPricesForDownload()) {
            download.add(new JsonPrimitive(priceID));
        }
        jsonObject.add("download", download);

        JsonArray update = new JsonArray();
        for (Long priceID : pricesUpdateAggregator.getPricesForUpdate()) {
            update.add(new JsonPrimitive(priceID));
        }
        jsonObject.add("update", update);

        JsonArray delete = new JsonArray();
        for (Long priceID : pricesUpdateAggregator.getPricesForDelete()) {
            delete.add(new JsonPrimitive(priceID));
        }
        jsonObject.add("delete", delete);
        return jsonObject;
    }

    public PricesUpdateAggregator getPricesUpdateAggregator() {
        return pricesUpdateAggregator;
    }

    public void setPricesUpdateAggregator(PricesUpdateAggregator pricesUpdateAggregator) {
        this.pricesUpdateAggregator = pricesUpdateAggregator;
    }
}
