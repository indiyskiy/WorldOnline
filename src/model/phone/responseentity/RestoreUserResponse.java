package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

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

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userID", getUserID());
        return jsonObject;
    }
}
