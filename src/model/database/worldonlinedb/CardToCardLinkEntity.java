package model.database.worldonlinedb;

import model.constants.databaseenumeration.CardToCardLinkType;

import javax.persistence.*;

@javax.persistence.Table(name = "CardToCardLink", schema = "", catalog = "worldonline")
@Entity
public class CardToCardLinkEntity {
    @javax.persistence.Column(name = "CardToCardLinkID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardToCardLinkID;

    public Long getCardToCardLinkID() {
        return cardToCardLinkID;
    }

    public void setCardToCardLinkID(Long cardToCardLinkID) {
        cardToCardLinkID = cardToCardLinkID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SourceCardID")
    private CardEntity sourceCard;

    public CardEntity getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(CardEntity sourceCard) {
        this.sourceCard = sourceCard;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TargetCardID")
    private CardEntity targetCard;

    public CardEntity getTargetCard() {
        return targetCard;
    }

    public void setTargetCard(CardEntity targetCard) {
        this.targetCard = targetCard;
    }

    @javax.persistence.Column(name = "CardToCardLinkType")
    @Basic
    private Integer cardToCardLinkType;

    public Integer getCardToCardLinkType() {
        return cardToCardLinkType;
    }

    public void setCardToCardLinkType(Integer cardToCardLinkType) {
        this.cardToCardLinkType = cardToCardLinkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardToCardLinkEntity that = (CardToCardLinkEntity) o;

        if (cardToCardLinkID != null ? !cardToCardLinkID.equals(that.cardToCardLinkID) : that.cardToCardLinkID != null)
            return false;
        if (sourceCard != null ? !sourceCard.equals(that.sourceCard) : that.sourceCard != null)
            return false;
        if (targetCard != null ? !targetCard.equals(that.targetCard) : that.targetCard != null)
            return false;
        if (cardToCardLinkType != null ? !cardToCardLinkType.equals(that.cardToCardLinkType) : that.cardToCardLinkType != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardToCardLinkID != null ? cardToCardLinkID.hashCode() : 0;
        result = 31 * result + (sourceCard != null ? sourceCard.hashCode() : 0);
        result = 31 * result + (targetCard != null ? targetCard.hashCode() : 0);
        result = 31 * result + (cardToCardLinkType != null ? cardToCardLinkType.hashCode() : 0);
        return result;
    }


    public CardToCardLinkEntity() {
    }

    public CardToCardLinkEntity(CardEntity sourceCard, CardEntity targetCard, CardToCardLinkType cardToCardLinkType) {
        this.sourceCard = sourceCard;
        this.targetCard = targetCard;
        this.cardToCardLinkType = cardToCardLinkType.getValue();
    }
}
