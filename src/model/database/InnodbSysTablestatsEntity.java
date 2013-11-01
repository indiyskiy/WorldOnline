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
@javax.persistence.Table(name = "INNODB_SYS_TABLESTATS", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysTablestatsEntity {
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

    private String statsInitialized;

    @javax.persistence.Column(name = "STATS_INITIALIZED")
    @Basic
    public String getStatsInitialized() {
        return statsInitialized;
    }

    public void setStatsInitialized(String statsInitialized) {
        this.statsInitialized = statsInitialized;
    }

    private long numRows;

    @javax.persistence.Column(name = "NUM_ROWS")
    @Basic
    public long getNumRows() {
        return numRows;
    }

    public void setNumRows(long numRows) {
        this.numRows = numRows;
    }

    private long clustIndexSize;

    @javax.persistence.Column(name = "CLUST_INDEX_SIZE")
    @Basic
    public long getClustIndexSize() {
        return clustIndexSize;
    }

    public void setClustIndexSize(long clustIndexSize) {
        this.clustIndexSize = clustIndexSize;
    }

    private long otherIndexSize;

    @javax.persistence.Column(name = "OTHER_INDEX_SIZE")
    @Basic
    public long getOtherIndexSize() {
        return otherIndexSize;
    }

    public void setOtherIndexSize(long otherIndexSize) {
        this.otherIndexSize = otherIndexSize;
    }

    private long modifiedCounter;

    @javax.persistence.Column(name = "MODIFIED_COUNTER")
    @Basic
    public long getModifiedCounter() {
        return modifiedCounter;
    }

    public void setModifiedCounter(long modifiedCounter) {
        this.modifiedCounter = modifiedCounter;
    }

    private long autoinc;

    @javax.persistence.Column(name = "AUTOINC")
    @Basic
    public long getAutoinc() {
        return autoinc;
    }

    public void setAutoinc(long autoinc) {
        this.autoinc = autoinc;
    }

    private int refCount;

    @javax.persistence.Column(name = "REF_COUNT")
    @Basic
    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysTablestatsEntity that = (InnodbSysTablestatsEntity) o;

        if (autoinc != that.autoinc) return false;
        if (clustIndexSize != that.clustIndexSize) return false;
        if (modifiedCounter != that.modifiedCounter) return false;
        if (numRows != that.numRows) return false;
        if (otherIndexSize != that.otherIndexSize) return false;
        if (refCount != that.refCount) return false;
        if (tableId != that.tableId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (statsInitialized != null ? !statsInitialized.equals(that.statsInitialized) : that.statsInitialized != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tableId ^ (tableId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (statsInitialized != null ? statsInitialized.hashCode() : 0);
        result = 31 * result + (int) (numRows ^ (numRows >>> 32));
        result = 31 * result + (int) (clustIndexSize ^ (clustIndexSize >>> 32));
        result = 31 * result + (int) (otherIndexSize ^ (otherIndexSize >>> 32));
        result = 31 * result + (int) (modifiedCounter ^ (modifiedCounter >>> 32));
        result = 31 * result + (int) (autoinc ^ (autoinc >>> 32));
        result = 31 * result + refCount;
        return result;
    }
}
