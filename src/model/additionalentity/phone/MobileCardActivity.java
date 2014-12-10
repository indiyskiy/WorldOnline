package model.additionalentity.phone;

import java.sql.Timestamp;

public class MobileCardActivity {
    private long cardID;
    private Timestamp onTimestamp;
    private Timestamp offTimestamp;

    public MobileCardActivity() {
    }

    public MobileCardActivity(long cardID, Timestamp onTimestamp, Timestamp offTimestamp) {
        this.cardID = cardID;
        this.onTimestamp = onTimestamp;
        this.offTimestamp = offTimestamp;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public Timestamp getOnTimestamp() {
        return onTimestamp;
    }

    public void setOnTimestamp(Timestamp onTimestamp) {
        this.onTimestamp = onTimestamp;
    }

    public Timestamp getOffTimestamp() {
        return offTimestamp;
    }

    public void setOffTimestamp(Timestamp offTimestamp) {
        this.offTimestamp = offTimestamp;
    }
}
