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
@javax.persistence.Table(name = "mutex_instances", schema = "", catalog = "performance_schema")
@Entity
public class MutexInstancesEntity {
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

    private long lockedByThreadId;

    @javax.persistence.Column(name = "LOCKED_BY_THREAD_ID")
    @Basic
    public long getLockedByThreadId() {
        return lockedByThreadId;
    }

    public void setLockedByThreadId(long lockedByThreadId) {
        this.lockedByThreadId = lockedByThreadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutexInstancesEntity that = (MutexInstancesEntity) o;

        if (lockedByThreadId != that.lockedByThreadId) return false;
        if (objectInstanceBegin != that.objectInstanceBegin) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (objectInstanceBegin ^ (objectInstanceBegin >>> 32));
        result = 31 * result + (int) (lockedByThreadId ^ (lockedByThreadId >>> 32));
        return result;
    }
}
