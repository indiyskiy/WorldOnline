package model.additionalentity;

import model.constants.databaseenumeration.DataBaseEntityType;
import model.constants.databaseenumeration.UpdateStatus;
import model.constants.databaseenumeration.UpdateType;

public class CardUpdateInfo {
    private long cardID;
    private UpdateType updateType;
    private DataBaseEntityType dataType;
    private UpdateStatus updateStatus;

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getCardID() {
        return cardID;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setDataType(DataBaseEntityType dataType) {
        this.dataType = dataType;
    }

    public DataBaseEntityType getDataType() {
        return dataType;
    }

    public void setUpdateStatus(UpdateStatus updateStatus) {
        this.updateStatus = updateStatus;
    }

    public UpdateStatus getUpdateStatus() {
        return updateStatus;
    }
}
