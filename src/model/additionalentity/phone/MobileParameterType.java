package model.additionalentity.phone;

import com.google.gson.JsonObject;
import model.constants.ApplicationBlock;
import model.constants.databaseenumeration.DataType;

public class MobileParameterType {
    private Long cardParameterTypeID;
    private ApplicationBlock block;
    private DataType dataType;
    private int position;
    private String name;
    private long iconID;

    public void setCardParameterTypeID(Long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }

    public Long getCardParameterTypeID() {
        return cardParameterTypeID;
    }

    public void setBlock(ApplicationBlock block) {
        this.block = block;
    }

    public ApplicationBlock getBlock() {
        return block;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIconID(long iconID) {
        this.iconID = iconID;
    }

    public long getIconID() {
        return iconID;
    }

    public JsonObject toJson() {
        JsonObject mobileParameterObj = new JsonObject();
        mobileParameterObj.addProperty("cardParameterTypeID", cardParameterTypeID);
        mobileParameterObj.addProperty("iconID", iconID);
        mobileParameterObj.addProperty("name", name);
        mobileParameterObj.addProperty("position", position);
        mobileParameterObj.addProperty("block", block.getValue());
        mobileParameterObj.addProperty("dataType", dataType.getValue());
        return mobileParameterObj;
    }
}
