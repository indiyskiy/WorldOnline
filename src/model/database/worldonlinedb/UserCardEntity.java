package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Илья on 28.03.14.
 */
@javax.persistence.Table(name = "UserCard", schema = "", catalog = "worldonline")
@Entity
public class UserCardEntity {
    @javax.persistence.Column(name = "UserCardID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCard;

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
    private UserContentEntity userContentID;

    public Long getUserCard() {
        return userCard;
    }

    public void setUserCard(Long userCard) {
        this.userCard = userCard;
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

    public UserContentEntity getUserContentID() {
        return userContentID;
    }

    public void setUserContentID(UserContentEntity userContentID) {
        this.userContentID = userContentID;
    }
}
