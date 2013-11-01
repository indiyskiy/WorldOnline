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
@javax.persistence.Table(name = "performance_timers", schema = "", catalog = "performance_schema")
@Entity
public class PerformanceTimersEntity {
    private String timerName;

    @javax.persistence.Column(name = "TIMER_NAME")
    @Basic
    public String getTimerName() {
        return timerName;
    }

    public void setTimerName(String timerName) {
        this.timerName = timerName;
    }

    private long timerFrequency;

    @javax.persistence.Column(name = "TIMER_FREQUENCY")
    @Basic
    public long getTimerFrequency() {
        return timerFrequency;
    }

    public void setTimerFrequency(long timerFrequency) {
        this.timerFrequency = timerFrequency;
    }

    private long timerResolution;

    @javax.persistence.Column(name = "TIMER_RESOLUTION")
    @Basic
    public long getTimerResolution() {
        return timerResolution;
    }

    public void setTimerResolution(long timerResolution) {
        this.timerResolution = timerResolution;
    }

    private long timerOverhead;

    @javax.persistence.Column(name = "TIMER_OVERHEAD")
    @Basic
    public long getTimerOverhead() {
        return timerOverhead;
    }

    public void setTimerOverhead(long timerOverhead) {
        this.timerOverhead = timerOverhead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformanceTimersEntity that = (PerformanceTimersEntity) o;

        if (timerFrequency != that.timerFrequency) return false;
        if (timerOverhead != that.timerOverhead) return false;
        if (timerResolution != that.timerResolution) return false;
        if (timerName != null ? !timerName.equals(that.timerName) : that.timerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timerName != null ? timerName.hashCode() : 0;
        result = 31 * result + (int) (timerFrequency ^ (timerFrequency >>> 32));
        result = 31 * result + (int) (timerResolution ^ (timerResolution >>> 32));
        result = 31 * result + (int) (timerOverhead ^ (timerOverhead >>> 32));
        return result;
    }
}
