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
@javax.persistence.IdClass(model.database.InnodbIndexStatsEntityPK.class)
@javax.persistence.Table(name = "innodb_index_stats", schema = "", catalog = "mysql")
@Entity
public class InnodbIndexStatsEntity {
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

    private String indexName;

    @javax.persistence.Column(name = "index_name")
    @Id
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
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

    private String statName;

    @javax.persistence.Column(name = "stat_name")
    @Id
    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    private long statValue;

    @javax.persistence.Column(name = "stat_value")
    @Basic
    public long getStatValue() {
        return statValue;
    }

    public void setStatValue(long statValue) {
        this.statValue = statValue;
    }

    private long sampleSize;

    @javax.persistence.Column(name = "sample_size")
    @Basic
    public long getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(long sampleSize) {
        this.sampleSize = sampleSize;
    }

    private String statDescription;

    @javax.persistence.Column(name = "stat_description")
    @Basic
    public String getStatDescription() {
        return statDescription;
    }

    public void setStatDescription(String statDescription) {
        this.statDescription = statDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbIndexStatsEntity that = (InnodbIndexStatsEntity) o;

        if (sampleSize != that.sampleSize) return false;
        if (statValue != that.statValue) return false;
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) return false;
        if (indexName != null ? !indexName.equals(that.indexName) : that.indexName != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (statDescription != null ? !statDescription.equals(that.statDescription) : that.statDescription != null)
            return false;
        if (statName != null ? !statName.equals(that.statName) : that.statName != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = databaseName != null ? databaseName.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (statName != null ? statName.hashCode() : 0);
        result = 31 * result + (int) (statValue ^ (statValue >>> 32));
        result = 31 * result + (int) (sampleSize ^ (sampleSize >>> 32));
        result = 31 * result + (statDescription != null ? statDescription.hashCode() : 0);
        return result;
    }
}
