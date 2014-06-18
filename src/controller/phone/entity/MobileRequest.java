package controller.phone.entity;

/**
 * Created by Илья on 10.04.14.
 */
public abstract class MobileRequest {
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
