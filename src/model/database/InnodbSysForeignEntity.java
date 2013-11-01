package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "INNODB_SYS_FOREIGN", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysForeignEntity {
    private String id;

    @javax.persistence.Column(name = "ID")
    @Basic
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String forName;

    @javax.persistence.Column(name = "FOR_NAME")
    @Basic
    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    private String refName;

    @javax.persistence.Column(name = "REF_NAME")
    @Basic
    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    private int nCols;

    @javax.persistence.Column(name = "N_COLS")
    @Basic
    public int getnCols() {
        return nCols;
    }

    public void setnCols(int nCols) {
        this.nCols = nCols;
    }

    private int type;

    @javax.persistence.Column(name = "TYPE")
    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysForeignEntity that = (InnodbSysForeignEntity) o;

        if (nCols != that.nCols) return false;
        if (type != that.type) return false;
        if (forName != null ? !forName.equals(that.forName) : that.forName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (refName != null ? !refName.equals(that.refName) : that.refName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (forName != null ? forName.hashCode() : 0);
        result = 31 * result + (refName != null ? refName.hashCode() : 0);
        result = 31 * result + nCols;
        result = 31 * result + type;
        return result;
    }
}
