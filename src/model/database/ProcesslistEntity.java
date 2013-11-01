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
@javax.persistence.Table(name = "PROCESSLIST", schema = "", catalog = "information_schema")
@Entity
public class ProcesslistEntity {
    private long id;

    @javax.persistence.Column(name = "ID")
    @Basic
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String user;

    @javax.persistence.Column(name = "USER")
    @Basic
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String host;

    @javax.persistence.Column(name = "HOST")
    @Basic
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @javax.persistence.Column(name = "DB")
    @Basic
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String command;

    @javax.persistence.Column(name = "COMMAND")
    @Basic
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private int time;

    @javax.persistence.Column(name = "TIME")
    @Basic
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private String state;

    @javax.persistence.Column(name = "STATE")
    @Basic
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String info;

    @javax.persistence.Column(name = "INFO")
    @Basic
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcesslistEntity that = (ProcesslistEntity) o;

        if (id != that.id) return false;
        if (time != that.time) return false;
        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + time;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
