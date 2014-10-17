package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMenuServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteMenuServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            long menuID = Long.parseLong(request.getParameter("menuID"));
            int size = MenuRequest.countSubmenus(menuID);
            if (size == 0) {
                MenuRequest.deleteCardMenuByMenuID(menuID);
                MenuRequest.deleteMenu(menuID);
                ServletHelper.sendRedirect("/allmenus", request, response);
            } else {
                throw new ServletException("there are " + size + " submenus. Please delete them before delete their parent");
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.HeightModerator;
    }

    @Override
    public String getTitle() {
        return null;
    }

}
