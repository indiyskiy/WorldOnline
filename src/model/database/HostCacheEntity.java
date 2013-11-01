package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "host_cache", schema = "", catalog = "performance_schema")
@Entity
public class HostCacheEntity {
    private String ip;

    @javax.persistence.Column(name = "IP")
    @Basic
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    private String hostValidated;

    @javax.persistence.Column(name = "HOST_VALIDATED")
    @Basic
    public String getHostValidated() {
        return hostValidated;
    }

    public void setHostValidated(String hostValidated) {
        this.hostValidated = hostValidated;
    }

    private long sumConnectErrors;

    @javax.persistence.Column(name = "SUM_CONNECT_ERRORS")
    @Basic
    public long getSumConnectErrors() {
        return sumConnectErrors;
    }

    public void setSumConnectErrors(long sumConnectErrors) {
        this.sumConnectErrors = sumConnectErrors;
    }

    private long countHostBlockedErrors;

    @javax.persistence.Column(name = "COUNT_HOST_BLOCKED_ERRORS")
    @Basic
    public long getCountHostBlockedErrors() {
        return countHostBlockedErrors;
    }

    public void setCountHostBlockedErrors(long countHostBlockedErrors) {
        this.countHostBlockedErrors = countHostBlockedErrors;
    }

    private long countNameinfoTransientErrors;

    @javax.persistence.Column(name = "COUNT_NAMEINFO_TRANSIENT_ERRORS")
    @Basic
    public long getCountNameinfoTransientErrors() {
        return countNameinfoTransientErrors;
    }

    public void setCountNameinfoTransientErrors(long countNameinfoTransientErrors) {
        this.countNameinfoTransientErrors = countNameinfoTransientErrors;
    }

    private long countNameinfoPermanentErrors;

    @javax.persistence.Column(name = "COUNT_NAMEINFO_PERMANENT_ERRORS")
    @Basic
    public long getCountNameinfoPermanentErrors() {
        return countNameinfoPermanentErrors;
    }

    public void setCountNameinfoPermanentErrors(long countNameinfoPermanentErrors) {
        this.countNameinfoPermanentErrors = countNameinfoPermanentErrors;
    }

    private long countFormatErrors;

    @javax.persistence.Column(name = "COUNT_FORMAT_ERRORS")
    @Basic
    public long getCountFormatErrors() {
        return countFormatErrors;
    }

    public void setCountFormatErrors(long countFormatErrors) {
        this.countFormatErrors = countFormatErrors;
    }

    private long countAddrinfoTransientErrors;

    @javax.persistence.Column(name = "COUNT_ADDRINFO_TRANSIENT_ERRORS")
    @Basic
    public long getCountAddrinfoTransientErrors() {
        return countAddrinfoTransientErrors;
    }

    public void setCountAddrinfoTransientErrors(long countAddrinfoTransientErrors) {
        this.countAddrinfoTransientErrors = countAddrinfoTransientErrors;
    }

    private long countAddrinfoPermanentErrors;

    @javax.persistence.Column(name = "COUNT_ADDRINFO_PERMANENT_ERRORS")
    @Basic
    public long getCountAddrinfoPermanentErrors() {
        return countAddrinfoPermanentErrors;
    }

    public void setCountAddrinfoPermanentErrors(long countAddrinfoPermanentErrors) {
        this.countAddrinfoPermanentErrors = countAddrinfoPermanentErrors;
    }

    private long countFcrdnsErrors;

    @javax.persistence.Column(name = "COUNT_FCRDNS_ERRORS")
    @Basic
    public long getCountFcrdnsErrors() {
        return countFcrdnsErrors;
    }

    public void setCountFcrdnsErrors(long countFcrdnsErrors) {
        this.countFcrdnsErrors = countFcrdnsErrors;
    }

    private long countHostAclErrors;

    @javax.persistence.Column(name = "COUNT_HOST_ACL_ERRORS")
    @Basic
    public long getCountHostAclErrors() {
        return countHostAclErrors;
    }

