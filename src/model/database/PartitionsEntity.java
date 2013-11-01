package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "PARTITIONS", schema = "", catalog = "information_schema")
@Entity
public class PartitionsEntity {
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

    private String partitionName;

    @javax.persistence.Column(name = "PARTITION_NAME")
    @Basic
    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    private String subpartitionName;

    @javax.persistence.Column(name = "SUBPARTITION_NAME")
    @Basic
    public String getSubpartitionName() {
        return subpartitionName;
    }

    public void setSubpartitionName(String subpartitionName) {
        this.subpartitionName = subpartitionName;
    }

    private long partitionOrdinalPosition;

    @javax.persistence.Column(name = "PARTITION_ORDINAL_POSITION")
    @Basic
    public long getPartitionOrdinalPosition() {
        return partitionOrdinalPosition;
    }

    public void setPartitionOrdinalPosition(long partitionOrdinalPosition) {
        this.partitionOrdinalPosition = partitionOrdinalPosition;
    }

    private long subpartitionOrdinalPosition;

    @javax.persistence.Column(name = "SUBPARTITION_ORDINAL_POSITION")
    @Basic
    public long getSubpartitionOrdinalPosition() {
        return subpartitionOrdinalPosition;
    }

    public void setSubpartitionOrdinalPosition(long subpartitionOrdinalPosition) {
        this.subpartitionOrdinalPosition = subpartitionOrdinalPosition;
    }

    private String partitionMethod;

    @javax.persistence.Column(name = "PARTITION_METHOD")
    @Basic
    public String getPartitionMethod() {
        return partitionMethod;
    }

    public void setPartitionMethod(String partitionMethod) {
        this.partitionMethod = partitionMethod;
    }

    private String subpartitionMethod;

    @javax.persistence.Column(name = "SUBPARTITION_METHOD")
    @Basic
    public String getSubpartitionMethod() {
        return subpartitionMethod;
    }

    public void setSubpartitionMethod(String subpartitionMethod) {
        this.subpartitionMethod = subpartitionMethod;
    }

    private String partitionExpression;

    @javax.persistence.Column(name = "PARTITION_EXPRESSION")
    @Basic
    public String getPartitionExpression() {
        return partitionExpression;
    }

    public void setPartitionExpression(String partitionExpression) {
        this.partitionExpression = partitionExpression;
    }

    private String subpartitionExpression;

    @javax.persistence.Column(name = "SUBPARTITION_EXPRESSION")
    @Basic
    public String getSubpartitionExpression() {
        return subpartitionExpression;
    }

    public void setSubpartitionExpression(String subpartitionExpression) {
        this.subpartitionExpression = subpartitionExpression;
    }

    private String partitionDescription;

    @javax.persistence.Column(name = "PARTITION_DESCRIPTION")
    @Basic
    public String getPartitionDescription() {
        return partitionDescription;
    }

    public void setPartitionDescription(String partitionDescription) {
        this.partitionDescription = partitionDescription;
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

    private String partitionComment;

    @javax.persistence.Column(name = "PARTITION_COMMENT")
    @Basic
    public String getPartitionComment() {
        return partitionComment;
    }

    public void setPartitionComment(String partitionComment) {
        this.partitionComment = partitionComment;
    }

    private String nodegroup;

    @javax.persistence.Column(name = "NODEGROUP")
    @Basic
    public String getNodegroup() {
        return nodegroup;
    }

    public void setNodegroup(String nodegroup) {
        this.nodegroup = nodegroup;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartitionsEntity that = (PartitionsEntity) o;

        if (avgRowLength != that.avgRowLength) return false;
        if (checksum != that.checksum) return false;
        if (dataFree != that.dataFree) return false;
        if (dataLength != that.dataLength) return false;
        if (indexLength != that.indexLength) return false;
        if (maxDataLength != that.maxDataLength) return false;
        if (partitionOrdinalPosition != that.partitionOrdinalPosition) return false;
        if (subpartitionOrdinalPosition != that.subpartitionOrdinalPosition) return false;
        if (tableRows != that.tableRows) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (nodegroup != null ? !nodegroup.equals(that.nodegroup) : that.nodegroup != null) return false;
        if (partitionComment != null ? !partitionComment.equals(that.partitionComment) : that.partitionComment != null)
            return false;
        if (partitionDescription != null ? !partitionDescription.equals(that.partitionDescription) : that.partitionDescription != null)
            return false;
        if (partitionExpression != null ? !partitionExpression.equals(that.partitionExpression) : that.partitionExpression != null)
            return false;
        if (partitionMethod != null ? !partitionMethod.equals(that.partitionMethod) : that.partitionMethod != null)
            return false;
        if (partitionName != null ? !partitionName.equals(that.partitionName) : that.partitionName != null)
            return false;
        if (subpartitionExpression != null ? !subpartitionExpression.equals(that.subpartitionExpression) : that.subpartitionExpression != null)
            return false;
        if (subpartitionMethod != null ? !subpartitionMethod.equals(that.subpartitionMethod) : that.subpartitionMethod != null)
            return false;
        if (subpartitionName != null ? !subpartitionName.equals(that.subpartitionName) : that.subpartitionName != null)
            return false;
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
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (partitionName != null ? partitionName.hashCode() : 0);
        result = 31 * result + (subpartitionName != null ? subpartitionName.hashCode() : 0);
        result = 31 * result + (int) (partitionOrdinalPosition ^ (partitionOrdinalPosition >>> 32));
        result = 31 * result + (int) (subpartitionOrdinalPosition ^ (subpartitionOrdinalPosition >>> 32));
        result = 31 * result + (partitionMethod != null ? partitionMethod.hashCode() : 0);
        result = 31 * result + (subpartitionMethod != null ? subpartitionMethod.hashCode() : 0);
        result = 31 * result + (partitionExpression != null ? partitionExpression.hashCode() : 0);
        result = 31 * result + (subpartitionExpression != null ? subpartitionExpression.hashCode() : 0);
        result = 31 * result + (partitionDescription != null ? partitionDescription.hashCode() : 0);
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
        result = 31 * result + (partitionComment != null ? partitionComment.hashCode() : 0);
        result = 31 * result + (nodegroup != null ? nodegroup.hashCode() : 0);
        result = 31 * result + (tablespaceName != null ? tablespaceName.hashCode() : 0);
        return result;
    }
}
