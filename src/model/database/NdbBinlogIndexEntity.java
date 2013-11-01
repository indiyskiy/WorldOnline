package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.NdbBinlogIndexEntityPK.class)
@javax.persistence.Table(name = "ndb_binlog_index", schema = "", catalog = "mysql")
@Entity
public class NdbBinlogIndexEntity {
    private long position;

    @javax.persistence.Column(name = "Position")
    @Basic
    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    private String file;

    @javax.persistence.Column(name = "File")
    @Basic
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    private long epoch;

    @javax.persistence.Column(name = "epoch")
    @Id
    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    private int inserts;

    @javax.persistence.Column(name = "inserts")
    @Basic
    public int getInserts() {
        return inserts;
    }

    public void setInserts(int inserts) {
        this.inserts = inserts;
    }

    private int updates;

    @javax.persistence.Column(name = "updates")
    @Basic
    public int getUpdates() {
        return updates;
    }

    public void setUpdates(int updates) {
        this.updates = updates;
    }

    private int deletes;

    @javax.persistence.Column(name = "deletes")
    @Basic
    public int getDeletes() {
        return deletes;
    }

    public void setDeletes(int deletes) {
        this.deletes = deletes;
    }

    private int schemaops;

    @javax.persistence.Column(name = "schemaops")
    @Basic
    public int getSchemaops() {
        return schemaops;
    }

    public void setSchemaops(int schemaops) {
        this.schemaops = schemaops;
    }

    private int origServerId;

    @javax.persistence.Column(name = "orig_server_id")
    @Id
    public int getOrigServerId() {
        return origServerId;
    }

    public void setOrigServerId(int origServerId) {
        this.origServerId = origServerId;
    }

    private long origEpoch;

    @javax.persistence.Column(name = "orig_epoch")
    @Id
    public long getOrigEpoch() {
        return origEpoch;
    }

    public void setOrigEpoch(long origEpoch) {
        this.origEpoch = origEpoch;
    }

    private int gci;

    @javax.persistence.Column(name = "gci")
    @Basic
    public int getGci() {
        return gci;
    }

    public void setGci(int gci) {
        this.gci = gci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NdbBinlogIndexEntity that = (NdbBinlogIndexEntity) o;

        if (deletes != that.deletes) return false;
        if (epoch != that.epoch) return false;
        if (gci != that.gci) return false;
        if (inserts != that.inserts) return false;
        if (origEpoch != that.origEpoch) return false;
        if (origServerId != that.origServerId) return false;
        if (position != that.position) return false;
        if (schemaops != that.schemaops) return false;
        if (updates != that.updates) return false;
        if (file != null ? !file.equals(that.file) : that.file != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (position ^ (position >>> 32));
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (int) (epoch ^ (epoch >>> 32));
        result = 31 * result + inserts;
        result = 31 * result + updates;
        result = 31 * result + deletes;
        result = 31 * result + schemaops;
        result = 31 * result + origServerId;
        result = 31 * result + (int) (origEpoch ^ (origEpoch >>> 32));
        result = 31 * result + gci;
        return result;
    }
}
