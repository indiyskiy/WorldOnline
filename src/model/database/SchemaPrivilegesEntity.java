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
@javax.persistence.Table(name = "SCHEMA_PRIVILEGES", schema = "", catalog = "information_schema")
@Entity
public class SchemaPrivilegesEntity {
    private String grantee;

    @javax.persistence.Column(name = "GRANTEE")
    @Basic
    public String getGrantee() {
        return grantee;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
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

    private String privilegeType;

    @javax.persistence.Column(name = "PRIVILEGE_TYPE")
    @Basic
    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    private String isGrantable;

    @javax.persistence.Column(name = "IS_GRANTABLE")
    @Basic
    public String getGrantable() {
        return isGrantable;
    }

    public void setGrantable(String grantable) {
        isGrantable = grantable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchemaPrivilegesEntity that = (SchemaPrivilegesEntity) o;

        if (grantee != null ? !grantee.equals(that.grantee) : that.grantee != null) return false;
        if (isGrantable != null ? !isGrantable.equals(that.isGrantable) : that.isGrantable != null) return false;
        if (privilegeType != null ? !privilegeType.equals(that.privilegeType) : that.privilegeType != null)
            return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = grantee != null ? grantee.hashCode() : 0;
        result = 31 * result + (tableCatalog != null ? tableCatalog.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (privilegeType != null ? privilegeType.hashCode() : 0);
        result = 31 * result + (isGrantable != null ? isGrantable.hashCode() : 0);
        return result;
    }
}
