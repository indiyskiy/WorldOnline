package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public class CardEventRegistrationResponse extends MobileResponseEntity {
    public CardEventRegistrationResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        return new JsonObject();
    }
}
