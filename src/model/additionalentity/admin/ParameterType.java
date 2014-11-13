package model.additionalentity.admin;

import model.constants.ApplicationBlock;

public class ParameterType {
    private ApplicationBlock block;
    private Long parameterTypeID;
    private String name;
    private boolean multiply;
    private boolean translatable;

    public ApplicationBlock getBlock() {
        return block;
    }

    public void setBlock(ApplicationBlock block) {
        this.block = block;
    }

    public Long getParameterTypeID() {
        return parameterTypeID;
    }

    public void setParameterTypeID(Long parameterTypeID) {
        this.parameterTypeID = parameterTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMultiply() {
        return multiply;
    }

    public void setMultiply(boolean multiply) {
        this.multiply = multiply;
    }

    public boolean isTranslatable() {
        return translatable;
    }

    public void setTranslatable(boolean translatable) {
        this.translatable = translatable;
    }
}
