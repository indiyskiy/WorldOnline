package controller.phone.entity;


import java.util.ArrayList;

public class PricesRegistrationRequest extends MobileRequest {

    private ArrayList<Long> pricesIDs;

    public ArrayList<Long> getPricesIDs() {
        return pricesIDs;
    }

    public void setPricesIDs(ArrayList<Long> pricesIDs) {
        this.pricesIDs = pricesIDs;
    }
}

