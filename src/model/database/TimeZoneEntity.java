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
@javax.persistence.Table(name = "time_zone", schema = "", catalog = "mysql")
@Entity
public class TimeZoneEntity {
    private int timeZoneId;

    @javax.persistence.Column(name = "Time_zone_id")
    @Id
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    private String useLeapSeconds;

    @javax.persistence.Column(name = "Use_leap_seconds")
    @Basic
    public String getUseLeapSeconds() {
        return useLeapSeconds;
    }

    public void setUseLeapSeconds(String useLeapSeconds) {
        this.useLeapSeconds = useLeapSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneEntity that = (TimeZoneEntity) o;

        if (timeZoneId != that.timeZoneId) return false;
        if (useLeapSeconds != null ? !useLeapSeconds.equals(that.useLeapSeconds) : that.useLeapSeconds != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeZoneId;
        result = 31 * result + (useLeapSeconds != null ? useLeapSeconds.hashCode() : 0);
        return result;
    }
}
