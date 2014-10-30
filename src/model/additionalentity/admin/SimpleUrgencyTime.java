package model.additionalentity.admin;


import java.sql.Timestamp;

public class SimpleUrgencyTime {
    private Timestamp start;
    private Timestamp end;
    private Long urgencyTimeID;

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Timestamp getEnd() {
        return end;
    }

    public Long getUrgencyTimeID() {
        return urgencyTimeID;
    }

    public void setUrgencyTimeID(Long urgencyTimeID) {
        this.urgencyTimeID = urgencyTimeID;
    }
}
