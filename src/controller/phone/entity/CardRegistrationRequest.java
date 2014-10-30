package controller.phone.entity;

import model.database.worldonlinedb.CardEntity;

import java.util.ArrayList;

public class CardRegistrationRequest extends MobileRequest {
    private ArrayList<CardEntity> cardEntities;

    public void setCardEntities(ArrayList<CardEntity> cardEntities) {
        this.cardEntities = cardEntities;
    }

    public ArrayList<CardEntity> getCardEntities() {
        return cardEntities;
    }
}
