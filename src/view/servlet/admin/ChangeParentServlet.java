package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.database.requests.ParameterRequest;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeParentServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ChangeParentServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long parentID = Long.parseLong(request.getParameter("parentID"));
            Long menuID = Long.parseLong(request.getParameter("menuID"));
            MenuEntity parent = MenuRequest.getMenu(parentID);
            MenuEntity menu = MenuRequest.getMenu(menuID);
            if (parent == null) {
                throw new ServletException("There is no menu with ID=" + menuID);
            }
            if (menu == null) {
                throw new ServletException("There is no menu with ID=" + menuID);
            }
            if (menu.getParentMenu() == null) {
                throw new ServletException("You can't change parent of the root menu");
            }
            if (menu.getMenuID().equals(parent.getMenuID())) {
                throw new ServletException("Menu can't be a parent of itself");
            }
            if (parent.getParentMenu() != null && menu.getMenuID().equals(parent.getParentMenu().getMenuID())) {
                throw new ServletException("The endless loopback operation exception");
            }
            menu.setParentMenu(parent);
            MenuRequest.updateMenu(menu);
            ServletHelper.sendForward("/completemenuinfo?menuID=" + menuID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
