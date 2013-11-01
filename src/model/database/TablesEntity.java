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
@javax.persistence.Table(name = "TABLES", schema = "", catalog = "information_schema")
@Entity
public class TablesEntity {
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

    private String tableType;

    @javax.persistence.Column(name = "TABLE_TYPE")
    @Basic
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    private String engine;

    @javax.persistence.Column(name = "ENGINE")
    @Basic
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    private long version;

    @javax.persistence.Column(name = "VERSION")
    @Basic
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    private String rowFormat;

    @javax.persistence.Column(name = "ROW_FORMAT")
    @Basic
    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    private long tableRows;

    @javax.persistence.Column(name = "TABLE_ROWS")
    @Basic
    public long getTableRows() {
        return tableRows;
    }

    public void setTableRows(long tableRows) {
        this.tableRows = tableRows;
    }

    private long avgRowLength;

    @javax.persistence.Column(name = "AVG_ROW_LENGTH")
    @Basic
    public long getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(long avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    private long dataLength;

    @javax.persistence.Column(name = "DATA_LENGTH")
    @Basic
    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }

    private long maxDataLength;

    @javax.persistence.Column(name = "MAX_DATA_LENGTH")
    @Basic
    public long getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(long maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    private long indexLength;

    @javax.persistence.Column(name = "INDEX_LENGTH")
    @Basic
    public long getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(long indexLength) {
        this.indexLength = indexLength;
    }

    private long dataFree;

    @javax.persistence.Column(name = "DATA_FREE")
    @Basic
    public long getDataFree() {
        return dataFree;
    }

    public void setDataFree(long dataFree) {
        this.dataFree = dataFree;
    }

    private long autoIncrement;

    @javax.persistence.Column(name = "AUTO_INCREMENT")
    @Basic
    public long getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(long autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    private Timestamp createTime;

    @javax.persistence.Column(name = "CREATE_TIME")
    @Basic
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private Timestamp updateTime;

    @javax.persistence.Column(name = "UPDATE_TIME")
    @Basic
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    private Timestamp checkTime;

    @javax.persistence.Column(name = "CHECK_TIME")
    @Basic
    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    private String tableCollation;

    @javax.persistence.Column(name = "TABLE_COLLATION")
    @Basic
    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    private long checksum;

    @javax.persistence.Column(name = "CHECKSUM")
    @Basic
    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    private String createOptions;

    @javax.persistence.Column(name = "CREATE_OPTIONS")
    @Basic
    public String getCreateOptions() {
        return createOptions;
    }

    public void setCreateOptions(String createOptions) {
        this.createOptions = createOptions;
    }

    private String tableComment;

    @javax.persistence.Column(name = "TABLE_COMMENT")
    @Basic
    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TablesEntity that = (TablesEntity) o;

        if (autoIncrement != that.autoIncrement) return false;
        if (avgRowLength != that.avgRowLength) return false;
        if (checksum != that.checksum) return false;
        if (dataFree != that.dataFree) return false;
        if (dataLength != that.dataLength) return false;
        if (indexLength != that.indexLength) return false;
        if (maxDataLength != that.maxDataLength) return false;
        if (tableRows != that.tableRows) return false;
        if (version != that.version) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (createOptions != null ? !createOptions.equals(that.createOptions) : that.createOptions != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (rowFormat != null ? !rowFormat.equals(that.rowFormat) : that.rowFormat != null) return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableCollation != null ? !tableCollation.equals(that.tableCollation) : that.tableCollation != null)
            return false;
        if (tableComment != null ? !tableComment.equals(that.tableComment) : that.tableComment != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;
        if (tableType != null ? !tableType.equals(that.tableType) : that.tableType != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (tableType != null ? tableType.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (rowFormat != null ? rowFormat.hashCode() : 0);
        result = 31 * result + (int) (tableRows ^ (tableRows >>> 32));
        result = 31 * result + (int) (avgRowLength ^ (avgRowLength >>> 32));
        result = 31 * result + (int) (dataLength ^ (dataLength >>> 32));
        result = 31 * result + (int) (maxDataLength ^ (maxDataLength >>> 32));
        result = 31 * result + (int) (indexLength ^ (indexLength >>> 32));
        result = 31 * result + (int) (dataFree ^ (dataFree >>> 32));
        result = 31 * result + (int) (autoIncrement ^ (autoIncrement >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (tableCollation != null ? tableCollation.hashCode() : 0);
        result = 31 * result + (int) (checksum ^ (checksum >>> 32));
        result = 31 * result + (createOptions != null ? createOptions.hashCode() : 0);
        result = 31 * result + (tableComment != null ? tableComment.hashCode() : 0);
        return result;
    }
}
