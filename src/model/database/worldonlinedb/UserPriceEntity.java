package model.database.worldonlinedb;

import model.database.worldonlinedb.dishes.PriceEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "UserPrice", schema = "", catalog = "worldonline")
@Entity
public class UserPriceEntity {

    @javax.persistence.Column(name = "UserPriceID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userPriceID;

    @javax.persistence.Column(name = "LastUpdateTimeStamp")
    @Basic
    private Timestamp lastUpdateTimeStamp;

    @javax.persistence.Column(name = "DownloadTimeStamp")
    @Basic
    private Timestamp downloadTimeStamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PriceID")
    private PriceEntity price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    public Long getUserPriceID() {
        return userPriceID;
    }

    public void setUserPriceID(Long userPriceID) {
        this.userPriceID = userPriceID;
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

    public UserContentEntity getUserContent() {
        return userContent;
    }

    public void setUserContent(UserContentEntity userContent) {
        this.userContent = userContent;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        UserPriceEntity userPriceEntity = (UserPriceEntity) o;
        if (!userPriceEntity.userPriceID.equals(this.userPriceID)) {
            return false;
        }
        if (!this.price.equals(userPriceEntity.price)) {
            return false;
        }
        if (this.downloadTimeStamp != userPriceEntity.downloadTimeStamp) {
            return false;
        }
        if (this.lastUpdateTimeStamp != userPriceEntity.lastUpdateTimeStamp) {
            return false;
        }
        if (!this.userContent.equals(userPriceEntity.userContent)) {
            return false;
        }
        return true;
    }
}
