package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "events_stages_current", schema = "", catalog = "performance_schema")
@Entity
public class EventsStagesCurrentEntity {
    private long threadId;

    @javax.persistence.Column(name = "THREAD_ID")
    @Basic
    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    private long eventId;

    @javax.persistence.Column(name = "EVENT_ID")
    @Basic
    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    private long endEventId;

    @javax.persistence.Column(name = "END_EVENT_ID")
    @Basic
    public long getEndEventId() {
        return endEventId;
    }

    public void setEndEventId(long endEventId) {
        this.endEventId = endEventId;
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

    private String source;

    @javax.persistence.Column(name = "SOURCE")
    @Basic
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private long timerStart;

    @javax.persistence.Column(name = "TIMER_START")
    @Basic
    public long getTimerStart() {
        return timerStart;
    }

    public void setTimerStart(long timerStart) {
        this.timerStart = timerStart;
    }

    private long timerEnd;

    @javax.persistence.Column(name = "TIMER_END")
    @Basic
    public long getTimerEnd() {
        return timerEnd;
    }

    public void setTimerEnd(long timerEnd) {
        this.timerEnd = timerEnd;
    }

    private long timerWait;

    @javax.persistence.Column(name = "TIMER_WAIT")
    @Basic
    public long getTimerWait() {
        return timerWait;
    }

    public void setTimerWait(long timerWait) {
        this.timerWait = timerWait;
    }

    private long nestingEventId;

    @javax.persistence.Column(name = "NESTING_EVENT_ID")
    @Basic
    public long getNestingEventId() {
        return nestingEventId;
    }

    public void setNestingEventId(long nestingEventId) {
        this.nestingEventId = nestingEventId;
    }

    private String nestingEventType;

    @javax.persistence.Column(name = "NESTING_EVENT_TYPE")
    @Basic
    public String getNestingEventType() {
        return nestingEventType;
    }

    public void setNestingEventType(String nestingEventType) {
        this.nestingEventType = nestingEventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsStagesCurrentEntity that = (EventsStagesCurrentEntity) o;

        if (endEventId != that.endEventId) return false;
        if (eventId != that.eventId) return false;
        if (nestingEventId != that.nestingEventId) return false;
        if (threadId != that.threadId) return false;
        if (timerEnd != that.timerEnd) return false;
        if (timerStart != that.timerStart) return false;
        if (timerWait != that.timerWait) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (nestingEventType != null ? !nestingEventType.equals(that.nestingEventType) : that.nestingEventType != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (int) (endEventId ^ (endEventId >>> 32));
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (int) (timerStart ^ (timerStart >>> 32));
        result = 31 * result + (int) (timerEnd ^ (timerEnd >>> 32));
        result = 31 * result + (int) (timerWait ^ (timerWait >>> 32));
        result = 31 * result + (int) (nestingEventId ^ (nestingEventId >>> 32));
        result = 31 * result + (nestingEventType != null ? nestingEventType.hashCode() : 0);
        return result;
    }
}
