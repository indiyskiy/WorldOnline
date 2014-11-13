package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;


public class UserStatsEntity extends MobileResponseEntity {
    UserGlobalUpdateStatus userGlobalUpdateStatus;
    Long cardCounter;
    Long pricesCounter;
    boolean needToCardsUpdate;
    boolean needToPricesUpdate;
//    ArrayList<UserEvent> userEvents


    public UserGlobalUpdateStatus getUserGlobalUpdateStatus() {
        return userGlobalUpdateStatus;
    }

    public void setUserGlobalUpdateStatus(UserGlobalUpdateStatus userGlobalUpdateStatus) {
        this.userGlobalUpdateStatus = userGlobalUpdateStatus;
    }

    public boolean isNeedToCardsUpdate() {
        return needToCardsUpdate;
    }

    public void setNeedToCardsUpdate(boolean needToCardsUpdate) {
        this.needToCardsUpdate = needToCardsUpdate;
    }

    public boolean isNeedToPricesUpdate() {
        return needToPricesUpdate;
    }

    public void setNeedToPricesUpdate(boolean needToPricesUpdate) {
        this.needToPricesUpdate = needToPricesUpdate;
    }

    public Long getCardCounter() {
        return cardCounter;
    }

    public void setCardCounter(Long cardCounter) {
        this.cardCounter = cardCounter;
    }

    public Long getPricesCounter() {
        return pricesCounter;
    }

    public void setPricesCounter(Long pricesCounter) {
        this.pricesCounter = pricesCounter;
    }

    public UserStatsEntity() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("userGlobalUpdateStatus", userGlobalUpdateStatus.toJson());
        return jsonObject;
    }
}
