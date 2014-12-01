package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardRouteEntity;
import model.database.worldonlinedb.RouteElementEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRouteElementServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddRouteElementServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            Long cardRouteID = Long.parseLong(request.getParameter("cardRouteID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            CardRouteEntity cardRouteEntity = RouteRequest.getCardRoute(cardRouteID);
            if (cardEntity != null && cardRouteEntity != null) {
                RouteElementEntity routeElementEntity = new RouteElementEntity();
                routeElementEntity.setPlaceCard(cardEntity);
                routeElementEntity.setCardRoute(cardRouteEntity);
                routeElementEntity.setRouteElementNumber(RouteRequest.getMaxNumber(cardRouteEntity) + 1);
                RouteRequest.addRouteElement(routeElementEntity);
                CardRequest.updateCard(cardRouteEntity.getCard());
                ServletHelper.sendForward("/completecardinfo?cardID=" + cardRouteEntity.getCard().getCardID(), this, request, response);

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
