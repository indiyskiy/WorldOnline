package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.TextGroupEntity;

import javax.persistence.*;

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
}
