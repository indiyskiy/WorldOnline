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
public class TimeZoneTransitionEntityPK implements Serializable {
    private int timeZoneId;

    @Id
    @Column(name = "Time_zone_id")
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    private long transitionTime;

    @Id
    @Column(name = "Transition_time")
    public long getTransitionTime() {
        return transitionTime;
    }

    public void setTransitionTime(long transitionTime) {
        this.transitionTime = transitionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneTransitionEntityPK that = (TimeZoneTransitionEntityPK) o;

        if (timeZoneId != that.timeZoneId) return false;
        if (transitionTime != that.transitionTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeZoneId;
        result = 31 * result + (int) (transitionTime ^ (transitionTime >>> 32));
        return result;
    }
}
