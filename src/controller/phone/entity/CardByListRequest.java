package controller.phone.entity;

import java.util.ArrayList;

public class CardByListRequest extends MobileRequest {
    private ArrayList<Object> cardIDs;

    public void setCardIDs(ArrayList<Object> cardIDs) {
        this.cardIDs = cardIDs;
    }

    public ArrayList<Object> getCardIDs() {
        return cardIDs;
    }
}
