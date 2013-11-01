package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "slave_worker_info", schema = "", catalog = "mysql")
@Entity
public class SlaveWorkerInfoEntity {
    private int id;

    @javax.persistence.Column(name = "Id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String relayLogName;

    @javax.persistence.Column(name = "Relay_log_name")
    @Basic
    public String getRelayLogName() {
        return relayLogName;
    }

    public void setRelayLogName(String relayLogName) {
        this.relayLogName = relayLogName;
    }

    private long relayLogPos;

    @javax.persistence.Column(name = "Relay_log_pos")
    @Basic
    public long getRelayLogPos() {
        return relayLogPos;
    }

    public void setRelayLogPos(long relayLogPos) {
        this.relayLogPos = relayLogPos;
    }

    private String masterLogName;

    @javax.persistence.Column(name = "Master_log_name")
    @Basic
    public String getMasterLogName() {
        return masterLogName;
    }

    public void setMasterLogName(String masterLogName) {
        this.masterLogName = masterLogName;
    }

    private long masterLogPos;

    @javax.persistence.Column(name = "Master_log_pos")
    @Basic
    public long getMasterLogPos() {
        return masterLogPos;
    }

    public void setMasterLogPos(long masterLogPos) {
        this.masterLogPos = masterLogPos;
    }

    private String checkpointRelayLogName;

    @javax.persistence.Column(name = "Checkpoint_relay_log_name")
    @Basic
    public String getCheckpointRelayLogName() {
        return checkpointRelayLogName;
    }

    public void setCheckpointRelayLogName(String checkpointRelayLogName) {
        this.checkpointRelayLogName = checkpointRelayLogName;
    }

    private long checkpointRelayLogPos;

    @javax.persistence.Column(name = "Checkpoint_relay_log_pos")
    @Basic
    public long getCheckpointRelayLogPos() {
        return checkpointRelayLogPos;
    }

    public void setCheckpointRelayLogPos(long checkpointRelayLogPos) {
        this.checkpointRelayLogPos = checkpointRelayLogPos;
    }

    private String checkpointMasterLogName;

    @javax.persistence.Column(name = "Checkpoint_master_log_name")
    @Basic
    public String getCheckpointMasterLogName() {
        return checkpointMasterLogName;
    }

    public void setCheckpointMasterLogName(String checkpointMasterLogName) {
        this.checkpointMasterLogName = checkpointMasterLogName;
    }

    private long checkpointMasterLogPos;

    @javax.persistence.Column(name = "Checkpoint_master_log_pos")
    @Basic
    public long getCheckpointMasterLogPos() {
        return checkpointMasterLogPos;
    }

    public void setCheckpointMasterLogPos(long checkpointMasterLogPos) {
        this.checkpointMasterLogPos = checkpointMasterLogPos;
    }

    private int checkpointSeqno;

    @javax.persistence.Column(name = "Checkpoint_seqno")
    @Basic
    public int getCheckpointSeqno() {
        return checkpointSeqno;
    }

    public void setCheckpointSeqno(int checkpointSeqno) {
        this.checkpointSeqno = checkpointSeqno;
    }

    private int checkpointGroupSize;

    @javax.persistence.Column(name = "Checkpoint_group_size")
    @Basic
    public int getCheckpointGroupSize() {
        return checkpointGroupSize;
    }

    public void setCheckpointGroupSize(int checkpointGroupSize) {
        this.checkpointGroupSize = checkpointGroupSize;
    }

    private byte[] checkpointGroupBitmap;

    @javax.persistence.Column(name = "Checkpoint_group_bitmap")
    @Basic
    public byte[] getCheckpointGroupBitmap() {
        return checkpointGroupBitmap;
    }

    public void setCheckpointGroupBitmap(byte[] checkpointGroupBitmap) {
        this.checkpointGroupBitmap = checkpointGroupBitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlaveWorkerInfoEntity that = (SlaveWorkerInfoEntity) o;

        if (checkpointGroupSize != that.checkpointGroupSize) return false;
        if (checkpointMasterLogPos != that.checkpointMasterLogPos) return false;
        if (checkpointRelayLogPos != that.checkpointRelayLogPos) return false;
        if (checkpointSeqno != that.checkpointSeqno) return false;
        if (id != that.id) return false;
        if (masterLogPos != that.masterLogPos) return false;
        if (relayLogPos != that.relayLogPos) return false;
        if (!Arrays.equals(checkpointGroupBitmap, that.checkpointGroupBitmap)) return false;
        if (checkpointMasterLogName != null ? !checkpointMasterLogName.equals(that.checkpointMasterLogName) : that.checkpointMasterLogName != null)
            return false;
        if (checkpointRelayLogName != null ? !checkpointRelayLogName.equals(that.checkpointRelayLogName) : that.checkpointRelayLogName != null)
            return false;
        if (masterLogName != null ? !masterLogName.equals(that.masterLogName) : that.masterLogName != null)
            return false;
        if (relayLogName != null ? !relayLogName.equals(that.relayLogName) : that.relayLogName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (relayLogName != null ? relayLogName.hashCode() : 0);
        result = 31 * result + (int) (relayLogPos ^ (relayLogPos >>> 32));
        result = 31 * result + (masterLogName != null ? masterLogName.hashCode() : 0);
        result = 31 * result + (int) (masterLogPos ^ (masterLogPos >>> 32));
        result = 31 * result + (checkpointRelayLogName != null ? checkpointRelayLogName.hashCode() : 0);
        result = 31 * result + (int) (checkpointRelayLogPos ^ (checkpointRelayLogPos >>> 32));
        result = 31 * result + (checkpointMasterLogName != null ? checkpointMasterLogName.hashCode() : 0);
        result = 31 * result + (int) (checkpointMasterLogPos ^ (checkpointMasterLogPos >>> 32));
        result = 31 * result + checkpointSeqno;
        result = 31 * result + checkpointGroupSize;
        result = 31 * result + (checkpointGroupBitmap != null ? Arrays.hashCode(checkpointGroupBitmap) : 0);
        return result;
    }
}
