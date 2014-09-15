package model.additionalentity.admin;

public class CardText {
    private Long textCardID;
    private int block;
    private String typeName;
    private long textID;
    private String name;

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long getTextID() {
        return textID;
    }

    public void setTextID(long textID) {
        this.textID = textID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTextCardID() {
        return textCardID;
    }

    public void setTextCardID(Long textCardID) {
        this.textCardID = textCardID;
    }
}
