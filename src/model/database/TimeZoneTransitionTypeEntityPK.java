package model.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
public class TimeZoneTransitionTypeEntityPK implements Serializable {
    private int timeZoneId;

    @Id
    @Column(name = "Time_zone_id")
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    private int transitionTypeId;

    @Id
    @Column(name = "Transition_type_id")
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

        TimeZoneTransitionTypeEntityPK that = (TimeZoneTransitionTypeEntityPK) o;

        if (timeZoneId != that.timeZoneId) return false;
        if (transitionTypeId != that.transitionTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeZoneId;
        result = 31 * result + transitionTypeId;
        return result;
    }
}
