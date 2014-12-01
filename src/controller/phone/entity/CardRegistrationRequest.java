package controller.phone.entity;

import model.database.worldonlinedb.CardEntity;

import java.util.ArrayList;

public class CardRegistrationRequest extends MobileRequest {

    private ArrayList<Long> cardIDs;

    public ArrayList<Long> getCardIDs() {
        return cardIDs;
    }

    public void setCardIDs(ArrayList<Long> cardIDs) {
        this.cardIDs = cardIDs;
    }
}
