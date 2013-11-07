package model.additionalentity;

import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.ImageEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 05.11.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
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
