package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 28.10.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "UserContent", schema = "", catalog = "worldonline")
@Entity
public class UserContentEntity {

    @javax.persistence.Column(name = "UserContentID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userContentID;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "GlobalVersionID")
    private GlobalVersionEntity globalVersion;
    @javax.persistence.Column(name = "LastConnectionTimestamp")
    @Basic
    private Timestamp lastConnectionTimestamp;
    @javax.persistence.Column(name = "LastSynchronizeTimestamp")
    @Basic
    private Timestamp lastSynchronizeTimestamp;

    public static UserContentEntity getNewUserContentEntity() {
        UserContentEntity userContentEntity = new UserContentEntity();
        userContentEntity.setLastConnectionTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return userContentEntity;
    }

    public Long getUserContentID() {
        return userContentID;
    }

    public void setUserContentID(Long userContentID) {
        this.userContentID = userContentID;
    }

    public GlobalVersionEntity getGlobalVersion() {
        return globalVersion;
    }

    public void setGlobalVersion(GlobalVersionEntity globalVersion) {
        this.globalVersion = globalVersion;
    }

    public Timestamp getLastConnectionTimestamp() {
        return lastConnectionTimestamp;
    }

    public void setLastConnectionTimestamp(Timestamp lastConnectionTimestamp) {
        this.lastConnectionTimestamp = lastConnectionTimestamp;
    }

    public Timestamp getLastSynchronizeTimestamp() {
        return lastSynchronizeTimestamp;
    }

    public void setLastSynchronizeTimestamp(Timestamp lastSynchronizeTimestamp) {
        this.lastSynchronizeTimestamp = lastSynchronizeTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserContentEntity that = (UserContentEntity) o;

        if (userContentID != null ? !userContentID.equals(that.userContentID) : that.userContentID != null)
            return false;
        if (globalVersion != null ? !globalVersion.equals(that.globalVersion) : that.globalVersion != null)
            return false;
        if (lastConnectionTimestamp != null ? !lastConnectionTimestamp.equals(that.lastConnectionTimestamp) : that.lastConnectionTimestamp != null)
            return false;
        if (lastSynchronizeTimestamp != null ? !lastSynchronizeTimestamp.equals(that.lastSynchronizeTimestamp) : that.lastSynchronizeTimestamp != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userContentID != null ? userContentID.hashCode() : 0;
        result = 31 * result + (globalVersion != null ? globalVersion.hashCode() : 0);
        result = 31 * result + (lastConnectionTimestamp != null ? lastConnectionTimestamp.hashCode() : 0);
        result = 31 * result + (lastSynchronizeTimestamp != null ? lastSynchronizeTimestamp.hashCode() : 0);
        return result;
    }

    public void updateLastSinc() {
        this.lastSynchronizeTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
