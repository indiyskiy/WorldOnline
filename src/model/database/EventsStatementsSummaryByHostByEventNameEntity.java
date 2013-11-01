package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "events_statements_summary_by_host_by_event_name", schema = "", catalog = "performance_schema")
@Entity
public class EventsStatementsSummaryByHostByEventNameEntity {
    private String host;

    @javax.persistence.Column(name = "HOST")
    @Basic
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String eventName;

    @javax.persistence.Column(name = "EVENT_NAME")
    @Basic
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    private long sumLockTime;

    @javax.persistence.Column(name = "SUM_LOCK_TIME")
    @Basic
    public long getSumLockTime() {
        return sumLockTime;
    }

    public void setSumLockTime(long sumLockTime) {
        this.sumLockTime = sumLockTime;
    }

    private long sumErrors;

    @javax.persistence.Column(name = "SUM_ERRORS")
    @Basic
    public long getSumErrors() {
        return sumErrors;
    }

    public void setSumErrors(long sumErrors) {
        this.sumErrors = sumErrors;
    }

    private long sumWarnings;

    @javax.persistence.Column(name = "SUM_WARNINGS")
    @Basic
    public long getSumWarnings() {
        return sumWarnings;
    }

    public void setSumWarnings(long sumWarnings) {
        this.sumWarnings = sumWarnings;
    }

    private long sumRowsAffected;

    @javax.persistence.Column(name = "SUM_ROWS_AFFECTED")
    @Basic
    public long getSumRowsAffected() {
        return sumRowsAffected;
    }

    public void setSumRowsAffected(long sumRowsAffected) {
        this.sumRowsAffected = sumRowsAffected;
    }

    private long sumRowsSent;

    @javax.persistence.Column(name = "SUM_ROWS_SENT")
    @Basic
    public long getSumRowsSent() {
        return sumRowsSent;
    }

    public void setSumRowsSent(long sumRowsSent) {
        this.sumRowsSent = sumRowsSent;
    }

    private long sumRowsExamined;

    @javax.persistence.Column(name = "SUM_ROWS_EXAMINED")
    @Basic
    public long getSumRowsExamined() {
        return sumRowsExamined;
    }

    public void setSumRowsExamined(long sumRowsExamined) {
        this.sumRowsExamined = sumRowsExamined;
    }

    private long sumCreatedTmpDiskTables;

    @javax.persistence.Column(name = "SUM_CREATED_TMP_DISK_TABLES")
    @Basic
    public long getSumCreatedTmpDiskTables() {
        return sumCreatedTmpDiskTables;
    }

    public void setSumCreatedTmpDiskTables(long sumCreatedTmpDiskTables) {
        this.sumCreatedTmpDiskTables = sumCreatedTmpDiskTables;
    }

    private long sumCreatedTmpTables;

    @javax.persistence.Column(name = "SUM_CREATED_TMP_TABLES")
    @Basic
    public long getSumCreatedTmpTables() {
        return sumCreatedTmpTables;
    }

    public void setSumCreatedTmpTables(long sumCreatedTmpTables) {
        this.sumCreatedTmpTables = sumCreatedTmpTables;
    }

    private long sumSelectFullJoin;

    @javax.persistence.Column(name = "SUM_SELECT_FULL_JOIN")
    @Basic
    public long getSumSelectFullJoin() {
        return sumSelectFullJoin;
    }

    public void setSumSelectFullJoin(long sumSelectFullJoin) {
        this.sumSelectFullJoin = sumSelectFullJoin;
    }

    private long sumSelectFullRangeJoin;

    @javax.persistence.Column(name = "SUM_SELECT_FULL_RANGE_JOIN")
    @Basic
    public long getSumSelectFullRangeJoin() {
        return sumSelectFullRangeJoin;
    }

    public void setSumSelectFullRangeJoin(long sumSelectFullRangeJoin) {
        this.sumSelectFullRangeJoin = sumSelectFullRangeJoin;
    }

    private long sumSelectRange;

    @javax.persistence.Column(name = "SUM_SELECT_RANGE")
    @Basic
    public long getSumSelectRange() {
        return sumSelectRange;
    }

    public void setSumSelectRange(long sumSelectRange) {
        this.sumSelectRange = sumSelectRange;
    }

    private long sumSelectRangeCheck;

    @javax.persistence.Column(name = "SUM_SELECT_RANGE_CHECK")
    @Basic
    public long getSumSelectRangeCheck() {
        return sumSelectRangeCheck;
    }

    public void setSumSelectRangeCheck(long sumSelectRangeCheck) {
        this.sumSelectRangeCheck = sumSelectRangeCheck;
    }

    private long sumSelectScan;

    @javax.persistence.Column(name = "SUM_SELECT_SCAN")
    @Basic
    public long getSumSelectScan() {
        return sumSelectScan;
    }

    public void setSumSelectScan(long sumSelectScan) {
        this.sumSelectScan = sumSelectScan;
    }

    private long sumSortMergePasses;

    @javax.persistence.Column(name = "SUM_SORT_MERGE_PASSES")
    @Basic
    public long getSumSortMergePasses() {
        return sumSortMergePasses;
    }

    public void setSumSortMergePasses(long sumSortMergePasses) {
        this.sumSortMergePasses = sumSortMergePasses;
    }

    private long sumSortRange;

    @javax.persistence.Column(name = "SUM_SORT_RANGE")
    @Basic
    public long getSumSortRange() {
        return sumSortRange;
    }

    public void setSumSortRange(long sumSortRange) {
        this.sumSortRange = sumSortRange;
    }

