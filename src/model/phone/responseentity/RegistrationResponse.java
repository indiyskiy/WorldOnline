package model.phone.responseentity;

import model.constants.Status;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
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
}
