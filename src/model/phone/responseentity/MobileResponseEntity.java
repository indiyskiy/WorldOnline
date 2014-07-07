package model.phone.responseentity;

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
}
