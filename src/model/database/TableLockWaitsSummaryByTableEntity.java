package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:10
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "table_lock_waits_summary_by_table", schema = "", catalog = "performance_schema")
@Entity
public class TableLockWaitsSummaryByTableEntity {
    private String objectType;

    @javax.persistence.Column(name = "OBJECT_TYPE")
    @Basic
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    private String objectSchema;

    @javax.persistence.Column(name = "OBJECT_SCHEMA")
    @Basic
    public String getObjectSchema() {
        return objectSchema;
    }

    public void setObjectSchema(String objectSchema) {
        this.objectSchema = objectSchema;
    }

    private String objectName;

    @javax.persistence.Column(name = "OBJECT_NAME")
    @Basic
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    private long countStar;

    @javax.persistence.Column(name = "COUNT_STAR")
    @Basic
    public long getCountStar() {
        return countStar;
    }

    public void setCountStar(long countStar) {
        this.countStar = countStar;
    }

    private long sumTimerWait;

    @javax.persistence.Column(name = "SUM_TIMER_WAIT")
    @Basic
    public long getSumTimerWait() {
        return sumTimerWait;
    }

    public void setSumTimerWait(long sumTimerWait) {
        this.sumTimerWait = sumTimerWait;
    }

    private long minTimerWait;

    @javax.persistence.Column(name = "MIN_TIMER_WAIT")
    @Basic
    public long getMinTimerWait() {
        return minTimerWait;
    }

    public void setMinTimerWait(long minTimerWait) {
        this.minTimerWait = minTimerWait;
    }

    private long avgTimerWait;

    @javax.persistence.Column(name = "AVG_TIMER_WAIT")
    @Basic
    public long getAvgTimerWait() {
        return avgTimerWait;
    }

    public void setAvgTimerWait(long avgTimerWait) {
        this.avgTimerWait = avgTimerWait;
    }

    private long maxTimerWait;

    @javax.persistence.Column(name = "MAX_TIMER_WAIT")
    @Basic
    public long getMaxTimerWait() {
        return maxTimerWait;
    }

    public void setMaxTimerWait(long maxTimerWait) {
        this.maxTimerWait = maxTimerWait;
    }

    private long countRead;

    @javax.persistence.Column(name = "COUNT_READ")
    @Basic
    public long getCountRead() {
        return countRead;
    }

    public void setCountRead(long countRead) {
        this.countRead = countRead;
    }

    private long sumTimerRead;

    @javax.persistence.Column(name = "SUM_TIMER_READ")
    @Basic
    public long getSumTimerRead() {
        return sumTimerRead;
    }

    public void setSumTimerRead(long sumTimerRead) {
        this.sumTimerRead = sumTimerRead;
    }

    private long minTimerRead;

    @javax.persistence.Column(name = "MIN_TIMER_READ")
    @Basic
    public long getMinTimerRead() {
        return minTimerRead;
    }

    public void setMinTimerRead(long minTimerRead) {
        this.minTimerRead = minTimerRead;
    }

    private long avgTimerRead;

    @javax.persistence.Column(name = "AVG_TIMER_READ")
    @Basic
    public long getAvgTimerRead() {
        return avgTimerRead;
    }

    public void setAvgTimerRead(long avgTimerRead) {
        this.avgTimerRead = avgTimerRead;
    }

    private long maxTimerRead;

    @javax.persistence.Column(name = "MAX_TIMER_READ")
    @Basic
    public long getMaxTimerRead() {
        return maxTimerRead;
    }

    public void setMaxTimerRead(long maxTimerRead) {
        this.maxTimerRead = maxTimerRead;
    }

    private long countWrite;

    @javax.persistence.Column(name = "COUNT_WRITE")
    @Basic
    public long getCountWrite() {
        return countWrite;
    }

    public void setCountWrite(long countWrite) {
        this.countWrite = countWrite;
    }

    private long sumTimerWrite;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE")
    @Basic
    public long getSumTimerWrite() {
        return sumTimerWrite;
    }

    public void setSumTimerWrite(long sumTimerWrite) {
        this.sumTimerWrite = sumTimerWrite;
    }

    private long minTimerWrite;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE")
    @Basic
    public long getMinTimerWrite() {
        return minTimerWrite;
    }

    public void setMinTimerWrite(long minTimerWrite) {
        this.minTimerWrite = minTimerWrite;
    }

    private long avgTimerWrite;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE")
    @Basic
    public long getAvgTimerWrite() {
        return avgTimerWrite;
    }

    public void setAvgTimerWrite(long avgTimerWrite) {
        this.avgTimerWrite = avgTimerWrite;
    }

    private long maxTimerWrite;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE")
    @Basic
    public long getMaxTimerWrite() {
        return maxTimerWrite;
    }

    public void setMaxTimerWrite(long maxTimerWrite) {
        this.maxTimerWrite = maxTimerWrite;
    }

    private long countReadNormal;

