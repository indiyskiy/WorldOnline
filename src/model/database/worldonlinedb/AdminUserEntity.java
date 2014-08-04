package model.database.worldonlinedb;

import javax.persistence.*;

@javax.persistence.Table(name = "AdminUser", schema = "", catalog = "worldonline")
@Entity
public class AdminUserEntity {
    @javax.persistence.Column(name = "AdminUserID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminUserID;

    @javax.persistence.Column(name = "AdminUserName")
    @Basic
    private String adminUserName;

    @javax.persistence.Column(name = "AdminUserPassword")
    @Basic
    private String adminUserPassword;

    @javax.persistence.Column(name = "Confirmed")
    @Basic
    private Boolean confirmed;

    @javax.persistence.Column(name = "AdminRole")
    @Basic
    private Integer adminRole;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AdminUserAdditionalInfoID")
    private AdminUserAdditionalInfoEntity adminUserAdditionalInfo;


    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminUserPassword() {
        return adminUserPassword;
    }

    public void setAdminUserPassword(String adminUserPassword) {
        this.adminUserPassword = adminUserPassword;
    }

    public Long getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(Long adminUserID) {
        this.adminUserID = adminUserID;
    }

    public Integer getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(Integer adminRole) {
        this.adminRole = adminRole;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public AdminUserAdditionalInfoEntity getAdminUserAdditionalInfo() {
        return adminUserAdditionalInfo;
    }

    public void setAdminUserAdditionalInfo(AdminUserAdditionalInfoEntity adminUserAdditionalInfo) {
        this.adminUserAdditionalInfo = adminUserAdditionalInfo;
    }
}

