package model.additionalentity.admin;


public class CardInfoElement {
    private String text;
    private Long textGroupID;
    private Long imageID;
    private int position;
    private long informationElementID;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public Long getImageID() {
        return imageID;
    }

    public void setImageID(Long imageID) {
        this.imageID = imageID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getInformationElementID() {
        return informationElementID;
    }

    public void setInformationElementID(long informationElementID) {
        this.informationElementID = informationElementID;
    }
}
