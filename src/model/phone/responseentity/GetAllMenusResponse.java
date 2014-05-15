package model.phone.responseentity;

import model.additionalentity.phone.MenuCompleteInformation;
import model.constants.Status;

import java.util.ArrayList;

/**
 * Created by Илья on 15.04.14.
 */
public class GetAllMenusResponse extends MobileResponseEntity {
    private ArrayList<MenuCompleteInformation> menusCompleteInformation;

    public GetAllMenusResponse() {
        super(Status.OK);
    }

    public void setMenusCompleteInformation(ArrayList<MenuCompleteInformation> menusCompleteInformation) {
        this.menusCompleteInformation = menusCompleteInformation;
    }

    public ArrayList<MenuCompleteInformation> getMenusCompleteInformation() {
        return menusCompleteInformation;
    }
}
