package model.additionalentity;

import model.database.worldonlinedb.CardRootEntity;
import model.database.worldonlinedb.RootElementEntity;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 14.11.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
public class CompleteCardRootInfo {
    private CardRootEntity cardRootEntity;
    private HashMap<Long, RootElementEntity> rootElementEntityMap = new HashMap<Long, RootElementEntity>();
    private CompleteTextGroupInfo completeTextGroupInfo;

    public CompleteCardRootInfo(CardRootEntity cardRootEntity) {
        this.cardRootEntity = cardRootEntity;
    }

    public CompleteTextGroupInfo getCompleteTextGroupInfo() {
        return completeTextGroupInfo;
    }

    public void setCompleteTextGroupInfo(CompleteTextGroupInfo completeTextGroupInfo) {
        this.completeTextGroupInfo = completeTextGroupInfo;
    }

    public CardRootEntity getCardRootEntity() {
        return cardRootEntity;
    }

    public void setCardRootEntity(CardRootEntity cardRootEntity) {
        this.cardRootEntity = cardRootEntity;
    }

    public HashMap<Long, RootElementEntity> getRootElementEntityMap() {
        return rootElementEntityMap;
    }

    public void setRootElementEntityMap(HashMap<Long, RootElementEntity> rootElementEntityMap) {
        this.rootElementEntityMap = rootElementEntityMap;
    }
}
