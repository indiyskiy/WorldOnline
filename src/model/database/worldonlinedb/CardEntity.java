package model.database.worldonlinedb;

import model.constants.databaseenumeration.CardType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 29.10.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Card", schema = "", catalog = "worldonline")
@Entity
public class CardEntity {

    @javax.persistence.Column(name = "CardID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardID;

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    @javax.persistence.Column(name = "CardType")
    @Basic
    private Integer cardType;

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    @javax.persistence.Column(name = "CardVersion")
    @Basic
    private Integer cardVersion;

    public Integer getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Integer cardVersion) {
        this.cardVersion = cardVersion;
    }

    @javax.persistence.Column(name = "CreationTimestamp")
    @Basic
    private Timestamp creationTimestamp;

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @javax.persistence.Column(name = "LastUpdateTimestamp")
    @Basic
    private Timestamp lastUpdateTimestamp;

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    @javax.persistence.Column(name = "CardName")
    @Basic
    private String cardName;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (cardID != null ? !cardID.equals(that.cardID) : that.cardID != null)
            return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null)
            return false;
        if (cardVersion != null ? !cardVersion.equals(that.cardVersion) : that.cardVersion != null)
            return false;
        if (creationTimestamp != null ? !creationTimestamp.equals(that.creationTimestamp) : that.creationTimestamp != null)
            return false;
        if (lastUpdateTimestamp != null ? !lastUpdateTimestamp.equals(that.lastUpdateTimestamp) : that.lastUpdateTimestamp != null)
            return false;
        if (cardName != null ? !cardName.equals(that.cardName) : that.cardName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardID != null ? cardID.hashCode() : 0;
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (cardVersion != null ? cardVersion.hashCode() : 0);
        result = 31 * result + (creationTimestamp != null ? creationTimestamp.hashCode() : 0);
        result = 31 * result + (lastUpdateTimestamp != null ? lastUpdateTimestamp.hashCode() : 0);
        result = 31 * result + (cardName != null ? cardName.hashCode() : 0);
        return result;
    }

    public CardEntity() {
    }

    public CardEntity(CardType cardType, String cardName) {
        setLastUpdateTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        setCreationTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        setCardVersion(0);
        setCardType(cardType.getValue());
        setCardName(cardName);
    }
}
