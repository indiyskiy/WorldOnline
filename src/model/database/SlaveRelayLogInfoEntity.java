package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "slave_relay_log_info", schema = "", catalog = "mysql")
@Entity
public class SlaveRelayLogInfoEntity {
    private int numberOfLines;

    @javax.persistence.Column(name = "Number_of_lines")
    @Basic
    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    private String relayLogName;

    @javax.persistence.Column(name = "Relay_log_name")
    @Basic
    public String getRelayLogName() {
        return relayLogName;
    }

    public void setRelayLogName(String relayLogName) {
        this.relayLogName = relayLogName;
    }

    private long relayLogPos;

    @javax.persistence.Column(name = "Relay_log_pos")
    @Basic
    public long getRelayLogPos() {
        return relayLogPos;
    }

    public void setRelayLogPos(long relayLogPos) {
        this.relayLogPos = relayLogPos;
    }

    private String masterLogName;

    @javax.persistence.Column(name = "Master_log_name")
    @Basic
    public String getMasterLogName() {
        return masterLogName;
    }

    public void setMasterLogName(String masterLogName) {
        this.masterLogName = masterLogName;
    }

    private long masterLogPos;

    @javax.persistence.Column(name = "Master_log_pos")
    @Basic
    public long getMasterLogPos() {
        return masterLogPos;
    }

    public void setMasterLogPos(long masterLogPos) {
        this.masterLogPos = masterLogPos;
    }

    private int sqlDelay;

    @javax.persistence.Column(name = "Sql_delay")
    @Basic
    public int getSqlDelay() {
        return sqlDelay;
    }

    public void setSqlDelay(int sqlDelay) {
        this.sqlDelay = sqlDelay;
    }

    private int numberOfWorkers;

    @javax.persistence.Column(name = "Number_of_workers")
    @Basic
    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    private int id;

    @javax.persistence.Column(name = "Id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlaveRelayLogInfoEntity that = (SlaveRelayLogInfoEntity) o;

        if (id != that.id) return false;
        if (masterLogPos != that.masterLogPos) return false;
        if (numberOfLines != that.numberOfLines) return false;
        if (numberOfWorkers != that.numberOfWorkers) return false;
        if (relayLogPos != that.relayLogPos) return false;
        if (sqlDelay != that.sqlDelay) return false;
        if (masterLogName != null ? !masterLogName.equals(that.masterLogName) : that.masterLogName != null)
            return false;
        if (relayLogName != null ? !relayLogName.equals(that.relayLogName) : that.relayLogName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numberOfLines;
        result = 31 * result + (relayLogName != null ? relayLogName.hashCode() : 0);
        result = 31 * result + (int) (relayLogPos ^ (relayLogPos >>> 32));
        result = 31 * result + (masterLogName != null ? masterLogName.hashCode() : 0);
        result = 31 * result + (int) (masterLogPos ^ (masterLogPos >>> 32));
        result = 31 * result + sqlDelay;
        result = 31 * result + numberOfWorkers;
        result = 31 * result + id;
        return result;
    }
}
