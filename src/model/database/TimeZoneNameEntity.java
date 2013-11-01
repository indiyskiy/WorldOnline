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
@javax.persistence.Table(name = "time_zone_name", schema = "", catalog = "mysql")
@Entity
public class TimeZoneNameEntity {
    private String name;

    @javax.persistence.Column(name = "Name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int timeZoneId;

    @javax.persistence.Column(name = "Time_zone_id")
    @Basic
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneNameEntity that = (TimeZoneNameEntity) o;

        if (timeZoneId != that.timeZoneId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + timeZoneId;
        return result;
    }
}
