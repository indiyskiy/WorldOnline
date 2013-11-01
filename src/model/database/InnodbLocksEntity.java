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
@javax.persistence.Table(name = "INNODB_LOCKS", schema = "", catalog = "information_schema")
@Entity
public class InnodbLocksEntity {
    private String lockId;

    @javax.persistence.Column(name = "lock_id")
    @Basic
    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    private String lockTrxId;

    @javax.persistence.Column(name = "lock_trx_id")
    @Basic
    public String getLockTrxId() {
        return lockTrxId;
    }

    public void setLockTrxId(String lockTrxId) {
        this.lockTrxId = lockTrxId;
    }

    private String lockMode;

    @javax.persistence.Column(name = "lock_mode")
    @Basic
    public String getLockMode() {
        return lockMode;
    }

    public void setLockMode(String lockMode) {
        this.lockMode = lockMode;
    }

    private String lockType;

    @javax.persistence.Column(name = "lock_type")
    @Basic
    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    private String lockTable;

    @javax.persistence.Column(name = "lock_table")
    @Basic
    public String getLockTable() {
        return lockTable;
    }

    public void setLockTable(String lockTable) {
        this.lockTable = lockTable;
    }

    private String lockIndex;

    @javax.persistence.Column(name = "lock_index")
    @Basic
    public String getLockIndex() {
        return lockIndex;
    }

    public void setLockIndex(String lockIndex) {
        this.lockIndex = lockIndex;
    }

    private long lockSpace;

    @javax.persistence.Column(name = "lock_space")
    @Basic
    public long getLockSpace() {
        return lockSpace;
    }

    public void setLockSpace(long lockSpace) {
        this.lockSpace = lockSpace;
    }

    private long lockPage;

    @javax.persistence.Column(name = "lock_page")
    @Basic
    public long getLockPage() {
        return lockPage;
    }

    public void setLockPage(long lockPage) {
        this.lockPage = lockPage;
    }

    private long lockRec;

    @javax.persistence.Column(name = "lock_rec")
    @Basic
    public long getLockRec() {
        return lockRec;
    }

    public void setLockRec(long lockRec) {
        this.lockRec = lockRec;
    }

    private String lockData;

    @javax.persistence.Column(name = "lock_data")
    @Basic
    public String getLockData() {
        return lockData;
    }

    public void setLockData(String lockData) {
        this.lockData = lockData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbLocksEntity that = (InnodbLocksEntity) o;

        if (lockPage != that.lockPage) return false;
        if (lockRec != that.lockRec) return false;
        if (lockSpace != that.lockSpace) return false;
        if (lockData != null ? !lockData.equals(that.lockData) : that.lockData != null) return false;
        if (lockId != null ? !lockId.equals(that.lockId) : that.lockId != null) return false;
        if (lockIndex != null ? !lockIndex.equals(that.lockIndex) : that.lockIndex != null) return false;
        if (lockMode != null ? !lockMode.equals(that.lockMode) : that.lockMode != null) return false;
        if (lockTable != null ? !lockTable.equals(that.lockTable) : that.lockTable != null) return false;
        if (lockTrxId != null ? !lockTrxId.equals(that.lockTrxId) : that.lockTrxId != null) return false;
        if (lockType != null ? !lockType.equals(that.lockType) : that.lockType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lockId != null ? lockId.hashCode() : 0;
        result = 31 * result + (lockTrxId != null ? lockTrxId.hashCode() : 0);
        result = 31 * result + (lockMode != null ? lockMode.hashCode() : 0);
        result = 31 * result + (lockType != null ? lockType.hashCode() : 0);
        result = 31 * result + (lockTable != null ? lockTable.hashCode() : 0);
        result = 31 * result + (lockIndex != null ? lockIndex.hashCode() : 0);
        result = 31 * result + (int) (lockSpace ^ (lockSpace >>> 32));
        result = 31 * result + (int) (lockPage ^ (lockPage >>> 32));
        result = 31 * result + (int) (lockRec ^ (lockRec >>> 32));
        result = 31 * result + (lockData != null ? lockData.hashCode() : 0);
        return result;
    }
}
