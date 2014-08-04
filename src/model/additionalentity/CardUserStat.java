package model.additionalentity;

public class CardUserStat {
    private Long userID;
    private Long cardID;
    private Long userVersion;
    private Long cardVersion;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public Long getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(Long userVersion) {
        this.userVersion = userVersion;
    }

    public Long getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Long cardVersion) {
        this.cardVersion = cardVersion;
    }

    public boolean getIsActual() {
        return userVersion != null && userVersion.equals(cardVersion);
    }
}
