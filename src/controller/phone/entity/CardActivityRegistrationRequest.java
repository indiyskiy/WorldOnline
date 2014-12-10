package controller.phone.entity;

import model.additionalentity.phone.MobileCardActivity;

import java.util.ArrayList;

public class CardActivityRegistrationRequest extends MobileRequest {
    private ArrayList<MobileCardActivity> mobileCardActivities = new ArrayList<>();

    public ArrayList<MobileCardActivity> getMobileCardActivities() {
        return mobileCardActivities;
    }
}
