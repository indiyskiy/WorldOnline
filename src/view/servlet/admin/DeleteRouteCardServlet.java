package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.RouteElementEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRouteCardServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteRouteCardServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long routeElementID = Long.parseLong(request.getParameter("routeElementID"));
            RouteElementEntity routeElementEntity = RouteRequest.getRouteElement(routeElementID);
            if (routeElementEntity != null) {
                CardEntity cardEntity = routeElementEntity.getCardRoute().getCard();
                Long cardID = cardEntity.getCardID();
                CardRequest.updateCard(cardEntity);
                RouteRequest.deleteRouteElement(routeElementEntity);
                ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
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
