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
@javax.persistence.Table(name = "INNODB_SYS_INDEXES", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysIndexesEntity {
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

    private long tableId;

    @javax.persistence.Column(name = "TABLE_ID")
    @Basic
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    private int type;

    @javax.persistence.Column(name = "TYPE")
    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int nFields;

    @javax.persistence.Column(name = "N_FIELDS")
    @Basic
    public int getnFields() {
        return nFields;
    }

    public void setnFields(int nFields) {
        this.nFields = nFields;
    }

    private int pageNo;

    @javax.persistence.Column(name = "PAGE_NO")
    @Basic
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    private int space;

    @javax.persistence.Column(name = "SPACE")
    @Basic
    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysIndexesEntity that = (InnodbSysIndexesEntity) o;

        if (indexId != that.indexId) return false;
        if (nFields != that.nFields) return false;
        if (pageNo != that.pageNo) return false;
        if (space != that.space) return false;
        if (tableId != that.tableId) return false;
        if (type != that.type) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (indexId ^ (indexId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (tableId ^ (tableId >>> 32));
        result = 31 * result + type;
        result = 31 * result + nFields;
        result = 31 * result + pageNo;
        result = 31 * result + space;
        return result;
    }
}
