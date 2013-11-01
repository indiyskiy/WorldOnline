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
@javax.persistence.Table(name = "OPTIMIZER_TRACE", schema = "", catalog = "information_schema")
@Entity
public class OptimizerTraceEntity {
    private String query;

    @javax.persistence.Column(name = "QUERY")
    @Basic
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String trace;

    @javax.persistence.Column(name = "TRACE")
    @Basic
    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    private int missingBytesBeyondMaxMemSize;

    @javax.persistence.Column(name = "MISSING_BYTES_BEYOND_MAX_MEM_SIZE")
    @Basic
    public int getMissingBytesBeyondMaxMemSize() {
        return missingBytesBeyondMaxMemSize;
    }

    public void setMissingBytesBeyondMaxMemSize(int missingBytesBeyondMaxMemSize) {
        this.missingBytesBeyondMaxMemSize = missingBytesBeyondMaxMemSize;
    }

    private boolean insufficientPrivileges;

    @javax.persistence.Column(name = "INSUFFICIENT_PRIVILEGES")
    @Basic
    public boolean isInsufficientPrivileges() {
        return insufficientPrivileges;
    }

    public void setInsufficientPrivileges(boolean insufficientPrivileges) {
        this.insufficientPrivileges = insufficientPrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptimizerTraceEntity that = (OptimizerTraceEntity) o;

        if (insufficientPrivileges != that.insufficientPrivileges) return false;
        if (missingBytesBeyondMaxMemSize != that.missingBytesBeyondMaxMemSize) return false;
        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        if (trace != null ? !trace.equals(that.trace) : that.trace != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (trace != null ? trace.hashCode() : 0);
        result = 31 * result + missingBytesBeyondMaxMemSize;
        result = 31 * result + (insufficientPrivileges ? 1 : 0);
        return result;
    }
}
