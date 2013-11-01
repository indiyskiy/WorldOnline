package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "INNODB_TRX", schema = "", catalog = "information_schema")
@Entity
public class InnodbTrxEntity {
    private String trxId;

    @javax.persistence.Column(name = "trx_id")
    @Basic
    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    private String trxState;

    @javax.persistence.Column(name = "trx_state")
    @Basic
    public String getTrxState() {
        return trxState;
    }

    public void setTrxState(String trxState) {
        this.trxState = trxState;
    }

    private Timestamp trxStarted;

    @javax.persistence.Column(name = "trx_started")
    @Basic
    public Timestamp getTrxStarted() {
        return trxStarted;
    }

    public void setTrxStarted(Timestamp trxStarted) {
        this.trxStarted = trxStarted;
    }

    private String trxRequestedLockId;

    @javax.persistence.Column(name = "trx_requested_lock_id")
    @Basic
    public String getTrxRequestedLockId() {
        return trxRequestedLockId;
    }

    public void setTrxRequestedLockId(String trxRequestedLockId) {
        this.trxRequestedLockId = trxRequestedLockId;
    }

    private Timestamp trxWaitStarted;

    @javax.persistence.Column(name = "trx_wait_started")
    @Basic
    public Timestamp getTrxWaitStarted() {
        return trxWaitStarted;
    }

    public void setTrxWaitStarted(Timestamp trxWaitStarted) {
        this.trxWaitStarted = trxWaitStarted;
    }

    private long trxWeight;

    @javax.persistence.Column(name = "trx_weight")
    @Basic
    public long getTrxWeight() {
        return trxWeight;
    }

    public void setTrxWeight(long trxWeight) {
        this.trxWeight = trxWeight;
    }

    private long trxMysqlThreadId;

    @javax.persistence.Column(name = "trx_mysql_thread_id")
    @Basic
    public long getTrxMysqlThreadId() {
        return trxMysqlThreadId;
    }

    public void setTrxMysqlThreadId(long trxMysqlThreadId) {
        this.trxMysqlThreadId = trxMysqlThreadId;
    }

    private String trxQuery;

    @javax.persistence.Column(name = "trx_query")
    @Basic
    public String getTrxQuery() {
        return trxQuery;
    }

    public void setTrxQuery(String trxQuery) {
        this.trxQuery = trxQuery;
    }

    private String trxOperationState;

    @javax.persistence.Column(name = "trx_operation_state")
    @Basic
    public String getTrxOperationState() {
        return trxOperationState;
    }

    public void setTrxOperationState(String trxOperationState) {
        this.trxOperationState = trxOperationState;
    }

    private long trxTablesInUse;

    @javax.persistence.Column(name = "trx_tables_in_use")
    @Basic
    public long getTrxTablesInUse() {
        return trxTablesInUse;
    }

    public void setTrxTablesInUse(long trxTablesInUse) {
        this.trxTablesInUse = trxTablesInUse;
    }

    private long trxTablesLocked;

    @javax.persistence.Column(name = "trx_tables_locked")
    @Basic
    public long getTrxTablesLocked() {
        return trxTablesLocked;
    }

    public void setTrxTablesLocked(long trxTablesLocked) {
        this.trxTablesLocked = trxTablesLocked;
    }

    private long trxLockStructs;

    @javax.persistence.Column(name = "trx_lock_structs")
    @Basic
    public long getTrxLockStructs() {
        return trxLockStructs;
    }

    public void setTrxLockStructs(long trxLockStructs) {
        this.trxLockStructs = trxLockStructs;
    }

    private long trxLockMemoryBytes;

    @javax.persistence.Column(name = "trx_lock_memory_bytes")
    @Basic
    public long getTrxLockMemoryBytes() {
        return trxLockMemoryBytes;
    }

    public void setTrxLockMemoryBytes(long trxLockMemoryBytes) {
        this.trxLockMemoryBytes = trxLockMemoryBytes;
    }

    private long trxRowsLocked;

    @javax.persistence.Column(name = "trx_rows_locked")
    @Basic
    public long getTrxRowsLocked() {
        return trxRowsLocked;
    }

