package model.phone.responseentity;

import model.additionalentity.phone.MobileParameterType;
import model.constants.Status;

import java.util.ArrayList;

public class AllCardParameterTypesResponse extends MobileResponseEntity {
    private ArrayList<MobileParameterType> mobileParameterTypes;

    public AllCardParameterTypesResponse() {
        super(Status.OK);
    }

    public void setMobileParameterTypes(ArrayList<MobileParameterType> mobileParameterTypes) {
        this.mobileParameterTypes = mobileParameterTypes;
    }

    public ArrayList<MobileParameterType> getMobileParameterTypes() {
        return mobileParameterTypes;
    }
}
