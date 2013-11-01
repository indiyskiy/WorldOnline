package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.EventEntityPK.class)
@javax.persistence.Table(name = "event", schema = "", catalog = "mysql")
@Entity
public class EventEntity {
    private String db;

    @javax.persistence.Column(name = "db")
    @Id
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private byte[] body;

    @javax.persistence.Column(name = "body")
    @Basic
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    private String definer;

    @javax.persistence.Column(name = "definer")
    @Basic
    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    private Timestamp executeAt;

    @javax.persistence.Column(name = "execute_at")
    @Basic
    public Timestamp getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Timestamp executeAt) {
        this.executeAt = executeAt;
    }

    private int intervalValue;

    @javax.persistence.Column(name = "interval_value")
    @Basic
    public int getIntervalValue() {
        return intervalValue;
    }

    public void setIntervalValue(int intervalValue) {
        this.intervalValue = intervalValue;
    }

    private String intervalField;

    @javax.persistence.Column(name = "interval_field")
    @Basic
    public String getIntervalField() {
        return intervalField;
    }

    public void setIntervalField(String intervalField) {
        this.intervalField = intervalField;
    }

    private Timestamp created;

    @javax.persistence.Column(name = "created")
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private Timestamp modified;

    @javax.persistence.Column(name = "modified")
    @Basic
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    private Timestamp lastExecuted;

    @javax.persistence.Column(name = "last_executed")
    @Basic
    public Timestamp getLastExecuted() {
        return lastExecuted;
    }

    public void setLastExecuted(Timestamp lastExecuted) {
        this.lastExecuted = lastExecuted;
    }

    private Timestamp starts;

    @javax.persistence.Column(name = "starts")
    @Basic
    public Timestamp getStarts() {
        return starts;
    }

    public void setStarts(Timestamp starts) {
        this.starts = starts;
    }

    private Timestamp ends;

    @javax.persistence.Column(name = "ends")
    @Basic
    public Timestamp getEnds() {
        return ends;
    }

    public void setEnds(Timestamp ends) {
        this.ends = ends;
    }

    private String status;

    @javax.persistence.Column(name = "status")
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String onCompletion;

    @javax.persistence.Column(name = "on_completion")
    @Basic
    public String getOnCompletion() {
        return onCompletion;
    }

    public void setOnCompletion(String onCompletion) {
        this.onCompletion = onCompletion;
    }

    private String sqlMode;

    @javax.persistence.Column(name = "sql_mode")
    @Basic
    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
    }

    private String comment;

    @javax.persistence.Column(name = "comment")
    @Basic
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private int originator;

    @javax.persistence.Column(name = "originator")
    @Basic
    public int getOriginator() {
        return originator;
    }

    public void setOriginator(int originator) {
        this.originator = originator;
    }

    private String timeZone;

    @javax.persistence.Column(name = "time_zone")
    @Basic
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private String characterSetClient;

    @javax.persistence.Column(name = "character_set_client")
    @Basic
    public String getCharacterSetClient() {
        return characterSetClient;
    }

    public void setCharacterSetClient(String characterSetClient) {
        this.characterSetClient = characterSetClient;
    }

    private String collationConnection;

    @javax.persistence.Column(name = "collation_connection")
    @Basic
    public String getCollationConnection() {
        return collationConnection;
    }

    public void setCollationConnection(String collationConnection) {
        this.collationConnection = collationConnection;
    }

    private String dbCollation;

    @javax.persistence.Column(name = "db_collation")
    @Basic
    public String getDbCollation() {
        return dbCollation;
    }

    public void setDbCollation(String dbCollation) {
        this.dbCollation = dbCollation;
    }

    private byte[] bodyUtf8;

    @javax.persistence.Column(name = "body_utf8")
    @Basic
    public byte[] getBodyUtf8() {
        return bodyUtf8;
    }

    public void setBodyUtf8(byte[] bodyUtf8) {
        this.bodyUtf8 = bodyUtf8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (intervalValue != that.intervalValue) return false;
        if (originator != that.originator) return false;
        if (!Arrays.equals(body, that.body)) return false;
        if (!Arrays.equals(bodyUtf8, that.bodyUtf8)) return false;
        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (dbCollation != null ? !dbCollation.equals(that.dbCollation) : that.dbCollation != null) return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (ends != null ? !ends.equals(that.ends) : that.ends != null) return false;
        if (executeAt != null ? !executeAt.equals(that.executeAt) : that.executeAt != null) return false;
        if (intervalField != null ? !intervalField.equals(that.intervalField) : that.intervalField != null)
            return false;
        if (lastExecuted != null ? !lastExecuted.equals(that.lastExecuted) : that.lastExecuted != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (onCompletion != null ? !onCompletion.equals(that.onCompletion) : that.onCompletion != null) return false;
        if (sqlMode != null ? !sqlMode.equals(that.sqlMode) : that.sqlMode != null) return false;
        if (starts != null ? !starts.equals(that.starts) : that.starts != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (timeZone != null ? !timeZone.equals(that.timeZone) : that.timeZone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = db != null ? db.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (body != null ? Arrays.hashCode(body) : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (executeAt != null ? executeAt.hashCode() : 0);
        result = 31 * result + intervalValue;
        result = 31 * result + (intervalField != null ? intervalField.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (lastExecuted != null ? lastExecuted.hashCode() : 0);
        result = 31 * result + (starts != null ? starts.hashCode() : 0);
        result = 31 * result + (ends != null ? ends.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (onCompletion != null ? onCompletion.hashCode() : 0);
        result = 31 * result + (sqlMode != null ? sqlMode.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + originator;
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        result = 31 * result + (dbCollation != null ? dbCollation.hashCode() : 0);
        result = 31 * result + (bodyUtf8 != null ? Arrays.hashCode(bodyUtf8) : 0);
        return result;
    }
}
