package model.additionalentity.admin;


public class CardParameter {
    private long parameterID;
    private int block;
    private String name;
    private String value;

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getParameterID() {
        return parameterID;
    }

    public void setParameterID(long parameterID) {
        this.parameterID = parameterID;
    }
}
