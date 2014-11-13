package controller.phone.entity;

import model.database.worldonlinedb.CardEntity;

import java.util.ArrayList;

public class CardRegistrationRequest extends MobileRequest {
//    private ArrayList<CardEntity> cardEntities;

    private ArrayList<Long> cardIDs;


//    public void setCardEntities(ArrayList<CardEntity> cardEntities) {
//        this.cardEntities = cardEntities;
//    }

//    public ArrayList<CardEntity> getCardEntities() {
//        return cardEntities;
//    }


    public ArrayList<Long> getCardIDs() {
        return cardIDs;
    }

    public void setCardIDs(ArrayList<Long> cardIDs) {
        this.cardIDs = cardIDs;
    }
}
