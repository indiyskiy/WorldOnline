package model.additionalentity.admin;

import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;

import java.sql.Timestamp;

public class CardInfo {
    private long cardID;
    private String name;
    private CardState cardState;
    private CardType cardType;
    private Timestamp creationTime;
    private Timestamp updateTime;
//    private Integer numberInList;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

//    public void setNumberInList(Integer numberInList) {
//        this.numberInList = numberInList;
//    }
//
//    public Integer getNumberInList() {
//        return numberInList;
//    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getCardID() {
        return cardID;
    }
}
