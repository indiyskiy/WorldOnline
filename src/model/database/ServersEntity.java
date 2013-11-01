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
@javax.persistence.Table(name = "servers", schema = "", catalog = "mysql")
@Entity
public class ServersEntity {
    private String serverName;

    @javax.persistence.Column(name = "Server_name")
    @Id
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    private String host;

    @javax.persistence.Column(name = "Host")
    @Basic
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @javax.persistence.Column(name = "Db")
    @Basic
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String username;

    @javax.persistence.Column(name = "Username")
    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    @javax.persistence.Column(name = "Password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int port;

    @javax.persistence.Column(name = "Port")
    @Basic
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String socket;

    @javax.persistence.Column(name = "Socket")
    @Basic
    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    private String wrapper;

    @javax.persistence.Column(name = "Wrapper")
    @Basic
    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    private String owner;

    @javax.persistence.Column(name = "Owner")
    @Basic
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServersEntity that = (ServersEntity) o;

        if (port != that.port) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null) return false;
        if (socket != null ? !socket.equals(that.socket) : that.socket != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (wrapper != null ? !wrapper.equals(that.wrapper) : that.wrapper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverName != null ? serverName.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (socket != null ? socket.hashCode() : 0);
        result = 31 * result + (wrapper != null ? wrapper.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