    private long sumSortRows;

    @javax.persistence.Column(name = "SUM_SORT_ROWS")
    @Basic
    public long getSumSortRows() {
        return sumSortRows;
    }

    public void setSumSortRows(long sumSortRows) {
        this.sumSortRows = sumSortRows;
    }

    private long sumSortScan;

    @javax.persistence.Column(name = "SUM_SORT_SCAN")
    @Basic
    public long getSumSortScan() {
        return sumSortScan;
    }

    public void setSumSortScan(long sumSortScan) {
        this.sumSortScan = sumSortScan;
    }

    private long sumNoIndexUsed;

    @javax.persistence.Column(name = "SUM_NO_INDEX_USED")
    @Basic
    public long getSumNoIndexUsed() {
        return sumNoIndexUsed;
    }

    public void setSumNoIndexUsed(long sumNoIndexUsed) {
        this.sumNoIndexUsed = sumNoIndexUsed;
    }

    private long sumNoGoodIndexUsed;

    @javax.persistence.Column(name = "SUM_NO_GOOD_INDEX_USED")
    @Basic
    public long getSumNoGoodIndexUsed() {
        return sumNoGoodIndexUsed;
    }

    public void setSumNoGoodIndexUsed(long sumNoGoodIndexUsed) {
        this.sumNoGoodIndexUsed = sumNoGoodIndexUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsStatementsSummaryByHostByEventNameEntity that = (EventsStatementsSummaryByHostByEventNameEntity) o;

        if (avgTimerWait != that.avgTimerWait) return false;
        if (countStar != that.countStar) return false;
        if (maxTimerWait != that.maxTimerWait) return false;
        if (minTimerWait != that.minTimerWait) return false;
        if (sumCreatedTmpDiskTables != that.sumCreatedTmpDiskTables) return false;
        if (sumCreatedTmpTables != that.sumCreatedTmpTables) return false;
        if (sumErrors != that.sumErrors) return false;
        if (sumLockTime != that.sumLockTime) return false;
        if (sumNoGoodIndexUsed != that.sumNoGoodIndexUsed) return false;
        if (sumNoIndexUsed != that.sumNoIndexUsed) return false;
        if (sumRowsAffected != that.sumRowsAffected) return false;
        if (sumRowsExamined != that.sumRowsExamined) return false;
        if (sumRowsSent != that.sumRowsSent) return false;
        if (sumSelectFullJoin != that.sumSelectFullJoin) return false;
        if (sumSelectFullRangeJoin != that.sumSelectFullRangeJoin) return false;
        if (sumSelectRange != that.sumSelectRange) return false;
        if (sumSelectRangeCheck != that.sumSelectRangeCheck) return false;
        if (sumSelectScan != that.sumSelectScan) return false;
        if (sumSortMergePasses != that.sumSortMergePasses) return false;
        if (sumSortRange != that.sumSortRange) return false;
        if (sumSortRows != that.sumSortRows) return false;
        if (sumSortScan != that.sumSortScan) return false;
        if (sumTimerWait != that.sumTimerWait) return false;
        if (sumWarnings != that.sumWarnings) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (int) (countStar ^ (countStar >>> 32));
        result = 31 * result + (int) (sumTimerWait ^ (sumTimerWait >>> 32));
        result = 31 * result + (int) (minTimerWait ^ (minTimerWait >>> 32));
        result = 31 * result + (int) (avgTimerWait ^ (avgTimerWait >>> 32));
        result = 31 * result + (int) (maxTimerWait ^ (maxTimerWait >>> 32));
        result = 31 * result + (int) (sumLockTime ^ (sumLockTime >>> 32));
        result = 31 * result + (int) (sumErrors ^ (sumErrors >>> 32));
        result = 31 * result + (int) (sumWarnings ^ (sumWarnings >>> 32));
        result = 31 * result + (int) (sumRowsAffected ^ (sumRowsAffected >>> 32));
        result = 31 * result + (int) (sumRowsSent ^ (sumRowsSent >>> 32));
        result = 31 * result + (int) (sumRowsExamined ^ (sumRowsExamined >>> 32));
        result = 31 * result + (int) (sumCreatedTmpDiskTables ^ (sumCreatedTmpDiskTables >>> 32));
        result = 31 * result + (int) (sumCreatedTmpTables ^ (sumCreatedTmpTables >>> 32));
        result = 31 * result + (int) (sumSelectFullJoin ^ (sumSelectFullJoin >>> 32));
        result = 31 * result + (int) (sumSelectFullRangeJoin ^ (sumSelectFullRangeJoin >>> 32));
        result = 31 * result + (int) (sumSelectRange ^ (sumSelectRange >>> 32));
        result = 31 * result + (int) (sumSelectRangeCheck ^ (sumSelectRangeCheck >>> 32));
        result = 31 * result + (int) (sumSelectScan ^ (sumSelectScan >>> 32));
        result = 31 * result + (int) (sumSortMergePasses ^ (sumSortMergePasses >>> 32));
        result = 31 * result + (int) (sumSortRange ^ (sumSortRange >>> 32));
        result = 31 * result + (int) (sumSortRows ^ (sumSortRows >>> 32));
        result = 31 * result + (int) (sumSortScan ^ (sumSortScan >>> 32));
        result = 31 * result + (int) (sumNoIndexUsed ^ (sumNoIndexUsed >>> 32));
        result = 31 * result + (int) (sumNoGoodIndexUsed ^ (sumNoGoodIndexUsed >>> 32));
        return result;
    }
}
