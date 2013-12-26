package model.additionalentity;

import model.database.worldonlinedb.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 02.11.13
 * Time: 5:13
 * To change this template use File | Settings | File Templates.
 */
public class CompleteCardInfo {
    private CardEntity cardEntity;
    private HashMap<Long, CompleteCardTagInfo> completeCardTagInfoMap = new HashMap<Long, CompleteCardTagInfo>();
    private HashMap<Long,CompleteCardImageInfo> completeCardImageInfoMap=new HashMap<Long, CompleteCardImageInfo>();
    private HashMap<Long, CardParameterEntity> cardParameterEntityMap=new HashMap<Long, CardParameterEntity>();
    private CompleteCardRootInfo completeCardRootInfo;
    private HashMap<Long,CompleteTextCardInfo> completeTextCardInfoMap=new HashMap<Long, CompleteTextCardInfo>();
    private CardCoordinateEntity cardCoordinateEntity;
    private HashMap<Long, CardToCardLinkEntity> cardToCardLinkEntityMap=new HashMap<Long, CardToCardLinkEntity>();
    private HashMap<Long, CardToCardLinkEntity> cardToCardLinkedOnEntityMap=new HashMap<Long, CardToCardLinkEntity>();

    public HashMap<Long, CardToCardLinkEntity> getCardToCardLinkEntityMap() {
        return cardToCardLinkEntityMap;
    }

    public void setCardToCardLinkEntityMap(HashMap<Long, CardToCardLinkEntity> cardToCardLinkEntityMap) {
        this.cardToCardLinkEntityMap = cardToCardLinkEntityMap;
    }

    public CompleteCardInfo(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public HashMap<Long, CompleteCardTagInfo> getCompleteCardTagInfoMap() {
        return completeCardTagInfoMap;
    }

    public void setCompleteCardTagInfoMap(HashMap<Long, CompleteCardTagInfo> completeCardTagInfoMap) {
        this.completeCardTagInfoMap = completeCardTagInfoMap;
    }

    public HashMap<Long, CompleteCardImageInfo> getCompleteCardImageInfoMap() {
        return completeCardImageInfoMap;
    }

    public void setCompleteCardImageInfoMap(HashMap<Long, CompleteCardImageInfo> completeCardImageInfoMap) {
        this.completeCardImageInfoMap = completeCardImageInfoMap;
    }

    public HashMap<Long, CardParameterEntity> getCardParameterEntityMap() {
        return cardParameterEntityMap;
    }

    public void setCardParameterEntityMap(HashMap<Long, CardParameterEntity> cardParameterEntityMap) {
        this.cardParameterEntityMap = cardParameterEntityMap;
    }

    public void setCompleteCardRootInfo(CompleteCardRootInfo completeCardRootInfo) {
        this.completeCardRootInfo = completeCardRootInfo;
    }

    public CompleteCardRootInfo getCompleteCardRootInfo() {
        return completeCardRootInfo;
    }

    public HashMap<Long, CompleteTextCardInfo> getCompleteTextCardInfoMap() {
        return completeTextCardInfoMap;
    }

    public void setCompleteTextCardInfoMap(HashMap<Long, CompleteTextCardInfo> completeTextCardInfoMap) {
        this.completeTextCardInfoMap = completeTextCardInfoMap;
    }

    public void setCardCoordinateEntity(CardCoordinateEntity cardCoordinateEntity) {
        this.cardCoordinateEntity = cardCoordinateEntity;
    }

    public CardCoordinateEntity getCardCoordinateEntity() {
        return cardCoordinateEntity;
    }

    public HashMap<Long, CardToCardLinkEntity> getCardToCardLinkedOnEntityMap() {
        return cardToCardLinkedOnEntityMap;
    }
}
