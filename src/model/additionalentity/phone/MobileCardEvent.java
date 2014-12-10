package model.additionalentity.phone;

import model.constants.databaseenumeration.CardEventType;

import java.sql.Timestamp;

public class MobileCardEvent {
    private long cardID;
    //    private int eventType;
    private CardEventType eventType;
    private Timestamp eventTimestamp;
    private String text;


    public MobileCardEvent() {
    }

    public MobileCardEvent(long cardID, CardEventType eventType, Timestamp eventTimestamp) {
        this.cardID = cardID;
        this.eventType = eventType;
        this.eventTimestamp = eventTimestamp;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public CardEventType getEventType() {
        return eventType;
    }

    public void setEventType(CardEventType eventType) {
        this.eventType = eventType;
    }

    public Timestamp getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Timestamp eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
