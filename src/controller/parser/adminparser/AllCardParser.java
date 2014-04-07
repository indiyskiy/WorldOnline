package controller.parser.adminparser;

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
    String cardName = null;
    CardType cardType = null;
    int firstElem = 0;
    int maxItems = 0;

    public AllCardParser(int maxItems) {
        this.maxItems = maxItems;
    }

    public void parse(HttpServletRequest request) {
        if (request.getParameter("CardIDRe") != null && !request.getParameter("CardIDRe").isEmpty()) {
            cardID = Long.parseLong(ServletHelper.getAndSetAttribute(request, "CardIDRe"));
        }
        cardName = ServletHelper.getAndSetAttribute(request, "CardNameRe");
        if (request.getParameter("CardTypeRe") != null && !request.getParameter("CardTypeRe").isEmpty()) {
            cardType = CardType.parseInt(Integer.parseInt(ServletHelper.getAndSetAttribute(request, "CardTypeRe")));
        }
        if (request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()) {
            firstElem = maxItems * Integer.parseInt((ServletHelper.getAndSetAttribute(request, "Page")));
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

    public int getFirstElem() {
        return firstElem;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public boolean haveMatter() {
        if (cardID != null) {
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
