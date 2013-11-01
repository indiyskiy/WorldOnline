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
@javax.persistence.Table(name = "hosts", schema = "", catalog = "performance_schema")
@Entity
public class HostsEntity {
    private String host;

    @javax.persistence.Column(name = "HOST")
    @Basic
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

        HostsEntity that = (HostsEntity) o;

        if (currentConnections != that.currentConnections) return false;
        if (totalConnections != that.totalConnections) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (int) (currentConnections ^ (currentConnections >>> 32));
        result = 31 * result + (int) (totalConnections ^ (totalConnections >>> 32));
        return result;
    }
}
