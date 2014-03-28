package model.database.worldonlinedb;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 15.01.14
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "AdminUser", schema = "", catalog = "worldonline")
@Entity
public class AdminUserEntity {
    @javax.persistence.Column(name = "adminUserName")
    @Id
    private String adminUserName;

    @javax.persistence.Column(name = "adminUserPassword")
    @Basic
    private String adminUserPassword;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AdminRole")
    private AdminRoleEntity adminRole;

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
}
