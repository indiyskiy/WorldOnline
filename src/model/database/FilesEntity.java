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
@javax.persistence.Table(name = "FILES", schema = "", catalog = "information_schema")
@Entity
public class FilesEntity {
    private long fileId;

    @javax.persistence.Column(name = "FILE_ID")
    @Basic
    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    private String fileName;

    @javax.persistence.Column(name = "FILE_NAME")
    @Basic
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileType;

    @javax.persistence.Column(name = "FILE_TYPE")
    @Basic
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    private String tablespaceName;

    @javax.persistence.Column(name = "TABLESPACE_NAME")
    @Basic
    public String getTablespaceName() {
        return tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
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

    private String logfileGroupName;

    @javax.persistence.Column(name = "LOGFILE_GROUP_NAME")
    @Basic
    public String getLogfileGroupName() {
        return logfileGroupName;
    }

    public void setLogfileGroupName(String logfileGroupName) {
        this.logfileGroupName = logfileGroupName;
    }

    private long logfileGroupNumber;

    @javax.persistence.Column(name = "LOGFILE_GROUP_NUMBER")
    @Basic
    public long getLogfileGroupNumber() {
        return logfileGroupNumber;
    }

    public void setLogfileGroupNumber(long logfileGroupNumber) {
        this.logfileGroupNumber = logfileGroupNumber;
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

    private String fulltextKeys;

    @javax.persistence.Column(name = "FULLTEXT_KEYS")
    @Basic
    public String getFulltextKeys() {
        return fulltextKeys;
    }

    public void setFulltextKeys(String fulltextKeys) {
        this.fulltextKeys = fulltextKeys;
    }

    private long deletedRows;

    @javax.persistence.Column(name = "DELETED_ROWS")
    @Basic
    public long getDeletedRows() {
        return deletedRows;
    }

    public void setDeletedRows(long deletedRows) {
        this.deletedRows = deletedRows;
    }

    private long updateCount;

    @javax.persistence.Column(name = "UPDATE_COUNT")
    @Basic
    public long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(long updateCount) {
        this.updateCount = updateCount;
    }

    private long freeExtents;

    @javax.persistence.Column(name = "FREE_EXTENTS")
    @Basic
    public long getFreeExtents() {
        return freeExtents;
    }

    public void setFreeExtents(long freeExtents) {
        this.freeExtents = freeExtents;
    }

    private long totalExtents;

    @javax.persistence.Column(name = "TOTAL_EXTENTS")
    @Basic
    public long getTotalExtents() {
        return totalExtents;
    }

    public void setTotalExtents(long totalExtents) {
        this.totalExtents = totalExtents;
    }

    private long extentSize;

    @javax.persistence.Column(name = "EXTENT_SIZE")
    @Basic
    public long getExtentSize() {
        return extentSize;
    }

    public void setExtentSize(long extentSize) {
        this.extentSize = extentSize;
    }

    private long initialSize;

    @javax.persistence.Column(name = "INITIAL_SIZE")
    @Basic
    public long getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(long initialSize) {
        this.initialSize = initialSize;
    }

    private long maximumSize;

    @javax.persistence.Column(name = "MAXIMUM_SIZE")
    @Basic
    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }

    private long autoextendSize;

    @javax.persistence.Column(name = "AUTOEXTEND_SIZE")
    @Basic
    public long getAutoextendSize() {
        return autoextendSize;
    }

    public void setAutoextendSize(long autoextendSize) {
        this.autoextendSize = autoextendSize;
    }

    private Timestamp creationTime;

    @javax.persistence.Column(name = "CREATION_TIME")
    @Basic
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    private Timestamp lastUpdateTime;

    @javax.persistence.Column(name = "LAST_UPDATE_TIME")
    @Basic
    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    private Timestamp lastAccessTime;

    @javax.persistence.Column(name = "LAST_ACCESS_TIME")
    @Basic
    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    private long recoverTime;

    @javax.persistence.Column(name = "RECOVER_TIME")
    @Basic
    public long getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(long recoverTime) {
        this.recoverTime = recoverTime;
    }

    private long transactionCounter;

    @javax.persistence.Column(name = "TRANSACTION_COUNTER")
    @Basic
    public long getTransactionCounter() {
        return transactionCounter;
    }

    public void setTransactionCounter(long transactionCounter) {
        this.transactionCounter = transactionCounter;
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

    private long checksum;

    @javax.persistence.Column(name = "CHECKSUM")
    @Basic
    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
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

    private String extra;

    @javax.persistence.Column(name = "EXTRA")
    @Basic
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (autoextendSize != that.autoextendSize) return false;
        if (avgRowLength != that.avgRowLength) return false;
        if (checksum != that.checksum) return false;
        if (dataFree != that.dataFree) return false;
        if (dataLength != that.dataLength) return false;
        if (deletedRows != that.deletedRows) return false;
        if (extentSize != that.extentSize) return false;
        if (fileId != that.fileId) return false;
        if (freeExtents != that.freeExtents) return false;
        if (indexLength != that.indexLength) return false;
        if (initialSize != that.initialSize) return false;
        if (logfileGroupNumber != that.logfileGroupNumber) return false;
        if (maxDataLength != that.maxDataLength) return false;
        if (maximumSize != that.maximumSize) return false;
        if (recoverTime != that.recoverTime) return false;
        if (tableRows != that.tableRows) return false;
        if (totalExtents != that.totalExtents) return false;
        if (transactionCounter != that.transactionCounter) return false;
        if (updateCount != that.updateCount) return false;
        if (version != that.version) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (fulltextKeys != null ? !fulltextKeys.equals(that.fulltextKeys) : that.fulltextKeys != null) return false;
        if (lastAccessTime != null ? !lastAccessTime.equals(that.lastAccessTime) : that.lastAccessTime != null)
            return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (logfileGroupName != null ? !logfileGroupName.equals(that.logfileGroupName) : that.logfileGroupName != null)
            return false;
        if (rowFormat != null ? !rowFormat.equals(that.rowFormat) : that.rowFormat != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;
        if (tablespaceName != null ? !tablespaceName.equals(that.tablespaceName) : that.tablespaceName != null)
            return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (fileId ^ (fileId >>> 32));
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (tablespaceName != null ? tablespaceName.hashCode() : 0);
        result = 31 * result + (tableCatalog != null ? tableCatalog.hashCode() : 0);
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (logfileGroupName != null ? logfileGroupName.hashCode() : 0);
        result = 31 * result + (int) (logfileGroupNumber ^ (logfileGroupNumber >>> 32));
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (fulltextKeys != null ? fulltextKeys.hashCode() : 0);
        result = 31 * result + (int) (deletedRows ^ (deletedRows >>> 32));
        result = 31 * result + (int) (updateCount ^ (updateCount >>> 32));
        result = 31 * result + (int) (freeExtents ^ (freeExtents >>> 32));
        result = 31 * result + (int) (totalExtents ^ (totalExtents >>> 32));
        result = 31 * result + (int) (extentSize ^ (extentSize >>> 32));
        result = 31 * result + (int) (initialSize ^ (initialSize >>> 32));
        result = 31 * result + (int) (maximumSize ^ (maximumSize >>> 32));
        result = 31 * result + (int) (autoextendSize ^ (autoextendSize >>> 32));
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (lastAccessTime != null ? lastAccessTime.hashCode() : 0);
        result = 31 * result + (int) (recoverTime ^ (recoverTime >>> 32));
        result = 31 * result + (int) (transactionCounter ^ (transactionCounter >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (rowFormat != null ? rowFormat.hashCode() : 0);
        result = 31 * result + (int) (tableRows ^ (tableRows >>> 32));
        result = 31 * result + (int) (avgRowLength ^ (avgRowLength >>> 32));
        result = 31 * result + (int) (dataLength ^ (dataLength >>> 32));
        result = 31 * result + (int) (maxDataLength ^ (maxDataLength >>> 32));
        result = 31 * result + (int) (indexLength ^ (indexLength >>> 32));
        result = 31 * result + (int) (dataFree ^ (dataFree >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (int) (checksum ^ (checksum >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }
}
