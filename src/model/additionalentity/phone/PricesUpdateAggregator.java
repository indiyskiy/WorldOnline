package model.additionalentity.phone;

import java.util.ArrayList;

public class PricesUpdateAggregator {
    private ArrayList<Long> pricesForDownload = new ArrayList<>();
    private ArrayList<Long> pricesForDelete = new ArrayList<>();
    private ArrayList<Long> pricesForUpdate = new ArrayList<>();

    public ArrayList<Long> getPricesForDownload() {
        return pricesForDownload;
    }

    public void setPricesForDownload(ArrayList<Long> pricesForDownload) {
        this.pricesForDownload = pricesForDownload;
    }

    public ArrayList<Long> getPricesForDelete() {
        return pricesForDelete;
    }

    public void setPricesForDelete(ArrayList<Long> pricesForDelete) {
        this.pricesForDelete = pricesForDelete;
    }

    public ArrayList<Long> getPricesForUpdate() {
        return pricesForUpdate;
    }

    public void setPricesForUpdate(ArrayList<Long> pricesForUpdate) {
        this.pricesForUpdate = pricesForUpdate;
    }
}
