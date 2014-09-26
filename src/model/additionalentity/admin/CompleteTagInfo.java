package model.additionalentity.admin;

import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.SimpleCard;
import model.database.worldonlinedb.TagEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class CompleteTagInfo {
    private String tagName;
    private Long tagID;
    private Long textGroupID;

    private Long tagGroupID;
    private String tagGroupName;

    private Long iconID;
    private ArrayList<SimpleCard> cards;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public Long getTagGroupID() {
        return tagGroupID;
    }

    public void setTagGroupID(Long tagGroupID) {
        this.tagGroupID = tagGroupID;
    }

    public String getTagGroupName() {
        return tagGroupName;
    }

    public void setTagGroupName(String tagGroupName) {
        this.tagGroupName = tagGroupName;
    }

    public Long getIconID() {
        return iconID;
    }

    public void setIconID(Long iconID) {
        this.iconID = iconID;
    }

    public void setCards(ArrayList<SimpleCard> cards) {
        this.cards = cards;
    }

    public ArrayList<SimpleCard> getCards() {
        return cards;
    }
}
