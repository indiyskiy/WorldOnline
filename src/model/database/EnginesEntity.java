package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "ENGINES", schema = "", catalog = "information_schema")
@Entity
public class EnginesEntity {
    private String engine;

    @javax.persistence.Column(name = "ENGINE")
    @Basic
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    private String support;

    @javax.persistence.Column(name = "SUPPORT")
    @Basic
    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
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

    private String transactions;

    @javax.persistence.Column(name = "TRANSACTIONS")
    @Basic
    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    private String xa;

    @javax.persistence.Column(name = "XA")
    @Basic
    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    private String savepoints;

    @javax.persistence.Column(name = "SAVEPOINTS")
    @Basic
    public String getSavepoints() {
        return savepoints;
    }

    public void setSavepoints(String savepoints) {
        this.savepoints = savepoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnginesEntity that = (EnginesEntity) o;

        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (engine != null ? !engine.equals(that.engine) : that.engine != null) return false;
        if (savepoints != null ? !savepoints.equals(that.savepoints) : that.savepoints != null) return false;
        if (support != null ? !support.equals(that.support) : that.support != null) return false;
        if (transactions != null ? !transactions.equals(that.transactions) : that.transactions != null) return false;
        if (xa != null ? !xa.equals(that.xa) : that.xa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = engine != null ? engine.hashCode() : 0;
        result = 31 * result + (support != null ? support.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (transactions != null ? transactions.hashCode() : 0);
        result = 31 * result + (xa != null ? xa.hashCode() : 0);
        result = 31 * result + (savepoints != null ? savepoints.hashCode() : 0);
        return result;
    }
}
