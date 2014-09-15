package model.additionalentity.admin;

import java.util.ArrayList;

public class TagGroup {
    private ArrayList<CardTag> tagList = new ArrayList<>();
    private String name;
    private Long tagGroupID;

    public ArrayList<CardTag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<CardTag> tagList) {
        this.tagList = tagList;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTagGroupID(Long tagGroupID) {
        this.tagGroupID = tagGroupID;
    }

    public Long getTagGroupID() {
        return tagGroupID;
    }

    public boolean getIsEmpty() {
        return tagList.isEmpty();
    }
}
