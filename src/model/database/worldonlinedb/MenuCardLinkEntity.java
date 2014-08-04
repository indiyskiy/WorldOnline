package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "MenuCardLink", schema = "", catalog = "worldonline")
@Entity
public class MenuCardLinkEntity {
    @javax.persistence.Column(name = "MenuCardLinkID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuCardLinkID;

    public Long getMenuCardLinkID() {
        return menuCardLinkID;
    }

    public void setMenuCardLinkID(Long menuCardLinkID) {
        this.menuCardLinkID = menuCardLinkID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MenuID")
    private MenuEntity menu;

    public MenuEntity getMenu() {

        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CardID")
    private CardEntity card;

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
