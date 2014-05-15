package model.additionalentity;

import model.additionalentity.admin.CompleteMenuInfo;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.TextType;
import model.database.worldonlinedb.*;

import java.util.Collection;
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
    private HashMap<Long, CompleteCardImageInfo> completeCardImageInfoMap = new HashMap<Long, CompleteCardImageInfo>();
    private HashMap<Long, CardParameterEntity> cardParameterEntityMap = new HashMap<Long, CardParameterEntity>();
    private CompleteCardRootInfo completeCardRootInfo;
    private HashMap<Long, CompleteTextCardInfo> completeTextCardInfoMap = new HashMap<Long, CompleteTextCardInfo>();
    private CardCoordinateEntity cardCoordinateEntity;
    private HashMap<Long, CardToCardLinkEntity> cardToCardLinkEntityMap = new HashMap<Long, CardToCardLinkEntity>();
    private HashMap<Long, CardToCardLinkEntity> cardToCardLinkedOnEntityMap = new HashMap<Long, CardToCardLinkEntity>();
    private HashMap<Long, CompleteMenuInfo> completeMenuInfoMap = new HashMap<Long, CompleteMenuInfo>();

    public CompleteCardInfo(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public HashMap<Long, CardToCardLinkEntity> getCardToCardLinkEntityMap() {
        return cardToCardLinkEntityMap;
    }

    public void setCardToCardLinkEntityMap(HashMap<Long, CardToCardLinkEntity> cardToCardLinkEntityMap) {
        this.cardToCardLinkEntityMap = cardToCardLinkEntityMap;
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

    public CompleteCardRootInfo getCompleteCardRootInfo() {
        return completeCardRootInfo;
    }

    public void setCompleteCardRootInfo(CompleteCardRootInfo completeCardRootInfo) {
        this.completeCardRootInfo = completeCardRootInfo;
    }

    public HashMap<Long, CompleteTextCardInfo> getCompleteTextCardInfoMap() {
        return completeTextCardInfoMap;
    }

    public void setCompleteTextCardInfoMap(HashMap<Long, CompleteTextCardInfo> completeTextCardInfoMap) {
        this.completeTextCardInfoMap = completeTextCardInfoMap;
    }

    public CardCoordinateEntity getCardCoordinateEntity() {
        return cardCoordinateEntity;
    }

    public void setCardCoordinateEntity(CardCoordinateEntity cardCoordinateEntity) {
        this.cardCoordinateEntity = cardCoordinateEntity;
    }

    public HashMap<Long, CardToCardLinkEntity> getCardToCardLinkedOnEntityMap() {
        return cardToCardLinkedOnEntityMap;
    }

    public void setCardToCardLinkedOnEntityMap(HashMap<Long, CardToCardLinkEntity> cardToCardLinkedOnEntityMap) {
        this.cardToCardLinkedOnEntityMap = cardToCardLinkedOnEntityMap;
    }

    public HashMap<Long, CompleteMenuInfo> getCompleteMenuInfoMap() {
        return completeMenuInfoMap;
    }

    public void setCompleteMenuInfoMap(HashMap<Long, CompleteMenuInfo> completeMenuInfoMap) {
        this.completeMenuInfoMap = completeMenuInfoMap;
    }

    public SimpleCard getSimpleCard() {
        SimpleCard simpleCard = new SimpleCard();
        simpleCard.setCardEntity(cardEntity);
        Collection<CompleteTextCardInfo> texts = completeTextCardInfoMap.values();
        for (CompleteTextCardInfo text : texts) {
            if (text.getTextCardEntity().getCardTextType() == TextType.Address.getValue()) {
                CompleteTextGroupInfo completeTextGroupInfo = text.getCompleteTextGroupInfo();
                Collection<TextEntity> textEntities = completeTextGroupInfo.getTextEntityMap().values();
                for (TextEntity textEntity : textEntities) {
                    if (textEntity.getLanguageID() == LanguageType.Russian.getValue()) {
                        simpleCard.setAddress(textEntity.getText());
                        break;
                    }
                }
            }
            if (text.getTextCardEntity().getCardTextType() == TextType.Description.getValue()) {
                CompleteTextGroupInfo completeTextGroupInfo = text.getCompleteTextGroupInfo();
                Collection<TextEntity> textEntities = completeTextGroupInfo.getTextEntityMap().values();
                for (TextEntity textEntity : textEntities) {
                    if (textEntity.getLanguageID() == LanguageType.Russian.getValue()) {
                        simpleCard.setDescription(textEntity.getText());
                        break;
                    }
                }
            }
            if (text.getTextCardEntity().getCardTextType() == TextType.Name.getValue()) {
                CompleteTextGroupInfo completeTextGroupInfo = text.getCompleteTextGroupInfo();
                Collection<TextEntity> textEntities = completeTextGroupInfo.getTextEntityMap().values();
                for (TextEntity textEntity : textEntities) {
                    if (textEntity.getLanguageID() == LanguageType.Russian.getValue()) {
                        simpleCard.setName(textEntity.getText());
                        break;
                    }
                }
            }
        }
        return simpleCard;
    }
}
