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
@javax.persistence.Table(name = "setup_timers", schema = "", catalog = "performance_schema")
@Entity
public class SetupTimersEntity {
    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String timerName;

    @javax.persistence.Column(name = "TIMER_NAME")
    @Basic
    public String getTimerName() {
        return timerName;
    }

    public void setTimerName(String timerName) {
        this.timerName = timerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetupTimersEntity that = (SetupTimersEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (timerName != null ? !timerName.equals(that.timerName) : that.timerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (timerName != null ? timerName.hashCode() : 0);
        return result;
    }
}
