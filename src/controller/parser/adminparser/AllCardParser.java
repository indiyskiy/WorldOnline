package controller.parser.adminparser;

import helper.ServletHelper;
import model.constants.databaseenumeration.CardType;

import javax.servlet.http.HttpServletRequest;

public class AllCardParser {
    private Long cardID = null;
    private String cardName = null;
    private CardType cardType = null;
    private int firstElem = 0;
    private int maxItems = 0;
    private int page;

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
            page = Integer.parseInt(ServletHelper.getAndSetAttribute(request, "Page"));
            firstElem = maxItems * page;
        } else {
            page = 0;
            firstElem = maxItems * page;
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

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setFirstElem(int firstElem) {
        this.firstElem = firstElem;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
