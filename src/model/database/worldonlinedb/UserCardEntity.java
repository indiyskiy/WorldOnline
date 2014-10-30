package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "UserCard", schema = "", catalog = "worldonline")
@Entity
public class UserCardEntity {
    @javax.persistence.Column(name = "UserCardID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCardID;

    @javax.persistence.Column(name = "LastUpdateTimeStamp")
    @Basic
    private Timestamp lastUpdateTimeStamp;

    @javax.persistence.Column(name = "DownloadTimeStamp")
    @Basic
    private Timestamp downloadTimeStamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    public Long getUserCardID() {
        return userCardID;
    }

    public void setUserCardID(Long userCardID) {
        this.userCardID = userCardID;
    }

    public Timestamp getLastUpdateTimeStamp() {
        return lastUpdateTimeStamp;
    }

    public void setLastUpdateTimeStamp(Timestamp lastUpdateTimeStamp) {
        this.lastUpdateTimeStamp = lastUpdateTimeStamp;
    }

    public Timestamp getDownloadTimeStamp() {
        return downloadTimeStamp;
    }

    public void setDownloadTimeStamp(Timestamp downloadTimeStamp) {
        this.downloadTimeStamp = downloadTimeStamp;
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

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        UserCardEntity userCardEntity = (UserCardEntity) o;
        if (!userCardEntity.userCardID.equals(this.userCardID)) {
            return false;
        }
        if (!this.card.equals(userCardEntity.card)) {
            return false;
        }
        if (this.downloadTimeStamp != userCardEntity.downloadTimeStamp) {
            return false;
        }
        if (this.lastUpdateTimeStamp != userCardEntity.lastUpdateTimeStamp) {
            return false;
        }
        if (!this.userContent.equals(userCardEntity.userContent)) {
            return false;
        }
        return true;
    }
}
