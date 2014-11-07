package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobilePrice;
import model.constants.Status;

import java.util.ArrayList;

public class PriceByListResponse extends MobileResponseEntity {
    private ArrayList<MobilePrice> mobilePrices;

    public PriceByListResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray prices = new JsonArray();
        for (MobilePrice mobilePriceInfo : mobilePrices) {
            prices.add(mobilePriceInfo.toJson());
        }
        jsonObject.add("prices", prices);
        return jsonObject;
    }

    public void setMobilePrices(ArrayList<MobilePrice> mobilePrices) {
        this.mobilePrices = mobilePrices;
    }

    public ArrayList<MobilePrice> getMobilePrices() {
        return mobilePrices;
    }
}
