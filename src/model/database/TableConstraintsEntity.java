package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "TABLE_CONSTRAINTS", schema = "", catalog = "information_schema")
@Entity
public class TableConstraintsEntity {
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

    private String constraintType;

    @javax.persistence.Column(name = "CONSTRAINT_TYPE")
    @Basic
    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableConstraintsEntity that = (TableConstraintsEntity) o;

        if (constraintCatalog != null ? !constraintCatalog.equals(that.constraintCatalog) : that.constraintCatalog != null)
            return false;
        if (constraintName != null ? !constraintName.equals(that.constraintName) : that.constraintName != null)
            return false;
        if (constraintSchema != null ? !constraintSchema.equals(that.constraintSchema) : that.constraintSchema != null)
            return false;
        if (constraintType != null ? !constraintType.equals(that.constraintType) : that.constraintType != null)
            return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = constraintCatalog != null ? constraintCatalog.hashCode() : 0;
        result = 31 * result + (constraintSchema != null ? constraintSchema.hashCode() : 0);
        result = 31 * result + (constraintName != null ? constraintName.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (constraintType != null ? constraintType.hashCode() : 0);
        return result;
    }
}
