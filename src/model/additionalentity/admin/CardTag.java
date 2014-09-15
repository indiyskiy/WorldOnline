package model.additionalentity.admin;

public class CardTag {
    Long tagID;
    Long cardTagID;
    String tagGroup;
    String tagName;
    Long tagGroupID;
    boolean added;

    public boolean getAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public String getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(String tagGroup) {
        this.tagGroup = tagGroup;
    }

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

    public Long getCardTagID() {
        return cardTagID;
    }

    public void setCardTagID(Long cardTagID) {
        this.cardTagID = cardTagID;
    }

    public Long getTagGroupID() {
        return tagGroupID;
    }

    public void setTagGroupID(Long tagGroupID) {
        this.tagGroupID = tagGroupID;
    }
}
