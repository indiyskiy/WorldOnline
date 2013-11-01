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
@javax.persistence.Table(name = "setup_instruments", schema = "", catalog = "performance_schema")
@Entity
public class SetupInstrumentsEntity {
    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        SetupInstrumentsEntity that = (SetupInstrumentsEntity) o;

        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (timed != null ? !timed.equals(that.timed) : that.timed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (timed != null ? timed.hashCode() : 0);
        return result;
    }
}
