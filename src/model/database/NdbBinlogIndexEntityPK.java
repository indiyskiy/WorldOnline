package model.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public class NdbBinlogIndexEntityPK implements Serializable {
    private long epoch;

    @Id
    @Column(name = "epoch")
    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    private int origServerId;

    @Id
    @Column(name = "orig_server_id")
    public int getOrigServerId() {
        return origServerId;
    }

    public void setOrigServerId(int origServerId) {
        this.origServerId = origServerId;
    }

    private long origEpoch;

    @Id
    @Column(name = "orig_epoch")
    public long getOrigEpoch() {
        return origEpoch;
    }

    public void setOrigEpoch(long origEpoch) {
        this.origEpoch = origEpoch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NdbBinlogIndexEntityPK that = (NdbBinlogIndexEntityPK) o;

        if (epoch != that.epoch) return false;
        if (origEpoch != that.origEpoch) return false;
        if (origServerId != that.origServerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (epoch ^ (epoch >>> 32));
        result = 31 * result + origServerId;
        result = 31 * result + (int) (origEpoch ^ (origEpoch >>> 32));
        return result;
    }
}
