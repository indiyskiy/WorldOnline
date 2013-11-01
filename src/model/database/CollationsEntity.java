package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "COLLATIONS", schema = "", catalog = "information_schema")
@Entity
public class CollationsEntity {
    private String collationName;

    @javax.persistence.Column(name = "COLLATION_NAME")
    @Basic
    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    private String characterSetName;

    @javax.persistence.Column(name = "CHARACTER_SET_NAME")
    @Basic
    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    private long id;

    @javax.persistence.Column(name = "ID")
    @Basic
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String isDefault;

    @javax.persistence.Column(name = "IS_DEFAULT")
    @Basic
    public String getDefault() {
        return isDefault;
    }

    public void setDefault(String aDefault) {
        isDefault = aDefault;
    }

    private String isCompiled;

    @javax.persistence.Column(name = "IS_COMPILED")
    @Basic
    public String getCompiled() {
        return isCompiled;
    }

    public void setCompiled(String compiled) {
        isCompiled = compiled;
    }

    private long sortlen;

    @javax.persistence.Column(name = "SORTLEN")
    @Basic
    public long getSortlen() {
        return sortlen;
    }

    public void setSortlen(long sortlen) {
        this.sortlen = sortlen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollationsEntity that = (CollationsEntity) o;

        if (id != that.id) return false;
        if (sortlen != that.sortlen) return false;
        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (collationName != null ? !collationName.equals(that.collationName) : that.collationName != null)
            return false;
        if (isCompiled != null ? !isCompiled.equals(that.isCompiled) : that.isCompiled != null) return false;
        if (isDefault != null ? !isDefault.equals(that.isDefault) : that.isDefault != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collationName != null ? collationName.hashCode() : 0;
        result = 31 * result + (characterSetName != null ? characterSetName.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (isDefault != null ? isDefault.hashCode() : 0);
        result = 31 * result + (isCompiled != null ? isCompiled.hashCode() : 0);
        result = 31 * result + (int) (sortlen ^ (sortlen >>> 32));
        return result;
    }
}
