package model.database.worldonlinedb;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:10
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "User", schema = "", catalog = "worldonline")
@Entity
public class UserEntity{
    @javax.persistence.Column(name = "UserID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserPersonalDataID")
    private UserPersonalDataEntity userPersonalData;

    public UserPersonalDataEntity getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(UserPersonalDataEntity userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserHardwareID")
    private UserHardwareEntity userHardware;

    public UserHardwareEntity getUserHardware() {
        return userHardware;
    }

    public void setUserHardware(UserHardwareEntity userHardware) {
        this.userHardware = userHardware;
    }

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "UserContentID")
    private UserContentEntity userContent;

    public UserContentEntity getUserContent() {
        return userContent;
    }

    public void setUserContent(UserContentEntity userContent) {
        this.userContent = userContent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null)
            return false;
        if (userPersonalData != null ? !userPersonalData.equals(that.userPersonalData) : that.userPersonalData != null)
            return false;
        if (userHardware != null ? !userHardware.equals(that.userHardware) : that.userHardware != null)
            return false;
        if (userContent != null ? !userContent.equals(that.userContent) : that.userContent != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userPersonalData != null ? userPersonalData.hashCode() : 0);
        result = 31 * result + (userHardware != null ? userHardware.hashCode() : 0);
        result = 31 * result + (userContent != null ? userContent.hashCode() : 0);
        return result;
    }
}
