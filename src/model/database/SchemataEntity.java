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
@javax.persistence.Table(name = "SCHEMATA", schema = "", catalog = "information_schema")
@Entity
public class SchemataEntity {
    private String catalogName;

    @javax.persistence.Column(name = "CATALOG_NAME")
    @Basic
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    private String schemaName;

    @javax.persistence.Column(name = "SCHEMA_NAME")
    @Basic
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    private String defaultCharacterSetName;

    @javax.persistence.Column(name = "DEFAULT_CHARACTER_SET_NAME")
    @Basic
    public String getDefaultCharacterSetName() {
        return defaultCharacterSetName;
    }

    public void setDefaultCharacterSetName(String defaultCharacterSetName) {
        this.defaultCharacterSetName = defaultCharacterSetName;
    }

    private String defaultCollationName;

    @javax.persistence.Column(name = "DEFAULT_COLLATION_NAME")
    @Basic
    public String getDefaultCollationName() {
        return defaultCollationName;
    }

    public void setDefaultCollationName(String defaultCollationName) {
        this.defaultCollationName = defaultCollationName;
    }

    private String sqlPath;

    @javax.persistence.Column(name = "SQL_PATH")
    @Basic
    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchemataEntity that = (SchemataEntity) o;

        if (catalogName != null ? !catalogName.equals(that.catalogName) : that.catalogName != null) return false;
        if (defaultCharacterSetName != null ? !defaultCharacterSetName.equals(that.defaultCharacterSetName) : that.defaultCharacterSetName != null)
            return false;
        if (defaultCollationName != null ? !defaultCollationName.equals(that.defaultCollationName) : that.defaultCollationName != null)
            return false;
        if (schemaName != null ? !schemaName.equals(that.schemaName) : that.schemaName != null) return false;
        if (sqlPath != null ? !sqlPath.equals(that.sqlPath) : that.sqlPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catalogName != null ? catalogName.hashCode() : 0;
        result = 31 * result + (schemaName != null ? schemaName.hashCode() : 0);
        result = 31 * result + (defaultCharacterSetName != null ? defaultCharacterSetName.hashCode() : 0);
        result = 31 * result + (defaultCollationName != null ? defaultCollationName.hashCode() : 0);
        result = 31 * result + (sqlPath != null ? sqlPath.hashCode() : 0);
        return result;
    }
}
