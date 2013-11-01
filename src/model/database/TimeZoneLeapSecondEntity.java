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
@javax.persistence.Table(name = "time_zone_leap_second", schema = "", catalog = "mysql")
@Entity
public class TimeZoneLeapSecondEntity {
    private long transitionTime;

    @javax.persistence.Column(name = "Transition_time")
    @Id
    public long getTransitionTime() {
        return transitionTime;
    }

    public void setTransitionTime(long transitionTime) {
        this.transitionTime = transitionTime;
    }

    private int correction;

    @javax.persistence.Column(name = "Correction")
    @Basic
    public int getCorrection() {
        return correction;
    }

    public void setCorrection(int correction) {
        this.correction = correction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneLeapSecondEntity that = (TimeZoneLeapSecondEntity) o;

        if (correction != that.correction) return false;
        if (transitionTime != that.transitionTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (transitionTime ^ (transitionTime >>> 32));
        result = 31 * result + correction;
        return result;
    }
}
