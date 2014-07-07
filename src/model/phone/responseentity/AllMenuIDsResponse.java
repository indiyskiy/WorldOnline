package model.phone.responseentity;

import model.constants.Status;

import java.util.LinkedList;

public class AllMenuIDsResponse extends MobileResponseEntity {

    private LinkedList<Long> menuIDs;

    public AllMenuIDsResponse() {
        super(Status.OK);
    }

    public void setMenuIDs(LinkedList<Long> menuIDs) {
        this.menuIDs = menuIDs;
    }

    public LinkedList<Long> getMenuIDs() {
        return menuIDs;
    }
}
