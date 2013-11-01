package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "PROFILING", schema = "", catalog = "information_schema")
@Entity
public class ProfilingEntity {
    private int queryId;

    @javax.persistence.Column(name = "QUERY_ID")
    @Basic
    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    private int seq;

    @javax.persistence.Column(name = "SEQ")
    @Basic
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    private String state;

    @javax.persistence.Column(name = "STATE")
    @Basic
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private BigDecimal duration;

    @javax.persistence.Column(name = "DURATION")
    @Basic
    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    private BigDecimal cpuUser;

    @javax.persistence.Column(name = "CPU_USER")
    @Basic
    public BigDecimal getCpuUser() {
        return cpuUser;
    }

    public void setCpuUser(BigDecimal cpuUser) {
        this.cpuUser = cpuUser;
    }

    private BigDecimal cpuSystem;

    @javax.persistence.Column(name = "CPU_SYSTEM")
    @Basic
    public BigDecimal getCpuSystem() {
        return cpuSystem;
    }

    public void setCpuSystem(BigDecimal cpuSystem) {
        this.cpuSystem = cpuSystem;
    }

    private int contextVoluntary;

    @javax.persistence.Column(name = "CONTEXT_VOLUNTARY")
    @Basic
    public int getContextVoluntary() {
        return contextVoluntary;
    }

    public void setContextVoluntary(int contextVoluntary) {
        this.contextVoluntary = contextVoluntary;
    }

    private int contextInvoluntary;

    @javax.persistence.Column(name = "CONTEXT_INVOLUNTARY")
    @Basic
    public int getContextInvoluntary() {
        return contextInvoluntary;
    }

    public void setContextInvoluntary(int contextInvoluntary) {
        this.contextInvoluntary = contextInvoluntary;
    }

    private int blockOpsIn;

    @javax.persistence.Column(name = "BLOCK_OPS_IN")
    @Basic
    public int getBlockOpsIn() {
        return blockOpsIn;
    }

    public void setBlockOpsIn(int blockOpsIn) {
        this.blockOpsIn = blockOpsIn;
    }

    private int blockOpsOut;

    @javax.persistence.Column(name = "BLOCK_OPS_OUT")
    @Basic
    public int getBlockOpsOut() {
        return blockOpsOut;
    }

    public void setBlockOpsOut(int blockOpsOut) {
        this.blockOpsOut = blockOpsOut;
    }

    private int messagesSent;

    @javax.persistence.Column(name = "MESSAGES_SENT")
    @Basic
    public int getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(int messagesSent) {
        this.messagesSent = messagesSent;
    }

    private int messagesReceived;

    @javax.persistence.Column(name = "MESSAGES_RECEIVED")
    @Basic
    public int getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(int messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    private int pageFaultsMajor;

    @javax.persistence.Column(name = "PAGE_FAULTS_MAJOR")
    @Basic
    public int getPageFaultsMajor() {
        return pageFaultsMajor;
    }

    public void setPageFaultsMajor(int pageFaultsMajor) {
        this.pageFaultsMajor = pageFaultsMajor;
    }

    private int pageFaultsMinor;

    @javax.persistence.Column(name = "PAGE_FAULTS_MINOR")
    @Basic
    public int getPageFaultsMinor() {
        return pageFaultsMinor;
    }

    public void setPageFaultsMinor(int pageFaultsMinor) {
        this.pageFaultsMinor = pageFaultsMinor;
    }

    private int swaps;

    @javax.persistence.Column(name = "SWAPS")
    @Basic
    public int getSwaps() {
        return swaps;
    }

    public void setSwaps(int swaps) {
        this.swaps = swaps;
    }

    private String sourceFunction;

    @javax.persistence.Column(name = "SOURCE_FUNCTION")
    @Basic
    public String getSourceFunction() {
        return sourceFunction;
    }

    public void setSourceFunction(String sourceFunction) {
        this.sourceFunction = sourceFunction;
    }

    private String sourceFile;

    @javax.persistence.Column(name = "SOURCE_FILE")
    @Basic
    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    private int sourceLine;

    @javax.persistence.Column(name = "SOURCE_LINE")
    @Basic
    public int getSourceLine() {
        return sourceLine;
    }

    public void setSourceLine(int sourceLine) {
        this.sourceLine = sourceLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfilingEntity that = (ProfilingEntity) o;

        if (blockOpsIn != that.blockOpsIn) return false;
        if (blockOpsOut != that.blockOpsOut) return false;
        if (contextInvoluntary != that.contextInvoluntary) return false;
        if (contextVoluntary != that.contextVoluntary) return false;
        if (messagesReceived != that.messagesReceived) return false;
        if (messagesSent != that.messagesSent) return false;
        if (pageFaultsMajor != that.pageFaultsMajor) return false;
        if (pageFaultsMinor != that.pageFaultsMinor) return false;
        if (queryId != that.queryId) return false;
        if (seq != that.seq) return false;
        if (sourceLine != that.sourceLine) return false;
        if (swaps != that.swaps) return false;
        if (cpuSystem != null ? !cpuSystem.equals(that.cpuSystem) : that.cpuSystem != null) return false;
        if (cpuUser != null ? !cpuUser.equals(that.cpuUser) : that.cpuUser != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (sourceFile != null ? !sourceFile.equals(that.sourceFile) : that.sourceFile != null) return false;
        if (sourceFunction != null ? !sourceFunction.equals(that.sourceFunction) : that.sourceFunction != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queryId;
        result = 31 * result + seq;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (cpuUser != null ? cpuUser.hashCode() : 0);
        result = 31 * result + (cpuSystem != null ? cpuSystem.hashCode() : 0);
        result = 31 * result + contextVoluntary;
        result = 31 * result + contextInvoluntary;
        result = 31 * result + blockOpsIn;
        result = 31 * result + blockOpsOut;
        result = 31 * result + messagesSent;
        result = 31 * result + messagesReceived;
        result = 31 * result + pageFaultsMajor;
        result = 31 * result + pageFaultsMinor;
        result = 31 * result + swaps;
        result = 31 * result + (sourceFunction != null ? sourceFunction.hashCode() : 0);
        result = 31 * result + (sourceFile != null ? sourceFile.hashCode() : 0);
        result = 31 * result + sourceLine;
        return result;
    }
}
