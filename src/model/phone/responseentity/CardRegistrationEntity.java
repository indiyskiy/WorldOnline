package model.phone.responseentity;


import com.google.gson.JsonObject;
import model.constants.Status;

public class CardRegistrationEntity extends MobileResponseEntity {
    public CardRegistrationEntity() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        return jsonObject;
    }
}
