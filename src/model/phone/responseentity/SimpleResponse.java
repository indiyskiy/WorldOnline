package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public class SimpleResponse extends MobileResponseEntity {
    public SimpleResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        return new JsonObject();
    }
}
