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
@javax.persistence.Table(name = "INNODB_SYS_FIELDS", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysFieldsEntity {
    private long indexId;

    @javax.persistence.Column(name = "INDEX_ID")
    @Basic
    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        InnodbSysFieldsEntity that = (InnodbSysFieldsEntity) o;

        if (indexId != that.indexId) return false;
        if (pos != that.pos) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (indexId ^ (indexId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + pos;
        return result;
    }
}
