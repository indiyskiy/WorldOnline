package model.phone.responseentity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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


    @Override
    protected JsonObject toJson() {
        JsonObject responseObj = new JsonObject();
        JsonArray cardParameterTypesArray = new JsonArray();
        for (MobileParameterType mobileParameterType : mobileParameterTypes) {
            JsonObject mobileParameterObj = mobileParameterType.toJson();
            cardParameterTypesArray.add(mobileParameterObj);
        }
        responseObj.add("cardParameterTypes", cardParameterTypesArray);
        return responseObj;
    }
}
