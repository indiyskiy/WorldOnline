package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 6:49
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "CardCoordinate", schema = "", catalog = "worldonline")
@Entity
public class CardCoordinateEntity {
    @javax.persistence.Column(name = "CardCoordinateID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardCoordinateID;

    public Long getCardCoordinateID() {
        return cardCoordinateID;
    }

    public void setCardCoordinateID(Long cardCoordinateID) {
        this.cardCoordinateID = cardCoordinateID;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    @javax.persistence.Column(name = "Longitude")
    @Basic
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @javax.persistence.Column(name = "Latitude")
    @Basic
    private Double latitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCoordinateEntity that = (CardCoordinateEntity) o;

        if (cardCoordinateID != null ? !cardCoordinateID.equals(that.cardCoordinateID) : that.cardCoordinateID != null)
            return false;
        if (card != null ? !card.equals(that.card) : that.card != null)
            return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null)
            return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardCoordinateID != null ? cardCoordinateID.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }
}
