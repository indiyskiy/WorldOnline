package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "events_statements_history", schema = "", catalog = "performance_schema")
@Entity
public class EventsStatementsHistoryEntity {
    private long threadId;

    @javax.persistence.Column(name = "THREAD_ID")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    private long eventId;

    @javax.persistence.Column(name = "EVENT_ID")
    @Basic
    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    private long endEventId;

    @javax.persistence.Column(name = "END_EVENT_ID")
    @Basic
    public long getEndEventId() {
        return endEventId;
    }

    public void setEndEventId(long endEventId) {
        this.endEventId = endEventId;
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

    private String source;

    @javax.persistence.Column(name = "SOURCE")
    @Basic
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private long timerStart;

    @javax.persistence.Column(name = "TIMER_START")
    @Basic
    public long getTimerStart() {
        return timerStart;
    }

    public void setTimerStart(long timerStart) {
        this.timerStart = timerStart;
    }

    private long timerEnd;

    @javax.persistence.Column(name = "TIMER_END")
    @Basic
    public long getTimerEnd() {
        return timerEnd;
    }

    public void setTimerEnd(long timerEnd) {
        this.timerEnd = timerEnd;
    }

    private long timerWait;

    @javax.persistence.Column(name = "TIMER_WAIT")
    @Basic
    public long getTimerWait() {
        return timerWait;
    }

    public void setTimerWait(long timerWait) {
        this.timerWait = timerWait;
    }

    private long lockTime;

    @javax.persistence.Column(name = "LOCK_TIME")
    @Basic
    public long getLockTime() {
        return lockTime;
    }

    public void setLockTime(long lockTime) {
        this.lockTime = lockTime;
    }

    private String sqlText;

    @javax.persistence.Column(name = "SQL_TEXT")
    @Basic
    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    private String digest;

    @javax.persistence.Column(name = "DIGEST")
    @Basic
    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    private String digestText;

    @javax.persistence.Column(name = "DIGEST_TEXT")
    @Basic
    public String getDigestText() {
        return digestText;
    }

    public void setDigestText(String digestText) {
        this.digestText = digestText;
    }

    private String currentSchema;

    @javax.persistence.Column(name = "CURRENT_SCHEMA")
    @Basic
    public String getCurrentSchema() {
        return currentSchema;
    }

    public void setCurrentSchema(String currentSchema) {
        this.currentSchema = currentSchema;
    }

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

    private long objectInstanceBegin;

    @javax.persistence.Column(name = "OBJECT_INSTANCE_BEGIN")
    @Basic
    public long getObjectInstanceBegin() {
        return objectInstanceBegin;
    }

    public void setObjectInstanceBegin(long objectInstanceBegin) {
        this.objectInstanceBegin = objectInstanceBegin;
    }

    private int mysqlErrno;

    @javax.persistence.Column(name = "MYSQL_ERRNO")
    @Basic
    public int getMysqlErrno() {
        return mysqlErrno;
    }

    public void setMysqlErrno(int mysqlErrno) {
        this.mysqlErrno = mysqlErrno;
    }

    private String returnedSqlstate;

    @javax.persistence.Column(name = "RETURNED_SQLSTATE")
    @Basic
    public String getReturnedSqlstate() {
        return returnedSqlstate;
    }

    public void setReturnedSqlstate(String returnedSqlstate) {
        this.returnedSqlstate = returnedSqlstate;
    }

    private String messageText;

    @javax.persistence.Column(name = "MESSAGE_TEXT")
    @Basic
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    private long errors;

    @javax.persistence.Column(name = "ERRORS")
    @Basic
    public long getErrors() {
        return errors;
    }

    public void setErrors(long errors) {
        this.errors = errors;
    }

    private long warnings;

    @javax.persistence.Column(name = "WARNINGS")
    @Basic
    public long getWarnings() {
        return warnings;
    }

    public void setWarnings(long warnings) {
        this.warnings = warnings;
    }

    private long rowsAffected;

    @javax.persistence.Column(name = "ROWS_AFFECTED")
    @Basic
    public long getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(long rowsAffected) {
        this.rowsAffected = rowsAffected;
    }

    private long rowsSent;

    @javax.persistence.Column(name = "ROWS_SENT")
    @Basic
    public long getRowsSent() {
        return rowsSent;
    }

    public void setRowsSent(long rowsSent) {
        this.rowsSent = rowsSent;
    }

    private long rowsExamined;

    @javax.persistence.Column(name = "ROWS_EXAMINED")
    @Basic
    public long getRowsExamined() {
        return rowsExamined;
    }

    public void setRowsExamined(long rowsExamined) {
        this.rowsExamined = rowsExamined;
    }

    private long createdTmpDiskTables;

    @javax.persistence.Column(name = "CREATED_TMP_DISK_TABLES")
    @Basic
    public long getCreatedTmpDiskTables() {
        return createdTmpDiskTables;
    }

    public void setCreatedTmpDiskTables(long createdTmpDiskTables) {
        this.createdTmpDiskTables = createdTmpDiskTables;
    }

    private long createdTmpTables;

    @javax.persistence.Column(name = "CREATED_TMP_TABLES")
    @Basic
    public long getCreatedTmpTables() {
        return createdTmpTables;
    }

    public void setCreatedTmpTables(long createdTmpTables) {
        this.createdTmpTables = createdTmpTables;
    }

    private long selectFullJoin;

    @javax.persistence.Column(name = "SELECT_FULL_JOIN")
    @Basic
    public long getSelectFullJoin() {
        return selectFullJoin;
    }

    public void setSelectFullJoin(long selectFullJoin) {
        this.selectFullJoin = selectFullJoin;
    }

    private long selectFullRangeJoin;

    @javax.persistence.Column(name = "SELECT_FULL_RANGE_JOIN")
    @Basic
    public long getSelectFullRangeJoin() {
        return selectFullRangeJoin;
    }

    public void setSelectFullRangeJoin(long selectFullRangeJoin) {
        this.selectFullRangeJoin = selectFullRangeJoin;
    }

    private long selectRange;

    @javax.persistence.Column(name = "SELECT_RANGE")
    @Basic
    public long getSelectRange() {
        return selectRange;
    }

    public void setSelectRange(long selectRange) {
        this.selectRange = selectRange;
    }

    private long selectRangeCheck;

    @javax.persistence.Column(name = "SELECT_RANGE_CHECK")
    @Basic
    public long getSelectRangeCheck() {
        return selectRangeCheck;
    }

    public void setSelectRangeCheck(long selectRangeCheck) {
        this.selectRangeCheck = selectRangeCheck;
    }

    private long selectScan;

    @javax.persistence.Column(name = "SELECT_SCAN")
    @Basic
    public long getSelectScan() {
        return selectScan;
    }

    public void setSelectScan(long selectScan) {
        this.selectScan = selectScan;
    }

    private long sortMergePasses;

    @javax.persistence.Column(name = "SORT_MERGE_PASSES")
    @Basic
    public long getSortMergePasses() {
        return sortMergePasses;
    }

    public void setSortMergePasses(long sortMergePasses) {
        this.sortMergePasses = sortMergePasses;
    }

    private long sortRange;

    @javax.persistence.Column(name = "SORT_RANGE")
    @Basic
    public long getSortRange() {
        return sortRange;
    }

    public void setSortRange(long sortRange) {
        this.sortRange = sortRange;
    }

    private long sortRows;

    @javax.persistence.Column(name = "SORT_ROWS")
    @Basic
    public long getSortRows() {
        return sortRows;
    }

    public void setSortRows(long sortRows) {
        this.sortRows = sortRows;
    }

    private long sortScan;

    @javax.persistence.Column(name = "SORT_SCAN")
    @Basic
    public long getSortScan() {
        return sortScan;
    }

    public void setSortScan(long sortScan) {
        this.sortScan = sortScan;
    }

    private long noIndexUsed;

    @javax.persistence.Column(name = "NO_INDEX_USED")
    @Basic
    public long getNoIndexUsed() {
        return noIndexUsed;
    }

    public void setNoIndexUsed(long noIndexUsed) {
        this.noIndexUsed = noIndexUsed;
    }

    private long noGoodIndexUsed;

    @javax.persistence.Column(name = "NO_GOOD_INDEX_USED")
    @Basic
    public long getNoGoodIndexUsed() {
        return noGoodIndexUsed;
    }

    public void setNoGoodIndexUsed(long noGoodIndexUsed) {
        this.noGoodIndexUsed = noGoodIndexUsed;
    }

    private long nestingEventId;

    @javax.persistence.Column(name = "NESTING_EVENT_ID")
    @Basic
    public long getNestingEventId() {
        return nestingEventId;
    }

    public void setNestingEventId(long nestingEventId) {
        this.nestingEventId = nestingEventId;
    }

    private String nestingEventType;

    @javax.persistence.Column(name = "NESTING_EVENT_TYPE")
    @Basic
    public String getNestingEventType() {
        return nestingEventType;
    }

    public void setNestingEventType(String nestingEventType) {
        this.nestingEventType = nestingEventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsStatementsHistoryEntity that = (EventsStatementsHistoryEntity) o;

        if (createdTmpDiskTables != that.createdTmpDiskTables) return false;
        if (createdTmpTables != that.createdTmpTables) return false;
        if (endEventId != that.endEventId) return false;
        if (errors != that.errors) return false;
        if (eventId != that.eventId) return false;
        if (lockTime != that.lockTime) return false;
        if (mysqlErrno != that.mysqlErrno) return false;
        if (nestingEventId != that.nestingEventId) return false;
        if (noGoodIndexUsed != that.noGoodIndexUsed) return false;
        if (noIndexUsed != that.noIndexUsed) return false;
        if (objectInstanceBegin != that.objectInstanceBegin) return false;
        if (rowsAffected != that.rowsAffected) return false;
        if (rowsExamined != that.rowsExamined) return false;
        if (rowsSent != that.rowsSent) return false;
        if (selectFullJoin != that.selectFullJoin) return false;
        if (selectFullRangeJoin != that.selectFullRangeJoin) return false;
        if (selectRange != that.selectRange) return false;
        if (selectRangeCheck != that.selectRangeCheck) return false;
        if (selectScan != that.selectScan) return false;
        if (sortMergePasses != that.sortMergePasses) return false;
        if (sortRange != that.sortRange) return false;
        if (sortRows != that.sortRows) return false;
        if (sortScan != that.sortScan) return false;
        if (threadId != that.threadId) return false;
        if (timerEnd != that.timerEnd) return false;
        if (timerStart != that.timerStart) return false;
        if (timerWait != that.timerWait) return false;
        if (warnings != that.warnings) return false;
        if (currentSchema != null ? !currentSchema.equals(that.currentSchema) : that.currentSchema != null)
            return false;
        if (digest != null ? !digest.equals(that.digest) : that.digest != null) return false;
        if (digestText != null ? !digestText.equals(that.digestText) : that.digestText != null) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (messageText != null ? !messageText.equals(that.messageText) : that.messageText != null) return false;
        if (nestingEventType != null ? !nestingEventType.equals(that.nestingEventType) : that.nestingEventType != null)
            return false;
        if (objectName != null ? !objectName.equals(that.objectName) : that.objectName != null) return false;
        if (objectSchema != null ? !objectSchema.equals(that.objectSchema) : that.objectSchema != null) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (returnedSqlstate != null ? !returnedSqlstate.equals(that.returnedSqlstate) : that.returnedSqlstate != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (sqlText != null ? !sqlText.equals(that.sqlText) : that.sqlText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (int) (endEventId ^ (endEventId >>> 32));
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (int) (timerStart ^ (timerStart >>> 32));
        result = 31 * result + (int) (timerEnd ^ (timerEnd >>> 32));
        result = 31 * result + (int) (timerWait ^ (timerWait >>> 32));
        result = 31 * result + (int) (lockTime ^ (lockTime >>> 32));
        result = 31 * result + (sqlText != null ? sqlText.hashCode() : 0);
        result = 31 * result + (digest != null ? digest.hashCode() : 0);
        result = 31 * result + (digestText != null ? digestText.hashCode() : 0);
        result = 31 * result + (currentSchema != null ? currentSchema.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (objectSchema != null ? objectSchema.hashCode() : 0);
        result = 31 * result + (objectName != null ? objectName.hashCode() : 0);
        result = 31 * result + (int) (objectInstanceBegin ^ (objectInstanceBegin >>> 32));
        result = 31 * result + mysqlErrno;
        result = 31 * result + (returnedSqlstate != null ? returnedSqlstate.hashCode() : 0);
        result = 31 * result + (messageText != null ? messageText.hashCode() : 0);
        result = 31 * result + (int) (errors ^ (errors >>> 32));
        result = 31 * result + (int) (warnings ^ (warnings >>> 32));
        result = 31 * result + (int) (rowsAffected ^ (rowsAffected >>> 32));
        result = 31 * result + (int) (rowsSent ^ (rowsSent >>> 32));
        result = 31 * result + (int) (rowsExamined ^ (rowsExamined >>> 32));
        result = 31 * result + (int) (createdTmpDiskTables ^ (createdTmpDiskTables >>> 32));
        result = 31 * result + (int) (createdTmpTables ^ (createdTmpTables >>> 32));
        result = 31 * result + (int) (selectFullJoin ^ (selectFullJoin >>> 32));
        result = 31 * result + (int) (selectFullRangeJoin ^ (selectFullRangeJoin >>> 32));
        result = 31 * result + (int) (selectRange ^ (selectRange >>> 32));
        result = 31 * result + (int) (selectRangeCheck ^ (selectRangeCheck >>> 32));
        result = 31 * result + (int) (selectScan ^ (selectScan >>> 32));
        result = 31 * result + (int) (sortMergePasses ^ (sortMergePasses >>> 32));
        result = 31 * result + (int) (sortRange ^ (sortRange >>> 32));
        result = 31 * result + (int) (sortRows ^ (sortRows >>> 32));
        result = 31 * result + (int) (sortScan ^ (sortScan >>> 32));
        result = 31 * result + (int) (noIndexUsed ^ (noIndexUsed >>> 32));
        result = 31 * result + (int) (noGoodIndexUsed ^ (noGoodIndexUsed >>> 32));
        result = 31 * result + (int) (nestingEventId ^ (nestingEventId >>> 32));
        result = 31 * result + (nestingEventType != null ? nestingEventType.hashCode() : 0);
        return result;
    }
}
