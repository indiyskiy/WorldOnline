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
@javax.persistence.Table(name = "INNODB_FT_INDEX_TABLE", schema = "", catalog = "information_schema")
@Entity
public class InnodbFtIndexTableEntity {
    private String word;

    @javax.persistence.Column(name = "WORD")
    @Basic
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    private long firstDocId;

    @javax.persistence.Column(name = "FIRST_DOC_ID")
    @Basic
    public long getFirstDocId() {
        return firstDocId;
    }

    public void setFirstDocId(long firstDocId) {
        this.firstDocId = firstDocId;
    }

    private long lastDocId;

    @javax.persistence.Column(name = "LAST_DOC_ID")
    @Basic
    public long getLastDocId() {
        return lastDocId;
    }

    public void setLastDocId(long lastDocId) {
        this.lastDocId = lastDocId;
    }

    private long docCount;

    @javax.persistence.Column(name = "DOC_COUNT")
    @Basic
    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    private long docId;

    @javax.persistence.Column(name = "DOC_ID")
    @Basic
    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    private long position;

    @javax.persistence.Column(name = "POSITION")
    @Basic
    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbFtIndexTableEntity that = (InnodbFtIndexTableEntity) o;

        if (docCount != that.docCount) return false;
        if (docId != that.docId) return false;
        if (firstDocId != that.firstDocId) return false;
        if (lastDocId != that.lastDocId) return false;
        if (position != that.position) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (int) (firstDocId ^ (firstDocId >>> 32));
        result = 31 * result + (int) (lastDocId ^ (lastDocId >>> 32));
        result = 31 * result + (int) (docCount ^ (docCount >>> 32));
        result = 31 * result + (int) (docId ^ (docId >>> 32));
        result = 31 * result + (int) (position ^ (position >>> 32));
        return result;
    }
}
