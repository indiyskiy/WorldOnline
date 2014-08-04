package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.additionalentity.phone.MenuCompleteInformation;
import model.constants.Status;

public class MenuResponse extends MobileResponseEntity {
    private MenuCompleteInformation menuCompleteInformation;

    public MenuResponse() {
        super(Status.OK);
    }

    public void setMenuCompleteInformation(MenuCompleteInformation menuCompleteInformation) {
        this.menuCompleteInformation = menuCompleteInformation;
    }

    public MenuCompleteInformation getMenuCompleteInformation() {
        return menuCompleteInformation;
    }

    @Override
    protected JsonObject toJson() {
        JsonObject responseObj = new JsonObject();
        JsonObject menuObject = menuCompleteInformation.toJson();
        responseObj.add("menu", menuObject);
        return responseObj;
    }
}
