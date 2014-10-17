package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "RouteCoordinate", schema = "", catalog = "worldonline")
@Entity
public class RouteCoordinateEntity {
    @javax.persistence.Column(name = "RouteCoordinateID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routeCoordinateID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardRouteID")
    private CardRouteEntity cardRoute;

    @javax.persistence.Column(name = "Longitude")
    @Basic
    private Double longitude;


    @javax.persistence.Column(name = "Position")
    @Basic
    private Integer position;

    @javax.persistence.Column(name = "Latitude")
    @Basic
    private Double latitude;


    public Long getRouteCoordinateID() {
        return routeCoordinateID;
    }

    public void setRouteCoordinateID(Long routeCoordinateID) {
        this.routeCoordinateID = routeCoordinateID;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public CardRouteEntity getCardRoute() {
        return cardRoute;
    }

    public void setCardRoute(CardRouteEntity cardRoute) {
        this.cardRoute = cardRoute;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

