package model.additionalentity;

import model.database.worldonlinedb.CardImageEntity;

public class CompleteCardImageInfo {
    private CardImageEntity cardImageEntity;
    private CompleteTextGroupInfo completeTextGroupInfo;

    public CompleteCardImageInfo(CardImageEntity cardImageEntity) {
        this.cardImageEntity = cardImageEntity;
    }

    public CardImageEntity getCardImageEntity() {
        return cardImageEntity;
    }

    public void setCardImageEntity(CardImageEntity cardImageEntity) {
        this.cardImageEntity = cardImageEntity;
    }

    public CompleteTextGroupInfo getCompleteTextGroupInfo() {
        return completeTextGroupInfo;
    }

    public void setCompleteTextGroupInfo(CompleteTextGroupInfo completeTextGroupInfo) {
        this.completeTextGroupInfo = completeTextGroupInfo;
    }
}
