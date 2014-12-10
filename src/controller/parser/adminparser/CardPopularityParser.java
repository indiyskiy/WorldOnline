package controller.parser.adminparser;

import model.constants.adminenumerations.CardPopularitySortBy;
import model.constants.databaseenumeration.CardState;

import javax.servlet.http.HttpServletRequest;

public class CardPopularityParser {
    private long cardID;
    private CardState state;
    private CardPopularitySortBy sortBy;
    private int firstElem;
    private int maxItems;
    private int page;

    public CardPopularityParser(int maxItems) {
        this.maxItems = maxItems;
    }

    public void parse(HttpServletRequest request) {
        if (request.getParameter("cardID") != null && !request.getParameter("cardID").isEmpty()) {
            cardID = Long.parseLong(request.getParameter("cardID"));
            request.setAttribute("cardID", cardID);
        }
        if (request.getParameter("state") != null && !request.getParameter("state").isEmpty()) {
            state = CardState.valueOf(request.getParameter("state"));
            request.setAttribute("state", state);
        }
        if (request.getParameter("sortBy") != null && !request.getParameter("sortBy").isEmpty()) {
            sortBy = CardPopularitySortBy.valueOf(request.getParameter("sortBy"));
            request.setAttribute("sortBy", sortBy);
        } else {
            sortBy = CardPopularitySortBy.ALL_TIME;
        }
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
            firstElem = maxItems * page;
        } else {
            page = 0;
            firstElem = 0;
        }
    }

    public long getCardID() {
        return cardID;
    }

    public CardState getState() {
        return state;
    }

    public CardPopularitySortBy getSortBy() {
        return sortBy;
    }
}
