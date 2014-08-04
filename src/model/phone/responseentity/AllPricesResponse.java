package model.phone.responseentity;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobilePrice;
import model.constants.Status;

import java.util.ArrayList;

public class AllPricesResponse extends MobileResponseEntity {
    private ArrayList<MobilePrice> mobilePrices;

    public AllPricesResponse() {
        super(Status.OK);
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray price = new JsonArray();
        for (MobilePrice mobilePrice : mobilePrices) {
            price.add(mobilePrice.toJson());
        }
        jsonObject.add("prices", price);
        return jsonObject;
    }

    public void setMobilePrices(ArrayList<MobilePrice> mobileDishes) {
        this.mobilePrices = mobileDishes;
    }


}