    public void setCountHostAclErrors(long countHostAclErrors) {
        this.countHostAclErrors = countHostAclErrors;
    }

    private long countNoAuthPluginErrors;

    @javax.persistence.Column(name = "COUNT_NO_AUTH_PLUGIN_ERRORS")
    @Basic
    public long getCountNoAuthPluginErrors() {
        return countNoAuthPluginErrors;
    }

    public void setCountNoAuthPluginErrors(long countNoAuthPluginErrors) {
        this.countNoAuthPluginErrors = countNoAuthPluginErrors;
    }

    private long countAuthPluginErrors;

    @javax.persistence.Column(name = "COUNT_AUTH_PLUGIN_ERRORS")
    @Basic
    public long getCountAuthPluginErrors() {
        return countAuthPluginErrors;
    }

    public void setCountAuthPluginErrors(long countAuthPluginErrors) {
        this.countAuthPluginErrors = countAuthPluginErrors;
    }

    private long countHandshakeErrors;

    @javax.persistence.Column(name = "COUNT_HANDSHAKE_ERRORS")
    @Basic
    public long getCountHandshakeErrors() {
        return countHandshakeErrors;
    }

    public void setCountHandshakeErrors(long countHandshakeErrors) {
        this.countHandshakeErrors = countHandshakeErrors;
    }

    private long countProxyUserErrors;

    @javax.persistence.Column(name = "COUNT_PROXY_USER_ERRORS")
    @Basic
    public long getCountProxyUserErrors() {
        return countProxyUserErrors;
    }

    public void setCountProxyUserErrors(long countProxyUserErrors) {
        this.countProxyUserErrors = countProxyUserErrors;
    }

    private long countProxyUserAclErrors;

    @javax.persistence.Column(name = "COUNT_PROXY_USER_ACL_ERRORS")
    @Basic
    public long getCountProxyUserAclErrors() {
        return countProxyUserAclErrors;
    }

    public void setCountProxyUserAclErrors(long countProxyUserAclErrors) {
        this.countProxyUserAclErrors = countProxyUserAclErrors;
    }

    private long countAuthenticationErrors;

    @javax.persistence.Column(name = "COUNT_AUTHENTICATION_ERRORS")
    @Basic
    public long getCountAuthenticationErrors() {
        return countAuthenticationErrors;
    }

    public void setCountAuthenticationErrors(long countAuthenticationErrors) {
        this.countAuthenticationErrors = countAuthenticationErrors;
    }

    private long countSslErrors;

    @javax.persistence.Column(name = "COUNT_SSL_ERRORS")
    @Basic
    public long getCountSslErrors() {
        return countSslErrors;
    }

    public void setCountSslErrors(long countSslErrors) {
        this.countSslErrors = countSslErrors;
    }

    private long countMaxUserConnectionsErrors;

    @javax.persistence.Column(name = "COUNT_MAX_USER_CONNECTIONS_ERRORS")
    @Basic
    public long getCountMaxUserConnectionsErrors() {
        return countMaxUserConnectionsErrors;
    }

    public void setCountMaxUserConnectionsErrors(long countMaxUserConnectionsErrors) {
        this.countMaxUserConnectionsErrors = countMaxUserConnectionsErrors;
    }

    private long countMaxUserConnectionsPerHourErrors;

    @javax.persistence.Column(name = "COUNT_MAX_USER_CONNECTIONS_PER_HOUR_ERRORS")
    @Basic
    public long getCountMaxUserConnectionsPerHourErrors() {
        return countMaxUserConnectionsPerHourErrors;
    }

    public void setCountMaxUserConnectionsPerHourErrors(long countMaxUserConnectionsPerHourErrors) {
        this.countMaxUserConnectionsPerHourErrors = countMaxUserConnectionsPerHourErrors;
    }

    private long countDefaultDatabaseErrors;

