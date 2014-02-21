package model.additionalentity;

import model.database.worldonlinedb.CardEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 04.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class SimpleCard {
   private CardEntity cardEntity;
   private String name;
   private String description;
   private String address;

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
