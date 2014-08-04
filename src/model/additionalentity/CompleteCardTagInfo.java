package model.additionalentity;

import model.additionalentity.admin.CompleteTagInfo;
import model.database.worldonlinedb.CardTagEntity;

public class CompleteCardTagInfo {
    private CardTagEntity cardTagEntity;
    private CompleteTagInfo completeTagInfo;

    public CompleteCardTagInfo(CardTagEntity cardTagEntity) {
        this.cardTagEntity = cardTagEntity;
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
