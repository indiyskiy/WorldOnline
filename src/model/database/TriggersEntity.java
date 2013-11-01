package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "TRIGGERS", schema = "", catalog = "information_schema")
@Entity
public class TriggersEntity {
    private String triggerCatalog;

    @javax.persistence.Column(name = "TRIGGER_CATALOG")
    @Basic
    public String getTriggerCatalog() {
        return triggerCatalog;
    }

    public void setTriggerCatalog(String triggerCatalog) {
        this.triggerCatalog = triggerCatalog;
    }

    private String triggerSchema;

    @javax.persistence.Column(name = "TRIGGER_SCHEMA")
    @Basic
    public String getTriggerSchema() {
        return triggerSchema;
    }

    public void setTriggerSchema(String triggerSchema) {
        this.triggerSchema = triggerSchema;
    }

    private String triggerName;

    @javax.persistence.Column(name = "TRIGGER_NAME")
    @Basic
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    private String eventManipulation;

    @javax.persistence.Column(name = "EVENT_MANIPULATION")
    @Basic
    public String getEventManipulation() {
        return eventManipulation;
    }

    public void setEventManipulation(String eventManipulation) {
        this.eventManipulation = eventManipulation;
    }

    private String eventObjectCatalog;

    @javax.persistence.Column(name = "EVENT_OBJECT_CATALOG")
    @Basic
    public String getEventObjectCatalog() {
        return eventObjectCatalog;
    }

    public void setEventObjectCatalog(String eventObjectCatalog) {
        this.eventObjectCatalog = eventObjectCatalog;
    }

    private String eventObjectSchema;

    @javax.persistence.Column(name = "EVENT_OBJECT_SCHEMA")
    @Basic
    public String getEventObjectSchema() {
        return eventObjectSchema;
    }

    public void setEventObjectSchema(String eventObjectSchema) {
        this.eventObjectSchema = eventObjectSchema;
    }

    private String eventObjectTable;

    @javax.persistence.Column(name = "EVENT_OBJECT_TABLE")
    @Basic
    public String getEventObjectTable() {
        return eventObjectTable;
    }

    public void setEventObjectTable(String eventObjectTable) {
        this.eventObjectTable = eventObjectTable;
    }

    private long actionOrder;

    @javax.persistence.Column(name = "ACTION_ORDER")
    @Basic
    public long getActionOrder() {
        return actionOrder;
    }

    public void setActionOrder(long actionOrder) {
        this.actionOrder = actionOrder;
    }

    private String actionCondition;

    @javax.persistence.Column(name = "ACTION_CONDITION")
    @Basic
    public String getActionCondition() {
        return actionCondition;
    }

    public void setActionCondition(String actionCondition) {
        this.actionCondition = actionCondition;
    }

    private String actionStatement;

    @javax.persistence.Column(name = "ACTION_STATEMENT")
    @Basic
    public String getActionStatement() {
        return actionStatement;
    }

    public void setActionStatement(String actionStatement) {
        this.actionStatement = actionStatement;
    }

    private String actionOrientation;

    @javax.persistence.Column(name = "ACTION_ORIENTATION")
    @Basic
    public String getActionOrientation() {
        return actionOrientation;
    }

    public void setActionOrientation(String actionOrientation) {
        this.actionOrientation = actionOrientation;
    }

    private String actionTiming;

    @javax.persistence.Column(name = "ACTION_TIMING")
    @Basic
    public String getActionTiming() {
        return actionTiming;
    }

    public void setActionTiming(String actionTiming) {
        this.actionTiming = actionTiming;
    }

    private String actionReferenceOldTable;

    @javax.persistence.Column(name = "ACTION_REFERENCE_OLD_TABLE")
    @Basic
    public String getActionReferenceOldTable() {
        return actionReferenceOldTable;
    }

    public void setActionReferenceOldTable(String actionReferenceOldTable) {
        this.actionReferenceOldTable = actionReferenceOldTable;
    }

    private String actionReferenceNewTable;

    @javax.persistence.Column(name = "ACTION_REFERENCE_NEW_TABLE")
    @Basic
    public String getActionReferenceNewTable() {
        return actionReferenceNewTable;
    }

    public void setActionReferenceNewTable(String actionReferenceNewTable) {
        this.actionReferenceNewTable = actionReferenceNewTable;
    }

    private String actionReferenceOldRow;

    @javax.persistence.Column(name = "ACTION_REFERENCE_OLD_ROW")
    @Basic
    public String getActionReferenceOldRow() {
        return actionReferenceOldRow;
    }

    public void setActionReferenceOldRow(String actionReferenceOldRow) {
        this.actionReferenceOldRow = actionReferenceOldRow;
    }

