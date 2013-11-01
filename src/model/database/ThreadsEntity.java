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
@javax.persistence.Table(name = "threads", schema = "", catalog = "performance_schema")
@Entity
public class ThreadsEntity {
    private long threadId;

    @javax.persistence.Column(name = "THREAD_ID")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @javax.persistence.Column(name = "TYPE")
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private long processlistId;

    @javax.persistence.Column(name = "PROCESSLIST_ID")
    @Basic
    public long getProcesslistId() {
        return processlistId;
    }

    public void setProcesslistId(long processlistId) {
        this.processlistId = processlistId;
    }

    private String processlistUser;

    @javax.persistence.Column(name = "PROCESSLIST_USER")
    @Basic
    public String getProcesslistUser() {
        return processlistUser;
    }

    public void setProcesslistUser(String processlistUser) {
        this.processlistUser = processlistUser;
    }

    private String processlistHost;

    @javax.persistence.Column(name = "PROCESSLIST_HOST")
    @Basic
    public String getProcesslistHost() {
        return processlistHost;
    }

    public void setProcesslistHost(String processlistHost) {
        this.processlistHost = processlistHost;
    }

    private String processlistDb;

    @javax.persistence.Column(name = "PROCESSLIST_DB")
    @Basic
    public String getProcesslistDb() {
        return processlistDb;
    }

    public void setProcesslistDb(String processlistDb) {
        this.processlistDb = processlistDb;
    }

    private String processlistCommand;

    @javax.persistence.Column(name = "PROCESSLIST_COMMAND")
    @Basic
    public String getProcesslistCommand() {
        return processlistCommand;
    }

    public void setProcesslistCommand(String processlistCommand) {
        this.processlistCommand = processlistCommand;
    }

    private long processlistTime;

    @javax.persistence.Column(name = "PROCESSLIST_TIME")
    @Basic
    public long getProcesslistTime() {
        return processlistTime;
    }

    public void setProcesslistTime(long processlistTime) {
        this.processlistTime = processlistTime;
    }

    private String processlistState;

    @javax.persistence.Column(name = "PROCESSLIST_STATE")
    @Basic
    public String getProcesslistState() {
        return processlistState;
    }

    public void setProcesslistState(String processlistState) {
        this.processlistState = processlistState;
    }

    private String processlistInfo;

    @javax.persistence.Column(name = "PROCESSLIST_INFO")
    @Basic
    public String getProcesslistInfo() {
        return processlistInfo;
    }

    public void setProcesslistInfo(String processlistInfo) {
        this.processlistInfo = processlistInfo;
    }

    private long parentThreadId;

    @javax.persistence.Column(name = "PARENT_THREAD_ID")
    @Basic
    public long getParentThreadId() {
        return parentThreadId;
    }

    public void setParentThreadId(long parentThreadId) {
        this.parentThreadId = parentThreadId;
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

    private String instrumented;

    @javax.persistence.Column(name = "INSTRUMENTED")
    @Basic
    public String getInstrumented() {
        return instrumented;
    }

    public void setInstrumented(String instrumented) {
        this.instrumented = instrumented;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadsEntity that = (ThreadsEntity) o;

        if (parentThreadId != that.parentThreadId) return false;
        if (processlistId != that.processlistId) return false;
        if (processlistTime != that.processlistTime) return false;
        if (threadId != that.threadId) return false;
        if (instrumented != null ? !instrumented.equals(that.instrumented) : that.instrumented != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (processlistCommand != null ? !processlistCommand.equals(that.processlistCommand) : that.processlistCommand != null)
            return false;
        if (processlistDb != null ? !processlistDb.equals(that.processlistDb) : that.processlistDb != null)
            return false;
        if (processlistHost != null ? !processlistHost.equals(that.processlistHost) : that.processlistHost != null)
            return false;
        if (processlistInfo != null ? !processlistInfo.equals(that.processlistInfo) : that.processlistInfo != null)
            return false;
        if (processlistState != null ? !processlistState.equals(that.processlistState) : that.processlistState != null)
            return false;
        if (processlistUser != null ? !processlistUser.equals(that.processlistUser) : that.processlistUser != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (processlistId ^ (processlistId >>> 32));
        result = 31 * result + (processlistUser != null ? processlistUser.hashCode() : 0);
        result = 31 * result + (processlistHost != null ? processlistHost.hashCode() : 0);
        result = 31 * result + (processlistDb != null ? processlistDb.hashCode() : 0);
        result = 31 * result + (processlistCommand != null ? processlistCommand.hashCode() : 0);
        result = 31 * result + (int) (processlistTime ^ (processlistTime >>> 32));
        result = 31 * result + (processlistState != null ? processlistState.hashCode() : 0);
        result = 31 * result + (processlistInfo != null ? processlistInfo.hashCode() : 0);
        result = 31 * result + (int) (parentThreadId ^ (parentThreadId >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (instrumented != null ? instrumented.hashCode() : 0);
        return result;
    }
}
