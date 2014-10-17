package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "CardRoute", schema = "", catalog = "worldonline")
@Entity
public class CardRouteEntity {
    @javax.persistence.Column(name = "CardRouteID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardRouteID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RouteDescriptionTextGroupID")
    private TextGroupEntity routeDescriptionTextGroup;

    public Long getCardRouteID() {
        return cardRouteID;
    }

    public void setCardRouteID(Long cardRouteID) {
        this.cardRouteID = cardRouteID;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public TextGroupEntity getRouteDescriptionTextGroup() {
        return routeDescriptionTextGroup;
    }

    public void setRouteDescriptionTextGroup(TextGroupEntity routeDescriptionTextGroup) {
        this.routeDescriptionTextGroup = routeDescriptionTextGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRouteEntity that = (CardRouteEntity) o;

        if (cardRouteID != null ? !cardRouteID.equals(that.cardRouteID) : that.cardRouteID != null) return false;
        if (card != null ? !card.equals(that.card) : that.card != null) return false;
        if (routeDescriptionTextGroup != null ? !routeDescriptionTextGroup.equals(that.routeDescriptionTextGroup) : that.routeDescriptionTextGroup != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardRouteID != null ? cardRouteID.hashCode() : 0;
        result = 31 * result + (card != null ? card.hashCode() : 0);

        result = 31 * result + (routeDescriptionTextGroup != null ? routeDescriptionTextGroup.hashCode() : 0);
        return result;
    }

    public CardRouteEntity() {
    }

    public CardRouteEntity(CardEntity card) {
        this.card = card;
    }
}
