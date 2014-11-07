package controller.phone.entity;


import model.database.worldonlinedb.dishes.PriceEntity;

import java.util.ArrayList;

public class PricesRegistrationRequest extends MobileRequest {
    private ArrayList<PriceEntity> priceEntities;

    public void setPriceEntities(ArrayList<PriceEntity> priceEntities) {
        this.priceEntities = priceEntities;
    }

    public ArrayList<PriceEntity> getPriceEntities() {
        return priceEntities;
    }
}
