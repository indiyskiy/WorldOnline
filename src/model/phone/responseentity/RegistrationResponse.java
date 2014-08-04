package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public class RegistrationResponse extends MobileResponseEntity {

    private Long userID;

    public RegistrationResponse() {
        super(Status.OK);
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    @Override
    protected JsonObject toJson() {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("id", userID);
        return responseObj;
    }
}
