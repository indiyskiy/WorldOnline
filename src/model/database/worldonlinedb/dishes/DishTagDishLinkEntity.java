package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.CardEntity;

import javax.persistence.*;

@javax.persistence.Table(name = "DishTagDishLink", schema = "", catalog = "worldonline")
@Entity
public class DishTagDishLinkEntity {
    @javax.persistence.Column(name = "DishTagDishLinkID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishTagDishLinkID;

    public Long getDishTagDishLinkID() {
        return dishTagDishLinkID;
    }

    public void setDishTagDishLinkID(Long dishTagDishLinkID) {
        this.dishTagDishLinkID = dishTagDishLinkID;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DishID")
    private DishEntity dish;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DishTagID")
    private DishTagEntity dishTag;

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public DishTagEntity getDishTag() {
        return dishTag;
    }

    public void setDishTag(DishTagEntity dishTag) {
        this.dishTag = dishTag;
    }
}
