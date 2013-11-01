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
@javax.persistence.Table(name = "INNODB_LOCK_WAITS", schema = "", catalog = "information_schema")
@Entity
public class InnodbLockWaitsEntity {
    private String requestingTrxId;

    @javax.persistence.Column(name = "requesting_trx_id")
    @Basic
    public String getRequestingTrxId() {
        return requestingTrxId;
    }

    public void setRequestingTrxId(String requestingTrxId) {
        this.requestingTrxId = requestingTrxId;
    }

    private String requestedLockId;

    @javax.persistence.Column(name = "requested_lock_id")
    @Basic
    public String getRequestedLockId() {
        return requestedLockId;
    }

    public void setRequestedLockId(String requestedLockId) {
        this.requestedLockId = requestedLockId;
    }

    private String blockingTrxId;

    @javax.persistence.Column(name = "blocking_trx_id")
    @Basic
    public String getBlockingTrxId() {
        return blockingTrxId;
    }

    public void setBlockingTrxId(String blockingTrxId) {
        this.blockingTrxId = blockingTrxId;
    }

    private String blockingLockId;

    @javax.persistence.Column(name = "blocking_lock_id")
    @Basic
    public String getBlockingLockId() {
        return blockingLockId;
    }

    public void setBlockingLockId(String blockingLockId) {
        this.blockingLockId = blockingLockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbLockWaitsEntity that = (InnodbLockWaitsEntity) o;

        if (blockingLockId != null ? !blockingLockId.equals(that.blockingLockId) : that.blockingLockId != null)
            return false;
        if (blockingTrxId != null ? !blockingTrxId.equals(that.blockingTrxId) : that.blockingTrxId != null)
            return false;
        if (requestedLockId != null ? !requestedLockId.equals(that.requestedLockId) : that.requestedLockId != null)
            return false;
        if (requestingTrxId != null ? !requestingTrxId.equals(that.requestingTrxId) : that.requestingTrxId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = requestingTrxId != null ? requestingTrxId.hashCode() : 0;
        result = 31 * result + (requestedLockId != null ? requestedLockId.hashCode() : 0);
        result = 31 * result + (blockingTrxId != null ? blockingTrxId.hashCode() : 0);
        result = 31 * result + (blockingLockId != null ? blockingLockId.hashCode() : 0);
        return result;
    }
}
