package model.phone.responseentity;

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
}
