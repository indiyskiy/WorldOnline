package model.additionalentity.admin;

import java.util.ArrayList;

public class CompleteCardInfo {
    private CardInfo cardInfo;
    private CardCoordinate cardCoordinate;
    private CardPrice cardPrice;
    private ArrayList<CardParameter> cardParameterArrayList;
    //    private ArrayList<CardText> cardTextArrayList=new ArrayList<>();
    private ArrayList<CardLink> targetCardLinks = new ArrayList<>();
    private ArrayList<CardLink> sourceCardLinks = new ArrayList<>();
    private ArrayList<CardMenu> cardMenuArrayList = new ArrayList<>();
    //    private ArrayList<CardTag> cardTagArrayList=new ArrayList<>();
    private ArrayList<CardImage> cardImages = new ArrayList<>();

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public CardCoordinate getCardCoordinate() {
        return cardCoordinate;
    }

    public void setCardCoordinate(CardCoordinate cardCoordinate) {
        this.cardCoordinate = cardCoordinate;
    }

    public CardPrice getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(CardPrice cardPrice) {
        this.cardPrice = cardPrice;
    }

    public ArrayList<CardLink> getTargetCardLinks() {
        return targetCardLinks;
    }

    public void setTargetCardLinks(ArrayList<CardLink> targetCardLinks) {
        this.targetCardLinks = targetCardLinks;
    }

    public ArrayList<CardLink> getSourceCardLinks() {
        return sourceCardLinks;
    }

    public void setSourceCardLinks(ArrayList<CardLink> sourceCardLinks) {
        this.sourceCardLinks = sourceCardLinks;
    }

    public ArrayList<CardMenu> getCardMenuArrayList() {
        return cardMenuArrayList;
    }

    public void setCardMenuArrayList(ArrayList<CardMenu> cardMenuArrayList) {
        this.cardMenuArrayList = cardMenuArrayList;
    }

    public ArrayList<CardImage> getCardImages() {
        return cardImages;
    }

    public void setCardImages(ArrayList<CardImage> cardImages) {
        this.cardImages = cardImages;
    }

    public ArrayList<CardParameter> getCardParameterArrayList() {
        return cardParameterArrayList;
    }

    public void setCardParameterArrayList(ArrayList<CardParameter> cardParameterArrayList) {
        this.cardParameterArrayList = cardParameterArrayList;
    }
}
