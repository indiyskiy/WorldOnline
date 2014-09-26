package model.additionalentity.admin;

import model.constants.ApplicationBlock;

public class SimpleTagGroup {
    private long tagGroupID;
    private String name;
    private int position;
    private ApplicationBlock block;

    public long getTagGroupID() {
        return tagGroupID;
    }

    public void setTagGroupID(long tagGroupID) {
        this.tagGroupID = tagGroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ApplicationBlock getBlock() {
        return block;
    }

    public void setBlock(ApplicationBlock block) {
        this.block = block;
    }
}
