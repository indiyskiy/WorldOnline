package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "general_log", schema = "", catalog = "mysql")
@Entity
public class GeneralLogEntity {
    private Timestamp eventTime;

    @javax.persistence.Column(name = "event_time")
    @Basic
    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    private String userHost;

    @javax.persistence.Column(name = "user_host")
    @Basic
    public String getUserHost() {
        return userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    private long threadId;

    @javax.persistence.Column(name = "thread_id")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    private int serverId;

    @javax.persistence.Column(name = "server_id")
    @Basic
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    private String commandType;

    @javax.persistence.Column(name = "command_type")
    @Basic
    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    private String argument;

    @javax.persistence.Column(name = "argument")
    @Basic
    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralLogEntity that = (GeneralLogEntity) o;

        if (serverId != that.serverId) return false;
        if (threadId != that.threadId) return false;
        if (argument != null ? !argument.equals(that.argument) : that.argument != null) return false;
        if (commandType != null ? !commandType.equals(that.commandType) : that.commandType != null) return false;
        if (eventTime != null ? !eventTime.equals(that.eventTime) : that.eventTime != null) return false;
        if (userHost != null ? !userHost.equals(that.userHost) : that.userHost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventTime != null ? eventTime.hashCode() : 0;
        result = 31 * result + (userHost != null ? userHost.hashCode() : 0);
        result = 31 * result + (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + serverId;
        result = 31 * result + (commandType != null ? commandType.hashCode() : 0);
        result = 31 * result + (argument != null ? argument.hashCode() : 0);
        return result;
    }
}
