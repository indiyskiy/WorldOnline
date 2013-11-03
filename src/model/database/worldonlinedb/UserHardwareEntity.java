package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 25.10.13
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "UserHardware", schema = "", catalog = "worldonline")
@Entity
public class UserHardwareEntity {
    @javax.persistence.Column(name = "UserHardwareID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userHardwareID;

    public Long getUserHardwareID() {
        return userHardwareID;
    }

    public void setUserHardwareID(Long userHardwareID) {
        this.userHardwareID = userHardwareID;
    }

    @javax.persistence.Column(name = "DeviceUniqueKey")
    @Basic
    private String deviceUniqueKey;

    public String getDeviceUniqueKey() {
        return deviceUniqueKey;
    }

    public void setDeviceUniqueKey(String deviceUniqueKey) {
        this.deviceUniqueKey = deviceUniqueKey;
    }

    @javax.persistence.Column(name = "DeviceTokenKey")
    @Basic
    private String deviceTokenKey;

    public String getDeviceTokenKey() {
        return deviceTokenKey;
    }

    public void setDeviceTokenKey(String deviceTokenKey) {
        this.deviceTokenKey = deviceTokenKey;
    }


    @OneToOne
    @JoinColumn(name = "UserGlobalVersionID")
    private GlobalVersionEntity globalVersionEntity;

    public GlobalVersionEntity getGlobalVersionEntity() {
        return globalVersionEntity;
    }

    public void setGlobalVersionEntity(GlobalVersionEntity globalVersionEntity) {
        this.globalVersionEntity = globalVersionEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserHardwareEntity that = (UserHardwareEntity) o;

        if (userHardwareID != null ? !userHardwareID.equals(that.userHardwareID) : that.userHardwareID != null)
            return false;
        if (deviceUniqueKey != null ? !deviceUniqueKey.equals(that.deviceUniqueKey) : that.deviceUniqueKey != null)
            return false;
        if (deviceTokenKey != null ? !deviceTokenKey.equals(that.deviceTokenKey) : that.deviceTokenKey != null)
            return false;
        if (globalVersionEntity != null ? !globalVersionEntity.equals(that.globalVersionEntity) : that.globalVersionEntity != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userHardwareID != null ? userHardwareID.hashCode() : 0;
        result = 31 * result + (deviceUniqueKey != null ? deviceUniqueKey.hashCode() : 0);
        result = 31 * result + (deviceTokenKey != null ? deviceTokenKey.hashCode() : 0);
        result = 31 * result + (globalVersionEntity != null ? globalVersionEntity.hashCode() : 0);
        return result;
    }
}
