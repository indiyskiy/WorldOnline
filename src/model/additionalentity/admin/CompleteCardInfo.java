package model.additionalentity.admin;

import model.constants.ApplicationBlock;
import model.constants.Component;
import model.logger.LoggerFactory;

import java.util.ArrayList;

public class CompleteCardInfo {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteCardInfo.class);
    private CardInfo cardInfo;
    private CardCoordinate cardCoordinate;
    private CardPrice cardPrice;
    private ArrayList<CardParameter> cardParameterArrayList;
    private ArrayList<CardText> cardTextArrayList = new ArrayList<>();
    private ArrayList<CardLink> targetCardLinks = new ArrayList<>();
    private ArrayList<CardLink> sourceCardLinks = new ArrayList<>();
    private ArrayList<CardMenu> cardMenuArrayList = new ArrayList<>();
    private ArrayList<CardTag> cardTagArrayList = new ArrayList<>();
    private ArrayList<CardImage> cardImages = new ArrayList<>();
    private ArrayList<CardBlock> cardBlocks = new ArrayList<>();

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

    public ArrayList<CardText> getCardTextArrayList() {
        return cardTextArrayList;
    }

    public void setCardTextArrayList(ArrayList<CardText> cardTextArrayList) {
        this.cardTextArrayList = cardTextArrayList;
    }

    public ArrayList<CardTag> getCardTagArrayList() {
        return cardTagArrayList;
    }

    public void setCardTagArrayList(ArrayList<CardTag> cardTagArrayList) {
        this.cardTagArrayList = cardTagArrayList;
    }

    public ArrayList<CardBlock> getCardBlocks() {
        return cardBlocks;
    }

    public void setCardBlocks(ArrayList<CardBlock> cardBlocks) {
        this.cardBlocks = cardBlocks;
    }

    public void uploadCardBlocks() {
        for (ApplicationBlock applicationBlock : ApplicationBlock.values()) {
            CardBlock cardBlock = new CardBlock();
            cardBlock.setName(applicationBlock.getRusText());
            cardBlock.setPosition(applicationBlock.getPosition());
            cardBlock.setId(applicationBlock.getValue());
            cardBlocks.add(cardBlock);
            for (CardParameter cardParameter : cardParameterArrayList) {
                if (cardParameter.getBlock() == cardBlock.getId()) {
                    cardBlock.getCardParameters().add(cardParameter);
                }
            }
            for (CardText cardText : cardTextArrayList) {
                if (cardText.getBlock() == cardBlock.getId()) {
                    cardBlock.getCardTexts().add(cardText);
                }
            }
        }
        sort(cardBlocks);
    }

    private void sort(ArrayList<CardBlock> cardBlocks) {
        ArrayList<CardBlock> cardBlocksNew = new ArrayList<>();
        for (int i = 0; i < cardBlocks.size(); i++) {
            for (CardBlock cardBlock : cardBlocks) {
                if (cardBlock.getId() == i) {
                    cardBlocksNew.add(cardBlock);
                }
            }
        }
        this.cardBlocks = cardBlocksNew;
    }

}
