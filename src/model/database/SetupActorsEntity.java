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
@javax.persistence.Table(name = "setup_actors", schema = "", catalog = "performance_schema")
@Entity
public class SetupActorsEntity {
    private String host;

    @javax.persistence.Column(name = "HOST")
    @Basic
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    private String role;

    @javax.persistence.Column(name = "ROLE")
    @Basic
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetupActorsEntity that = (SetupActorsEntity) o;

        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
