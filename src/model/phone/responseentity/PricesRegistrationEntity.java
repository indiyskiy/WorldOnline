package model.phone.responseentity;


import com.google.gson.JsonObject;
import model.constants.Status;

public class PricesRegistrationEntity extends MobileResponseEntity {
    public PricesRegistrationEntity() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        return jsonObject;
    }
}