    public void setTrxRowsLocked(long trxRowsLocked) {
        this.trxRowsLocked = trxRowsLocked;
    }

    private long trxRowsModified;

    @javax.persistence.Column(name = "trx_rows_modified")
    @Basic
    public long getTrxRowsModified() {
        return trxRowsModified;
    }

    public void setTrxRowsModified(long trxRowsModified) {
        this.trxRowsModified = trxRowsModified;
    }

    private long trxConcurrencyTickets;

    @javax.persistence.Column(name = "trx_concurrency_tickets")
    @Basic
    public long getTrxConcurrencyTickets() {
        return trxConcurrencyTickets;
    }

    public void setTrxConcurrencyTickets(long trxConcurrencyTickets) {
        this.trxConcurrencyTickets = trxConcurrencyTickets;
    }

    private String trxIsolationLevel;

    @javax.persistence.Column(name = "trx_isolation_level")
    @Basic
    public String getTrxIsolationLevel() {
        return trxIsolationLevel;
    }

    public void setTrxIsolationLevel(String trxIsolationLevel) {
        this.trxIsolationLevel = trxIsolationLevel;
    }

    private int trxUniqueChecks;

    @javax.persistence.Column(name = "trx_unique_checks")
    @Basic
    public int getTrxUniqueChecks() {
        return trxUniqueChecks;
    }

    public void setTrxUniqueChecks(int trxUniqueChecks) {
        this.trxUniqueChecks = trxUniqueChecks;
    }

    private int trxForeignKeyChecks;

    @javax.persistence.Column(name = "trx_foreign_key_checks")
    @Basic
    public int getTrxForeignKeyChecks() {
        return trxForeignKeyChecks;
    }

    public void setTrxForeignKeyChecks(int trxForeignKeyChecks) {
        this.trxForeignKeyChecks = trxForeignKeyChecks;
    }

    private String trxLastForeignKeyError;

    @javax.persistence.Column(name = "trx_last_foreign_key_error")
    @Basic
    public String getTrxLastForeignKeyError() {
        return trxLastForeignKeyError;
    }

    public void setTrxLastForeignKeyError(String trxLastForeignKeyError) {
        this.trxLastForeignKeyError = trxLastForeignKeyError;
    }

    private int trxAdaptiveHashLatched;

    @javax.persistence.Column(name = "trx_adaptive_hash_latched")
    @Basic
    public int getTrxAdaptiveHashLatched() {
        return trxAdaptiveHashLatched;
    }

    public void setTrxAdaptiveHashLatched(int trxAdaptiveHashLatched) {
        this.trxAdaptiveHashLatched = trxAdaptiveHashLatched;
    }

    private long trxAdaptiveHashTimeout;

    @javax.persistence.Column(name = "trx_adaptive_hash_timeout")
    @Basic
    public long getTrxAdaptiveHashTimeout() {
        return trxAdaptiveHashTimeout;
    }

    public void setTrxAdaptiveHashTimeout(long trxAdaptiveHashTimeout) {
        this.trxAdaptiveHashTimeout = trxAdaptiveHashTimeout;
    }

    private int trxIsReadOnly;

    @javax.persistence.Column(name = "trx_is_read_only")
    @Basic
    public int getTrxIsReadOnly() {
        return trxIsReadOnly;
    }

    public void setTrxIsReadOnly(int trxIsReadOnly) {
        this.trxIsReadOnly = trxIsReadOnly;
    }

    private int trxAutocommitNonLocking;

    @javax.persistence.Column(name = "trx_autocommit_non_locking")
    @Basic
    public int getTrxAutocommitNonLocking() {
        return trxAutocommitNonLocking;
    }