    @javax.persistence.Column(name = "COUNT_READ_NORMAL")
    @Basic
    public long getCountReadNormal() {
        return countReadNormal;
    }

    public void setCountReadNormal(long countReadNormal) {
        this.countReadNormal = countReadNormal;
    }

    private long sumTimerReadNormal;

    @javax.persistence.Column(name = "SUM_TIMER_READ_NORMAL")
    @Basic
    public long getSumTimerReadNormal() {
        return sumTimerReadNormal;
    }

    public void setSumTimerReadNormal(long sumTimerReadNormal) {
        this.sumTimerReadNormal = sumTimerReadNormal;
    }

    private long minTimerReadNormal;

    @javax.persistence.Column(name = "MIN_TIMER_READ_NORMAL")
    @Basic
    public long getMinTimerReadNormal() {
        return minTimerReadNormal;
    }

    public void setMinTimerReadNormal(long minTimerReadNormal) {
        this.minTimerReadNormal = minTimerReadNormal;
    }

    private long avgTimerReadNormal;

    @javax.persistence.Column(name = "AVG_TIMER_READ_NORMAL")
    @Basic
    public long getAvgTimerReadNormal() {
        return avgTimerReadNormal;
    }

    public void setAvgTimerReadNormal(long avgTimerReadNormal) {
        this.avgTimerReadNormal = avgTimerReadNormal;
    }

    private long maxTimerReadNormal;

    @javax.persistence.Column(name = "MAX_TIMER_READ_NORMAL")
    @Basic
    public long getMaxTimerReadNormal() {
        return maxTimerReadNormal;
    }

    public void setMaxTimerReadNormal(long maxTimerReadNormal) {
        this.maxTimerReadNormal = maxTimerReadNormal;
    }

    private long countReadWithSharedLocks;

    @javax.persistence.Column(name = "COUNT_READ_WITH_SHARED_LOCKS")
    @Basic
    public long getCountReadWithSharedLocks() {
        return countReadWithSharedLocks;
    }

    public void setCountReadWithSharedLocks(long countReadWithSharedLocks) {
        this.countReadWithSharedLocks = countReadWithSharedLocks;
    }

    private long sumTimerReadWithSharedLocks;

    @javax.persistence.Column(name = "SUM_TIMER_READ_WITH_SHARED_LOCKS")
    @Basic
    public long getSumTimerReadWithSharedLocks() {
        return sumTimerReadWithSharedLocks;
    }

    public void setSumTimerReadWithSharedLocks(long sumTimerReadWithSharedLocks) {
        this.sumTimerReadWithSharedLocks = sumTimerReadWithSharedLocks;
    }

    private long minTimerReadWithSharedLocks;

    @javax.persistence.Column(name = "MIN_TIMER_READ_WITH_SHARED_LOCKS")
    @Basic
    public long getMinTimerReadWithSharedLocks() {
        return minTimerReadWithSharedLocks;
    }

    public void setMinTimerReadWithSharedLocks(long minTimerReadWithSharedLocks) {
        this.minTimerReadWithSharedLocks = minTimerReadWithSharedLocks;
    }

    private long avgTimerReadWithSharedLocks;

    @javax.persistence.Column(name = "AVG_TIMER_READ_WITH_SHARED_LOCKS")
    @Basic
    public long getAvgTimerReadWithSharedLocks() {
        return avgTimerReadWithSharedLocks;
    }

    public void setAvgTimerReadWithSharedLocks(long avgTimerReadWithSharedLocks) {
        this.avgTimerReadWithSharedLocks = avgTimerReadWithSharedLocks;
    }

    private long maxTimerReadWithSharedLocks;

    @javax.persistence.Column(name = "MAX_TIMER_READ_WITH_SHARED_LOCKS")
    @Basic
    public long getMaxTimerReadWithSharedLocks() {
        return maxTimerReadWithSharedLocks;
    }

    public void setMaxTimerReadWithSharedLocks(long maxTimerReadWithSharedLocks) {
        this.maxTimerReadWithSharedLocks = maxTimerReadWithSharedLocks;
    }

    private long countReadHighPriority;

    @javax.persistence.Column(name = "COUNT_READ_HIGH_PRIORITY")
    @Basic
    public long getCountReadHighPriority() {
        return countReadHighPriority;
    }

    public void setCountReadHighPriority(long countReadHighPriority) {
        this.countReadHighPriority = countReadHighPriority;
    }

    private long sumTimerReadHighPriority;

    @javax.persistence.Column(name = "SUM_TIMER_READ_HIGH_PRIORITY")
    @Basic
    public long getSumTimerReadHighPriority() {
        return sumTimerReadHighPriority;
    }

    public void setSumTimerReadHighPriority(long sumTimerReadHighPriority) {
        this.sumTimerReadHighPriority = sumTimerReadHighPriority;
    }

    private long minTimerReadHighPriority;

    @javax.persistence.Column(name = "MIN_TIMER_READ_HIGH_PRIORITY")
    @Basic
    public long getMinTimerReadHighPriority() {
        return minTimerReadHighPriority;
    }

