package model.additionalentity;

import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.CardParameterEntity;

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
}
