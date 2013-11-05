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

    @javax.persistence.Column(name = "TextGroupName")
    @Basic
    private String textGroupName;

    public String getTextGroupName() {
        return textGroupName;
    }

    public void setTextGroupName(String textGroupName) {
        this.textGroupName = textGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextGroupEntity that = (TextGroupEntity) o;

        if (textGroupID != null ? !textGroupID.equals(that.textGroupID) : that.textGroupID != null)
            return false;
        if (textGroupName != null ? !textGroupName.equals(that.textGroupName) : that.textGroupName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = textGroupID != null ? textGroupID.hashCode() : 0;
        result = 31 * result + (textGroupName != null ? textGroupName.hashCode() : 0);
        return result;
    }

    public TextGroupEntity() {

    }

    public TextGroupEntity(String textGroupName) {
        setTextGroupName(textGroupName);
    }
}
