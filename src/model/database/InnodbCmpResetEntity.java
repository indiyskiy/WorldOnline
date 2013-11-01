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
@javax.persistence.Table(name = "INNODB_CMP_RESET", schema = "", catalog = "information_schema")
@Entity
public class InnodbCmpResetEntity {
    private int pageSize;

    @javax.persistence.Column(name = "page_size")
    @Basic
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int compressOps;

    @javax.persistence.Column(name = "compress_ops")
    @Basic
    public int getCompressOps() {
        return compressOps;
    }

    public void setCompressOps(int compressOps) {
        this.compressOps = compressOps;
    }

    private int compressOpsOk;

    @javax.persistence.Column(name = "compress_ops_ok")
    @Basic
    public int getCompressOpsOk() {
        return compressOpsOk;
    }

    public void setCompressOpsOk(int compressOpsOk) {
        this.compressOpsOk = compressOpsOk;
    }

    private int compressTime;

    @javax.persistence.Column(name = "compress_time")
    @Basic
    public int getCompressTime() {
        return compressTime;
    }

    public void setCompressTime(int compressTime) {
        this.compressTime = compressTime;
    }

    private int uncompressOps;

    @javax.persistence.Column(name = "uncompress_ops")
    @Basic
    public int getUncompressOps() {
        return uncompressOps;
    }

    public void setUncompressOps(int uncompressOps) {
        this.uncompressOps = uncompressOps;
    }

    private int uncompressTime;

    @javax.persistence.Column(name = "uncompress_time")
    @Basic
    public int getUncompressTime() {
        return uncompressTime;
    }

    public void setUncompressTime(int uncompressTime) {
        this.uncompressTime = uncompressTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbCmpResetEntity that = (InnodbCmpResetEntity) o;

        if (compressOps != that.compressOps) return false;
        if (compressOpsOk != that.compressOpsOk) return false;
        if (compressTime != that.compressTime) return false;
        if (pageSize != that.pageSize) return false;
        if (uncompressOps != that.uncompressOps) return false;
        if (uncompressTime != that.uncompressTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageSize;
        result = 31 * result + compressOps;
        result = 31 * result + compressOpsOk;
        result = 31 * result + compressTime;
        result = 31 * result + uncompressOps;
        result = 31 * result + uncompressTime;
        return result;
    }
}
