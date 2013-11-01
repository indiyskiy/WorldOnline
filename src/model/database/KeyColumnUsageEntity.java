package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "KEY_COLUMN_USAGE", schema = "", catalog = "information_schema")
@Entity
public class KeyColumnUsageEntity {
    private String constraintCatalog;

    @javax.persistence.Column(name = "CONSTRAINT_CATALOG")
    @Basic
    public String getConstraintCatalog() {
        return constraintCatalog;
    }

    public void setConstraintCatalog(String constraintCatalog) {
        this.constraintCatalog = constraintCatalog;
    }

    private String constraintSchema;

    @javax.persistence.Column(name = "CONSTRAINT_SCHEMA")
    @Basic
    public String getConstraintSchema() {
        return constraintSchema;
    }

    public void setConstraintSchema(String constraintSchema) {
        this.constraintSchema = constraintSchema;
    }

    private String constraintName;

    @javax.persistence.Column(name = "CONSTRAINT_NAME")
    @Basic
    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    private String tableCatalog;

    @javax.persistence.Column(name = "TABLE_CATALOG")
    @Basic
    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    private String tableSchema;

    @javax.persistence.Column(name = "TABLE_SCHEMA")
    @Basic
    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    private String tableName;

    @javax.persistence.Column(name = "TABLE_NAME")
    @Basic
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String columnName;

    @javax.persistence.Column(name = "COLUMN_NAME")
    @Basic
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    private long ordinalPosition;

    @javax.persistence.Column(name = "ORDINAL_POSITION")
    @Basic
    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    private long positionInUniqueConstraint;

    @javax.persistence.Column(name = "POSITION_IN_UNIQUE_CONSTRAINT")
    @Basic
    public long getPositionInUniqueConstraint() {
        return positionInUniqueConstraint;
    }

    public void setPositionInUniqueConstraint(long positionInUniqueConstraint) {
        this.positionInUniqueConstraint = positionInUniqueConstraint;
    }

    private String referencedTableSchema;

    @javax.persistence.Column(name = "REFERENCED_TABLE_SCHEMA")
    @Basic
    public String getReferencedTableSchema() {
        return referencedTableSchema;
    }

    public void setReferencedTableSchema(String referencedTableSchema) {
        this.referencedTableSchema = referencedTableSchema;
    }

    private String referencedTableName;

    @javax.persistence.Column(name = "REFERENCED_TABLE_NAME")
    @Basic
    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    private String referencedColumnName;

    @javax.persistence.Column(name = "REFERENCED_COLUMN_NAME")
    @Basic
    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyColumnUsageEntity that = (KeyColumnUsageEntity) o;

        if (ordinalPosition != that.ordinalPosition) return false;
        if (positionInUniqueConstraint != that.positionInUniqueConstraint) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (constraintCatalog != null ? !constraintCatalog.equals(that.constraintCatalog) : that.constraintCatalog != null)
            return false;
        if (constraintName != null ? !constraintName.equals(that.constraintName) : that.constraintName != null)
            return false;
        if (constraintSchema != null ? !constraintSchema.equals(that.constraintSchema) : that.constraintSchema != null)
            return false;
        if (referencedColumnName != null ? !referencedColumnName.equals(that.referencedColumnName) : that.referencedColumnName != null)
            return false;
        if (referencedTableName != null ? !referencedTableName.equals(that.referencedTableName) : that.referencedTableName != null)
            return false;
        if (referencedTableSchema != null ? !referencedTableSchema.equals(that.referencedTableSchema) : that.referencedTableSchema != null)
            return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = constraintCatalog != null ? constraintCatalog.hashCode() : 0;
        result = 31 * result + (constraintSchema != null ? constraintSchema.hashCode() : 0);
        result = 31 * result + (constraintName != null ? constraintName.hashCode() : 0);
        result = 31 * result + (tableCatalog != null ? tableCatalog.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (int) (ordinalPosition ^ (ordinalPosition >>> 32));
        result = 31 * result + (int) (positionInUniqueConstraint ^ (positionInUniqueConstraint >>> 32));
        result = 31 * result + (referencedTableSchema != null ? referencedTableSchema.hashCode() : 0);
        result = 31 * result + (referencedTableName != null ? referencedTableName.hashCode() : 0);
        result = 31 * result + (referencedColumnName != null ? referencedColumnName.hashCode() : 0);
        return result;
    }
}
