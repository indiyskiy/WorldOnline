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
@javax.persistence.Table(name = "table_io_waits_summary_by_table", schema = "", catalog = "performance_schema")
@Entity
public class TableIoWaitsSummaryByTableEntity {
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

    private long countFetch;

    @javax.persistence.Column(name = "COUNT_FETCH")
    @Basic
    public long getCountFetch() {
        return countFetch;
    }

    public void setCountFetch(long countFetch) {
        this.countFetch = countFetch;
    }

    private long sumTimerFetch;

    @javax.persistence.Column(name = "SUM_TIMER_FETCH")
    @Basic
    public long getSumTimerFetch() {
        return sumTimerFetch;
    }

    public void setSumTimerFetch(long sumTimerFetch) {
        this.sumTimerFetch = sumTimerFetch;
    }

    private long minTimerFetch;

    @javax.persistence.Column(name = "MIN_TIMER_FETCH")
    @Basic
    public long getMinTimerFetch() {
        return minTimerFetch;
    }

    public void setMinTimerFetch(long minTimerFetch) {
        this.minTimerFetch = minTimerFetch;
    }

    private long avgTimerFetch;

    @javax.persistence.Column(name = "AVG_TIMER_FETCH")
    @Basic
    public long getAvgTimerFetch() {
        return avgTimerFetch;
    }

    public void setAvgTimerFetch(long avgTimerFetch) {
        this.avgTimerFetch = avgTimerFetch;
    }

    private long maxTimerFetch;

    @javax.persistence.Column(name = "MAX_TIMER_FETCH")
    @Basic
    public long getMaxTimerFetch() {
        return maxTimerFetch;
    }

    public void setMaxTimerFetch(long maxTimerFetch) {
        this.maxTimerFetch = maxTimerFetch;
    }

    private long countInsert;

    @javax.persistence.Column(name = "COUNT_INSERT")
    @Basic
    public long getCountInsert() {
        return countInsert;
    }

    public void setCountInsert(long countInsert) {
        this.countInsert = countInsert;
    }

    private long sumTimerInsert;

    @javax.persistence.Column(name = "SUM_TIMER_INSERT")
    @Basic
    public long getSumTimerInsert() {
        return sumTimerInsert;
    }

    public void setSumTimerInsert(long sumTimerInsert) {
        this.sumTimerInsert = sumTimerInsert;
    }

    private long minTimerInsert;

    @javax.persistence.Column(name = "MIN_TIMER_INSERT")
    @Basic
    public long getMinTimerInsert() {
        return minTimerInsert;
    }

    public void setMinTimerInsert(long minTimerInsert) {
        this.minTimerInsert = minTimerInsert;
    }

    private long avgTimerInsert;

    @javax.persistence.Column(name = "AVG_TIMER_INSERT")
    @Basic
    public long getAvgTimerInsert() {
        return avgTimerInsert;
    }

    public void setAvgTimerInsert(long avgTimerInsert) {
        this.avgTimerInsert = avgTimerInsert;
    }

    private long maxTimerInsert;

    @javax.persistence.Column(name = "MAX_TIMER_INSERT")
    @Basic
    public long getMaxTimerInsert() {
        return maxTimerInsert;
    }

    public void setMaxTimerInsert(long maxTimerInsert) {
        this.maxTimerInsert = maxTimerInsert;
    }

    private long countUpdate;

    @javax.persistence.Column(name = "COUNT_UPDATE")
    @Basic
    public long getCountUpdate() {
        return countUpdate;
    }

    public void setCountUpdate(long countUpdate) {
        this.countUpdate = countUpdate;
    }

    private long sumTimerUpdate;

    @javax.persistence.Column(name = "SUM_TIMER_UPDATE")
    @Basic
    public long getSumTimerUpdate() {
        return sumTimerUpdate;
    }

    public void setSumTimerUpdate(long sumTimerUpdate) {
        this.sumTimerUpdate = sumTimerUpdate;
    }

    private long minTimerUpdate;

    @javax.persistence.Column(name = "MIN_TIMER_UPDATE")
    @Basic
    public long getMinTimerUpdate() {
        return minTimerUpdate;
    }

    public void setMinTimerUpdate(long minTimerUpdate) {
        this.minTimerUpdate = minTimerUpdate;
    }

    private long avgTimerUpdate;

    @javax.persistence.Column(name = "AVG_TIMER_UPDATE")
    @Basic
    public long getAvgTimerUpdate() {
        return avgTimerUpdate;
    }

    public void setAvgTimerUpdate(long avgTimerUpdate) {
        this.avgTimerUpdate = avgTimerUpdate;
    }

    private long maxTimerUpdate;

    @javax.persistence.Column(name = "MAX_TIMER_UPDATE")
    @Basic
    public long getMaxTimerUpdate() {
        return maxTimerUpdate;
    }

    public void setMaxTimerUpdate(long maxTimerUpdate) {
        this.maxTimerUpdate = maxTimerUpdate;
    }

    private long countDelete;

    @javax.persistence.Column(name = "COUNT_DELETE")
    @Basic
    public long getCountDelete() {
        return countDelete;
    }

    public void setCountDelete(long countDelete) {
        this.countDelete = countDelete;
    }

    private long sumTimerDelete;

    @javax.persistence.Column(name = "SUM_TIMER_DELETE")
    @Basic
    public long getSumTimerDelete() {
        return sumTimerDelete;
    }

