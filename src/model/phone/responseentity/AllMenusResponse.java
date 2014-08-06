package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MenuCompleteInformation;
import model.constants.Status;

import java.util.ArrayList;

public class AllMenusResponse extends MobileResponseEntity {
    private ArrayList<MenuCompleteInformation> menusCompleteInformation;

    public AllMenusResponse() {
        super(Status.OK);
    }

    public void setMenusCompleteInformation(ArrayList<MenuCompleteInformation> menusCompleteInformation) {
        this.menusCompleteInformation = menusCompleteInformation;
    }

    public ArrayList<MenuCompleteInformation> getMenusCompleteInformation() {
        return menusCompleteInformation;
    }

    @Override
    protected JsonObject toJson() {
        JsonObject responseObj = new JsonObject();
        ArrayList<MenuCompleteInformation> informationArrayList = getMenusCompleteInformation();
//        responseObj.addProperty("menuCounter", informationArrayList.size());
        JsonArray menusArray = new JsonArray();
        for (MenuCompleteInformation menuCompleteInformation : informationArrayList) {
            JsonObject menuObj = menuCompleteInformation.toJson();
            menusArray.add(menuObj);
        }
        responseObj.add("menus", menusArray);
        return responseObj;
    }
}
