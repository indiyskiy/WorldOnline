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
@javax.persistence.Table(name = "VIEWS", schema = "", catalog = "information_schema")
@Entity
public class ViewsEntity {
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

    private String viewDefinition;

    @javax.persistence.Column(name = "VIEW_DEFINITION")
    @Basic
    public String getViewDefinition() {
        return viewDefinition;
    }

    public void setViewDefinition(String viewDefinition) {
        this.viewDefinition = viewDefinition;
    }

    private String checkOption;

    @javax.persistence.Column(name = "CHECK_OPTION")
    @Basic
    public String getCheckOption() {
        return checkOption;
    }

    public void setCheckOption(String checkOption) {
        this.checkOption = checkOption;
    }

    private String isUpdatable;

    @javax.persistence.Column(name = "IS_UPDATABLE")
    @Basic
    public String getUpdatable() {
        return isUpdatable;
    }

    public void setUpdatable(String updatable) {
        isUpdatable = updatable;
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

    private String securityType;

    @javax.persistence.Column(name = "SECURITY_TYPE")
    @Basic
    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewsEntity that = (ViewsEntity) o;

        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (checkOption != null ? !checkOption.equals(that.checkOption) : that.checkOption != null) return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (isUpdatable != null ? !isUpdatable.equals(that.isUpdatable) : that.isUpdatable != null) return false;
        if (securityType != null ? !securityType.equals(that.securityType) : that.securityType != null) return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;
        if (viewDefinition != null ? !viewDefinition.equals(that.viewDefinition) : that.viewDefinition != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (viewDefinition != null ? viewDefinition.hashCode() : 0);
        result = 31 * result + (checkOption != null ? checkOption.hashCode() : 0);
        result = 31 * result + (isUpdatable != null ? isUpdatable.hashCode() : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (securityType != null ? securityType.hashCode() : 0);
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        return result;
    }
}
