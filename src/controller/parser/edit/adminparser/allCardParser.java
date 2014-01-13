package controller.parser.edit.adminparser;

import model.constants.databaseenumeration.CardType;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 13.01.14
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class AllCardParser {
    Long cardID = null;
    String cardName=null;
    CardType cardType = null;

    public void parse(HttpServletRequest request) {
        if (ServletHelper.getAndSetAttribute(request, "CardIDRe") != null && !ServletHelper.getAndSetAttribute(request, "CardIDRe").isEmpty()) {
            cardID = Long.parseLong(ServletHelper.getAndSetAttribute(request, "CardIDRe"));
        }
        cardName = ServletHelper.getAndSetAttribute(request, "CardNameRe");
        if (ServletHelper.getAndSetAttribute(request, "CardTypeRe") != null && !ServletHelper.getAndSetAttribute(request, "CardTypeRe").isEmpty()) {
            cardType = CardType.parseInt(Integer.parseInt(ServletHelper.getAndSetAttribute(request, "CardTypeRe")));
        }
    }

    public Long getCardID() {
        return cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean haveMatter() {
        if (cardID!=null){
            return true;
        }
        if (cardName != null && !cardName.isEmpty()) {
            return true;
        }
        if (cardType != null) {
            return true;
        }
        return false;
    }
}
