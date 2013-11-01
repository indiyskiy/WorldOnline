package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.SlaveMasterInfoEntityPK.class)
@javax.persistence.Table(name = "slave_master_info", schema = "", catalog = "mysql")
@Entity
public class SlaveMasterInfoEntity {
    private int numberOfLines;

    @javax.persistence.Column(name = "Number_of_lines")
    @Basic
    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    private String masterLogName;

    @javax.persistence.Column(name = "Master_log_name")
    @Basic
    public String getMasterLogName() {
        return masterLogName;
    }

    public void setMasterLogName(String masterLogName) {
        this.masterLogName = masterLogName;
    }

    private long masterLogPos;

    @javax.persistence.Column(name = "Master_log_pos")
    @Basic
    public long getMasterLogPos() {
        return masterLogPos;
    }

    public void setMasterLogPos(long masterLogPos) {
        this.masterLogPos = masterLogPos;
    }

    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String userName;

    @javax.persistence.Column(name = "User_name")
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userPassword;

    @javax.persistence.Column(name = "User_password")
    @Basic
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private int port;

    @javax.persistence.Column(name = "Port")
    @Id
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private int connectRetry;

    @javax.persistence.Column(name = "Connect_retry")
    @Basic
    public int getConnectRetry() {
        return connectRetry;
    }

    public void setConnectRetry(int connectRetry) {
        this.connectRetry = connectRetry;
    }

    private boolean enabledSsl;

    @javax.persistence.Column(name = "Enabled_ssl")
    @Basic
    public boolean isEnabledSsl() {
        return enabledSsl;
    }

    public void setEnabledSsl(boolean enabledSsl) {
        this.enabledSsl = enabledSsl;
    }

    private String sslCa;

    @javax.persistence.Column(name = "Ssl_ca")
    @Basic
    public String getSslCa() {
        return sslCa;
    }

    public void setSslCa(String sslCa) {
        this.sslCa = sslCa;
    }

    private String sslCapath;

    @javax.persistence.Column(name = "Ssl_capath")
    @Basic
    public String getSslCapath() {
        return sslCapath;
    }

    public void setSslCapath(String sslCapath) {
        this.sslCapath = sslCapath;
    }

    private String sslCert;

    @javax.persistence.Column(name = "Ssl_cert")
    @Basic
    public String getSslCert() {
        return sslCert;
    }

    public void setSslCert(String sslCert) {
        this.sslCert = sslCert;
    }

    private String sslCipher;

    @javax.persistence.Column(name = "Ssl_cipher")
    @Basic
    public String getSslCipher() {
        return sslCipher;
    }

    public void setSslCipher(String sslCipher) {
        this.sslCipher = sslCipher;
    }

    private String sslKey;

    @javax.persistence.Column(name = "Ssl_key")
    @Basic
    public String getSslKey() {
        return sslKey;
    }

    public void setSslKey(String sslKey) {
        this.sslKey = sslKey;
    }

    private boolean sslVerifyServerCert;

    @javax.persistence.Column(name = "Ssl_verify_server_cert")
    @Basic
    public boolean isSslVerifyServerCert() {
        return sslVerifyServerCert;
    }

    public void setSslVerifyServerCert(boolean sslVerifyServerCert) {
        this.sslVerifyServerCert = sslVerifyServerCert;
    }

    private float heartbeat;

    @javax.persistence.Column(name = "Heartbeat")
    @Basic
    public float getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(float heartbeat) {
        this.heartbeat = heartbeat;
    }

    private String bind;

    @javax.persistence.Column(name = "Bind")
    @Basic
    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    private String ignoredServerIds;

    @javax.persistence.Column(name = "Ignored_server_ids")
    @Basic
    public String getIgnoredServerIds() {
        return ignoredServerIds;
    }

    public void setIgnoredServerIds(String ignoredServerIds) {
        this.ignoredServerIds = ignoredServerIds;
    }

    private String uuid;

    @javax.persistence.Column(name = "Uuid")
    @Basic
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private long retryCount;

    @javax.persistence.Column(name = "Retry_count")
    @Basic
    public long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(long retryCount) {
        this.retryCount = retryCount;
    }

