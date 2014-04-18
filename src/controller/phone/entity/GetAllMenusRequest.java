package controller.phone.entity;

/**
 * Created by Илья on 15.04.14.
 */
public class GetAllMenusRequest extends MobileRequest {
    private Long userID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }
}
