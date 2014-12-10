package controller.phone.entity;

import model.additionalentity.phone.MobileCardActivity;
import model.additionalentity.phone.MobileCardEvent;

import java.util.ArrayList;

public class CardEventRegistrationRequest extends MobileRequest {
    private ArrayList<MobileCardEvent> mobileCardEvents = new ArrayList<>();

    public ArrayList<MobileCardEvent> getMobileCardEvents() {
        return mobileCardEvents;
    }
}
