package model.database.worldonlinedb.dishes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Table(name = "Price", schema = "", catalog = "worldonline")
@Entity
public class PriceEntity {
    @javax.persistence.Column(name = "PriceID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long priceID;

    public Long getPriceID() {
        return priceID;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }
}
