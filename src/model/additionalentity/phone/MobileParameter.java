package model.additionalentity.phone;

import com.google.gson.JsonObject;
import model.constants.ApplicationBlock;
import model.constants.databaseenumeration.DataType;

public class MobileParameter {
    private String value;
    private Long parameterID;
    private long parameterTypeID;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("parameterID", parameterID);
        jsonObject.addProperty("cardParameterTypeID", parameterTypeID);
        return jsonObject;
    }

    public void setParameterID(Long parameterID) {
        this.parameterID = parameterID;
    }

    public void setParameterTypeID(long parameterTypeID) {
        this.parameterTypeID = parameterTypeID;
    }

    public Long getParameterID() {
        return parameterID;
    }

    public long getParameterTypeID() {
        return parameterTypeID;
    }


}
