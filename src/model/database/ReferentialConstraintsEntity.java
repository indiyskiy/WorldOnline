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
@javax.persistence.Table(name = "REFERENTIAL_CONSTRAINTS", schema = "", catalog = "information_schema")
@Entity
public class ReferentialConstraintsEntity {
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

    private String uniqueConstraintCatalog;

    @javax.persistence.Column(name = "UNIQUE_CONSTRAINT_CATALOG")
    @Basic
    public String getUniqueConstraintCatalog() {
        return uniqueConstraintCatalog;
    }

    public void setUniqueConstraintCatalog(String uniqueConstraintCatalog) {
        this.uniqueConstraintCatalog = uniqueConstraintCatalog;
    }

    private String uniqueConstraintSchema;

    @javax.persistence.Column(name = "UNIQUE_CONSTRAINT_SCHEMA")
    @Basic
    public String getUniqueConstraintSchema() {
        return uniqueConstraintSchema;
    }

    public void setUniqueConstraintSchema(String uniqueConstraintSchema) {
        this.uniqueConstraintSchema = uniqueConstraintSchema;
    }

    private String uniqueConstraintName;

    @javax.persistence.Column(name = "UNIQUE_CONSTRAINT_NAME")
    @Basic
    public String getUniqueConstraintName() {
        return uniqueConstraintName;
    }

    public void setUniqueConstraintName(String uniqueConstraintName) {
        this.uniqueConstraintName = uniqueConstraintName;
    }

    private String matchOption;

    @javax.persistence.Column(name = "MATCH_OPTION")
    @Basic
    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    private String updateRule;

    @javax.persistence.Column(name = "UPDATE_RULE")
    @Basic
    public String getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(String updateRule) {
        this.updateRule = updateRule;
    }

    private String deleteRule;

    @javax.persistence.Column(name = "DELETE_RULE")
    @Basic
    public String getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(String deleteRule) {
        this.deleteRule = deleteRule;
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

    private String referencedTableName;

    @javax.persistence.Column(name = "REFERENCED_TABLE_NAME")
    @Basic
    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferentialConstraintsEntity that = (ReferentialConstraintsEntity) o;

        if (constraintCatalog != null ? !constraintCatalog.equals(that.constraintCatalog) : that.constraintCatalog != null)
            return false;
        if (constraintName != null ? !constraintName.equals(that.constraintName) : that.constraintName != null)
            return false;
        if (constraintSchema != null ? !constraintSchema.equals(that.constraintSchema) : that.constraintSchema != null)
            return false;
        if (deleteRule != null ? !deleteRule.equals(that.deleteRule) : that.deleteRule != null) return false;
        if (matchOption != null ? !matchOption.equals(that.matchOption) : that.matchOption != null) return false;
        if (referencedTableName != null ? !referencedTableName.equals(that.referencedTableName) : that.referencedTableName != null)
            return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (uniqueConstraintCatalog != null ? !uniqueConstraintCatalog.equals(that.uniqueConstraintCatalog) : that.uniqueConstraintCatalog != null)
            return false;
        if (uniqueConstraintName != null ? !uniqueConstraintName.equals(that.uniqueConstraintName) : that.uniqueConstraintName != null)
            return false;
        if (uniqueConstraintSchema != null ? !uniqueConstraintSchema.equals(that.uniqueConstraintSchema) : that.uniqueConstraintSchema != null)
            return false;
        if (updateRule != null ? !updateRule.equals(that.updateRule) : that.updateRule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = constraintCatalog != null ? constraintCatalog.hashCode() : 0;
        result = 31 * result + (constraintSchema != null ? constraintSchema.hashCode() : 0);
        result = 31 * result + (constraintName != null ? constraintName.hashCode() : 0);
        result = 31 * result + (uniqueConstraintCatalog != null ? uniqueConstraintCatalog.hashCode() : 0);
        result = 31 * result + (uniqueConstraintSchema != null ? uniqueConstraintSchema.hashCode() : 0);
        result = 31 * result + (uniqueConstraintName != null ? uniqueConstraintName.hashCode() : 0);
        result = 31 * result + (matchOption != null ? matchOption.hashCode() : 0);
        result = 31 * result + (updateRule != null ? updateRule.hashCode() : 0);
        result = 31 * result + (deleteRule != null ? deleteRule.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (referencedTableName != null ? referencedTableName.hashCode() : 0);
        return result;
    }
}