    public void setSumTimerDelete(long sumTimerDelete) {
        this.sumTimerDelete = sumTimerDelete;
    }

    private long minTimerDelete;

    @javax.persistence.Column(name = "MIN_TIMER_DELETE")
    @Basic
    public long getMinTimerDelete() {
        return minTimerDelete;
    }

    public void setMinTimerDelete(long minTimerDelete) {
        this.minTimerDelete = minTimerDelete;
    }

    private long avgTimerDelete;

    @javax.persistence.Column(name = "AVG_TIMER_DELETE")
    @Basic
    public long getAvgTimerDelete() {
        return avgTimerDelete;
    }

    public void setAvgTimerDelete(long avgTimerDelete) {
        this.avgTimerDelete = avgTimerDelete;
    }

    private long maxTimerDelete;

    @javax.persistence.Column(name = "MAX_TIMER_DELETE")
    @Basic
    public long getMaxTimerDelete() {
        return maxTimerDelete;
    }

    public void setMaxTimerDelete(long maxTimerDelete) {
        this.maxTimerDelete = maxTimerDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableIoWaitsSummaryByTableEntity that = (TableIoWaitsSummaryByTableEntity) o;

        if (avgTimerDelete != that.avgTimerDelete) return false;
        if (avgTimerFetch != that.avgTimerFetch) return false;
        if (avgTimerInsert != that.avgTimerInsert) return false;
        if (avgTimerRead != that.avgTimerRead) return false;
        if (avgTimerUpdate != that.avgTimerUpdate) return false;
        if (avgTimerWait != that.avgTimerWait) return false;
        if (avgTimerWrite != that.avgTimerWrite) return false;
        if (countDelete != that.countDelete) return false;
        if (countFetch != that.countFetch) return false;
        if (countInsert != that.countInsert) return false;
        if (countRead != that.countRead) return false;
        if (countStar != that.countStar) return false;
        if (countUpdate != that.countUpdate) return false;
        if (countWrite != that.countWrite) return false;
        if (maxTimerDelete != that.maxTimerDelete) return false;
        if (maxTimerFetch != that.maxTimerFetch) return false;
        if (maxTimerInsert != that.maxTimerInsert) return false;
        if (maxTimerRead != that.maxTimerRead) return false;
        if (maxTimerUpdate != that.maxTimerUpdate) return false;
        if (maxTimerWait != that.maxTimerWait) return false;
        if (maxTimerWrite != that.maxTimerWrite) return false;
        if (minTimerDelete != that.minTimerDelete) return false;
        if (minTimerFetch != that.minTimerFetch) return false;
        if (minTimerInsert != that.minTimerInsert) return false;
        if (minTimerRead != that.minTimerRead) return false;
        if (minTimerUpdate != that.minTimerUpdate) return false;
        if (minTimerWait != that.minTimerWait) return false;
        if (minTimerWrite != that.minTimerWrite) return false;
        if (sumTimerDelete != that.sumTimerDelete) return false;
        if (sumTimerFetch != that.sumTimerFetch) return false;
        if (sumTimerInsert != that.sumTimerInsert) return false;
        if (sumTimerRead != that.sumTimerRead) return false;
        if (sumTimerUpdate != that.sumTimerUpdate) return false;
        if (sumTimerWait != that.sumTimerWait) return false;
        if (sumTimerWrite != that.sumTimerWrite) return false;
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
        result = 31 * result + (int) (countFetch ^ (countFetch >>> 32));
        result = 31 * result + (int) (sumTimerFetch ^ (sumTimerFetch >>> 32));
        result = 31 * result + (int) (minTimerFetch ^ (minTimerFetch >>> 32));
        result = 31 * result + (int) (avgTimerFetch ^ (avgTimerFetch >>> 32));
        result = 31 * result + (int) (maxTimerFetch ^ (maxTimerFetch >>> 32));
        result = 31 * result + (int) (countInsert ^ (countInsert >>> 32));
        result = 31 * result + (int) (sumTimerInsert ^ (sumTimerInsert >>> 32));
        result = 31 * result + (int) (minTimerInsert ^ (minTimerInsert >>> 32));
        result = 31 * result + (int) (avgTimerInsert ^ (avgTimerInsert >>> 32));
        result = 31 * result + (int) (maxTimerInsert ^ (maxTimerInsert >>> 32));
        result = 31 * result + (int) (countUpdate ^ (countUpdate >>> 32));
        result = 31 * result + (int) (sumTimerUpdate ^ (sumTimerUpdate >>> 32));
        result = 31 * result + (int) (minTimerUpdate ^ (minTimerUpdate >>> 32));
        result = 31 * result + (int) (avgTimerUpdate ^ (avgTimerUpdate >>> 32));
        result = 31 * result + (int) (maxTimerUpdate ^ (maxTimerUpdate >>> 32));
        result = 31 * result + (int) (countDelete ^ (countDelete >>> 32));
        result = 31 * result + (int) (sumTimerDelete ^ (sumTimerDelete >>> 32));
        result = 31 * result + (int) (minTimerDelete ^ (minTimerDelete >>> 32));
        result = 31 * result + (int) (avgTimerDelete ^ (avgTimerDelete >>> 32));
        result = 31 * result + (int) (maxTimerDelete ^ (maxTimerDelete >>> 32));
        return result;
    }
}
