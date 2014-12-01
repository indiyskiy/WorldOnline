package model.additionalentity.admin;


import model.database.worldonlinedb.RouteCoordinateEntity;

import java.util.ArrayList;

public class RouteCard {
    Long routeCardID;
    ArrayList<SimpleRouteElement> elements = new ArrayList<>();
    ArrayList<RouteCoordinateEntity> routeCoordinates = new ArrayList<>();
    Long cardRouteID;

    public ArrayList<SimpleRouteElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<SimpleRouteElement> elements) {
        this.elements = elements;
    }

    public ArrayList<RouteCoordinateEntity> getRouteCoordinates() {
        return routeCoordinates;
    }

    public void setRouteCoordinates(ArrayList<RouteCoordinateEntity> routeCoordinates) {
        this.routeCoordinates = routeCoordinates;
    }

    public Long getCardRouteID() {
        return cardRouteID;
    }

    public void setCardRouteID(Long cardRouteID) {
        this.cardRouteID = cardRouteID;
    }

    public Long getRouteCardID() {
        return routeCardID;
    }

    public void setRouteCardID(Long routeCardID) {
        this.routeCardID = routeCardID;
    }
}
