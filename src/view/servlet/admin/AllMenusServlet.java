package view.servlet.admin;

import model.additionalentity.MenuInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.MenuRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllMenusServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllMenusServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long menuID = null;
            if (request.getParameter("menuID") != null && !request.getParameter("menuID").isEmpty()) {
                menuID = Long.parseLong(request.getParameter("menuID"));
            }
            MenuInfo menuInfo;
            String redirect = "/allmenus.jsp";
            if (menuID != null) {
                menuInfo = MenuRequest.getMenuInfo(menuID, LanguageType.Russian);
                if (menuInfo == null) {
                    throw new ServletException("menu info is null. Incorrect menu ID mb?");
                }
                menuInfo.setCards(MenuRequest.getAllSimpleCardsOfMenu(menuID));
                redirect += "?menuID=" + menuID;
            } else {
                menuInfo = MenuRequest.getRootMenuInfo(LanguageType.Russian);
            }
            if (menuInfo != null) {
                request.setAttribute("menu", menuInfo.getMenu());
                request.setAttribute("submenus", menuInfo.getSubmenus());
                request.setAttribute("parent", menuInfo.getParentMenu());
                request.setAttribute("cards", menuInfo.getCards());
                request.setAttribute("active", CardState.Active);
                request.setAttribute("active", CardState.NotActive);
                request.setAttribute("active", CardState.Deleted);
            }
            ServletHelper.sendForward(redirect, this, request, response);
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
        return "Все меню деревом";
    }
}