    public void setTrxAutocommitNonLocking(int trxAutocommitNonLocking) {
        this.trxAutocommitNonLocking = trxAutocommitNonLocking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbTrxEntity that = (InnodbTrxEntity) o;

        if (trxAdaptiveHashLatched != that.trxAdaptiveHashLatched) return false;
        if (trxAdaptiveHashTimeout != that.trxAdaptiveHashTimeout) return false;
        if (trxAutocommitNonLocking != that.trxAutocommitNonLocking) return false;
        if (trxConcurrencyTickets != that.trxConcurrencyTickets) return false;
        if (trxForeignKeyChecks != that.trxForeignKeyChecks) return false;
        if (trxIsReadOnly != that.trxIsReadOnly) return false;
        if (trxLockMemoryBytes != that.trxLockMemoryBytes) return false;
        if (trxLockStructs != that.trxLockStructs) return false;
        if (trxMysqlThreadId != that.trxMysqlThreadId) return false;
        if (trxRowsLocked != that.trxRowsLocked) return false;
        if (trxRowsModified != that.trxRowsModified) return false;
        if (trxTablesInUse != that.trxTablesInUse) return false;
        if (trxTablesLocked != that.trxTablesLocked) return false;
        if (trxUniqueChecks != that.trxUniqueChecks) return false;
        if (trxWeight != that.trxWeight) return false;
        if (trxId != null ? !trxId.equals(that.trxId) : that.trxId != null) return false;
        if (trxIsolationLevel != null ? !trxIsolationLevel.equals(that.trxIsolationLevel) : that.trxIsolationLevel != null)
            return false;
        if (trxLastForeignKeyError != null ? !trxLastForeignKeyError.equals(that.trxLastForeignKeyError) : that.trxLastForeignKeyError != null)
            return false;
        if (trxOperationState != null ? !trxOperationState.equals(that.trxOperationState) : that.trxOperationState != null)
            return false;
        if (trxQuery != null ? !trxQuery.equals(that.trxQuery) : that.trxQuery != null) return false;
        if (trxRequestedLockId != null ? !trxRequestedLockId.equals(that.trxRequestedLockId) : that.trxRequestedLockId != null)
            return false;
        if (trxStarted != null ? !trxStarted.equals(that.trxStarted) : that.trxStarted != null) return false;
        if (trxState != null ? !trxState.equals(that.trxState) : that.trxState != null) return false;
        if (trxWaitStarted != null ? !trxWaitStarted.equals(that.trxWaitStarted) : that.trxWaitStarted != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trxId != null ? trxId.hashCode() : 0;
        result = 31 * result + (trxState != null ? trxState.hashCode() : 0);
        result = 31 * result + (trxStarted != null ? trxStarted.hashCode() : 0);
        result = 31 * result + (trxRequestedLockId != null ? trxRequestedLockId.hashCode() : 0);
        result = 31 * result + (trxWaitStarted != null ? trxWaitStarted.hashCode() : 0);
        result = 31 * result + (int) (trxWeight ^ (trxWeight >>> 32));
        result = 31 * result + (int) (trxMysqlThreadId ^ (trxMysqlThreadId >>> 32));
        result = 31 * result + (trxQuery != null ? trxQuery.hashCode() : 0);
        result = 31 * result + (trxOperationState != null ? trxOperationState.hashCode() : 0);
        result = 31 * result + (int) (trxTablesInUse ^ (trxTablesInUse >>> 32));
        result = 31 * result + (int) (trxTablesLocked ^ (trxTablesLocked >>> 32));
        result = 31 * result + (int) (trxLockStructs ^ (trxLockStructs >>> 32));
        result = 31 * result + (int) (trxLockMemoryBytes ^ (trxLockMemoryBytes >>> 32));
        result = 31 * result + (int) (trxRowsLocked ^ (trxRowsLocked >>> 32));
        result = 31 * result + (int) (trxRowsModified ^ (trxRowsModified >>> 32));
        result = 31 * result + (int) (trxConcurrencyTickets ^ (trxConcurrencyTickets >>> 32));
        result = 31 * result + (trxIsolationLevel != null ? trxIsolationLevel.hashCode() : 0);
        result = 31 * result + trxUniqueChecks;
        result = 31 * result + trxForeignKeyChecks;
        result = 31 * result + (trxLastForeignKeyError != null ? trxLastForeignKeyError.hashCode() : 0);
        result = 31 * result + trxAdaptiveHashLatched;
        result = 31 * result + (int) (trxAdaptiveHashTimeout ^ (trxAdaptiveHashTimeout >>> 32));
        result = 31 * result + trxIsReadOnly;
        result = 31 * result + trxAutocommitNonLocking;
        return result;
    }
}
