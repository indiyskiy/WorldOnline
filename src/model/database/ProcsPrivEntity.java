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
@javax.persistence.IdClass(model.database.ProcsPrivEntityPK.class)
@javax.persistence.Table(name = "procs_priv", schema = "", catalog = "mysql")
@Entity
public class ProcsPrivEntity {
    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @javax.persistence.Column(name = "Db")
    @Id
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String user;

    @javax.persistence.Column(name = "User")
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String routineName;

    @javax.persistence.Column(name = "Routine_name")
    @Id
    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    private String routineType;

    @javax.persistence.Column(name = "Routine_type")
    @Id
    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    private String grantor;

    @javax.persistence.Column(name = "Grantor")
    @Basic
    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    private String procPriv;

    @javax.persistence.Column(name = "Proc_priv")
    @Basic
    public String getProcPriv() {
        return procPriv;
    }

    public void setProcPriv(String procPriv) {
        this.procPriv = procPriv;
    }

    private Timestamp timestamp;

    @javax.persistence.Column(name = "Timestamp")
    @Basic
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcsPrivEntity that = (ProcsPrivEntity) o;

        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (grantor != null ? !grantor.equals(that.grantor) : that.grantor != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (procPriv != null ? !procPriv.equals(that.procPriv) : that.procPriv != null) return false;
        if (routineName != null ? !routineName.equals(that.routineName) : that.routineName != null) return false;
        if (routineType != null ? !routineType.equals(that.routineType) : that.routineType != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (routineName != null ? routineName.hashCode() : 0);
        result = 31 * result + (routineType != null ? routineType.hashCode() : 0);
        result = 31 * result + (grantor != null ? grantor.hashCode() : 0);
        result = 31 * result + (procPriv != null ? procPriv.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