    private String sslCrl;

    @javax.persistence.Column(name = "Ssl_crl")
    @Basic
    public String getSslCrl() {
        return sslCrl;
    }

    public void setSslCrl(String sslCrl) {
        this.sslCrl = sslCrl;
    }

    private String sslCrlpath;

    @javax.persistence.Column(name = "Ssl_crlpath")
    @Basic
    public String getSslCrlpath() {
        return sslCrlpath;
    }

    public void setSslCrlpath(String sslCrlpath) {
        this.sslCrlpath = sslCrlpath;
    }

    private boolean enabledAutoPosition;

    @javax.persistence.Column(name = "Enabled_auto_position")
    @Basic
    public boolean isEnabledAutoPosition() {
        return enabledAutoPosition;
    }

    public void setEnabledAutoPosition(boolean enabledAutoPosition) {
        this.enabledAutoPosition = enabledAutoPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlaveMasterInfoEntity that = (SlaveMasterInfoEntity) o;

        if (connectRetry != that.connectRetry) return false;
        if (enabledAutoPosition != that.enabledAutoPosition) return false;
        if (enabledSsl != that.enabledSsl) return false;
        if (Float.compare(that.heartbeat, heartbeat) != 0) return false;
        if (masterLogPos != that.masterLogPos) return false;
        if (numberOfLines != that.numberOfLines) return false;
        if (port != that.port) return false;
        if (retryCount != that.retryCount) return false;
        if (sslVerifyServerCert != that.sslVerifyServerCert) return false;
        if (bind != null ? !bind.equals(that.bind) : that.bind != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (ignoredServerIds != null ? !ignoredServerIds.equals(that.ignoredServerIds) : that.ignoredServerIds != null)
            return false;
        if (masterLogName != null ? !masterLogName.equals(that.masterLogName) : that.masterLogName != null)
            return false;
        if (sslCa != null ? !sslCa.equals(that.sslCa) : that.sslCa != null) return false;
        if (sslCapath != null ? !sslCapath.equals(that.sslCapath) : that.sslCapath != null) return false;
        if (sslCert != null ? !sslCert.equals(that.sslCert) : that.sslCert != null) return false;
        if (sslCipher != null ? !sslCipher.equals(that.sslCipher) : that.sslCipher != null) return false;
        if (sslCrl != null ? !sslCrl.equals(that.sslCrl) : that.sslCrl != null) return false;
        if (sslCrlpath != null ? !sslCrlpath.equals(that.sslCrlpath) : that.sslCrlpath != null) return false;
        if (sslKey != null ? !sslKey.equals(that.sslKey) : that.sslKey != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numberOfLines;
        result = 31 * result + (masterLogName != null ? masterLogName.hashCode() : 0);
        result = 31 * result + (int) (masterLogPos ^ (masterLogPos >>> 32));
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + connectRetry;
        result = 31 * result + (enabledSsl ? 1 : 0);
        result = 31 * result + (sslCa != null ? sslCa.hashCode() : 0);
        result = 31 * result + (sslCapath != null ? sslCapath.hashCode() : 0);
        result = 31 * result + (sslCert != null ? sslCert.hashCode() : 0);
        result = 31 * result + (sslCipher != null ? sslCipher.hashCode() : 0);
        result = 31 * result + (sslKey != null ? sslKey.hashCode() : 0);
        result = 31 * result + (sslVerifyServerCert ? 1 : 0);
        result = 31 * result + (heartbeat != +0.0f ? Float.floatToIntBits(heartbeat) : 0);
        result = 31 * result + (bind != null ? bind.hashCode() : 0);
        result = 31 * result + (ignoredServerIds != null ? ignoredServerIds.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (int) (retryCount ^ (retryCount >>> 32));
        result = 31 * result + (sslCrl != null ? sslCrl.hashCode() : 0);
        result = 31 * result + (sslCrlpath != null ? sslCrlpath.hashCode() : 0);
        result = 31 * result + (enabledAutoPosition ? 1 : 0);
        return result;
    }
}
