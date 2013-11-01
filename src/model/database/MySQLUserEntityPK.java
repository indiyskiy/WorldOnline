package model.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
public class MySQLUserEntityPK implements Serializable {
    private String host;

    @Id
    @Column(name = "Host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MySQLUserEntityPK that = (MySQLUserEntityPK) o;

        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
