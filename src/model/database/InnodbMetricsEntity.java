package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:06
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "INNODB_METRICS", schema = "", catalog = "information_schema")
@Entity
public class InnodbMetricsEntity {
    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String subsystem;

    @javax.persistence.Column(name = "SUBSYSTEM")
    @Basic
    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    private long count;

    @javax.persistence.Column(name = "COUNT")
    @Basic
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private long maxCount;

    @javax.persistence.Column(name = "MAX_COUNT")
    @Basic
    public long getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }

    private long minCount;

    @javax.persistence.Column(name = "MIN_COUNT")
    @Basic
    public long getMinCount() {
        return minCount;
    }

    public void setMinCount(long minCount) {
        this.minCount = minCount;
    }

    private double avgCount;

    @javax.persistence.Column(name = "AVG_COUNT")
    @Basic
    public double getAvgCount() {
        return avgCount;
    }

    public void setAvgCount(double avgCount) {
        this.avgCount = avgCount;
    }

    private long countReset;

    @javax.persistence.Column(name = "COUNT_RESET")
    @Basic
    public long getCountReset() {
        return countReset;
    }

    public void setCountReset(long countReset) {
        this.countReset = countReset;
    }

    private long maxCountReset;

    @javax.persistence.Column(name = "MAX_COUNT_RESET")
    @Basic
    public long getMaxCountReset() {
        return maxCountReset;
    }

    public void setMaxCountReset(long maxCountReset) {
        this.maxCountReset = maxCountReset;
    }

    private long minCountReset;

    @javax.persistence.Column(name = "MIN_COUNT_RESET")
    @Basic
    public long getMinCountReset() {
        return minCountReset;
    }

    public void setMinCountReset(long minCountReset) {
        this.minCountReset = minCountReset;
    }

    private double avgCountReset;

    @javax.persistence.Column(name = "AVG_COUNT_RESET")
    @Basic
    public double getAvgCountReset() {
        return avgCountReset;
    }

    public void setAvgCountReset(double avgCountReset) {
        this.avgCountReset = avgCountReset;
    }

    private Timestamp timeEnabled;

    @javax.persistence.Column(name = "TIME_ENABLED")
    @Basic
    public Timestamp getTimeEnabled() {
        return timeEnabled;
    }

    public void setTimeEnabled(Timestamp timeEnabled) {
        this.timeEnabled = timeEnabled;
    }

    private Timestamp timeDisabled;

    @javax.persistence.Column(name = "TIME_DISABLED")
    @Basic
    public Timestamp getTimeDisabled() {
        return timeDisabled;
    }

    public void setTimeDisabled(Timestamp timeDisabled) {
        this.timeDisabled = timeDisabled;
    }

    private long timeElapsed;

    @javax.persistence.Column(name = "TIME_ELAPSED")
    @Basic
    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    private Timestamp timeReset;

    @javax.persistence.Column(name = "TIME_RESET")
    @Basic
    public Timestamp getTimeReset() {
        return timeReset;
    }

    public void setTimeReset(Timestamp timeReset) {
        this.timeReset = timeReset;
    }

    private String status;

    @javax.persistence.Column(name = "STATUS")
    @Basic
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String type;

    @javax.persistence.Column(name = "TYPE")
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String comment;

    @javax.persistence.Column(name = "COMMENT")
    @Basic
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbMetricsEntity that = (InnodbMetricsEntity) o;

        if (Double.compare(that.avgCount, avgCount) != 0) return false;
        if (Double.compare(that.avgCountReset, avgCountReset) != 0) return false;
        if (count != that.count) return false;
        if (countReset != that.countReset) return false;
        if (maxCount != that.maxCount) return false;
        if (maxCountReset != that.maxCountReset) return false;
        if (minCount != that.minCount) return false;
        if (minCountReset != that.minCountReset) return false;
        if (timeElapsed != that.timeElapsed) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (subsystem != null ? !subsystem.equals(that.subsystem) : that.subsystem != null) return false;
        if (timeDisabled != null ? !timeDisabled.equals(that.timeDisabled) : that.timeDisabled != null) return false;
        if (timeEnabled != null ? !timeEnabled.equals(that.timeEnabled) : that.timeEnabled != null) return false;
        if (timeReset != null ? !timeReset.equals(that.timeReset) : that.timeReset != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subsystem != null ? subsystem.hashCode() : 0);
        result = 31 * result + (int) (count ^ (count >>> 32));
        result = 31 * result + (int) (maxCount ^ (maxCount >>> 32));
        result = 31 * result + (int) (minCount ^ (minCount >>> 32));
        temp = avgCount != +0.0d ? Double.doubleToLongBits(avgCount) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (countReset ^ (countReset >>> 32));
        result = 31 * result + (int) (maxCountReset ^ (maxCountReset >>> 32));
        result = 31 * result + (int) (minCountReset ^ (minCountReset >>> 32));
        temp = avgCountReset != +0.0d ? Double.doubleToLongBits(avgCountReset) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (timeEnabled != null ? timeEnabled.hashCode() : 0);
        result = 31 * result + (timeDisabled != null ? timeDisabled.hashCode() : 0);
        result = 31 * result + (int) (timeElapsed ^ (timeElapsed >>> 32));
        result = 31 * result + (timeReset != null ? timeReset.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
