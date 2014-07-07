package model.phone.responseentity;

import model.additionalentity.phone.MobileCardInfo;
import model.constants.Status;

import java.util.LinkedList;

public class AllCardsResponse extends MobileResponseEntity {
    private LinkedList<MobileCardInfo> cardList;

    public AllCardsResponse() {
        super(Status.OK);
    }

    public void setCardList(LinkedList<MobileCardInfo> cardList) {
        this.cardList = cardList;
    }

    public LinkedList<MobileCardInfo> getCardList() {
        return cardList;
    }
}
