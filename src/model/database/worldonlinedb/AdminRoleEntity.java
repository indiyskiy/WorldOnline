package model.database.worldonlinedb;

import javax.persistence.*;
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
    @javax.persistence.Column(name = "AdminRoleID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long AdminRoleID;

    @javax.persistence.Column(name = "AdminRoleName")
    @Basic
    private String adminRoleName;

}



