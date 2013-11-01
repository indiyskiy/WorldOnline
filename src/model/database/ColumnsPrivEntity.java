package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.ColumnsPrivEntityPK.class)
@javax.persistence.Table(name = "columns_priv", schema = "", catalog = "mysql")
@Entity
public class ColumnsPrivEntity {
    private String host;

    @javax.persistence.Column(name = "Host")
    @Id
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String db;

    @javax.persistence.Column(name = "Db")
    @Id
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String user;

    @javax.persistence.Column(name = "User")
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String tableName;

    @javax.persistence.Column(name = "Table_name")
    @Id
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String columnName;

    @javax.persistence.Column(name = "Column_name")
    @Id
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    private Timestamp timestamp;

    @javax.persistence.Column(name = "Timestamp")
    @Basic
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    private String columnPriv;

    @javax.persistence.Column(name = "Column_priv")
    @Basic
    public String getColumnPriv() {
        return columnPriv;
    }

    public void setColumnPriv(String columnPriv) {
        this.columnPriv = columnPriv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnsPrivEntity that = (ColumnsPrivEntity) o;

        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (columnPriv != null ? !columnPriv.equals(that.columnPriv) : that.columnPriv != null) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (db != null ? db.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (columnPriv != null ? columnPriv.hashCode() : 0);
        return result;
    }
}
