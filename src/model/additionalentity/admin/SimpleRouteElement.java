package model.additionalentity.admin;


import model.additionalentity.SimpleCard;

public class SimpleRouteElement {
    Long routeElementID;
    SimpleCard simpleCard;
    int position;

    public SimpleCard getSimpleCard() {
        return simpleCard;
    }

    public void setSimpleCard(SimpleCard simpleCard) {
        this.simpleCard = simpleCard;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Long getRouteElementID() {
        return routeElementID;
    }

    public void setRouteElementID(Long routeElementID) {
        this.routeElementID = routeElementID;
    }
}
