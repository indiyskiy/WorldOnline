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
@javax.persistence.Table(name = "socket_summary_by_instance", schema = "", catalog = "performance_schema")
@Entity
public class SocketSummaryByInstanceEntity {
    private String eventName;

    @javax.persistence.Column(name = "EVENT_NAME")
    @Basic
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private long objectInstanceBegin;

    @javax.persistence.Column(name = "OBJECT_INSTANCE_BEGIN")
    @Basic
    public long getObjectInstanceBegin() {
        return objectInstanceBegin;
    }

    public void setObjectInstanceBegin(long objectInstanceBegin) {
        this.objectInstanceBegin = objectInstanceBegin;
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

    private long sumNumberOfBytesRead;

    @javax.persistence.Column(name = "SUM_NUMBER_OF_BYTES_READ")
    @Basic
    public long getSumNumberOfBytesRead() {
        return sumNumberOfBytesRead;
    }

    public void setSumNumberOfBytesRead(long sumNumberOfBytesRead) {
        this.sumNumberOfBytesRead = sumNumberOfBytesRead;
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

    private long sumNumberOfBytesWrite;

    @javax.persistence.Column(name = "SUM_NUMBER_OF_BYTES_WRITE")
    @Basic
    public long getSumNumberOfBytesWrite() {
        return sumNumberOfBytesWrite;
    }

    public void setSumNumberOfBytesWrite(long sumNumberOfBytesWrite) {
        this.sumNumberOfBytesWrite = sumNumberOfBytesWrite;
    }

    private long countMisc;

    @javax.persistence.Column(name = "COUNT_MISC")
    @Basic
    public long getCountMisc() {
        return countMisc;
    }

    public void setCountMisc(long countMisc) {
        this.countMisc = countMisc;
    }

    private long sumTimerMisc;

    @javax.persistence.Column(name = "SUM_TIMER_MISC")
    @Basic
    public long getSumTimerMisc() {
        return sumTimerMisc;
    }

    public void setSumTimerMisc(long sumTimerMisc) {
        this.sumTimerMisc = sumTimerMisc;
    }

    private long minTimerMisc;

    @javax.persistence.Column(name = "MIN_TIMER_MISC")
    @Basic
    public long getMinTimerMisc() {
        return minTimerMisc;
    }

    public void setMinTimerMisc(long minTimerMisc) {
        this.minTimerMisc = minTimerMisc;
    }

    private long avgTimerMisc;

    @javax.persistence.Column(name = "AVG_TIMER_MISC")
    @Basic
    public long getAvgTimerMisc() {
        return avgTimerMisc;
    }

    public void setAvgTimerMisc(long avgTimerMisc) {
        this.avgTimerMisc = avgTimerMisc;
    }

    private long maxTimerMisc;

    @javax.persistence.Column(name = "MAX_TIMER_MISC")
    @Basic
    public long getMaxTimerMisc() {
        return maxTimerMisc;
    }

    public void setMaxTimerMisc(long maxTimerMisc) {
        this.maxTimerMisc = maxTimerMisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketSummaryByInstanceEntity that = (SocketSummaryByInstanceEntity) o;

        if (avgTimerMisc != that.avgTimerMisc) return false;
        if (avgTimerRead != that.avgTimerRead) return false;
        if (avgTimerWait != that.avgTimerWait) return false;
        if (avgTimerWrite != that.avgTimerWrite) return false;
        if (countMisc != that.countMisc) return false;
        if (countRead != that.countRead) return false;
        if (countStar != that.countStar) return false;
        if (countWrite != that.countWrite) return false;
        if (maxTimerMisc != that.maxTimerMisc) return false;
        if (maxTimerRead != that.maxTimerRead) return false;
        if (maxTimerWait != that.maxTimerWait) return false;
        if (maxTimerWrite != that.maxTimerWrite) return false;
        if (minTimerMisc != that.minTimerMisc) return false;
        if (minTimerRead != that.minTimerRead) return false;
        if (minTimerWait != that.minTimerWait) return false;
        if (minTimerWrite != that.minTimerWrite) return false;
        if (objectInstanceBegin != that.objectInstanceBegin) return false;
        if (sumNumberOfBytesRead != that.sumNumberOfBytesRead) return false;
        if (sumNumberOfBytesWrite != that.sumNumberOfBytesWrite) return false;
        if (sumTimerMisc != that.sumTimerMisc) return false;
        if (sumTimerRead != that.sumTimerRead) return false;
        if (sumTimerWait != that.sumTimerWait) return false;
        if (sumTimerWrite != that.sumTimerWrite) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + (int) (objectInstanceBegin ^ (objectInstanceBegin >>> 32));
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
        result = 31 * result + (int) (sumNumberOfBytesRead ^ (sumNumberOfBytesRead >>> 32));
        result = 31 * result + (int) (countWrite ^ (countWrite >>> 32));
        result = 31 * result + (int) (sumTimerWrite ^ (sumTimerWrite >>> 32));
        result = 31 * result + (int) (minTimerWrite ^ (minTimerWrite >>> 32));
        result = 31 * result + (int) (avgTimerWrite ^ (avgTimerWrite >>> 32));
        result = 31 * result + (int) (maxTimerWrite ^ (maxTimerWrite >>> 32));
        result = 31 * result + (int) (sumNumberOfBytesWrite ^ (sumNumberOfBytesWrite >>> 32));
        result = 31 * result + (int) (countMisc ^ (countMisc >>> 32));
        result = 31 * result + (int) (sumTimerMisc ^ (sumTimerMisc >>> 32));
        result = 31 * result + (int) (minTimerMisc ^ (minTimerMisc >>> 32));
        result = 31 * result + (int) (avgTimerMisc ^ (avgTimerMisc >>> 32));
        result = 31 * result + (int) (maxTimerMisc ^ (maxTimerMisc >>> 32));
        return result;
    }
}
