package model.phone.responseentity;

import model.constants.Status;
import model.phone.responseentity.MobileResponseEntity;

public class RestoreUserResponse extends MobileResponseEntity {
    private long userID;

    public RestoreUserResponse() {
        super(Status.OK);
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }
}
