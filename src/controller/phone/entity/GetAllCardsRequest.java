package controller.phone.entity;

/**
 * Created by Илья on 18.04.14.
 */
public class GetAllCardsRequest extends MobileRequest {
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
