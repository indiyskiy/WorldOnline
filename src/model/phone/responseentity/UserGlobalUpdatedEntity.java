package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public class UserGlobalUpdatedEntity extends MobileResponseEntity {
    public UserGlobalUpdatedEntity() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        return new JsonObject();
    }
}
