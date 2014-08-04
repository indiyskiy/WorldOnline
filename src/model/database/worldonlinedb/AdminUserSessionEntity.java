package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "AdminUserSession", schema = "", catalog = "worldonline")
@Entity

public class AdminUserSessionEntity {
    @javax.persistence.Column(name = "AdminUserSessionID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminUserSessionID;

    @javax.persistence.Column(name = "SessionKey")
    @Basic
    private String sessionKey;

    @javax.persistence.Column(name = "SessionCreationTimestamp")
    @Basic
    private Timestamp sessionCreationTimestamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AdminUserID")
    private AdminUserEntity adminUser;

    public Long getAdminUserSessionID() {
        return adminUserSessionID;
    }

    public void setAdminUserSessionID(Long adminUserSessionID) {
        this.adminUserSessionID = adminUserSessionID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Timestamp getSessionCreationTimestamp() {
        return sessionCreationTimestamp;
    }

    public void setSessionCreationTimestamp(Timestamp sessionCreationTimestamp) {
        this.sessionCreationTimestamp = sessionCreationTimestamp;
    }

    public AdminUserEntity getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUserEntity adminUser) {
        this.adminUser = adminUser;
    }
}
