package view.servlet.admin;

import controller.parser.adminparser.AllUsersParser;
import model.additionalentity.admin.PagesArray;
import model.additionalentity.admin.SimpleUserInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.UserRequests;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AllUsersServlet extends ProtectedServlet {
    private final int MAX_ITEMS = 50;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllUsersServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        try {
            AllUsersParser parser = new AllUsersParser(MAX_ITEMS);
            parser.parse(request);
            ServletHelper.setUTF8(request, response);
            ArrayList<SimpleUserInfo> userInfos;
            int pages;
            userInfos = UserRequests.getAllSimpleUsers(parser.getFirstElem(), parser.getMaxItems());
            Long results = UserRequests.countUser();
            pages = (int) (results / MAX_ITEMS);
            String prefix = "";
            prefix += "<a href=\"allusers?Page=${i}\"> ";
            request.setAttribute("pagesString", new PagesArray(pages, parser.getPage()).print(prefix, "</a> "));
            request.setAttribute("userList", userInfos);
            ServletHelper.sendForward("/allusers.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

    @Override
    public String getTitle() {
        return "Все пользователи";
    }
}