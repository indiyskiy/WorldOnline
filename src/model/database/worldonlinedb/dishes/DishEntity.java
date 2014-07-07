package model.database.worldonlinedb.dishes;


import javax.persistence.*;

@javax.persistence.Table(name = "Dish", schema = "", catalog = "worldonline")
@Entity
public class DishEntity {
    @javax.persistence.Column(name = "DishID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DishCategoryID")
    private DishCategoryEntity dishCategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PriceID")
    private PriceEntity price;


    @javax.persistence.Column(name = "coast")
    @Basic
    private Float coast;

    public Long getDishID() {
        return dishID;
    }

    public void setDishID(Long dishID) {
        this.dishID = dishID;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public DishCategoryEntity getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategoryEntity dishCategory) {
        this.dishCategory = dishCategory;
    }
}
