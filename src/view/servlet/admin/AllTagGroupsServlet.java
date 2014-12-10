package view.servlet.admin;

import model.additionalentity.admin.SimpleTagGroup;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.TagRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AllTagGroupsServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllTagGroupsServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            ArrayList<SimpleTagGroup> tagGroups = TagRequest.getAllSimpleTagGroups();
            request.setAttribute("tagGroups", tagGroups);
            ServletHelper.sendForward("/alltaggroups.jsp", this, request, response);
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
        return "Все группы тегов";
    }
}
