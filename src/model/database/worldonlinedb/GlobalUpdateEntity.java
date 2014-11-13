package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "GlobalUpdate", schema = "", catalog = "worldonline")
@Entity
public class GlobalUpdateEntity {
    @javax.persistence.Column(name = "GlobalUpdateID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long globalUpdateID;

    @javax.persistence.Column(name = "TagsUpdateTime")
    @Basic
    private Timestamp tagsUpdateTime;

    @javax.persistence.Column(name = "MenusUpdateTime")
    @Basic
    private Timestamp menusUpdateTime;

    @javax.persistence.Column(name = "ParameterTypeUpdateTime")
    @Basic
    private Timestamp parameterTypeUpdateTime;

    @javax.persistence.Column(name = "DishTagsUpdateTime")
    @Basic
    private Timestamp dishTagsUpdateTime;

    @javax.persistence.Column(name = "DishCategoriesUpdateTime")
    @Basic
    private Timestamp dishCategoriesUpdateTime;

    public Long getGlobalUpdateID() {
        return globalUpdateID;
    }

    public void setGlobalUpdateID(Long globalUpdateID) {
        this.globalUpdateID = globalUpdateID;
    }

    public Timestamp getTagsUpdateTime() {
        return tagsUpdateTime;
    }

    public void setTagsUpdateTime(Timestamp tagsUpdateTime) {
        this.tagsUpdateTime = tagsUpdateTime;
    }

    public Timestamp getMenusUpdateTime() {
        return menusUpdateTime;
    }

    public void setMenusUpdateTime(Timestamp menusUpdateTime) {
        this.menusUpdateTime = menusUpdateTime;
    }

    public Timestamp getParameterTypeUpdateTime() {
        return parameterTypeUpdateTime;
    }

    public void setParameterTypeUpdateTime(Timestamp parameterTypeUpdateTime) {
        this.parameterTypeUpdateTime = parameterTypeUpdateTime;
    }

    public Timestamp getDishTagsUpdateTime() {
        return dishTagsUpdateTime;
    }

    public void setDishTagsUpdateTime(Timestamp dishTagsUpdateTime) {
        this.dishTagsUpdateTime = dishTagsUpdateTime;
    }

    public Timestamp getDishCategoriesUpdateTime() {
        return dishCategoriesUpdateTime;
    }

    public void setDishCategoriesUpdateTime(Timestamp dishCategoriesUpdateTime) {
        this.dishCategoriesUpdateTime = dishCategoriesUpdateTime;
    }
}
