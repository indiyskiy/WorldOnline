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
@javax.persistence.IdClass(model.database.ProxiesPrivEntityPK.class)
@javax.persistence.Table(name = "proxies_priv", schema = "", catalog = "mysql")
@Entity
public class ProxiesPrivEntity {
    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    private String proxiedHost;

    @javax.persistence.Column(name = "Proxied_host")
    @Id
    public String getProxiedHost() {
        return proxiedHost;
    }

    public void setProxiedHost(String proxiedHost) {
        this.proxiedHost = proxiedHost;
    }

    private String proxiedUser;

    @javax.persistence.Column(name = "Proxied_user")
    @Id
    public String getProxiedUser() {
        return proxiedUser;
    }

    public void setProxiedUser(String proxiedUser) {
        this.proxiedUser = proxiedUser;
    }

    private boolean withGrant;

    @javax.persistence.Column(name = "With_grant")
    @Basic
    public boolean isWithGrant() {
        return withGrant;
    }

    public void setWithGrant(boolean withGrant) {
        this.withGrant = withGrant;
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

        ProxiesPrivEntity that = (ProxiesPrivEntity) o;

        if (withGrant != that.withGrant) return false;
        if (grantor != null ? !grantor.equals(that.grantor) : that.grantor != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (proxiedHost != null ? !proxiedHost.equals(that.proxiedHost) : that.proxiedHost != null) return false;
        if (proxiedUser != null ? !proxiedUser.equals(that.proxiedUser) : that.proxiedUser != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (proxiedHost != null ? proxiedHost.hashCode() : 0);
        result = 31 * result + (proxiedUser != null ? proxiedUser.hashCode() : 0);
        result = 31 * result + (withGrant ? 1 : 0);
        result = 31 * result + (grantor != null ? grantor.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
