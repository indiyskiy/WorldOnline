package controller.parser.adminparser;

import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;

public class AllUsersParser {
    private int maxItems = 0;
    long firstElem = 0;
    Long userID = null;
    private int page;


    public AllUsersParser(int maxItems) {
        this.maxItems = maxItems;
    }


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    public void parse(HttpServletRequest request) {
        if (request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("Page"));
            firstElem = maxItems * Integer.parseInt((ServletHelper.getAndSetAttribute(request, "Page")));
        }
    }

    public int getPage() {
        return page;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public long getFirstElem() {
        return firstElem;
    }

    public void setFirstElem(int firstElem) {
        this.firstElem = firstElem;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
