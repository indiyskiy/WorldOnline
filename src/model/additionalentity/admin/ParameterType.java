package model.additionalentity.admin;

public class ParameterType {
    private int block;
    private Long ParameterTypeID;
    private String name;
    private boolean multiply;
    private boolean translatable;

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public Long getParameterTypeID() {
        return ParameterTypeID;
    }

    public void setParameterTypeID(Long parameterTypeID) {
        ParameterTypeID = parameterTypeID;
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
