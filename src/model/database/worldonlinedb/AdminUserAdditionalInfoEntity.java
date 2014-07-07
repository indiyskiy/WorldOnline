package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "AdminUserAdditionalInfo", schema = "", catalog = "worldonline")
@Entity
public class AdminUserAdditionalInfoEntity {
    @javax.persistence.Column(name = "AdminUserAdditionalInfoID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminUserAdditionalInfoID;

    @javax.persistence.Column(name = "AdminUserEmail")
    @Basic
    private String adminUserEmail;

    @javax.persistence.Column(name = "AdminUserFirstName")
    @Basic
    private String adminUserFirstName;

    @javax.persistence.Column(name = "AdminUserSecondName")
    @Basic
    private String adminUserSecondName;

    @javax.persistence.Column(name = "AdminUserRegistrationTimestamp")
    @Basic
    private Timestamp adminUserRegistrationTimestamp;

    public Long getAdminUserAdditionalInfoID() {
        return adminUserAdditionalInfoID;
    }

    public void setAdminUserAdditionalInfoID(Long adminUserAdditionalInfoID) {
        this.adminUserAdditionalInfoID = adminUserAdditionalInfoID;
    }

    public String getAdminUserEmail() {
        return adminUserEmail;
    }

    public void setAdminUserEmail(String adminUserEmail) {
        this.adminUserEmail = adminUserEmail;
    }

    public String getAdminUserFirstName() {
        return adminUserFirstName;
    }

    public void setAdminUserFirstName(String adminUserFirstName) {
        this.adminUserFirstName = adminUserFirstName;
    }

    public String getAdminUserSecondName() {
        return adminUserSecondName;
    }

    public void setAdminUserSecondName(String adminUserSecondName) {
        this.adminUserSecondName = adminUserSecondName;
    }

    public Timestamp getAdminUserRegistrationTimestamp() {
        return adminUserRegistrationTimestamp;
    }

    public void setAdminUserRegistrationTimestamp(Timestamp adminUserRegistrationTimestamp) {
        this.adminUserRegistrationTimestamp = adminUserRegistrationTimestamp;
    }
}
