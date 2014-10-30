package model.additionalentity.phone;


import java.util.ArrayList;

public class CardUpdateAggregator {
    ArrayList<Long> cardsForUpdate = new ArrayList<>();
    ArrayList<Long> cardsForDownload = new ArrayList<>();
    ArrayList<Long> cardsForDelete = new ArrayList<>();

    public ArrayList<Long> getCardsForUpdate() {
        return cardsForUpdate;
    }

    public void setCardsForUpdate(ArrayList<Long> cardsForUpdate) {
        this.cardsForUpdate = cardsForUpdate;
    }

    public ArrayList<Long> getCardsForDownload() {
        return cardsForDownload;
    }

    public void setCardsForDownload(ArrayList<Long> cardsForDownload) {
        this.cardsForDownload = cardsForDownload;
    }

    public ArrayList<Long> getCardsForDelete() {
        return cardsForDelete;
    }

    public void setCardsForDelete(ArrayList<Long> cardsForDelete) {
        this.cardsForDelete = cardsForDelete;
    }
}
