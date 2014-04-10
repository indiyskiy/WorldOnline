package model.phone.responseentity;

import model.constants.Status;

import java.util.LinkedList;

/**
 * Created by Илья on 09.04.14.
 */
public class AllMenusResponse extends MobileResponseEntity {

    private LinkedList<Long> menuIDs;

    public AllMenusResponse() {
        super(Status.OK);
    }

    public void setMenuIDs(LinkedList<Long> menuIDs) {
        this.menuIDs = menuIDs;
    }

    public LinkedList<Long> getMenuIDs() {
        return menuIDs;
    }
}
