package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.TextGroupEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "Price", schema = "", catalog = "worldonline")
@Entity
public class PriceEntity {
    @javax.persistence.Column(name = "PriceID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long priceID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PriceNameID")
    private TextGroupEntity priceName;

    @javax.persistence.Column(name = "LastUpdateTimestamp")
    @Basic
    private Timestamp lastUpdateTimestamp;

    public Long getPriceID() {
        return priceID;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public TextGroupEntity getPriceName() {
        return priceName;
    }

    public void setPriceName(TextGroupEntity priceName) {
        this.priceName = priceName;
    }

    public Timestamp getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }
}
