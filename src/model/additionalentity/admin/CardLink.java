package model.additionalentity.admin;

import model.constants.databaseenumeration.CardToCardLinkType;

public class CardLink {

    private long cardID;
    private String cardName;
    private CardToCardLinkType linkType;
    private long cardToCardLinkID;

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setLinkType(CardToCardLinkType linkType) {
        this.linkType = linkType;
    }

    public CardToCardLinkType getLinkType() {
        return linkType;
    }

    public long getCardToCardLinkID() {
        return cardToCardLinkID;
    }

    public void setCardToCardLinkID(long cardToCardLinkID) {
        this.cardToCardLinkID = cardToCardLinkID;
    }
}
