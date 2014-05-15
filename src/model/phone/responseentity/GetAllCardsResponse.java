package model.phone.responseentity;

import model.additionalentity.phone.MobileCardInfo;
import model.constants.Status;

import java.util.LinkedList;

/**
 * Created by Илья on 18.04.14.
 */
public class GetAllCardsResponse extends MobileResponseEntity {
    private LinkedList<MobileCardInfo> cardList;

    public GetAllCardsResponse() {
        super(Status.OK);
    }

    public void setCardList(LinkedList<MobileCardInfo> cardList) {
        this.cardList = cardList;
    }

    public LinkedList<MobileCardInfo> getCardList() {
        return cardList;
    }
}
