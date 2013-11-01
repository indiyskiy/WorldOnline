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
@javax.persistence.Table(name = "INNODB_SYS_COLUMNS", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysColumnsEntity {
    private long tableId;

    @javax.persistence.Column(name = "TABLE_ID")
    @Basic
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
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

    private long pos;

    @javax.persistence.Column(name = "POS")
    @Basic
    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }

    private int mtype;

    @javax.persistence.Column(name = "MTYPE")
    @Basic
    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    private int prtype;

    @javax.persistence.Column(name = "PRTYPE")
    @Basic
    public int getPrtype() {
        return prtype;
    }

    public void setPrtype(int prtype) {
        this.prtype = prtype;
    }

    private int len;

    @javax.persistence.Column(name = "LEN")
    @Basic
    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysColumnsEntity that = (InnodbSysColumnsEntity) o;

        if (len != that.len) return false;
        if (mtype != that.mtype) return false;
        if (pos != that.pos) return false;
        if (prtype != that.prtype) return false;
        if (tableId != that.tableId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tableId ^ (tableId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (pos ^ (pos >>> 32));
        result = 31 * result + mtype;
        result = 31 * result + prtype;
        result = 31 * result + len;
        return result;
    }
}
