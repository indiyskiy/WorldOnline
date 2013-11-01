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
@javax.persistence.IdClass(model.database.TimeZoneTransitionTypeEntityPK.class)
@javax.persistence.Table(name = "time_zone_transition_type", schema = "", catalog = "mysql")
@Entity
public class TimeZoneTransitionTypeEntity {
    private int timeZoneId;

    @javax.persistence.Column(name = "Time_zone_id")
    @Id
    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    private int transitionTypeId;

    @javax.persistence.Column(name = "Transition_type_id")
    @Id
    public int getTransitionTypeId() {
        return transitionTypeId;
    }

    public void setTransitionTypeId(int transitionTypeId) {
        this.transitionTypeId = transitionTypeId;
    }

    private int offset;

    @javax.persistence.Column(name = "Offset")
    @Basic
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private byte isDst;

    @javax.persistence.Column(name = "Is_DST")
    @Basic
    public byte getDst() {
        return isDst;
    }

    public void setDst(byte dst) {
        isDst = dst;
    }

    private String abbreviation;

    @javax.persistence.Column(name = "Abbreviation")
    @Basic
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeZoneTransitionTypeEntity that = (TimeZoneTransitionTypeEntity) o;

        if (isDst != that.isDst) return false;
        if (offset != that.offset) return false;
        if (timeZoneId != that.timeZoneId) return false;
        if (transitionTypeId != that.transitionTypeId) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeZoneId;
        result = 31 * result + transitionTypeId;
        result = 31 * result + offset;
        result = 31 * result + (int) isDst;
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        return result;
    }
}
