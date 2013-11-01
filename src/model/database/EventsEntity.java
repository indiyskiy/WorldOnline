package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "EVENTS", schema = "", catalog = "information_schema")
@Entity
public class EventsEntity {
    private String eventCatalog;

    @javax.persistence.Column(name = "EVENT_CATALOG")
    @Basic
    public String getEventCatalog() {
        return eventCatalog;
    }

    public void setEventCatalog(String eventCatalog) {
        this.eventCatalog = eventCatalog;
    }

    private String eventSchema;

    @javax.persistence.Column(name = "EVENT_SCHEMA")
    @Basic
    public String getEventSchema() {
        return eventSchema;
    }

    public void setEventSchema(String eventSchema) {
        this.eventSchema = eventSchema;
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

    private String definer;

    @javax.persistence.Column(name = "DEFINER")
    @Basic
    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    private String timeZone;

    @javax.persistence.Column(name = "TIME_ZONE")
    @Basic
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private String eventBody;

    @javax.persistence.Column(name = "EVENT_BODY")
    @Basic
    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    private String eventDefinition;

    @javax.persistence.Column(name = "EVENT_DEFINITION")
    @Basic
    public String getEventDefinition() {
        return eventDefinition;
    }

    public void setEventDefinition(String eventDefinition) {
        this.eventDefinition = eventDefinition;
    }

    private String eventType;

    @javax.persistence.Column(name = "EVENT_TYPE")
    @Basic
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    private Timestamp executeAt;

    @javax.persistence.Column(name = "EXECUTE_AT")
    @Basic
    public Timestamp getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Timestamp executeAt) {
        this.executeAt = executeAt;
    }

    private String intervalValue;

    @javax.persistence.Column(name = "INTERVAL_VALUE")
    @Basic
    public String getIntervalValue() {
        return intervalValue;
    }

    public void setIntervalValue(String intervalValue) {
        this.intervalValue = intervalValue;
    }

    private String intervalField;

    @javax.persistence.Column(name = "INTERVAL_FIELD")
    @Basic
    public String getIntervalField() {
        return intervalField;
    }

    public void setIntervalField(String intervalField) {
        this.intervalField = intervalField;
    }

    private String sqlMode;

