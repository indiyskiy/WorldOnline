package model.xmlparser.xmlview;

import model.database.worldonlinedb.CardEntity;

public class CardDishesPair {
    private String menuName;
    private CardEntity cardEntity;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }
}
