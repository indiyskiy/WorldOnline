package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "slow_log", schema = "", catalog = "mysql")
@Entity
public class SlowLogEntity {
    private Timestamp startTime;

    @javax.persistence.Column(name = "start_time")
    @Basic
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    private String userHost;

    @javax.persistence.Column(name = "user_host")
    @Basic
    public String getUserHost() {
        return userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    private Time queryTime;

    @javax.persistence.Column(name = "query_time")
    @Basic
    public Time getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Time queryTime) {
        this.queryTime = queryTime;
    }

    private Time lockTime;

    @javax.persistence.Column(name = "lock_time")
    @Basic
    public Time getLockTime() {
        return lockTime;
    }

    public void setLockTime(Time lockTime) {
        this.lockTime = lockTime;
    }

    private int rowsSent;

    @javax.persistence.Column(name = "rows_sent")
    @Basic
    public int getRowsSent() {
        return rowsSent;
    }

    public void setRowsSent(int rowsSent) {
        this.rowsSent = rowsSent;
    }

    private int rowsExamined;

    @javax.persistence.Column(name = "rows_examined")
    @Basic
    public int getRowsExamined() {
        return rowsExamined;
    }

    public void setRowsExamined(int rowsExamined) {
        this.rowsExamined = rowsExamined;
    }

    private String db;

    @javax.persistence.Column(name = "db")
    @Basic
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private int lastInsertId;

    @javax.persistence.Column(name = "last_insert_id")
    @Basic
    public int getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(int lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    private int insertId;

    @javax.persistence.Column(name = "insert_id")
    @Basic
    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    private int serverId;

    @javax.persistence.Column(name = "server_id")
    @Basic
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    private String sqlText;

    @javax.persistence.Column(name = "sql_text")
    @Basic
    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    private long threadId;

    @javax.persistence.Column(name = "thread_id")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlowLogEntity that = (SlowLogEntity) o;

        if (insertId != that.insertId) return false;
        if (lastInsertId != that.lastInsertId) return false;
        if (rowsExamined != that.rowsExamined) return false;
        if (rowsSent != that.rowsSent) return false;
        if (serverId != that.serverId) return false;
        if (threadId != that.threadId) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (lockTime != null ? !lockTime.equals(that.lockTime) : that.lockTime != null) return false;
        if (queryTime != null ? !queryTime.equals(that.queryTime) : that.queryTime != null) return false;
        if (sqlText != null ? !sqlText.equals(that.sqlText) : that.sqlText != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (userHost != null ? !userHost.equals(that.userHost) : that.userHost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startTime != null ? startTime.hashCode() : 0;
        result = 31 * result + (userHost != null ? userHost.hashCode() : 0);
        result = 31 * result + (queryTime != null ? queryTime.hashCode() : 0);
        result = 31 * result + (lockTime != null ? lockTime.hashCode() : 0);
        result = 31 * result + rowsSent;
        result = 31 * result + rowsExamined;
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + lastInsertId;
        result = 31 * result + insertId;
        result = 31 * result + serverId;
        result = 31 * result + (sqlText != null ? sqlText.hashCode() : 0);
        result = 31 * result + (int) (threadId ^ (threadId >>> 32));
        return result;
    }
}
