package model.additionalentity.phone;

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
}
