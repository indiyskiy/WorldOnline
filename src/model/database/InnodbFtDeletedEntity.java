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
@javax.persistence.Table(name = "INNODB_FT_DELETED", schema = "", catalog = "information_schema")
@Entity
public class InnodbFtDeletedEntity {
    private long docId;

    @javax.persistence.Column(name = "DOC_ID")
    @Basic
    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbFtDeletedEntity that = (InnodbFtDeletedEntity) o;

        if (docId != that.docId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (docId ^ (docId >>> 32));
    }
}
