package model.additionalentity.admin;

public class CardUrgencyTime {
    private SimpleDate start;
    private SimpleDate end;
    private Long urgencyTimeID;

    public SimpleDate getStart() {
        return start;
    }

    public void setStart(SimpleDate start) {
        this.start = start;
    }

    public SimpleDate getEnd() {
        return end;
    }

    public void setEnd(SimpleDate end) {
        this.end = end;
    }

    public Long getUrgencyTimeID() {
        return urgencyTimeID;
    }

    public void setUrgencyTimeID(Long urgencyTimeID) {
        this.urgencyTimeID = urgencyTimeID;
    }
}