    public void setMinTimerReadHighPriority(long minTimerReadHighPriority) {
        this.minTimerReadHighPriority = minTimerReadHighPriority;
    }

    private long avgTimerReadHighPriority;

    @javax.persistence.Column(name = "AVG_TIMER_READ_HIGH_PRIORITY")
    @Basic
    public long getAvgTimerReadHighPriority() {
        return avgTimerReadHighPriority;
    }

    public void setAvgTimerReadHighPriority(long avgTimerReadHighPriority) {
        this.avgTimerReadHighPriority = avgTimerReadHighPriority;
    }

    private long maxTimerReadHighPriority;

    @javax.persistence.Column(name = "MAX_TIMER_READ_HIGH_PRIORITY")
    @Basic
    public long getMaxTimerReadHighPriority() {
        return maxTimerReadHighPriority;
    }

    public void setMaxTimerReadHighPriority(long maxTimerReadHighPriority) {
        this.maxTimerReadHighPriority = maxTimerReadHighPriority;
    }

    private long countReadNoInsert;

    @javax.persistence.Column(name = "COUNT_READ_NO_INSERT")
    @Basic
    public long getCountReadNoInsert() {
        return countReadNoInsert;
    }

    public void setCountReadNoInsert(long countReadNoInsert) {
        this.countReadNoInsert = countReadNoInsert;
    }

    private long sumTimerReadNoInsert;

    @javax.persistence.Column(name = "SUM_TIMER_READ_NO_INSERT")
    @Basic
    public long getSumTimerReadNoInsert() {
        return sumTimerReadNoInsert;
    }

    public void setSumTimerReadNoInsert(long sumTimerReadNoInsert) {
        this.sumTimerReadNoInsert = sumTimerReadNoInsert;
    }

    private long minTimerReadNoInsert;

    @javax.persistence.Column(name = "MIN_TIMER_READ_NO_INSERT")
    @Basic
    public long getMinTimerReadNoInsert() {
        return minTimerReadNoInsert;
    }

    public void setMinTimerReadNoInsert(long minTimerReadNoInsert) {
        this.minTimerReadNoInsert = minTimerReadNoInsert;
    }

    private long avgTimerReadNoInsert;

    @javax.persistence.Column(name = "AVG_TIMER_READ_NO_INSERT")
    @Basic
    public long getAvgTimerReadNoInsert() {
        return avgTimerReadNoInsert;
    }

    public void setAvgTimerReadNoInsert(long avgTimerReadNoInsert) {
        this.avgTimerReadNoInsert = avgTimerReadNoInsert;
    }

    private long maxTimerReadNoInsert;

    @javax.persistence.Column(name = "MAX_TIMER_READ_NO_INSERT")
    @Basic
    public long getMaxTimerReadNoInsert() {
        return maxTimerReadNoInsert;
    }

    public void setMaxTimerReadNoInsert(long maxTimerReadNoInsert) {
        this.maxTimerReadNoInsert = maxTimerReadNoInsert;
    }

    private long countReadExternal;

    @javax.persistence.Column(name = "COUNT_READ_EXTERNAL")
    @Basic
    public long getCountReadExternal() {
        return countReadExternal;
    }

    public void setCountReadExternal(long countReadExternal) {
        this.countReadExternal = countReadExternal;
    }

    private long sumTimerReadExternal;

    @javax.persistence.Column(name = "SUM_TIMER_READ_EXTERNAL")
    @Basic
    public long getSumTimerReadExternal() {
        return sumTimerReadExternal;
    }

    public void setSumTimerReadExternal(long sumTimerReadExternal) {
        this.sumTimerReadExternal = sumTimerReadExternal;
    }

    private long minTimerReadExternal;

    @javax.persistence.Column(name = "MIN_TIMER_READ_EXTERNAL")
    @Basic
    public long getMinTimerReadExternal() {
        return minTimerReadExternal;
    }

    public void setMinTimerReadExternal(long minTimerReadExternal) {
        this.minTimerReadExternal = minTimerReadExternal;
    }

    private long avgTimerReadExternal;

    @javax.persistence.Column(name = "AVG_TIMER_READ_EXTERNAL")
    @Basic
    public long getAvgTimerReadExternal() {
        return avgTimerReadExternal;
    }

    public void setAvgTimerReadExternal(long avgTimerReadExternal) {
        this.avgTimerReadExternal = avgTimerReadExternal;
    }

    private long maxTimerReadExternal;

    @javax.persistence.Column(name = "MAX_TIMER_READ_EXTERNAL")
    @Basic
    public long getMaxTimerReadExternal() {
        return maxTimerReadExternal;
    }

    public void setMaxTimerReadExternal(long maxTimerReadExternal) {
        this.maxTimerReadExternal = maxTimerReadExternal;
    }

    private long countWriteAllowWrite;

    @javax.persistence.Column(name = "COUNT_WRITE_ALLOW_WRITE")
    @Basic
    public long getCountWriteAllowWrite() {
        return countWriteAllowWrite;
    }

