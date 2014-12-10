package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardRouteEntity;
import model.database.worldonlinedb.RouteCoordinateEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRouteCoordinateServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddRouteCoordinateServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long routeCardID = Long.parseLong(request.getParameter("routeCardID"));
            if (routeCardID != null && routeCardID != 0) {
                CardRouteEntity cardRouteEntity = RouteRequest.getCardRoute(routeCardID);
                if (cardRouteEntity != null) {
                    Double latitude = Double.parseDouble(request.getParameter("latitude"));
                    Double longitude = Double.parseDouble(request.getParameter("longitude"));
                    String positionString = request.getParameter("position");
                    int position = 0;
                    int maxPosition = RouteRequest.getMaxCoordinateNumber(routeCardID);
                    if (positionString == null || positionString.isEmpty()) {
                        position = maxPosition;
                    } else {
                        position = Integer.parseInt(positionString);
                        if (position != maxPosition) {
                            RouteRequest.moveCoordinates(position, routeCardID);
                        }
                    }
                    RouteCoordinateEntity routeCoordinateEntity = new RouteCoordinateEntity();
                    routeCoordinateEntity.setCardRoute(cardRouteEntity);
                    routeCoordinateEntity.setLatitude(latitude);
                    routeCoordinateEntity.setLongitude(longitude);
                    routeCoordinateEntity.setPosition(position);
                    RouteRequest.addRouteCoordinate(routeCoordinateEntity);
                    ServletHelper.sendForward("/completecardinfo?cardID=" + cardRouteEntity.getCard().getCardID(), this, request, response);
                }
            }
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
