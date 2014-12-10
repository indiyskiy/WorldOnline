package model.additionalentity.phone;

import model.constants.databaseenumeration.CardEventType;

import java.sql.Timestamp;

public class MobileFieldActivity {
    private long cardID;
    //    private int eventType;
//    private CardEventType eventType;
    private Timestamp activityTimestamp;
    private long cardParameterTypeID;
    private String text;


    public MobileFieldActivity() {
    }

    public MobileFieldActivity(long cardID, Timestamp activityTimestamp, long cardParameterTypeID) {
        this.cardID = cardID;
        this.activityTimestamp = activityTimestamp;
        this.cardParameterTypeID = cardParameterTypeID;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public Timestamp getActivityTimestamp() {
        return activityTimestamp;
    }

    public void setActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
    }

    public long getCardParameterTypeID() {
        return cardParameterTypeID;
    }

    public void setCardParameterTypeID(long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
