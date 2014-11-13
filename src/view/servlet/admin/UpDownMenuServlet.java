package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.adminenumerations.RepositionDirection;
import model.database.requests.MenuRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpDownMenuServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, UpDownMenuServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long menuID = Long.parseLong(request.getParameter("menuID"));
            MenuEntity menuEntity = MenuRequest.getMenu(menuID);
            Long rootMenuID = Long.parseLong(request.getParameter("rootMenuID"));
            RepositionDirection repositionDirection = RepositionDirection.valueOf(request.getParameter("reposition"));
            MenuEntity nearbyMenu = MenuRequest.getNearbyMenu(menuID, rootMenuID, repositionDirection);
            if (nearbyMenu != null) {
                int number = nearbyMenu.getNumber();
                nearbyMenu.setNumber(menuEntity.getNumber());
                menuEntity.setNumber(number);
                MenuRequest.updateMenu(menuEntity);
                MenuRequest.updateMenu(nearbyMenu);
                UserDataRequest.updateMenus();
            }
            ServletHelper.sendForward("/allmenus?menuID=" + rootMenuID, this, request, response);
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