    public void setCountWriteAllowWrite(long countWriteAllowWrite) {
        this.countWriteAllowWrite = countWriteAllowWrite;
    }

    private long sumTimerWriteAllowWrite;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_ALLOW_WRITE")
    @Basic
    public long getSumTimerWriteAllowWrite() {
        return sumTimerWriteAllowWrite;
    }

    public void setSumTimerWriteAllowWrite(long sumTimerWriteAllowWrite) {
        this.sumTimerWriteAllowWrite = sumTimerWriteAllowWrite;
    }

    private long minTimerWriteAllowWrite;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_ALLOW_WRITE")
    @Basic
    public long getMinTimerWriteAllowWrite() {
        return minTimerWriteAllowWrite;
    }

    public void setMinTimerWriteAllowWrite(long minTimerWriteAllowWrite) {
        this.minTimerWriteAllowWrite = minTimerWriteAllowWrite;
    }

    private long avgTimerWriteAllowWrite;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_ALLOW_WRITE")
    @Basic
    public long getAvgTimerWriteAllowWrite() {
        return avgTimerWriteAllowWrite;
    }

    public void setAvgTimerWriteAllowWrite(long avgTimerWriteAllowWrite) {
        this.avgTimerWriteAllowWrite = avgTimerWriteAllowWrite;
    }

    private long maxTimerWriteAllowWrite;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_ALLOW_WRITE")
    @Basic
    public long getMaxTimerWriteAllowWrite() {
        return maxTimerWriteAllowWrite;
    }

    public void setMaxTimerWriteAllowWrite(long maxTimerWriteAllowWrite) {
        this.maxTimerWriteAllowWrite = maxTimerWriteAllowWrite;
    }

    private long countWriteConcurrentInsert;

    @javax.persistence.Column(name = "COUNT_WRITE_CONCURRENT_INSERT")
    @Basic
    public long getCountWriteConcurrentInsert() {
        return countWriteConcurrentInsert;
    }

    public void setCountWriteConcurrentInsert(long countWriteConcurrentInsert) {
        this.countWriteConcurrentInsert = countWriteConcurrentInsert;
    }

    private long sumTimerWriteConcurrentInsert;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_CONCURRENT_INSERT")
    @Basic
    public long getSumTimerWriteConcurrentInsert() {
        return sumTimerWriteConcurrentInsert;
    }

    public void setSumTimerWriteConcurrentInsert(long sumTimerWriteConcurrentInsert) {
        this.sumTimerWriteConcurrentInsert = sumTimerWriteConcurrentInsert;
    }

    private long minTimerWriteConcurrentInsert;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_CONCURRENT_INSERT")
    @Basic
    public long getMinTimerWriteConcurrentInsert() {
        return minTimerWriteConcurrentInsert;
    }

    public void setMinTimerWriteConcurrentInsert(long minTimerWriteConcurrentInsert) {
        this.minTimerWriteConcurrentInsert = minTimerWriteConcurrentInsert;
    }

    private long avgTimerWriteConcurrentInsert;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_CONCURRENT_INSERT")
    @Basic
    public long getAvgTimerWriteConcurrentInsert() {
        return avgTimerWriteConcurrentInsert;
    }

    public void setAvgTimerWriteConcurrentInsert(long avgTimerWriteConcurrentInsert) {
        this.avgTimerWriteConcurrentInsert = avgTimerWriteConcurrentInsert;
    }

    private long maxTimerWriteConcurrentInsert;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_CONCURRENT_INSERT")
    @Basic
    public long getMaxTimerWriteConcurrentInsert() {
        return maxTimerWriteConcurrentInsert;
    }

    public void setMaxTimerWriteConcurrentInsert(long maxTimerWriteConcurrentInsert) {
        this.maxTimerWriteConcurrentInsert = maxTimerWriteConcurrentInsert;
    }

    private long countWriteDelayed;

    @javax.persistence.Column(name = "COUNT_WRITE_DELAYED")
    @Basic
    public long getCountWriteDelayed() {
        return countWriteDelayed;
    }

    public void setCountWriteDelayed(long countWriteDelayed) {
        this.countWriteDelayed = countWriteDelayed;
    }

    private long sumTimerWriteDelayed;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_DELAYED")
    @Basic
    public long getSumTimerWriteDelayed() {
        return sumTimerWriteDelayed;
    }

    public void setSumTimerWriteDelayed(long sumTimerWriteDelayed) {
        this.sumTimerWriteDelayed = sumTimerWriteDelayed;
    }

    private long minTimerWriteDelayed;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_DELAYED")
    @Basic
    public long getMinTimerWriteDelayed() {
        return minTimerWriteDelayed;
    }

    public void setMinTimerWriteDelayed(long minTimerWriteDelayed) {
        this.minTimerWriteDelayed = minTimerWriteDelayed;
    }

