package controller.phone.entity;

/**
 * Created by Илья on 09.04.14.
 */
public class AllMenusRequest extends MobileRequest {
    private Long userID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }
}
