package model.database.worldonlinedb;

import model.constants.databaseenumeration.MobilePlatform;

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
    @javax.persistence.Column(name = "DeviceUniqueKey")
    @Basic
    private String deviceUniqueKey;
    @javax.persistence.Column(name = "DeviceTokenKey")
    @Basic
    private String deviceTokenKey;
    @javax.persistence.Column(name = "MobilePlatform")
    @Basic
    private Integer mobilePlatform;

    public UserHardwareEntity() {

    }

    public UserHardwareEntity(String deviceUniqueKey, String deviceTokenKey, MobilePlatform mobilePlatform) {
        this.deviceUniqueKey = deviceUniqueKey;
        this.deviceTokenKey = deviceTokenKey;
        this.mobilePlatform=mobilePlatform.getValue();
    }

    public Long getUserHardwareID() {
        return userHardwareID;
    }

    public void setUserHardwareID(Long userHardwareID) {
        this.userHardwareID = userHardwareID;
    }

    public String getDeviceUniqueKey() {
        return deviceUniqueKey;
    }

    public void setDeviceUniqueKey(String deviceUniqueKey) {
        this.deviceUniqueKey = deviceUniqueKey;
    }

    public String getDeviceTokenKey() {
        return deviceTokenKey;
    }

    public void setDeviceTokenKey(String deviceTokenKey) {
        this.deviceTokenKey = deviceTokenKey;
    }

    public Integer getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(Integer mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
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
        if (deviceTokenKey != null ? !deviceTokenKey.equals(that.deviceTokenKey) : that.deviceTokenKey != null)
            return false;
        if (mobilePlatform != null ? !mobilePlatform.equals(that.mobilePlatform) : that.mobilePlatform != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userHardwareID != null ? userHardwareID.hashCode() : 0;
        result = 31 * result + (deviceUniqueKey != null ? deviceUniqueKey.hashCode() : 0);
        result = 31 * result + (deviceTokenKey != null ? deviceTokenKey.hashCode() : 0);
        result = 31 * result + (mobilePlatform != null ? mobilePlatform.hashCode() : 0);
        return result;
    }
}
