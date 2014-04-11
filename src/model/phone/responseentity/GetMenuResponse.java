package model.phone.responseentity;

import model.additionalentity.MenuCompleteInformation;
import model.constants.Status;

public class GetMenuResponse extends MobileResponseEntity {
    private MenuCompleteInformation menuCompleteInformation;

    public GetMenuResponse() {
        super(Status.OK);
    }

    public void setMenuCompleteInformation(MenuCompleteInformation menuCompleteInformation) {
        this.menuCompleteInformation = menuCompleteInformation;
    }

    public MenuCompleteInformation getMenuCompleteInformation() {
        return menuCompleteInformation;
    }
}
