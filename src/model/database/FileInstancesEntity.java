package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "file_instances", schema = "", catalog = "performance_schema")
@Entity
public class FileInstancesEntity {
    private String fileName;

    @javax.persistence.Column(name = "FILE_NAME")
    @Basic
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String eventName;

    @javax.persistence.Column(name = "EVENT_NAME")
    @Basic
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private int openCount;

    @javax.persistence.Column(name = "OPEN_COUNT")
    @Basic
    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInstancesEntity that = (FileInstancesEntity) o;

        if (openCount != that.openCount) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + openCount;
        return result;
    }
}
