package model.additionalentity;

import model.constants.databaseenumeration.ChangingDataType;
import model.constants.databaseenumeration.UpdateStatus;
import model.constants.databaseenumeration.UpdateType;

/**
 * Created by Илья on 17.04.14.
 */
public class CardUpdateInfo {
    private long cardID;
    private UpdateType updateType;
    private ChangingDataType dataType;
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

    public void setDataType(ChangingDataType dataType) {
        this.dataType = dataType;
    }

    public ChangingDataType getDataType() {
        return dataType;
    }

    public void setUpdateStatus(UpdateStatus updateStatus) {
        this.updateStatus = updateStatus;
    }

    public UpdateStatus getUpdateStatus() {
        return updateStatus;
    }
}
