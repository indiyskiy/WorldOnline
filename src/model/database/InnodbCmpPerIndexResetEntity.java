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
@javax.persistence.Table(name = "INNODB_CMP_PER_INDEX_RESET", schema = "", catalog = "information_schema")
@Entity
public class InnodbCmpPerIndexResetEntity {
    private String databaseName;

    @javax.persistence.Column(name = "database_name")
    @Basic
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    private String tableName;

    @javax.persistence.Column(name = "table_name")
    @Basic
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String indexName;

    @javax.persistence.Column(name = "index_name")
    @Basic
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    private int compressOps;

    @javax.persistence.Column(name = "compress_ops")
    @Basic
    public int getCompressOps() {
        return compressOps;
    }

    public void setCompressOps(int compressOps) {
        this.compressOps = compressOps;
    }

    private int compressOpsOk;

    @javax.persistence.Column(name = "compress_ops_ok")
    @Basic
    public int getCompressOpsOk() {
        return compressOpsOk;
    }

    public void setCompressOpsOk(int compressOpsOk) {
        this.compressOpsOk = compressOpsOk;
    }

    private int compressTime;

    @javax.persistence.Column(name = "compress_time")
    @Basic
    public int getCompressTime() {
        return compressTime;
    }

    public void setCompressTime(int compressTime) {
        this.compressTime = compressTime;
    }

    private int uncompressOps;

    @javax.persistence.Column(name = "uncompress_ops")
    @Basic
    public int getUncompressOps() {
        return uncompressOps;
    }

    public void setUncompressOps(int uncompressOps) {
        this.uncompressOps = uncompressOps;
    }

    private int uncompressTime;

    @javax.persistence.Column(name = "uncompress_time")
    @Basic
    public int getUncompressTime() {
        return uncompressTime;
    }

    public void setUncompressTime(int uncompressTime) {
        this.uncompressTime = uncompressTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbCmpPerIndexResetEntity that = (InnodbCmpPerIndexResetEntity) o;

        if (compressOps != that.compressOps) return false;
        if (compressOpsOk != that.compressOpsOk) return false;
        if (compressTime != that.compressTime) return false;
        if (uncompressOps != that.uncompressOps) return false;
        if (uncompressTime != that.uncompressTime) return false;
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) return false;
        if (indexName != null ? !indexName.equals(that.indexName) : that.indexName != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = databaseName != null ? databaseName.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        result = 31 * result + compressOps;
        result = 31 * result + compressOpsOk;
        result = 31 * result + compressTime;
        result = 31 * result + uncompressOps;
        result = 31 * result + uncompressTime;
        return result;
    }
}
