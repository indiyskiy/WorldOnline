package model.additionalentity.admin;


import model.additionalentity.SimpleCard;

import java.util.ArrayList;

public class CompleteTagGroupInfo {
    private String name;
    private Long tagGroupID;
    private ArrayList<SimpleTag> simpleTags = new ArrayList<>();
    private SimpleCard tagCard;
    private Long textGroupID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTagGroupID() {
        return tagGroupID;
    }

    public void setTagGroupID(Long tagGroupID) {
        this.tagGroupID = tagGroupID;
    }

    public ArrayList<SimpleTag> getSimpleTags() {
        return simpleTags;
    }

    public void setSimpleTags(ArrayList<SimpleTag> simpleTags) {
        this.simpleTags = simpleTags;
    }

    public SimpleCard getTagCard() {
        return tagCard;
    }

    public void setTagCard(SimpleCard tagCard) {
        this.tagCard = tagCard;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }
}
