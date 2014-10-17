package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuCardLinkEntity;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardMenuServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCardToCardLinkServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            Long menuID = Long.parseLong(request.getParameter("menuID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            MenuEntity menuEntity = MenuRequest.getMenu(menuID);
            if (cardEntity != null && menuEntity != null) {
                MenuCardLinkEntity menuCardLinkEntity = new MenuCardLinkEntity();
                menuCardLinkEntity.setCard(cardEntity);
                menuCardLinkEntity.setMenu(menuEntity);
                MenuRequest.addMenuCardLinkSafe(menuCardLinkEntity);
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
