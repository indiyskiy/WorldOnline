package model.database.worldonlinedb;

import model.constants.databaseenumeration.CardParameterType;
import model.constants.databaseenumeration.DataType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 29.10.13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CardParameter", schema = "", catalog = "worldonline")
@Entity
public class CardParameterEntity {
    @javax.persistence.Column(name = "CardParameterID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardParameterID;

    public Long getCardParameterID() {
        return cardParameterID;
    }

    public void setCardParameterID(Long cardParameterID) {
        this.cardParameterID = cardParameterID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    @javax.persistence.Column(name = "CardParameterType")
    @Basic
    private Integer cardParameterType;

    public Integer getCardParameterType() {
        return cardParameterType;
    }

    public void setCardParameterType(Integer cardParameterType) {
        this.cardParameterType = cardParameterType;
    }

    @javax.persistence.Column(name = "CardParameterValue",columnDefinition="TEXT")
    @Basic
    private String cardParameterValue;

    public String getCardParameterValue() {
        return cardParameterValue;
    }

    public void setCardParameterValue(String cardParameterValue) {
        this.cardParameterValue = cardParameterValue;
    }

    @javax.persistence.Column(name = "CardParameterDataType")
    @Basic
    private Integer cardParameterDataType;

    public Integer getCardParameterDataType() {
        return cardParameterDataType;
    }

    public void setCardParameterDataType(Integer cardParameterDataType) {
        this.cardParameterDataType = cardParameterDataType;
    }

    @javax.persistence.Column(name = "CardParameterName")
    @Basic
    private String cardParameterName;

    public String getCardParameterName() {
        return cardParameterName;
    }

    public void setCardParameterName(String cardParameterName) {
        this.cardParameterName = cardParameterName;
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
        if (cardParameterDataType != null ? !cardParameterDataType.equals(that.cardParameterDataType) : that.cardParameterDataType != null)
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
        result = 31 * result + (cardParameterDataType != null ? cardParameterDataType.hashCode() : 0);
        result = 31 * result + (cardParameterName != null ? cardParameterName.hashCode() : 0);
        return result;
    }

    public CardParameterEntity() {
    }

    public CardParameterEntity(CardEntity card, CardParameterType cardParameterType, DataType cardParameterDataType, String cardParameterValue) {
        this.card = card;
        this.cardParameterType = cardParameterType.getValue();
        this.cardParameterValue = cardParameterValue;
        this.cardParameterDataType = cardParameterDataType.getValue();
        this.cardParameterName=card.getCardName()+"-"+cardParameterType;
    }
}
