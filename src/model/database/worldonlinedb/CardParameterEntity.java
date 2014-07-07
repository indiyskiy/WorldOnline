package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "CardParameter", schema = "", catalog = "worldonline")
@Entity
public class CardParameterEntity {
    @javax.persistence.Column(name = "CardParameterID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardParameterID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @javax.persistence.Column(name = "CardParameterValue", columnDefinition = "TEXT")
    @Basic
    private String cardParameterValue;

    @javax.persistence.Column(name = "CardParameterName")
    @Basic
    private String cardParameterName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardParameterTypeID")
    private CardParameterTypeEntity cardParameterType;

    public Long getCardParameterID() {
        return cardParameterID;
    }

    public void setCardParameterID(Long cardParameterID) {
        this.cardParameterID = cardParameterID;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public String getCardParameterValue() {
        return cardParameterValue;
    }

    public void setCardParameterValue(String cardParameterValue) {
        this.cardParameterValue = cardParameterValue;
    }

    public String getCardParameterName() {
        return cardParameterName;
    }

    public void setCardParameterName(String cardParameterName) {
        this.cardParameterName = cardParameterName;
    }

    public CardParameterTypeEntity getCardParameterType() {
        return cardParameterType;
    }

    public void setCardParameterType(CardParameterTypeEntity cardParameterType) {
        this.cardParameterType = cardParameterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardParameterEntity that = (CardParameterEntity) o;

        if (cardParameterID != null ? !cardParameterID.equals(that.cardParameterID) : that.cardParameterID != null)
            return false;
        if (card != null ? !card.equals(that.card) : that.card != null)
            return false;
        if (cardParameterType != null ? !cardParameterType.equals(that.cardParameterType) : that.cardParameterType != null)
            return false;
        if (cardParameterValue != null ? !cardParameterValue.equals(that.cardParameterValue) : that.cardParameterValue != null)
            return false;
        if (cardParameterType != null ? !cardParameterType.equals(that.cardParameterType) : that.cardParameterType != null)
            return false;
        if (cardParameterName != null ? !cardParameterName.equals(that.cardParameterName) : that.cardParameterName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardParameterID != null ? cardParameterID.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (cardParameterType != null ? cardParameterType.hashCode() : 0);
        result = 31 * result + (cardParameterValue != null ? cardParameterValue.hashCode() : 0);
        result = 31 * result + (cardParameterType != null ? cardParameterType.hashCode() : 0);
        result = 31 * result + (cardParameterName != null ? cardParameterName.hashCode() : 0);
        return result;
    }

    public CardParameterEntity() {
    }

    public CardParameterEntity(CardEntity card, CardParameterTypeEntity cardParameterType, String cardParameterValue) {
        this.card = card;
        this.cardParameterValue = cardParameterValue;
        this.cardParameterType = cardParameterType;
        this.cardParameterName = card.getCardName() + "-" + cardParameterType;
    }
}
