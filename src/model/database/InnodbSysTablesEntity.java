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
@javax.persistence.Table(name = "INNODB_SYS_TABLES", schema = "", catalog = "information_schema")
@Entity
public class InnodbSysTablesEntity {
    private long tableId;

    @javax.persistence.Column(name = "TABLE_ID")
    @Basic
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int flag;

    @javax.persistence.Column(name = "FLAG")
    @Basic
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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

    private int space;

    @javax.persistence.Column(name = "SPACE")
    @Basic
    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    private String fileFormat;

    @javax.persistence.Column(name = "FILE_FORMAT")
    @Basic
    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    private String rowFormat;

    @javax.persistence.Column(name = "ROW_FORMAT")
    @Basic
    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    private int zipPageSize;

    @javax.persistence.Column(name = "ZIP_PAGE_SIZE")
    @Basic
    public int getZipPageSize() {
        return zipPageSize;
    }

    public void setZipPageSize(int zipPageSize) {
        this.zipPageSize = zipPageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbSysTablesEntity that = (InnodbSysTablesEntity) o;

        if (flag != that.flag) return false;
        if (nCols != that.nCols) return false;
        if (space != that.space) return false;
        if (tableId != that.tableId) return false;
        if (zipPageSize != that.zipPageSize) return false;
        if (fileFormat != null ? !fileFormat.equals(that.fileFormat) : that.fileFormat != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rowFormat != null ? !rowFormat.equals(that.rowFormat) : that.rowFormat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tableId ^ (tableId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + flag;
        result = 31 * result + nCols;
        result = 31 * result + space;
        result = 31 * result + (fileFormat != null ? fileFormat.hashCode() : 0);
        result = 31 * result + (rowFormat != null ? rowFormat.hashCode() : 0);
        result = 31 * result + zipPageSize;
        return result;
    }
}
