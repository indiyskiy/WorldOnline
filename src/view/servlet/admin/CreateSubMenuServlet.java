package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.MenuType;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.MenuEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateSubMenuServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateCardCoordinateServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long menuID = Long.parseLong(request.getParameter("menuID"));
            MenuEntity parentMenu = MenuRequest.getMenu(menuID);
            if (parentMenu != null) {
                TextGroupEntity textGroupEntity = new TextGroupEntity("menuNameTextGroup");
                MenuType menuType;
                if (parentMenu.getParentMenu() == null) {
                    menuType = MenuType.RootMenu;
                } else {
                    menuType = MenuType.StandardMenu;
                }
                MenuEntity menu = new MenuEntity(parentMenu, textGroupEntity, null, menuType);
                MenuRequest.addMenu(menu);
                loggerFactory.debug("redirect to " + "/completemenuinfo?menuID=" + menu.getMenuID());
                ServletHelper.sendForward("/completemenuinfo?menuID=" + menu.getMenuID(), this, request, response);
            } else {
                throw new ServletException("incorrect menu ID");
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
