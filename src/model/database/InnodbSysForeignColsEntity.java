package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "INNODB_SYS_FOREIGN_COLS", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysForeignColsEntity {
    private String id;

    @javax.persistence.Column(name = "ID")
    @Basic
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String forColName;

    @javax.persistence.Column(name = "FOR_COL_NAME")
    @Basic
    public String getForColName() {
        return forColName;
    }

    public void setForColName(String forColName) {
        this.forColName = forColName;
    }

    private String refColName;

    @javax.persistence.Column(name = "REF_COL_NAME")
    @Basic
    public String getRefColName() {
        return refColName;
    }

    public void setRefColName(String refColName) {
        this.refColName = refColName;
    }

    private int pos;

    @javax.persistence.Column(name = "POS")
    @Basic
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysForeignColsEntity that = (InnodbSysForeignColsEntity) o;

        if (pos != that.pos) return false;
        if (forColName != null ? !forColName.equals(that.forColName) : that.forColName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (refColName != null ? !refColName.equals(that.refColName) : that.refColName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (forColName != null ? forColName.hashCode() : 0);
        result = 31 * result + (refColName != null ? refColName.hashCode() : 0);
        result = 31 * result + pos;
        return result;
    }
}
