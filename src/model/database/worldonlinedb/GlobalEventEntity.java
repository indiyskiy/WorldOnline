package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "GlobalEvent", schema = "", catalog = "worldonline")
@Entity
public class GlobalEventEntity {
    @Column(name = "GlobalEventID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long globalEventID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    @Column(name = "GlobalEventType")
    @Basic
    private Integer globalEventType;

    @Column(name = "EventTimestamp")
    @Basic
    private Timestamp eventTimestamp;

    @Column(name = "Text")
    @Basic
    private String text;

    public GlobalEventEntity() {
    }

    public GlobalEventEntity(UserContentEntity userContent, Integer globalEventType, Timestamp eventTimestamp) {
        this.userContent = userContent;
        this.globalEventType = globalEventType;
        this.eventTimestamp = eventTimestamp;
    }

    public GlobalEventEntity(UserContentEntity userContent, Integer globalEventType, Timestamp eventTimestamp, String text) {
        this.userContent = userContent;
        this.globalEventType = globalEventType;
        this.eventTimestamp = eventTimestamp;
        this.text = text;
    }

    public Long getGlobalEventID() {
        return globalEventID;
    }

    public void setGlobalEventID(Long globalEventID) {
        this.globalEventID = globalEventID;
    }

    public UserContentEntity getUserContent() {
        return userContent;
    }

    public void setUserContent(UserContentEntity userContent) {
        this.userContent = userContent;
    }

    public Integer getGlobalEventType() {
        return globalEventType;
    }

    public void setGlobalEventType(Integer globalEventType) {
        this.globalEventType = globalEventType;
    }

    public Timestamp getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Timestamp eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalEventEntity that = (GlobalEventEntity) o;

        if (!eventTimestamp.equals(that.eventTimestamp)) return false;
        if (!globalEventID.equals(that.globalEventID)) return false;
        if (!globalEventType.equals(that.globalEventType)) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (!userContent.equals(that.userContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = globalEventID.hashCode();
        result = 31 * result + userContent.hashCode();
        result = 31 * result + globalEventType.hashCode();
        result = 31 * result + eventTimestamp.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
