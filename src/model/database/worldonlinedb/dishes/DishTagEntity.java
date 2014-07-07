package model.database.worldonlinedb.dishes;

import model.database.worldonlinedb.TextGroupEntity;

import javax.persistence.*;

@javax.persistence.Table(name = "DishTag", schema = "", catalog = "worldonline")
@Entity
public class DishTagEntity {
    @javax.persistence.Column(name = "DishTagID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishTagID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "NameID")
    private TextGroupEntity name;

    public Long getDishTagID() {
        return dishTagID;
    }

    public void setDishTagID(Long dishTagID) {
        this.dishTagID = dishTagID;
    }

    public TextGroupEntity getName() {
        return name;
    }

    public void setName(TextGroupEntity name) {
        this.name = name;
    }
}
