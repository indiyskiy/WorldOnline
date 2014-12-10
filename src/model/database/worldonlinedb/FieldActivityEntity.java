package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "FieldActivity", schema = "", catalog = "worldonline")
@Entity
public class FieldActivityEntity {
    @Column(name = "FieldActivityID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fieldActivityID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    @Column(name = "ActivityTimestamp")
    @Basic
    private Timestamp activityTimestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardParameterTypeID")
    private CardParameterTypeEntity cardParameterType;

    @Column(name = "Text")
    @Basic
    private String text;

    public FieldActivityEntity() {
    }

    public FieldActivityEntity(CardEntity card, UserContentEntity userContent, Timestamp activityTimestamp, CardParameterTypeEntity cardParameterType) {
        this.card = card;
        this.userContent = userContent;
        this.activityTimestamp = activityTimestamp;
        this.cardParameterType = cardParameterType;
    }

    public FieldActivityEntity(CardEntity card, UserContentEntity userContent, Timestamp activityTimestamp, CardParameterTypeEntity cardParameterType, String text) {
        this.card = card;
        this.userContent = userContent;
        this.activityTimestamp = activityTimestamp;
        this.cardParameterType = cardParameterType;
        this.text = text;
    }

    public Long getCardEventID() {
        return fieldActivityID;
    }

    public void setCardEventID(Long cardEventID) {
        fieldActivityID = cardEventID;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public UserContentEntity getUserContent() {
        return userContent;
    }

    public void setUserContent(UserContentEntity userContent) {
        this.userContent = userContent;
    }

    public Timestamp getActivityTimestamp() {
        return activityTimestamp;
    }

    public void setActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
    }

    public CardParameterTypeEntity getCardParameterType() {
        return cardParameterType;
    }

    public void setCardParameterType(CardParameterTypeEntity cardParameterType) {
        this.cardParameterType = cardParameterType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldActivityEntity that = (FieldActivityEntity) o;

        if (!fieldActivityID.equals(that.fieldActivityID)) return false;
        if (!activityTimestamp.equals(that.activityTimestamp)) return false;
        if (!card.equals(that.card)) return false;
        if (!cardParameterType.equals(that.cardParameterType)) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (!userContent.equals(that.userContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fieldActivityID.hashCode();
        result = 31 * result + card.hashCode();
        result = 31 * result + userContent.hashCode();
        result = 31 * result + activityTimestamp.hashCode();
        result = 31 * result + cardParameterType.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
