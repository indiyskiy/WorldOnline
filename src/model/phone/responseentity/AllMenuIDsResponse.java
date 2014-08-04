package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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

    @Override
    protected JsonObject toJson() {
        JsonObject responseObj = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (Long id : menuIDs) {
            jsonArray.add(new JsonPrimitive(id));
        }
        responseObj.add("menuIDs", jsonArray);
        return responseObj;
    }
}
