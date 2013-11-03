package model.additionalentity;

import model.database.worldonlinedb.CardTagEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class CompleteCardTagInfo {
    private CardTagEntity cardTagEntity;
    private CompleteTagInfo completeTagInfo;

    public CompleteCardTagInfo(CardTagEntity cardTagEntity) {
        this.cardTagEntity=cardTagEntity;
    }

    public CardTagEntity getCardTagEntity() {
        return cardTagEntity;
    }

    public void setCardTagEntity(CardTagEntity cardTagEntity) {
        this.cardTagEntity = cardTagEntity;
    }

    public CompleteTagInfo getCompleteTagInfo() {
        return completeTagInfo;
    }

    public void setCompleteTagInfo(CompleteTagInfo completeTagInfo) {
        this.completeTagInfo = completeTagInfo;
    }
}
