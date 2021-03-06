package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:10
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "users", schema = "", catalog = "performance_schema")
@Entity
public class UsersEntity {
    private String user;

    @javax.persistence.Column(name = "USER")
    @Basic
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private long currentConnections;

    @javax.persistence.Column(name = "CURRENT_CONNECTIONS")
    @Basic
    public long getCurrentConnections() {
        return currentConnections;
    }

    public void setCurrentConnections(long currentConnections) {
        this.currentConnections = currentConnections;
    }

    private long totalConnections;

    @javax.persistence.Column(name = "TOTAL_CONNECTIONS")
    @Basic
    public long getTotalConnections() {
        return totalConnections;
    }

    public void setTotalConnections(long totalConnections) {
        this.totalConnections = totalConnections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (currentConnections != that.currentConnections) return false;
        if (totalConnections != that.totalConnections) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (int) (currentConnections ^ (currentConnections >>> 32));
        result = 31 * result + (int) (totalConnections ^ (totalConnections >>> 32));
        return result;
    }
}