    @javax.persistence.Column(name = "SQL_MODE")
    @Basic
    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
    }

    private Timestamp starts;

    @javax.persistence.Column(name = "STARTS")
    @Basic
    public Timestamp getStarts() {
        return starts;
    }

    public void setStarts(Timestamp starts) {
        this.starts = starts;
    }

    private Timestamp ends;

    @javax.persistence.Column(name = "ENDS")
    @Basic
    public Timestamp getEnds() {
        return ends;
    }

    public void setEnds(Timestamp ends) {
        this.ends = ends;
    }

    private String status;

    @javax.persistence.Column(name = "STATUS")
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String onCompletion;

    @javax.persistence.Column(name = "ON_COMPLETION")
    @Basic
    public String getOnCompletion() {
        return onCompletion;
    }

    public void setOnCompletion(String onCompletion) {
        this.onCompletion = onCompletion;
    }

    private Timestamp created;

    @javax.persistence.Column(name = "CREATED")
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private Timestamp lastAltered;

    @javax.persistence.Column(name = "LAST_ALTERED")
    @Basic
    public Timestamp getLastAltered() {
        return lastAltered;
    }

    public void setLastAltered(Timestamp lastAltered) {
        this.lastAltered = lastAltered;
    }

    private Timestamp lastExecuted;

    @javax.persistence.Column(name = "LAST_EXECUTED")
    @Basic
    public Timestamp getLastExecuted() {
        return lastExecuted;
    }

    public void setLastExecuted(Timestamp lastExecuted) {
        this.lastExecuted = lastExecuted;
    }

    private String eventComment;

    @javax.persistence.Column(name = "EVENT_COMMENT")
    @Basic
    public String getEventComment() {
        return eventComment;
    }

    public void setEventComment(String eventComment) {
        this.eventComment = eventComment;
    }

    private long originator;

    @javax.persistence.Column(name = "ORIGINATOR")
    @Basic
    public long getOriginator() {
        return originator;
    }

    public void setOriginator(long originator) {
        this.originator = originator;
    }

    private String characterSetClient;

    @javax.persistence.Column(name = "CHARACTER_SET_CLIENT")
    @Basic
    public String getCharacterSetClient() {
        return characterSetClient;
    }

    public void setCharacterSetClient(String characterSetClient) {
        this.characterSetClient = characterSetClient;
    }

    private String collationConnection;

    @javax.persistence.Column(name = "COLLATION_CONNECTION")
    @Basic
    public String getCollationConnection() {
        return collationConnection;
    }

    public void setCollationConnection(String collationConnection) {
        this.collationConnection = collationConnection;
    }

    private String databaseCollation;

    @javax.persistence.Column(name = "DATABASE_COLLATION")
    @Basic
    public String getDatabaseCollation() {
        return databaseCollation;
    }

    public void setDatabaseCollation(String databaseCollation) {
        this.databaseCollation = databaseCollation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsEntity that = (EventsEntity) o;

        if (originator != that.originator) return false;
        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (databaseCollation != null ? !databaseCollation.equals(that.databaseCollation) : that.databaseCollation != null)
            return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (ends != null ? !ends.equals(that.ends) : that.ends != null) return false;
        if (eventBody != null ? !eventBody.equals(that.eventBody) : that.eventBody != null) return false;
        if (eventCatalog != null ? !eventCatalog.equals(that.eventCatalog) : that.eventCatalog != null) return false;
        if (eventComment != null ? !eventComment.equals(that.eventComment) : that.eventComment != null) return false;
        if (eventDefinition != null ? !eventDefinition.equals(that.eventDefinition) : that.eventDefinition != null)
            return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (eventSchema != null ? !eventSchema.equals(that.eventSchema) : that.eventSchema != null) return false;
        if (eventType != null ? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (executeAt != null ? !executeAt.equals(that.executeAt) : that.executeAt != null) return false;
        if (intervalField != null ? !intervalField.equals(that.intervalField) : that.intervalField != null)
            return false;
        if (intervalValue != null ? !intervalValue.equals(that.intervalValue) : that.intervalValue != null)
            return false;
        if (lastAltered != null ? !lastAltered.equals(that.lastAltered) : that.lastAltered != null) return false;
        if (lastExecuted != null ? !lastExecuted.equals(that.lastExecuted) : that.lastExecuted != null) return false;
        if (onCompletion != null ? !onCompletion.equals(that.onCompletion) : that.onCompletion != null) return false;
        if (sqlMode != null ? !sqlMode.equals(that.sqlMode) : that.sqlMode != null) return false;
        if (starts != null ? !starts.equals(that.starts) : that.starts != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (timeZone != null ? !timeZone.equals(that.timeZone) : that.timeZone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventCatalog != null ? eventCatalog.hashCode() : 0;
        result = 31 * result + (eventSchema != null ? eventSchema.hashCode() : 0);
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (eventBody != null ? eventBody.hashCode() : 0);
        result = 31 * result + (eventDefinition != null ? eventDefinition.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (executeAt != null ? executeAt.hashCode() : 0);
        result = 31 * result + (intervalValue != null ? intervalValue.hashCode() : 0);
        result = 31 * result + (intervalField != null ? intervalField.hashCode() : 0);
        result = 31 * result + (sqlMode != null ? sqlMode.hashCode() : 0);
        result = 31 * result + (starts != null ? starts.hashCode() : 0);
        result = 31 * result + (ends != null ? ends.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (onCompletion != null ? onCompletion.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastAltered != null ? lastAltered.hashCode() : 0);
        result = 31 * result + (lastExecuted != null ? lastExecuted.hashCode() : 0);
        result = 31 * result + (eventComment != null ? eventComment.hashCode() : 0);
        result = 31 * result + (int) (originator ^ (originator >>> 32));
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        result = 31 * result + (databaseCollation != null ? databaseCollation.hashCode() : 0);
        return result;
    }
}
