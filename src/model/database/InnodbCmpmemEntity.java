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
@javax.persistence.Table(name = "INNODB_CMPMEM", schema = "", catalog = "information_schema")
@Entity
public class InnodbCmpmemEntity {
    private int pageSize;

    @javax.persistence.Column(name = "page_size")
    @Basic
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int bufferPoolInstance;

    @javax.persistence.Column(name = "buffer_pool_instance")
    @Basic
    public int getBufferPoolInstance() {
        return bufferPoolInstance;
    }

    public void setBufferPoolInstance(int bufferPoolInstance) {
        this.bufferPoolInstance = bufferPoolInstance;
    }

    private int pagesUsed;

    @javax.persistence.Column(name = "pages_used")
    @Basic
    public int getPagesUsed() {
        return pagesUsed;
    }

    public void setPagesUsed(int pagesUsed) {
        this.pagesUsed = pagesUsed;
    }

    private int pagesFree;

    @javax.persistence.Column(name = "pages_free")
    @Basic
    public int getPagesFree() {
        return pagesFree;
    }

    public void setPagesFree(int pagesFree) {
        this.pagesFree = pagesFree;
    }

    private long relocationOps;

    @javax.persistence.Column(name = "relocation_ops")
    @Basic
    public long getRelocationOps() {
        return relocationOps;
    }

    public void setRelocationOps(long relocationOps) {
        this.relocationOps = relocationOps;
    }

    private int relocationTime;

    @javax.persistence.Column(name = "relocation_time")
    @Basic
    public int getRelocationTime() {
        return relocationTime;
    }

    public void setRelocationTime(int relocationTime) {
        this.relocationTime = relocationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbCmpmemEntity that = (InnodbCmpmemEntity) o;

        if (bufferPoolInstance != that.bufferPoolInstance) return false;
        if (pageSize != that.pageSize) return false;
        if (pagesFree != that.pagesFree) return false;
        if (pagesUsed != that.pagesUsed) return false;
        if (relocationOps != that.relocationOps) return false;
        if (relocationTime != that.relocationTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageSize;
        result = 31 * result + bufferPoolInstance;
        result = 31 * result + pagesUsed;
        result = 31 * result + pagesFree;
        result = 31 * result + (int) (relocationOps ^ (relocationOps >>> 32));
        result = 31 * result + relocationTime;
        return result;
    }
}
