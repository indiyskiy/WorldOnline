package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.additionalentity.phone.MobileTagGroup;
import model.constants.Status;

import java.util.ArrayList;

public class AllTagsResponse extends MobileResponseEntity {
    private ArrayList<MobileTagGroup> mobileTagGroups;

    public AllTagsResponse() {
        super(Status.OK);
    }

    public ArrayList<MobileTagGroup> getMobileTagGroups() {
        return mobileTagGroups;
    }

    public void setMobileTagGroups(ArrayList<MobileTagGroup> mobileTagGroups) {
        this.mobileTagGroups = mobileTagGroups;
    }

    @Override
    protected JsonObject toJson() {
        ArrayList<MobileTagGroup> mobileTagGroups = getMobileTagGroups();
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (MobileTagGroup mobileTagGroup : mobileTagGroups) {
            jsonArray.add(mobileTagGroup.toJSON());
        }
        jsonObject.add("tagGroups", jsonArray);
        return jsonObject;
    }
}
