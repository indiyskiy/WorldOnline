package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "TABLESPACES", schema = "", catalog = "information_schema")
@Entity
public class TablespacesEntity {
    private String tablespaceName;

    @javax.persistence.Column(name = "TABLESPACE_NAME")
    @Basic
    public String getTablespaceName() {
        return tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    private String engine;

    @javax.persistence.Column(name = "ENGINE")
    @Basic
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    private String tablespaceType;

    @javax.persistence.Column(name = "TABLESPACE_TYPE")
    @Basic
    public String getTablespaceType() {
        return tablespaceType;
    }

    public void setTablespaceType(String tablespaceType) {
        this.tablespaceType = tablespaceType;
    }

    private String logfileGroupName;

    @javax.persistence.Column(name = "LOGFILE_GROUP_NAME")
    @Basic
    public String getLogfileGroupName() {
        return logfileGroupName;
    }

    public void setLogfileGroupName(String logfileGroupName) {
        this.logfileGroupName = logfileGroupName;
    }

    private long extentSize;

    @javax.persistence.Column(name = "EXTENT_SIZE")
    @Basic
    public long getExtentSize() {
        return extentSize;
    }

    public void setExtentSize(long extentSize) {
        this.extentSize = extentSize;
    }

    private long autoextendSize;

    @javax.persistence.Column(name = "AUTOEXTEND_SIZE")
    @Basic
    public long getAutoextendSize() {
        return autoextendSize;
    }

    public void setAutoextendSize(long autoextendSize) {
        this.autoextendSize = autoextendSize;
    }

    private long maximumSize;

    @javax.persistence.Column(name = "MAXIMUM_SIZE")
    @Basic
    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }

    private long nodegroupId;

    @javax.persistence.Column(name = "NODEGROUP_ID")
    @Basic
    public long getNodegroupId() {
        return nodegroupId;
    }

    public void setNodegroupId(long nodegroupId) {
        this.nodegroupId = nodegroupId;
    }

    private String tablespaceComment;

    @javax.persistence.Column(name = "TABLESPACE_COMMENT")
    @Basic
    public String getTablespaceComment() {
        return tablespaceComment;
    }

    public void setTablespaceComment(String tablespaceComment) {
        this.tablespaceComment = tablespaceComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TablespacesEntity that = (TablespacesEntity) o;

        if (autoextendSize != that.autoextendSize) return false;
        if (extentSize != that.extentSize) return false;
        if (maximumSize != that.maximumSize) return false;
        if (nodegroupId != that.nodegroupId) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (logfileGroupName != null ? !logfileGroupName.equals(that.logfileGroupName) : that.logfileGroupName != null)
            return false;
        if (tablespaceComment != null ? !tablespaceComment.equals(that.tablespaceComment) : that.tablespaceComment != null)
            return false;
        if (tablespaceName != null ? !tablespaceName.equals(that.tablespaceName) : that.tablespaceName != null)
            return false;
        if (tablespaceType != null ? !tablespaceType.equals(that.tablespaceType) : that.tablespaceType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tablespaceName != null ? tablespaceName.hashCode() : 0;
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (tablespaceType != null ? tablespaceType.hashCode() : 0);
        result = 31 * result + (logfileGroupName != null ? logfileGroupName.hashCode() : 0);
        result = 31 * result + (int) (extentSize ^ (extentSize >>> 32));
        result = 31 * result + (int) (autoextendSize ^ (autoextendSize >>> 32));
        result = 31 * result + (int) (maximumSize ^ (maximumSize >>> 32));
        result = 31 * result + (int) (nodegroupId ^ (nodegroupId >>> 32));
        result = 31 * result + (tablespaceComment != null ? tablespaceComment.hashCode() : 0);
        return result;
    }
}
