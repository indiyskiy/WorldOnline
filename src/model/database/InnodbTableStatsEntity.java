package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.InnodbTableStatsEntityPK.class)
@javax.persistence.Table(name = "innodb_table_stats", schema = "", catalog = "mysql")
@Entity
public class InnodbTableStatsEntity {
    private String databaseName;

    @javax.persistence.Column(name = "database_name")
    @Id
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    private String tableName;

    @javax.persistence.Column(name = "table_name")
    @Id
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private Timestamp lastUpdate;

    @javax.persistence.Column(name = "last_update")
    @Basic
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private long nRows;

    @javax.persistence.Column(name = "n_rows")
    @Basic
    public long getnRows() {
        return nRows;
    }

    public void setnRows(long nRows) {
        this.nRows = nRows;
    }

    private long clusteredIndexSize;

    @javax.persistence.Column(name = "clustered_index_size")
    @Basic
    public long getClusteredIndexSize() {
        return clusteredIndexSize;
    }

    public void setClusteredIndexSize(long clusteredIndexSize) {
        this.clusteredIndexSize = clusteredIndexSize;
    }

    private long sumOfOtherIndexSizes;

    @javax.persistence.Column(name = "sum_of_other_index_sizes")
    @Basic
    public long getSumOfOtherIndexSizes() {
        return sumOfOtherIndexSizes;
    }

    public void setSumOfOtherIndexSizes(long sumOfOtherIndexSizes) {
        this.sumOfOtherIndexSizes = sumOfOtherIndexSizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbTableStatsEntity that = (InnodbTableStatsEntity) o;

        if (clusteredIndexSize != that.clusteredIndexSize) return false;
        if (nRows != that.nRows) return false;
        if (sumOfOtherIndexSizes != that.sumOfOtherIndexSizes) return false;
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = databaseName != null ? databaseName.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (int) (nRows ^ (nRows >>> 32));
        result = 31 * result + (int) (clusteredIndexSize ^ (clusteredIndexSize >>> 32));
        result = 31 * result + (int) (sumOfOtherIndexSizes ^ (sumOfOtherIndexSizes >>> 32));
        return result;
    }
}