    private long avgTimerWriteDelayed;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_DELAYED")
    @Basic
    public long getAvgTimerWriteDelayed() {
        return avgTimerWriteDelayed;
    }

    public void setAvgTimerWriteDelayed(long avgTimerWriteDelayed) {
        this.avgTimerWriteDelayed = avgTimerWriteDelayed;
    }

    private long maxTimerWriteDelayed;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_DELAYED")
    @Basic
    public long getMaxTimerWriteDelayed() {
        return maxTimerWriteDelayed;
    }

    public void setMaxTimerWriteDelayed(long maxTimerWriteDelayed) {
        this.maxTimerWriteDelayed = maxTimerWriteDelayed;
    }

    private long countWriteLowPriority;

    @javax.persistence.Column(name = "COUNT_WRITE_LOW_PRIORITY")
    @Basic
    public long getCountWriteLowPriority() {
        return countWriteLowPriority;
    }

    public void setCountWriteLowPriority(long countWriteLowPriority) {
        this.countWriteLowPriority = countWriteLowPriority;
    }

    private long sumTimerWriteLowPriority;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_LOW_PRIORITY")
    @Basic
    public long getSumTimerWriteLowPriority() {
        return sumTimerWriteLowPriority;
    }

    public void setSumTimerWriteLowPriority(long sumTimerWriteLowPriority) {
        this.sumTimerWriteLowPriority = sumTimerWriteLowPriority;
    }

    private long minTimerWriteLowPriority;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_LOW_PRIORITY")
    @Basic
    public long getMinTimerWriteLowPriority() {
        return minTimerWriteLowPriority;
    }

    public void setMinTimerWriteLowPriority(long minTimerWriteLowPriority) {
        this.minTimerWriteLowPriority = minTimerWriteLowPriority;
    }

    private long avgTimerWriteLowPriority;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_LOW_PRIORITY")
    @Basic
    public long getAvgTimerWriteLowPriority() {
        return avgTimerWriteLowPriority;
    }

    public void setAvgTimerWriteLowPriority(long avgTimerWriteLowPriority) {
        this.avgTimerWriteLowPriority = avgTimerWriteLowPriority;
    }

    private long maxTimerWriteLowPriority;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_LOW_PRIORITY")
    @Basic
    public long getMaxTimerWriteLowPriority() {
        return maxTimerWriteLowPriority;
    }

    public void setMaxTimerWriteLowPriority(long maxTimerWriteLowPriority) {
        this.maxTimerWriteLowPriority = maxTimerWriteLowPriority;
    }

    private long countWriteNormal;

    @javax.persistence.Column(name = "COUNT_WRITE_NORMAL")
    @Basic
    public long getCountWriteNormal() {
        return countWriteNormal;
    }

    public void setCountWriteNormal(long countWriteNormal) {
        this.countWriteNormal = countWriteNormal;
    }

    private long sumTimerWriteNormal;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_NORMAL")
    @Basic
    public long getSumTimerWriteNormal() {
        return sumTimerWriteNormal;
    }

    public void setSumTimerWriteNormal(long sumTimerWriteNormal) {
        this.sumTimerWriteNormal = sumTimerWriteNormal;
    }

    private long minTimerWriteNormal;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_NORMAL")
    @Basic
    public long getMinTimerWriteNormal() {
        return minTimerWriteNormal;
    }

    public void setMinTimerWriteNormal(long minTimerWriteNormal) {
        this.minTimerWriteNormal = minTimerWriteNormal;
    }

    private long avgTimerWriteNormal;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_NORMAL")
    @Basic
    public long getAvgTimerWriteNormal() {
        return avgTimerWriteNormal;
    }

    public void setAvgTimerWriteNormal(long avgTimerWriteNormal) {
        this.avgTimerWriteNormal = avgTimerWriteNormal;
    }

    private long maxTimerWriteNormal;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_NORMAL")
    @Basic
    public long getMaxTimerWriteNormal() {
        return maxTimerWriteNormal;
    }

    public void setMaxTimerWriteNormal(long maxTimerWriteNormal) {
        this.maxTimerWriteNormal = maxTimerWriteNormal;
    }

    private long countWriteExternal;

    @javax.persistence.Column(name = "COUNT_WRITE_EXTERNAL")
    @Basic
    public long getCountWriteExternal() {
        return countWriteExternal;
    }

    public void setCountWriteExternal(long countWriteExternal) {
        this.countWriteExternal = countWriteExternal;
    }

    private long sumTimerWriteExternal;

    @javax.persistence.Column(name = "SUM_TIMER_WRITE_EXTERNAL")
    @Basic
    public long getSumTimerWriteExternal() {
        return sumTimerWriteExternal;
    }

    public void setSumTimerWriteExternal(long sumTimerWriteExternal) {
        this.sumTimerWriteExternal = sumTimerWriteExternal;
    }

    private long minTimerWriteExternal;

    @javax.persistence.Column(name = "MIN_TIMER_WRITE_EXTERNAL")
    @Basic
    public long getMinTimerWriteExternal() {
        return minTimerWriteExternal;
    }

