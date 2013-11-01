package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.TimeZoneTransitionEntityPK.class)
@javax.persistence.Table(name = "time_zone_transition", schema = "", catalog = "mysql")
@Entity
public class TimeZoneTransitionEntity {
    private int timeZoneId;

    @javax.persistence.Column(name = "Time_zone_id")
    @Id
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    private long transitionTime;

    @javax.persistence.Column(name = "Transition_time")
    @Id
    public long getTransitionTime() {
        return transitionTime;
    }

    public void setTransitionTime(long transitionTime) {
        this.transitionTime = transitionTime;
    }

    private int transitionTypeId;

    @javax.persistence.Column(name = "Transition_type_id")
    @Basic
    public int getTransitionTypeId() {
        return transitionTypeId;
    }

    public void setTransitionTypeId(int transitionTypeId) {
        this.transitionTypeId = transitionTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneTransitionEntity that = (TimeZoneTransitionEntity) o;

        if (timeZoneId != that.timeZoneId) return false;
        if (transitionTime != that.transitionTime) return false;
        if (transitionTypeId != that.transitionTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeZoneId;
        result = 31 * result + (int) (transitionTime ^ (transitionTime >>> 32));
        result = 31 * result + transitionTypeId;
        return result;
    }
}
