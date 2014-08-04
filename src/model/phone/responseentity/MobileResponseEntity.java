package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;

public abstract class MobileResponseEntity {
    private Status status;

    public MobileResponseEntity(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JsonObject getJson() {
        JsonObject jsonObject = toJson();
        addStatus(jsonObject);
        return jsonObject;
    }

    protected abstract JsonObject toJson();

    private void addStatus(JsonObject json) {
        json.addProperty("status", status.getValue());
    }
}