    public void setMinTimerWriteExternal(long minTimerWriteExternal) {
        this.minTimerWriteExternal = minTimerWriteExternal;
    }

    private long avgTimerWriteExternal;

    @javax.persistence.Column(name = "AVG_TIMER_WRITE_EXTERNAL")
    @Basic
    public long getAvgTimerWriteExternal() {
        return avgTimerWriteExternal;
    }

    public void setAvgTimerWriteExternal(long avgTimerWriteExternal) {
        this.avgTimerWriteExternal = avgTimerWriteExternal;
    }

    private long maxTimerWriteExternal;

    @javax.persistence.Column(name = "MAX_TIMER_WRITE_EXTERNAL")
    @Basic
    public long getMaxTimerWriteExternal() {
        return maxTimerWriteExternal;
    }

    public void setMaxTimerWriteExternal(long maxTimerWriteExternal) {
        this.maxTimerWriteExternal = maxTimerWriteExternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableLockWaitsSummaryByTableEntity that = (TableLockWaitsSummaryByTableEntity) o;

        if (avgTimerRead != that.avgTimerRead) return false;
        if (avgTimerReadExternal != that.avgTimerReadExternal) return false;
        if (avgTimerReadHighPriority != that.avgTimerReadHighPriority) return false;
        if (avgTimerReadNoInsert != that.avgTimerReadNoInsert) return false;
        if (avgTimerReadNormal != that.avgTimerReadNormal) return false;
        if (avgTimerReadWithSharedLocks != that.avgTimerReadWithSharedLocks) return false;
        if (avgTimerWait != that.avgTimerWait) return false;
        if (avgTimerWrite != that.avgTimerWrite) return false;
        if (avgTimerWriteAllowWrite != that.avgTimerWriteAllowWrite) return false;
        if (avgTimerWriteConcurrentInsert != that.avgTimerWriteConcurrentInsert) return false;
        if (avgTimerWriteDelayed != that.avgTimerWriteDelayed) return false;
        if (avgTimerWriteExternal != that.avgTimerWriteExternal) return false;
        if (avgTimerWriteLowPriority != that.avgTimerWriteLowPriority) return false;
        if (avgTimerWriteNormal != that.avgTimerWriteNormal) return false;
        if (countRead != that.countRead) return false;
        if (countReadExternal != that.countReadExternal) return false;
        if (countReadHighPriority != that.countReadHighPriority) return false;
        if (countReadNoInsert != that.countReadNoInsert) return false;
        if (countReadNormal != that.countReadNormal) return false;
        if (countReadWithSharedLocks != that.countReadWithSharedLocks) return false;
        if (countStar != that.countStar) return false;
        if (countWrite != that.countWrite) return false;
        if (countWriteAllowWrite != that.countWriteAllowWrite) return false;
        if (countWriteConcurrentInsert != that.countWriteConcurrentInsert) return false;
        if (countWriteDelayed != that.countWriteDelayed) return false;
        if (countWriteExternal != that.countWriteExternal) return false;
        if (countWriteLowPriority != that.countWriteLowPriority) return false;
        if (countWriteNormal != that.countWriteNormal) return false;
        if (maxTimerRead != that.maxTimerRead) return false;
        if (maxTimerReadExternal != that.maxTimerReadExternal) return false;
        if (maxTimerReadHighPriority != that.maxTimerReadHighPriority) return false;
        if (maxTimerReadNoInsert != that.maxTimerReadNoInsert) return false;
        if (maxTimerReadNormal != that.maxTimerReadNormal) return false;
        if (maxTimerReadWithSharedLocks != that.maxTimerReadWithSharedLocks) return false;
        if (maxTimerWait != that.maxTimerWait) return false;
        if (maxTimerWrite != that.maxTimerWrite) return false;
        if (maxTimerWriteAllowWrite != that.maxTimerWriteAllowWrite) return false;
        if (maxTimerWriteConcurrentInsert != that.maxTimerWriteConcurrentInsert) return false;
        if (maxTimerWriteDelayed != that.maxTimerWriteDelayed) return false;
        if (maxTimerWriteExternal != that.maxTimerWriteExternal) return false;
        if (maxTimerWriteLowPriority != that.maxTimerWriteLowPriority) return false;
        if (maxTimerWriteNormal != that.maxTimerWriteNormal) return false;
        if (minTimerRead != that.minTimerRead) return false;
        if (minTimerReadExternal != that.minTimerReadExternal) return false;
        if (minTimerReadHighPriority != that.minTimerReadHighPriority) return false;
        if (minTimerReadNoInsert != that.minTimerReadNoInsert) return false;
        if (minTimerReadNormal != that.minTimerReadNormal) return false;
        if (minTimerReadWithSharedLocks != that.minTimerReadWithSharedLocks) return false;
        if (minTimerWait != that.minTimerWait) return false;
        if (minTimerWrite != that.minTimerWrite) return false;
        if (minTimerWriteAllowWrite != that.minTimerWriteAllowWrite) return false;
        if (minTimerWriteConcurrentInsert != that.minTimerWriteConcurrentInsert) return false;
        if (minTimerWriteDelayed != that.minTimerWriteDelayed) return false;
        if (minTimerWriteExternal != that.minTimerWriteExternal) return false;
        if (minTimerWriteLowPriority != that.minTimerWriteLowPriority) return false;
        if (minTimerWriteNormal != that.minTimerWriteNormal) return false;
        if (sumTimerRead != that.sumTimerRead) return false;
        if (sumTimerReadExternal != that.sumTimerReadExternal) return false;
        if (sumTimerReadHighPriority != that.sumTimerReadHighPriority) return false;
        if (sumTimerReadNoInsert != that.sumTimerReadNoInsert) return false;
        if (sumTimerReadNormal != that.sumTimerReadNormal) return false;
        if (sumTimerReadWithSharedLocks != that.sumTimerReadWithSharedLocks) return false;
        if (sumTimerWait != that.sumTimerWait) return false;
        if (sumTimerWrite != that.sumTimerWrite) return false;
        if (sumTimerWriteAllowWrite != that.sumTimerWriteAllowWrite) return false;
        if (sumTimerWriteConcurrentInsert != that.sumTimerWriteConcurrentInsert) return false;
        if (sumTimerWriteDelayed != that.sumTimerWriteDelayed) return false;
        if (sumTimerWriteExternal != that.sumTimerWriteExternal) return false;
        if (sumTimerWriteLowPriority != that.sumTimerWriteLowPriority) return false;
        if (sumTimerWriteNormal != that.sumTimerWriteNormal) return false;
        if (objectName != null ? !objectName.equals(that.objectName) : that.objectName != null) return false;
        if (objectSchema != null ? !objectSchema.equals(that.objectSchema) : that.objectSchema != null) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectType != null ? objectType.hashCode() : 0;
        result = 31 * result + (objectSchema != null ? objectSchema.hashCode() : 0);
        result = 31 * result + (objectName != null ? objectName.hashCode() : 0);
        result = 31 * result + (int) (countStar ^ (countStar >>> 32));
        result = 31 * result + (int) (sumTimerWait ^ (sumTimerWait >>> 32));
        result = 31 * result + (int) (minTimerWait ^ (minTimerWait >>> 32));
        result = 31 * result + (int) (avgTimerWait ^ (avgTimerWait >>> 32));
        result = 31 * result + (int) (maxTimerWait ^ (maxTimerWait >>> 32));
        result = 31 * result + (int) (countRead ^ (countRead >>> 32));
        result = 31 * result + (int) (sumTimerRead ^ (sumTimerRead >>> 32));
        result = 31 * result + (int) (minTimerRead ^ (minTimerRead >>> 32));
        result = 31 * result + (int) (avgTimerRead ^ (avgTimerRead >>> 32));
        result = 31 * result + (int) (maxTimerRead ^ (maxTimerRead >>> 32));
        result = 31 * result + (int) (countWrite ^ (countWrite >>> 32));
        result = 31 * result + (int) (sumTimerWrite ^ (sumTimerWrite >>> 32));
        result = 31 * result + (int) (minTimerWrite ^ (minTimerWrite >>> 32));
        result = 31 * result + (int) (avgTimerWrite ^ (avgTimerWrite >>> 32));
        result = 31 * result + (int) (maxTimerWrite ^ (maxTimerWrite >>> 32));
        result = 31 * result + (int) (countReadNormal ^ (countReadNormal >>> 32));
        result = 31 * result + (int) (sumTimerReadNormal ^ (sumTimerReadNormal >>> 32));
        result = 31 * result + (int) (minTimerReadNormal ^ (minTimerReadNormal >>> 32));
        result = 31 * result + (int) (avgTimerReadNormal ^ (avgTimerReadNormal >>> 32));
        result = 31 * result + (int) (maxTimerReadNormal ^ (maxTimerReadNormal >>> 32));
        result = 31 * result + (int) (countReadWithSharedLocks ^ (countReadWithSharedLocks >>> 32));
        result = 31 * result + (int) (sumTimerReadWithSharedLocks ^ (sumTimerReadWithSharedLocks >>> 32));
        result = 31 * result + (int) (minTimerReadWithSharedLocks ^ (minTimerReadWithSharedLocks >>> 32));
        result = 31 * result + (int) (avgTimerReadWithSharedLocks ^ (avgTimerReadWithSharedLocks >>> 32));
        result = 31 * result + (int) (maxTimerReadWithSharedLocks ^ (maxTimerReadWithSharedLocks >>> 32));
        result = 31 * result + (int) (countReadHighPriority ^ (countReadHighPriority >>> 32));
        result = 31 * result + (int) (sumTimerReadHighPriority ^ (sumTimerReadHighPriority >>> 32));
        result = 31 * result + (int) (minTimerReadHighPriority ^ (minTimerReadHighPriority >>> 32));
        result = 31 * result + (int) (avgTimerReadHighPriority ^ (avgTimerReadHighPriority >>> 32));
        result = 31 * result + (int) (maxTimerReadHighPriority ^ (maxTimerReadHighPriority >>> 32));
        result = 31 * result + (int) (countReadNoInsert ^ (countReadNoInsert >>> 32));
        result = 31 * result + (int) (sumTimerReadNoInsert ^ (sumTimerReadNoInsert >>> 32));
        result = 31 * result + (int) (minTimerReadNoInsert ^ (minTimerReadNoInsert >>> 32));
        result = 31 * result + (int) (avgTimerReadNoInsert ^ (avgTimerReadNoInsert >>> 32));
        result = 31 * result + (int) (maxTimerReadNoInsert ^ (maxTimerReadNoInsert >>> 32));
        result = 31 * result + (int) (countReadExternal ^ (countReadExternal >>> 32));
        result = 31 * result + (int) (sumTimerReadExternal ^ (sumTimerReadExternal >>> 32));
        result = 31 * result + (int) (minTimerReadExternal ^ (minTimerReadExternal >>> 32));
        result = 31 * result + (int) (avgTimerReadExternal ^ (avgTimerReadExternal >>> 32));
        result = 31 * result + (int) (maxTimerReadExternal ^ (maxTimerReadExternal >>> 32));
        result = 31 * result + (int) (countWriteAllowWrite ^ (countWriteAllowWrite >>> 32));
        result = 31 * result + (int) (sumTimerWriteAllowWrite ^ (sumTimerWriteAllowWrite >>> 32));
        result = 31 * result + (int) (minTimerWriteAllowWrite ^ (minTimerWriteAllowWrite >>> 32));
        result = 31 * result + (int) (avgTimerWriteAllowWrite ^ (avgTimerWriteAllowWrite >>> 32));
        result = 31 * result + (int) (maxTimerWriteAllowWrite ^ (maxTimerWriteAllowWrite >>> 32));
        result = 31 * result + (int) (countWriteConcurrentInsert ^ (countWriteConcurrentInsert >>> 32));
        result = 31 * result + (int) (sumTimerWriteConcurrentInsert ^ (sumTimerWriteConcurrentInsert >>> 32));
        result = 31 * result + (int) (minTimerWriteConcurrentInsert ^ (minTimerWriteConcurrentInsert >>> 32));
        result = 31 * result + (int) (avgTimerWriteConcurrentInsert ^ (avgTimerWriteConcurrentInsert >>> 32));
        result = 31 * result + (int) (maxTimerWriteConcurrentInsert ^ (maxTimerWriteConcurrentInsert >>> 32));
        result = 31 * result + (int) (countWriteDelayed ^ (countWriteDelayed >>> 32));
        result = 31 * result + (int) (sumTimerWriteDelayed ^ (sumTimerWriteDelayed >>> 32));
        result = 31 * result + (int) (minTimerWriteDelayed ^ (minTimerWriteDelayed >>> 32));
        result = 31 * result + (int) (avgTimerWriteDelayed ^ (avgTimerWriteDelayed >>> 32));
        result = 31 * result + (int) (maxTimerWriteDelayed ^ (maxTimerWriteDelayed >>> 32));
        result = 31 * result + (int) (countWriteLowPriority ^ (countWriteLowPriority >>> 32));
        result = 31 * result + (int) (sumTimerWriteLowPriority ^ (sumTimerWriteLowPriority >>> 32));
        result = 31 * result + (int) (minTimerWriteLowPriority ^ (minTimerWriteLowPriority >>> 32));
        result = 31 * result + (int) (avgTimerWriteLowPriority ^ (avgTimerWriteLowPriority >>> 32));
        result = 31 * result + (int) (maxTimerWriteLowPriority ^ (maxTimerWriteLowPriority >>> 32));
        result = 31 * result + (int) (countWriteNormal ^ (countWriteNormal >>> 32));
        result = 31 * result + (int) (sumTimerWriteNormal ^ (sumTimerWriteNormal >>> 32));
        result = 31 * result + (int) (minTimerWriteNormal ^ (minTimerWriteNormal >>> 32));
        result = 31 * result + (int) (avgTimerWriteNormal ^ (avgTimerWriteNormal >>> 32));
        result = 31 * result + (int) (maxTimerWriteNormal ^ (maxTimerWriteNormal >>> 32));
        result = 31 * result + (int) (countWriteExternal ^ (countWriteExternal >>> 32));
        result = 31 * result + (int) (sumTimerWriteExternal ^ (sumTimerWriteExternal >>> 32));
        result = 31 * result + (int) (minTimerWriteExternal ^ (minTimerWriteExternal >>> 32));
        result = 31 * result + (int) (avgTimerWriteExternal ^ (avgTimerWriteExternal >>> 32));
        result = 31 * result + (int) (maxTimerWriteExternal ^ (maxTimerWriteExternal >>> 32));
        return result;
    }
}
