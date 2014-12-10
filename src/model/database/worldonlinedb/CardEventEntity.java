package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CardEvent", schema = "", catalog = "worldonline")
@Entity
public class CardEventEntity {
    @Column(name = "CardEventID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardEventID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    @Column(name = "EventType")
    @Basic
    private Integer eventType;

    @Column(name = "Text")
    @Basic
    private String text;

    @javax.persistence.Column(name = "EventTimestamp")
    @Basic
    private Timestamp eventTimestamp;

    public CardEventEntity() {
    }

    public CardEventEntity(CardEntity card, UserContentEntity userContent, Integer eventType, Timestamp eventTimestamp) {
        this.card = card;
        this.userContent = userContent;
        this.eventType = eventType;
        this.eventTimestamp = eventTimestamp;
    }

    public CardEventEntity(CardEntity card, UserContentEntity userContent, Integer eventType, String text, Timestamp eventTimestamp) {
        this.card = card;
        this.userContent = userContent;
        this.eventType = eventType;
        this.text = text;
        this.eventTimestamp = eventTimestamp;
    }

    public Long getCardEventID() {
        return cardEventID;
    }

    public void setCardEventID(Long cardEventID) {
        cardEventID = cardEventID;
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

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer cardImageType) {
        this.eventType = cardImageType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Timestamp eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEventEntity that = (CardEventEntity) o;

        if (!cardEventID.equals(that.cardEventID)) return false;
        if (!card.equals(that.card)) return false;
        if (!eventType.equals(that.eventType)) return false;
        if (!eventTimestamp.equals(that.eventTimestamp)) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (!userContent.equals(that.userContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardEventID.hashCode();
        result = 31 * result + card.hashCode();
        result = 31 * result + userContent.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + eventTimestamp.hashCode();
        return result;
    }
}
