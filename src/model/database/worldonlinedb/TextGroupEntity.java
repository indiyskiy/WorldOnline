package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 30.10.13
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "TextGroup", schema = "", catalog = "worldonline")
@Entity
public class TextGroupEntity {
    @javax.persistence.Column(name = "TextGroupID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long textGroupID;

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        textGroupID = textGroupID;
    }

    @javax.persistence.Column(name = "CardGroupName")
    @Basic
    private String cardGroupName;

    public String getCardGroupName() {
        return cardGroupName;
    }

    public void setCardGroupName(String cardGroupName) {
        this.cardGroupName = cardGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextGroupEntity that = (TextGroupEntity) o;

        if (textGroupID != null ? !textGroupID.equals(that.textGroupID) : that.textGroupID != null)
            return false;
        if (cardGroupName != null ? !cardGroupName.equals(that.cardGroupName) : that.cardGroupName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = textGroupID != null ? textGroupID.hashCode() : 0;
        result = 31 * result + (cardGroupName != null ? cardGroupName.hashCode() : 0);
        return result;
    }
}
