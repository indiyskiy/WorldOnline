package model.additionalentity;

import model.database.worldonlinedb.CardRouteEntity;
import model.database.worldonlinedb.RouteElementEntity;

import java.util.HashMap;

public class CompleteCardRouteInfo {
    private CardRouteEntity cardRouteEntity;
    private HashMap<Long, RouteElementEntity> routeElementEntityMap = new HashMap<Long, RouteElementEntity>();
    private CompleteTextGroupInfo completeTextGroupInfo;

    public CompleteCardRouteInfo(CardRouteEntity cardRouteEntity) {
        this.cardRouteEntity = cardRouteEntity;
    }

    public CompleteTextGroupInfo getCompleteTextGroupInfo() {
        return completeTextGroupInfo;
    }

    public void setCompleteTextGroupInfo(CompleteTextGroupInfo completeTextGroupInfo) {
        this.completeTextGroupInfo = completeTextGroupInfo;
    }

    public CardRouteEntity getCardRouteEntity() {
        return cardRouteEntity;
    }

    public void setCardRouteEntity(CardRouteEntity cardRouteEntity) {
        this.cardRouteEntity = cardRouteEntity;
    }

    public HashMap<Long, RouteElementEntity> getRouteElementEntityMap() {
        return routeElementEntityMap;
    }

    public void setRouteElementEntityMap(HashMap<Long, RouteElementEntity> routeElementEntityMap) {
        this.routeElementEntityMap = routeElementEntityMap;
    }
}
