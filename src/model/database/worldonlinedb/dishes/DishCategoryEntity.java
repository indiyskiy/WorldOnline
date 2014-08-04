package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.TextGroupEntity;

import javax.persistence.*;

@javax.persistence.Table(name = "DishCategory", schema = "", catalog = "worldonline")
@Entity
public class DishCategoryEntity {
    @javax.persistence.Column(name = "DishCategoryID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishCategoryID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "NameID")
    private TextGroupEntity name;

    @javax.persistence.Column(name = "Position")
    @Basic
    private Integer position;

    public Long getDishCategoryID() {
        return dishCategoryID;
    }

    public void setDishCategoryID(Long dishCategoryID) {
        this.dishCategoryID = dishCategoryID;
    }

    public TextGroupEntity getName() {
        return name;
    }

    public void setName(TextGroupEntity name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