    @javax.persistence.Column(name = "COUNT_DEFAULT_DATABASE_ERRORS")
    @Basic
    public long getCountDefaultDatabaseErrors() {
        return countDefaultDatabaseErrors;
    }

    public void setCountDefaultDatabaseErrors(long countDefaultDatabaseErrors) {
        this.countDefaultDatabaseErrors = countDefaultDatabaseErrors;
    }

    private long countInitConnectErrors;

    @javax.persistence.Column(name = "COUNT_INIT_CONNECT_ERRORS")
    @Basic
    public long getCountInitConnectErrors() {
        return countInitConnectErrors;
    }

    public void setCountInitConnectErrors(long countInitConnectErrors) {
        this.countInitConnectErrors = countInitConnectErrors;
    }

    private long countLocalErrors;

    @javax.persistence.Column(name = "COUNT_LOCAL_ERRORS")
    @Basic
    public long getCountLocalErrors() {
        return countLocalErrors;
    }

    public void setCountLocalErrors(long countLocalErrors) {
        this.countLocalErrors = countLocalErrors;
    }

    private long countUnknownErrors;

    @javax.persistence.Column(name = "COUNT_UNKNOWN_ERRORS")
    @Basic
    public long getCountUnknownErrors() {
        return countUnknownErrors;
    }

    public void setCountUnknownErrors(long countUnknownErrors) {
        this.countUnknownErrors = countUnknownErrors;
    }

    private Timestamp firstSeen;

