package view.servlet.admin;

import model.additionalentity.admin.CompleteMenuInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteMenuInfoServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteMenuInfoServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long menuID = Long.parseLong(request.getParameter("menuID"));
            CompleteMenuInfo menuInfo = MenuRequest.getCompleteMenuInfo(menuID);
            if (menuInfo != null) {
                request.setAttribute("menu", menuInfo);

                request.setAttribute("title", cutTitle("Меню [" +
                        menuInfo.getMenuID() +
                        "]" +
                        menuInfo.getName()));
                ServletHelper.sendForward("/completemenuinfo.jsp?menuID=" + menuID, this, request, response);
            }
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
        return null;
    }
}
