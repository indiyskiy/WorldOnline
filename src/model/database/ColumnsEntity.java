package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "COLUMNS", schema = "", catalog = "information_schema")
@Entity
public class ColumnsEntity {
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

    private String columnDefault;

    @javax.persistence.Column(name = "COLUMN_DEFAULT")
    @Basic
    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    private String isNullable;

    @javax.persistence.Column(name = "IS_NULLABLE")
    @Basic
    public String getNullable() {
        return isNullable;
    }

    public void setNullable(String nullable) {
        isNullable = nullable;
    }

    private String dataType;

    @javax.persistence.Column(name = "DATA_TYPE")
    @Basic
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    private long characterMaximumLength;

    @javax.persistence.Column(name = "CHARACTER_MAXIMUM_LENGTH")
    @Basic
    public long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    private long characterOctetLength;

    @javax.persistence.Column(name = "CHARACTER_OCTET_LENGTH")
    @Basic
    public long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    private long numericPrecision;

    @javax.persistence.Column(name = "NUMERIC_PRECISION")
    @Basic
    public long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    private long numericScale;

    @javax.persistence.Column(name = "NUMERIC_SCALE")
    @Basic
    public long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(long numericScale) {
        this.numericScale = numericScale;
    }

    private long datetimePrecision;

    @javax.persistence.Column(name = "DATETIME_PRECISION")
    @Basic
    public long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    private String characterSetName;

    @javax.persistence.Column(name = "CHARACTER_SET_NAME")
    @Basic
    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    private String collationName;

    @javax.persistence.Column(name = "COLLATION_NAME")
    @Basic
    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    private String columnType;

    @javax.persistence.Column(name = "COLUMN_TYPE")
    @Basic
    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    private String columnKey;

    @javax.persistence.Column(name = "COLUMN_KEY")
    @Basic
    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    private String extra;

    @javax.persistence.Column(name = "EXTRA")
    @Basic
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    private String privileges;

    @javax.persistence.Column(name = "PRIVILEGES")
    @Basic
    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    private String columnComment;

    @javax.persistence.Column(name = "COLUMN_COMMENT")
    @Basic
    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnsEntity that = (ColumnsEntity) o;

        if (characterMaximumLength != that.characterMaximumLength) return false;
        if (characterOctetLength != that.characterOctetLength) return false;
        if (datetimePrecision != that.datetimePrecision) return false;
        if (numericPrecision != that.numericPrecision) return false;
        if (numericScale != that.numericScale) return false;
        if (ordinalPosition != that.ordinalPosition) return false;
        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (collationName != null ? !collationName.equals(that.collationName) : that.collationName != null)
            return false;
        if (columnComment != null ? !columnComment.equals(that.columnComment) : that.columnComment != null)
            return false;
        if (columnDefault != null ? !columnDefault.equals(that.columnDefault) : that.columnDefault != null)
            return false;
        if (columnKey != null ? !columnKey.equals(that.columnKey) : that.columnKey != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (columnType != null ? !columnType.equals(that.columnType) : that.columnType != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) return false;
        if (isNullable != null ? !isNullable.equals(that.isNullable) : that.isNullable != null) return false;
        if (privileges != null ? !privileges.equals(that.privileges) : that.privileges != null) return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (int) (ordinalPosition ^ (ordinalPosition >>> 32));
        result = 31 * result + (columnDefault != null ? columnDefault.hashCode() : 0);
        result = 31 * result + (isNullable != null ? isNullable.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (int) (characterMaximumLength ^ (characterMaximumLength >>> 32));
        result = 31 * result + (int) (characterOctetLength ^ (characterOctetLength >>> 32));
        result = 31 * result + (int) (numericPrecision ^ (numericPrecision >>> 32));
        result = 31 * result + (int) (numericScale ^ (numericScale >>> 32));
        result = 31 * result + (int) (datetimePrecision ^ (datetimePrecision >>> 32));
        result = 31 * result + (characterSetName != null ? characterSetName.hashCode() : 0);
        result = 31 * result + (collationName != null ? collationName.hashCode() : 0);
        result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
        result = 31 * result + (columnKey != null ? columnKey.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
        result = 31 * result + (columnComment != null ? columnComment.hashCode() : 0);
        return result;
    }
}
