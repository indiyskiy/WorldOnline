package model.phone.responseentity;

import model.constants.Status;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
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
