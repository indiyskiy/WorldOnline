package model.additionalentity.phone;

import com.google.gson.JsonObject;
import model.constants.ApplicationBlock;
import model.constants.databaseenumeration.DataType;

public class MobileParameter {
    private String name;
    private long imageID;
    private ApplicationBlock applicationBlock;
    private DataType dataType;
    private String value;
    private Long parameterID;
    private Integer position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public ApplicationBlock getApplicationBlock() {
        return applicationBlock;
    }

    public void setApplicationBlock(ApplicationBlock applicationBlock) {
        this.applicationBlock = applicationBlock;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("type", dataType.getValue());
        jsonObject.addProperty("block", applicationBlock.getValue());
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("icon", imageID);
        jsonObject.addProperty("parameterID", parameterID);
        jsonObject.addProperty("position", position);
        return jsonObject;
    }

    public void setParameterID(Long parameterID) {
        this.parameterID = parameterID;
    }

    public Long getParameterID() {
        return parameterID;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }
}
