package model.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public class ProcsPrivEntityPK implements Serializable {
    private String host;

    @Id
    @Column(name = "Host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @Id
    @Column(name = "Db")
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String user;

    @Id
    @Column(name = "User")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String routineName;

    @Id
    @Column(name = "Routine_name")
    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    private String routineType;

    @Id
    @Column(name = "Routine_type")
    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcsPrivEntityPK that = (ProcsPrivEntityPK) o;

        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (routineName != null ? !routineName.equals(that.routineName) : that.routineName != null) return false;
        if (routineType != null ? !routineType.equals(that.routineType) : that.routineType != null) return false;
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
        return result;
    }
}
