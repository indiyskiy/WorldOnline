package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public class CardActivityRegistrationResponse extends MobileResponseEntity {
    public CardActivityRegistrationResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        return new JsonObject();
    }
}
