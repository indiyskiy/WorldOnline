package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.MenuType;
import model.database.requests.MenuRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.MenuEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateSubMenuServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateSubMenuServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
                Integer position = MenuRequest.getMaxPosition(menuID);
                MenuEntity menu = new MenuEntity(parentMenu, textGroupEntity, null, menuType);
                menu.setNumber(position + 1);
                MenuRequest.addMenu(menu);
                UserDataRequest.updateMenus();
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
