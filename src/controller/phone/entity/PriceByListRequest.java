package controller.phone.entity;


import java.util.ArrayList;

public class PriceByListRequest extends MobileRequest {
    private ArrayList<Object> priceIDs;

    public void setPriceIDs(ArrayList<Object> priceIDs) {
        this.priceIDs = priceIDs;
    }

    public ArrayList<Object> getPriceIDs() {
        return priceIDs;
    }
}