    private String actionReferenceNewRow;

    @javax.persistence.Column(name = "ACTION_REFERENCE_NEW_ROW")
    @Basic
    public String getActionReferenceNewRow() {
        return actionReferenceNewRow;
    }

    public void setActionReferenceNewRow(String actionReferenceNewRow) {
        this.actionReferenceNewRow = actionReferenceNewRow;
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

    private String sqlMode;

    @javax.persistence.Column(name = "SQL_MODE")
    @Basic
    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
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

        TriggersEntity that = (TriggersEntity) o;

        if (actionOrder != that.actionOrder) return false;
        if (actionCondition != null ? !actionCondition.equals(that.actionCondition) : that.actionCondition != null)
            return false;
        if (actionOrientation != null ? !actionOrientation.equals(that.actionOrientation) : that.actionOrientation != null)
            return false;
        if (actionReferenceNewRow != null ? !actionReferenceNewRow.equals(that.actionReferenceNewRow) : that.actionReferenceNewRow != null)
            return false;
        if (actionReferenceNewTable != null ? !actionReferenceNewTable.equals(that.actionReferenceNewTable) : that.actionReferenceNewTable != null)
            return false;
        if (actionReferenceOldRow != null ? !actionReferenceOldRow.equals(that.actionReferenceOldRow) : that.actionReferenceOldRow != null)
            return false;
        if (actionReferenceOldTable != null ? !actionReferenceOldTable.equals(that.actionReferenceOldTable) : that.actionReferenceOldTable != null)
            return false;
        if (actionStatement != null ? !actionStatement.equals(that.actionStatement) : that.actionStatement != null)
            return false;
        if (actionTiming != null ? !actionTiming.equals(that.actionTiming) : that.actionTiming != null) return false;
        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (databaseCollation != null ? !databaseCollation.equals(that.databaseCollation) : that.databaseCollation != null)
            return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (eventManipulation != null ? !eventManipulation.equals(that.eventManipulation) : that.eventManipulation != null)
            return false;
        if (eventObjectCatalog != null ? !eventObjectCatalog.equals(that.eventObjectCatalog) : that.eventObjectCatalog != null)
            return false;
        if (eventObjectSchema != null ? !eventObjectSchema.equals(that.eventObjectSchema) : that.eventObjectSchema != null)
            return false;
        if (eventObjectTable != null ? !eventObjectTable.equals(that.eventObjectTable) : that.eventObjectTable != null)
            return false;
        if (sqlMode != null ? !sqlMode.equals(that.sqlMode) : that.sqlMode != null) return false;
        if (triggerCatalog != null ? !triggerCatalog.equals(that.triggerCatalog) : that.triggerCatalog != null)
            return false;
        if (triggerName != null ? !triggerName.equals(that.triggerName) : that.triggerName != null) return false;
        if (triggerSchema != null ? !triggerSchema.equals(that.triggerSchema) : that.triggerSchema != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = triggerCatalog != null ? triggerCatalog.hashCode() : 0;
        result = 31 * result + (triggerSchema != null ? triggerSchema.hashCode() : 0);
        result = 31 * result + (triggerName != null ? triggerName.hashCode() : 0);
        result = 31 * result + (eventManipulation != null ? eventManipulation.hashCode() : 0);
        result = 31 * result + (eventObjectCatalog != null ? eventObjectCatalog.hashCode() : 0);
        result = 31 * result + (eventObjectSchema != null ? eventObjectSchema.hashCode() : 0);
        result = 31 * result + (eventObjectTable != null ? eventObjectTable.hashCode() : 0);
        result = 31 * result + (int) (actionOrder ^ (actionOrder >>> 32));
        result = 31 * result + (actionCondition != null ? actionCondition.hashCode() : 0);
        result = 31 * result + (actionStatement != null ? actionStatement.hashCode() : 0);
        result = 31 * result + (actionOrientation != null ? actionOrientation.hashCode() : 0);
        result = 31 * result + (actionTiming != null ? actionTiming.hashCode() : 0);
        result = 31 * result + (actionReferenceOldTable != null ? actionReferenceOldTable.hashCode() : 0);
        result = 31 * result + (actionReferenceNewTable != null ? actionReferenceNewTable.hashCode() : 0);
        result = 31 * result + (actionReferenceOldRow != null ? actionReferenceOldRow.hashCode() : 0);
        result = 31 * result + (actionReferenceNewRow != null ? actionReferenceNewRow.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (sqlMode != null ? sqlMode.hashCode() : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        result = 31 * result + (databaseCollation != null ? databaseCollation.hashCode() : 0);
        return result;
    }
}
