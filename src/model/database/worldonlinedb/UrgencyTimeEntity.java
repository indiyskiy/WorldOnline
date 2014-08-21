package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "UrgencyTime", schema = "", catalog = "worldonline")
@Entity
public class UrgencyTimeEntity {
    @javax.persistence.Column(name = "UrgencyTimeID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long urgencyTimeID;

    @javax.persistence.Column(name = "OnTimestamp")
    @Basic
    private Timestamp onTimeStamp;

    @javax.persistence.Column(name = "OffTimestamp")
    @Basic
    private Timestamp offTimeStamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cardID")
    private CardEntity card;

    public Long getUrgencyTimeID() {
        return urgencyTimeID;
    }

    public void setUrgencyTimeID(Long urgencyTimeID) {
        this.urgencyTimeID = urgencyTimeID;
    }

    public Timestamp getOnTimeStamp() {
        return onTimeStamp;
    }

    public void setOnTimeStamp(Timestamp onTimeStamp) {
        this.onTimeStamp = onTimeStamp;
    }

    public Timestamp getOffTimeStamp() {
        return offTimeStamp;
    }

    public void setOffTimeStamp(Timestamp offTimeStamp) {
        this.offTimeStamp = offTimeStamp;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
