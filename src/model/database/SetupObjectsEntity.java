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
@javax.persistence.Table(name = "setup_objects", schema = "", catalog = "performance_schema")
@Entity
public class SetupObjectsEntity {
    private String objectType;

    @javax.persistence.Column(name = "OBJECT_TYPE")
    @Basic
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    private String objectSchema;

    @javax.persistence.Column(name = "OBJECT_SCHEMA")
    @Basic
    public String getObjectSchema() {
        return objectSchema;
    }

    public void setObjectSchema(String objectSchema) {
        this.objectSchema = objectSchema;
    }

    private String objectName;

    @javax.persistence.Column(name = "OBJECT_NAME")
    @Basic
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    private String enabled;

    @javax.persistence.Column(name = "ENABLED")
    @Basic
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    private String timed;

    @javax.persistence.Column(name = "TIMED")
    @Basic
    public String getTimed() {
        return timed;
    }

    public void setTimed(String timed) {
        this.timed = timed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetupObjectsEntity that = (SetupObjectsEntity) o;

        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (objectName != null ? !objectName.equals(that.objectName) : that.objectName != null) return false;
        if (objectSchema != null ? !objectSchema.equals(that.objectSchema) : that.objectSchema != null) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (timed != null ? !timed.equals(that.timed) : that.timed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectType != null ? objectType.hashCode() : 0;
        result = 31 * result + (objectSchema != null ? objectSchema.hashCode() : 0);
        result = 31 * result + (objectName != null ? objectName.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (timed != null ? timed.hashCode() : 0);
        return result;
    }
}
