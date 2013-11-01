package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "events_waits_summary_by_user_by_event_name", schema = "", catalog = "performance_schema")
@Entity
public class EventsWaitsSummaryByUserByEventNameEntity {
    private String user;

    @javax.persistence.Column(name = "USER")
    @Basic
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String eventName;

    @javax.persistence.Column(name = "EVENT_NAME")
    @Basic
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private long countStar;

    @javax.persistence.Column(name = "COUNT_STAR")
    @Basic
    public long getCountStar() {
        return countStar;
    }

    public void setCountStar(long countStar) {
        this.countStar = countStar;
    }

    private long sumTimerWait;

    @javax.persistence.Column(name = "SUM_TIMER_WAIT")
    @Basic
    public long getSumTimerWait() {
        return sumTimerWait;
    }

    public void setSumTimerWait(long sumTimerWait) {
        this.sumTimerWait = sumTimerWait;
    }

    private long minTimerWait;

    @javax.persistence.Column(name = "MIN_TIMER_WAIT")
    @Basic
    public long getMinTimerWait() {
        return minTimerWait;
    }

    public void setMinTimerWait(long minTimerWait) {
        this.minTimerWait = minTimerWait;
    }

    private long avgTimerWait;

    @javax.persistence.Column(name = "AVG_TIMER_WAIT")
    @Basic
    public long getAvgTimerWait() {
        return avgTimerWait;
    }

    public void setAvgTimerWait(long avgTimerWait) {
        this.avgTimerWait = avgTimerWait;
    }

    private long maxTimerWait;

    @javax.persistence.Column(name = "MAX_TIMER_WAIT")
    @Basic
    public long getMaxTimerWait() {
        return maxTimerWait;
    }

    public void setMaxTimerWait(long maxTimerWait) {
        this.maxTimerWait = maxTimerWait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsWaitsSummaryByUserByEventNameEntity that = (EventsWaitsSummaryByUserByEventNameEntity) o;

        if (avgTimerWait != that.avgTimerWait) return false;
        if (countStar != that.countStar) return false;
        if (maxTimerWait != that.maxTimerWait) return false;
        if (minTimerWait != that.minTimerWait) return false;
        if (sumTimerWait != that.sumTimerWait) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (int) (countStar ^ (countStar >>> 32));
        result = 31 * result + (int) (sumTimerWait ^ (sumTimerWait >>> 32));
        result = 31 * result + (int) (minTimerWait ^ (minTimerWait >>> 32));
        result = 31 * result + (int) (avgTimerWait ^ (avgTimerWait >>> 32));
        result = 31 * result + (int) (maxTimerWait ^ (maxTimerWait >>> 32));
        return result;
    }
}
