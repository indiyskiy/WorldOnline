package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:10
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "session_account_connect_attrs", schema = "", catalog = "performance_schema")
@Entity
public class SessionAccountConnectAttrsEntity {
    private int processlistId;

    @javax.persistence.Column(name = "PROCESSLIST_ID")
    @Basic
    public int getProcesslistId() {
        return processlistId;
    }

    public void setProcesslistId(int processlistId) {
        this.processlistId = processlistId;
    }

    private String attrName;

    @javax.persistence.Column(name = "ATTR_NAME")
    @Basic
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    private String attrValue;

    @javax.persistence.Column(name = "ATTR_VALUE")
    @Basic
    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    private int ordinalPosition;

    @javax.persistence.Column(name = "ORDINAL_POSITION")
    @Basic
    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionAccountConnectAttrsEntity that = (SessionAccountConnectAttrsEntity) o;

        if (ordinalPosition != that.ordinalPosition) return false;
        if (processlistId != that.processlistId) return false;
        if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
        if (attrValue != null ? !attrValue.equals(that.attrValue) : that.attrValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processlistId;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
        result = 31 * result + ordinalPosition;
        return result;
    }
}
