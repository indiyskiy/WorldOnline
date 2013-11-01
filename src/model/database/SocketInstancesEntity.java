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
@javax.persistence.Table(name = "socket_instances", schema = "", catalog = "performance_schema")
@Entity
public class SocketInstancesEntity {
    private String eventName;

    @javax.persistence.Column(name = "EVENT_NAME")
    @Basic
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private long objectInstanceBegin;

    @javax.persistence.Column(name = "OBJECT_INSTANCE_BEGIN")
    @Basic
    public long getObjectInstanceBegin() {
        return objectInstanceBegin;
    }

    public void setObjectInstanceBegin(long objectInstanceBegin) {
        this.objectInstanceBegin = objectInstanceBegin;
    }

    private long threadId;

    @javax.persistence.Column(name = "THREAD_ID")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    private int socketId;

    @javax.persistence.Column(name = "SOCKET_ID")
    @Basic
    public int getSocketId() {
        return socketId;
    }

    public void setSocketId(int socketId) {
        this.socketId = socketId;
    }

    private String ip;

    @javax.persistence.Column(name = "IP")
    @Basic
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private int port;

    @javax.persistence.Column(name = "PORT")
    @Basic
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketInstancesEntity that = (SocketInstancesEntity) o;

        if (objectInstanceBegin != that.objectInstanceBegin) return false;
        if (port != that.port) return false;
        if (socketId != that.socketId) return false;
        if (threadId != that.threadId) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + (int) (objectInstanceBegin ^ (objectInstanceBegin >>> 32));
        result = 31 * result + (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + socketId;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
