package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "RouteElement", schema = "", catalog = "worldonline")
@Entity
public class RouteElementEntity {
    @javax.persistence.Column(name = "RouteElementID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routeElementID;

    public Long getRouteElementID() {
        return routeElementID;
    }

    public void setRouteElementID(Long routeElementID) {
        this.routeElementID = routeElementID;
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
    @JoinColumn(name = "CardRouteID")
    private CardRouteEntity cardRoute;

    public CardRouteEntity getCardRoute() {
        return cardRoute;
    }

    public void setCardRoute(CardRouteEntity cardRoute) {
        this.cardRoute = cardRoute;
    }

    @javax.persistence.Column(name = "RouteElementNumber")
    @Basic
    private Integer routeElementNumber;

    public Integer getRouteElementNumber() {
        return routeElementNumber;
    }

    public void setRouteElementNumber(Integer routeElementNumber) {
        this.routeElementNumber = routeElementNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteElementEntity that = (RouteElementEntity) o;

        if (routeElementID != null ? !routeElementID.equals(that.routeElementID) : that.routeElementID != null)
            return false;
        if (placeCard != null ? !placeCard.equals(that.placeCard) : that.placeCard != null)
            return false;
        if (cardRoute != null ? !cardRoute.equals(that.cardRoute) : that.cardRoute != null)
            return false;
        if (routeElementNumber != null ? !routeElementNumber.equals(that.routeElementNumber) : that.routeElementNumber != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = routeElementID != null ? routeElementID.hashCode() : 0;
        result = 31 * result + (placeCard != null ? placeCard.hashCode() : 0);
        result = 31 * result + (cardRoute != null ? cardRoute.hashCode() : 0);
        result = 31 * result + (routeElementNumber != null ? routeElementNumber.hashCode() : 0);
        return result;
    }
}
