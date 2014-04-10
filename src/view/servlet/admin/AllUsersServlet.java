package view.servlet.admin;

import controller.parser.adminparser.AllUsersParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.MobilePlatform;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Илья on 13.03.14.
 */
public class AllUsersServlet extends ProtectedServlet {
    private final int MAX_ITEMS = 50;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllUsersServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        AllUsersParser parser = new AllUsersParser(MAX_ITEMS);
        parser.parse(request);
        try {
            ServletHelper.setUTF8(request, response);
            ArrayList<UserEntity> userEntities;
            long pages;
            if (!parser.haveMatter()) {
                userEntities = UserRequests.getAllUsers(parser.getFirstElem(), MAX_ITEMS);
                long results = UserRequests.countUser();
                pages = (results / MAX_ITEMS);
            } else {
                userEntities = UserRequests.getAllUsers(parser);
                Long results = UserRequests.countUser(parser);
                pages = (results / MAX_ITEMS);
            }
            request.setAttribute("pages", pages);
            request.setAttribute("userList", userEntities);
            request.setAttribute("mobilePlatforms", MobilePlatform.values());
            ServletHelper.sendForward("/allusers.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }
}