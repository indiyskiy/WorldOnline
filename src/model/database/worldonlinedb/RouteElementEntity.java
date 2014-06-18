package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 31.10.13
 * Time: 5:50
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "RootElement", schema = "", catalog = "worldonline")
@Entity
public class RouteElementEntity {
    @javax.persistence.Column(name = "RootElementID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rootElementID;

    public Long getRootElementID() {
        return rootElementID;
    }

    public void setRootElementID(Long rootElementID) {
        this.rootElementID = rootElementID;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PlaceCardID")
    private CardEntity placeCard;

    public CardEntity getPlaceCard() {
        return placeCard;
    }

    public void setPlaceCard(CardEntity placeCard) {
        this.placeCard = placeCard;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardRootID")
    private CardRootEntity cardRoot;

    public CardRootEntity getCardRoot() {
        return cardRoot;
    }

    public void setCardRoot(CardRootEntity cardRoot) {
        this.cardRoot = cardRoot;
    }

    @javax.persistence.Column(name = "RootElementNumber")
    @Basic
    private Integer rootElementNumber;

    public Integer getRootElementNumber() {
        return rootElementNumber;
    }

    public void setRootElementNumber(Integer rootElementNumber) {
        this.rootElementNumber = rootElementNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteElementEntity that = (RouteElementEntity) o;

        if (rootElementID != null ? !rootElementID.equals(that.rootElementID) : that.rootElementID != null)
            return false;
        if (placeCard != null ? !placeCard.equals(that.placeCard) : that.placeCard != null)
            return false;
        if (cardRoot != null ? !cardRoot.equals(that.cardRoot) : that.cardRoot != null)
            return false;
        if (rootElementNumber != null ? !rootElementNumber.equals(that.rootElementNumber) : that.rootElementNumber != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = rootElementID != null ? rootElementID.hashCode() : 0;
        result = 31 * result + (placeCard != null ? placeCard.hashCode() : 0);
        result = 31 * result + (cardRoot != null ? cardRoot.hashCode() : 0);
        result = 31 * result + (rootElementNumber != null ? rootElementNumber.hashCode() : 0);
        return result;
    }
}
