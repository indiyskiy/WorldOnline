package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardRouteEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddRouteServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddRouteServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            if (!RouteRequest.isCardRouteExist(cardID)) {
                CardEntity cardEntity = CardRequest.getCardByID(cardID);
                if (cardEntity != null && cardEntity.getCardType() == CardType.CardRoute.getValue()) {
                    CardRouteEntity cardRouteEntity = new CardRouteEntity();
                    cardRouteEntity.setCard(cardEntity);
                    RouteRequest.addCardRoute(cardRouteEntity);
                }
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
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
