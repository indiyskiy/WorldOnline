package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CardActivity", schema = "", catalog = "worldonline")
@Entity
public class CardActivityEntity {
    @Column(name = "CardActivityID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardActivityID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    @Column(name = "OnTimestamp")
    @Basic
    private Timestamp onTimestamp;

    @Column(name = "OffTimestamp")
    @Basic
    private Timestamp offTimestamp;

    public CardActivityEntity() {
    }

    public CardActivityEntity(CardEntity card, UserContentEntity userContent, Timestamp onTimestamp, Timestamp offTimestamp) {
        this.card = card;
        this.userContent = userContent;
        this.onTimestamp = onTimestamp;
        this.offTimestamp = offTimestamp;
    }

    public Long getCardEventID() {
        return cardActivityID;
    }

    public void setCardEventID(Long cardEventID) {
        cardActivityID = cardEventID;
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

    public Timestamp getOnTimestamp() {
        return onTimestamp;
    }

    public void setOnTimestamp(Timestamp onTimestamp) {
        this.onTimestamp = onTimestamp;
    }

    public Timestamp getOffTimestamp() {
        return offTimestamp;
    }

    public void setOffTimestamp(Timestamp offTimestamp) {
        this.offTimestamp = offTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardActivityEntity that = (CardActivityEntity) o;

        if (!cardActivityID.equals(that.cardActivityID)) return false;
        if (!card.equals(that.card)) return false;
        if (!offTimestamp.equals(that.offTimestamp)) return false;
        if (!onTimestamp.equals(that.onTimestamp)) return false;
        if (!userContent.equals(that.userContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardActivityID.hashCode();
        result = 31 * result + card.hashCode();
        result = 31 * result + userContent.hashCode();
        result = 31 * result + onTimestamp.hashCode();
        result = 31 * result + offTimestamp.hashCode();
        return result;
    }
}
