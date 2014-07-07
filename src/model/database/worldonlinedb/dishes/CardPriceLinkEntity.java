package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.AdminUserAdditionalInfoEntity;
import model.database.worldonlinedb.CardEntity;

import javax.persistence.*;

@javax.persistence.Table(name = "CardPriceLink", schema = "", catalog = "worldonline")
@Entity
public class CardPriceLinkEntity {

    @javax.persistence.Column(name = "CardPriceLinkID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardPriceLinkID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PriceID")
    private PriceEntity price;

    public Long getCardPriceLinkID() {
        return cardPriceLinkID;
    }

    public void setCardPriceLinkID(Long cardPriceLinkID) {
        this.cardPriceLinkID = cardPriceLinkID;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}
