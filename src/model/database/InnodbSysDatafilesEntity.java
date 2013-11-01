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
@javax.persistence.Table(name = "INNODB_SYS_DATAFILES", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysDatafilesEntity {
    private int space;

    @javax.persistence.Column(name = "SPACE")
    @Basic
    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    private String path;

    @javax.persistence.Column(name = "PATH")
    @Basic
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysDatafilesEntity that = (InnodbSysDatafilesEntity) o;

        if (space != that.space) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = space;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
