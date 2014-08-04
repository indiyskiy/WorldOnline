package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "GlobalVersion", schema = "", catalog = "worldonline")
@Entity
public class GlobalVersionEntity {
    @javax.persistence.Column(name = "GlobalVersionID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long globalVersionID;

    public Long getGlobalVersionID() {
        return globalVersionID;
    }

    public void setGlobalVersionID(Long globalVersionID) {
        this.globalVersionID = globalVersionID;
    }

    @javax.persistence.Column(name = "GlobalVersionName")
    @Basic
    private String globalVersionName;

    public String getGlobalVersionName() {
        return globalVersionName;
    }

    public void setGlobalVersionName(String globalVersionName) {
        this.globalVersionName = globalVersionName;
    }

    @javax.persistence.Column(name = "CreationTimestamp")
    @Basic
    private Timestamp creationTimestamp;

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @javax.persistence.Column(name = "ActivationTimestamp")
    @Basic
    private Timestamp activationTimestamp;

    public Timestamp getActivationTimestamp() {
        return activationTimestamp;
    }

    public void setActivationTimestamp(Timestamp activationTimestamp) {
        this.activationTimestamp = activationTimestamp;
    }


    @javax.persistence.Column(name = "DisableTimestamp")
    @Basic
    private Timestamp disableTimestamp;

    public Timestamp getDisableTimestamp() {
        return disableTimestamp;
    }

    public void setDisableTimestamp(Timestamp disableTimestamp) {
        this.disableTimestamp = disableTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalVersionEntity that = (GlobalVersionEntity) o;

        if (globalVersionID != null ? !globalVersionID.equals(that.globalVersionID) : that.globalVersionID != null)
            return false;
        if (globalVersionName != null ? !globalVersionName.equals(that.globalVersionName) : that.globalVersionName != null)
            return false;
        if (creationTimestamp != null ? !creationTimestamp.equals(that.creationTimestamp) : that.creationTimestamp != null)
            return false;
        if (activationTimestamp != null ? !activationTimestamp.equals(that.activationTimestamp) : that.activationTimestamp != null)
            return false;
        if (disableTimestamp != null ? !disableTimestamp.equals(that.disableTimestamp) : that.disableTimestamp != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = globalVersionID != null ? globalVersionID.hashCode() : 0;
        result = 31 * result + (globalVersionName != null ? globalVersionName.hashCode() : 0);
        result = 31 * result + (creationTimestamp != null ? creationTimestamp.hashCode() : 0);
        result = 31 * result + (activationTimestamp != null ? activationTimestamp.hashCode() : 0);
        result = 31 * result + (disableTimestamp != null ? disableTimestamp.hashCode() : 0);
        return result;
    }
}
