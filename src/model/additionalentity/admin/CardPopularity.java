package model.additionalentity.admin;

public class CardPopularity {
    private long cardID;
    private String cardName;
    private long allTime;

    public CardPopularity(long cardID, String cardName, long allTime) {
        this.cardID = cardID;
        this.cardName = cardName;
        this.allTime = allTime;
    }

    public long getCardID() {
        return cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public long getAllTime() {
        return allTime;
    }
}