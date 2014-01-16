package model.database.worldonlinedb;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 15.01.14
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "AdminRole", schema = "", catalog = "worldonline")
@Entity
public class AdminRoleEntity {
    @EmbeddedId
    AdminRoleID id;
}

@Embeddable
class AdminRoleID implements Serializable {
    private String adminUserName;
    private Integer adminUserRole;

    public AdminRoleID() {}

   public AdminRoleID(String adminUserName, Integer adminUserRole) {
        this.adminUserName = adminUserName;
        this.adminUserRole = adminUserRole;
    }
}