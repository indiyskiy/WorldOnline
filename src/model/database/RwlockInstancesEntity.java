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
@javax.persistence.Table(name = "rwlock_instances", schema = "", catalog = "performance_schema")
@Entity
public class RwlockInstancesEntity {
    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private long writeLockedByThreadId;

    @javax.persistence.Column(name = "WRITE_LOCKED_BY_THREAD_ID")
    @Basic
    public long getWriteLockedByThreadId() {
        return writeLockedByThreadId;
    }

    public void setWriteLockedByThreadId(long writeLockedByThreadId) {
        this.writeLockedByThreadId = writeLockedByThreadId;
    }

    private int readLockedByCount;

    @javax.persistence.Column(name = "READ_LOCKED_BY_COUNT")
    @Basic
    public int getReadLockedByCount() {
        return readLockedByCount;
    }

    public void setReadLockedByCount(int readLockedByCount) {
        this.readLockedByCount = readLockedByCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RwlockInstancesEntity that = (RwlockInstancesEntity) o;

        if (objectInstanceBegin != that.objectInstanceBegin) return false;
        if (readLockedByCount != that.readLockedByCount) return false;
        if (writeLockedByThreadId != that.writeLockedByThreadId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (objectInstanceBegin ^ (objectInstanceBegin >>> 32));
        result = 31 * result + (int) (writeLockedByThreadId ^ (writeLockedByThreadId >>> 32));
        result = 31 * result + readLockedByCount;
        return result;
    }
}