    @javax.persistence.Column(name = "FIRST_SEEN")
    @Basic
    public Timestamp getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Timestamp firstSeen) {
        this.firstSeen = firstSeen;
    }

    private Timestamp lastSeen;

    @javax.persistence.Column(name = "LAST_SEEN")
    @Basic
    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }

    private Timestamp firstErrorSeen;

    @javax.persistence.Column(name = "FIRST_ERROR_SEEN")
    @Basic
    public Timestamp getFirstErrorSeen() {
        return firstErrorSeen;
    }

    public void setFirstErrorSeen(Timestamp firstErrorSeen) {
        this.firstErrorSeen = firstErrorSeen;
    }

    private Timestamp lastErrorSeen;

    @javax.persistence.Column(name = "LAST_ERROR_SEEN")
    @Basic
    public Timestamp getLastErrorSeen() {
        return lastErrorSeen;
    }

    public void setLastErrorSeen(Timestamp lastErrorSeen) {
        this.lastErrorSeen = lastErrorSeen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostCacheEntity that = (HostCacheEntity) o;

        if (countAddrinfoPermanentErrors != that.countAddrinfoPermanentErrors) return false;
        if (countAddrinfoTransientErrors != that.countAddrinfoTransientErrors) return false;
        if (countAuthPluginErrors != that.countAuthPluginErrors) return false;
        if (countAuthenticationErrors != that.countAuthenticationErrors) return false;
        if (countDefaultDatabaseErrors != that.countDefaultDatabaseErrors) return false;
        if (countFcrdnsErrors != that.countFcrdnsErrors) return false;
        if (countFormatErrors != that.countFormatErrors) return false;
        if (countHandshakeErrors != that.countHandshakeErrors) return false;
        if (countHostAclErrors != that.countHostAclErrors) return false;
        if (countHostBlockedErrors != that.countHostBlockedErrors) return false;
        if (countInitConnectErrors != that.countInitConnectErrors) return false;
        if (countLocalErrors != that.countLocalErrors) return false;
        if (countMaxUserConnectionsErrors != that.countMaxUserConnectionsErrors) return false;
        if (countMaxUserConnectionsPerHourErrors != that.countMaxUserConnectionsPerHourErrors) return false;
        if (countNameinfoPermanentErrors != that.countNameinfoPermanentErrors) return false;
        if (countNameinfoTransientErrors != that.countNameinfoTransientErrors) return false;
        if (countNoAuthPluginErrors != that.countNoAuthPluginErrors) return false;
        if (countProxyUserAclErrors != that.countProxyUserAclErrors) return false;
        if (countProxyUserErrors != that.countProxyUserErrors) return false;
        if (countSslErrors != that.countSslErrors) return false;
        if (countUnknownErrors != that.countUnknownErrors) return false;
        if (sumConnectErrors != that.sumConnectErrors) return false;
        if (firstErrorSeen != null ? !firstErrorSeen.equals(that.firstErrorSeen) : that.firstErrorSeen != null)
            return false;
        if (firstSeen != null ? !firstSeen.equals(that.firstSeen) : that.firstSeen != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (hostValidated != null ? !hostValidated.equals(that.hostValidated) : that.hostValidated != null)
            return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (lastErrorSeen != null ? !lastErrorSeen.equals(that.lastErrorSeen) : that.lastErrorSeen != null)
            return false;
        if (lastSeen != null ? !lastSeen.equals(that.lastSeen) : that.lastSeen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (hostValidated != null ? hostValidated.hashCode() : 0);
        result = 31 * result + (int) (sumConnectErrors ^ (sumConnectErrors >>> 32));
        result = 31 * result + (int) (countHostBlockedErrors ^ (countHostBlockedErrors >>> 32));
        result = 31 * result + (int) (countNameinfoTransientErrors ^ (countNameinfoTransientErrors >>> 32));
        result = 31 * result + (int) (countNameinfoPermanentErrors ^ (countNameinfoPermanentErrors >>> 32));
        result = 31 * result + (int) (countFormatErrors ^ (countFormatErrors >>> 32));
        result = 31 * result + (int) (countAddrinfoTransientErrors ^ (countAddrinfoTransientErrors >>> 32));
        result = 31 * result + (int) (countAddrinfoPermanentErrors ^ (countAddrinfoPermanentErrors >>> 32));
        result = 31 * result + (int) (countFcrdnsErrors ^ (countFcrdnsErrors >>> 32));
        result = 31 * result + (int) (countHostAclErrors ^ (countHostAclErrors >>> 32));
        result = 31 * result + (int) (countNoAuthPluginErrors ^ (countNoAuthPluginErrors >>> 32));
        result = 31 * result + (int) (countAuthPluginErrors ^ (countAuthPluginErrors >>> 32));
        result = 31 * result + (int) (countHandshakeErrors ^ (countHandshakeErrors >>> 32));
        result = 31 * result + (int) (countProxyUserErrors ^ (countProxyUserErrors >>> 32));
        result = 31 * result + (int) (countProxyUserAclErrors ^ (countProxyUserAclErrors >>> 32));
        result = 31 * result + (int) (countAuthenticationErrors ^ (countAuthenticationErrors >>> 32));
        result = 31 * result + (int) (countSslErrors ^ (countSslErrors >>> 32));
        result = 31 * result + (int) (countMaxUserConnectionsErrors ^ (countMaxUserConnectionsErrors >>> 32));
        result = 31 * result + (int) (countMaxUserConnectionsPerHourErrors ^ (countMaxUserConnectionsPerHourErrors >>> 32));
        result = 31 * result + (int) (countDefaultDatabaseErrors ^ (countDefaultDatabaseErrors >>> 32));
        result = 31 * result + (int) (countInitConnectErrors ^ (countInitConnectErrors >>> 32));
        result = 31 * result + (int) (countLocalErrors ^ (countLocalErrors >>> 32));
        result = 31 * result + (int) (countUnknownErrors ^ (countUnknownErrors >>> 32));
        result = 31 * result + (firstSeen != null ? firstSeen.hashCode() : 0);
        result = 31 * result + (lastSeen != null ? lastSeen.hashCode() : 0);
        result = 31 * result + (firstErrorSeen != null ? firstErrorSeen.hashCode() : 0);
        result = 31 * result + (lastErrorSeen != null ? lastErrorSeen.hashCode() : 0);
        return result;
    }
}
