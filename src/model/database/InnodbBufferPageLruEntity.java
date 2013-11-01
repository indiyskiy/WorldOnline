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
@javax.persistence.Table(name = "INNODB_BUFFER_PAGE_LRU", schema = "", catalog = "information_schema")
@Entity
public class InnodbBufferPageLruEntity {
    private long poolId;

    @javax.persistence.Column(name = "POOL_ID")
    @Basic
    public long getPoolId() {
        return poolId;
    }

    public void setPoolId(long poolId) {
        this.poolId = poolId;
    }

    private long lruPosition;

    @javax.persistence.Column(name = "LRU_POSITION")
    @Basic
    public long getLruPosition() {
        return lruPosition;
    }

    public void setLruPosition(long lruPosition) {
        this.lruPosition = lruPosition;
    }

    private long space;

    @javax.persistence.Column(name = "SPACE")
    @Basic
    public long getSpace() {
        return space;
    }

    public void setSpace(long space) {
        this.space = space;
    }

    private long pageNumber;

    @javax.persistence.Column(name = "PAGE_NUMBER")
    @Basic
    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    private String pageType;

    @javax.persistence.Column(name = "PAGE_TYPE")
    @Basic
    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    private long flushType;

    @javax.persistence.Column(name = "FLUSH_TYPE")
    @Basic
    public long getFlushType() {
        return flushType;
    }

    public void setFlushType(long flushType) {
        this.flushType = flushType;
    }

    private long fixCount;

    @javax.persistence.Column(name = "FIX_COUNT")
    @Basic
    public long getFixCount() {
        return fixCount;
    }

    public void setFixCount(long fixCount) {
        this.fixCount = fixCount;
    }

    private String isHashed;

    @javax.persistence.Column(name = "IS_HASHED")
    @Basic
    public String getHashed() {
        return isHashed;
    }

    public void setHashed(String hashed) {
        isHashed = hashed;
    }

    private long newestModification;

    @javax.persistence.Column(name = "NEWEST_MODIFICATION")
    @Basic
    public long getNewestModification() {
        return newestModification;
    }

    public void setNewestModification(long newestModification) {
        this.newestModification = newestModification;
    }

    private long oldestModification;

    @javax.persistence.Column(name = "OLDEST_MODIFICATION")
    @Basic
    public long getOldestModification() {
        return oldestModification;
    }

    public void setOldestModification(long oldestModification) {
        this.oldestModification = oldestModification;
    }

    private long accessTime;

    @javax.persistence.Column(name = "ACCESS_TIME")
    @Basic
    public long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }

    private String tableName;

    @javax.persistence.Column(name = "TABLE_NAME")
    @Basic
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String indexName;

    @javax.persistence.Column(name = "INDEX_NAME")
    @Basic
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    private long numberRecords;

    @javax.persistence.Column(name = "NUMBER_RECORDS")
    @Basic
    public long getNumberRecords() {
        return numberRecords;
    }

    public void setNumberRecords(long numberRecords) {
        this.numberRecords = numberRecords;
    }

    private long dataSize;

    @javax.persistence.Column(name = "DATA_SIZE")
    @Basic
    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    private long compressedSize;

    @javax.persistence.Column(name = "COMPRESSED_SIZE")
    @Basic
    public long getCompressedSize() {
        return compressedSize;
    }

    public void setCompressedSize(long compressedSize) {
        this.compressedSize = compressedSize;
    }

    private String compressed;

    @javax.persistence.Column(name = "COMPRESSED")
    @Basic
    public String getCompressed() {
        return compressed;
    }

    public void setCompressed(String compressed) {
        this.compressed = compressed;
    }

    private String ioFix;

    @javax.persistence.Column(name = "IO_FIX")
    @Basic
    public String getIoFix() {
        return ioFix;
    }

    public void setIoFix(String ioFix) {
        this.ioFix = ioFix;
    }

    private String isOld;

    @javax.persistence.Column(name = "IS_OLD")
    @Basic
    public String getOld() {
        return isOld;
    }

    public void setOld(String old) {
        isOld = old;
    }

    private long freePageClock;

    @javax.persistence.Column(name = "FREE_PAGE_CLOCK")
    @Basic
    public long getFreePageClock() {
        return freePageClock;
    }

    public void setFreePageClock(long freePageClock) {
        this.freePageClock = freePageClock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbBufferPageLruEntity that = (InnodbBufferPageLruEntity) o;

        if (accessTime != that.accessTime) return false;
        if (compressedSize != that.compressedSize) return false;
        if (dataSize != that.dataSize) return false;
        if (fixCount != that.fixCount) return false;
        if (flushType != that.flushType) return false;
        if (freePageClock != that.freePageClock) return false;
        if (lruPosition != that.lruPosition) return false;
        if (newestModification != that.newestModification) return false;
        if (numberRecords != that.numberRecords) return false;
        if (oldestModification != that.oldestModification) return false;
        if (pageNumber != that.pageNumber) return false;
        if (poolId != that.poolId) return false;
        if (space != that.space) return false;
        if (compressed != null ? !compressed.equals(that.compressed) : that.compressed != null) return false;
        if (indexName != null ? !indexName.equals(that.indexName) : that.indexName != null) return false;
        if (ioFix != null ? !ioFix.equals(that.ioFix) : that.ioFix != null) return false;
        if (isHashed != null ? !isHashed.equals(that.isHashed) : that.isHashed != null) return false;
        if (isOld != null ? !isOld.equals(that.isOld) : that.isOld != null) return false;
        if (pageType != null ? !pageType.equals(that.pageType) : that.pageType != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (poolId ^ (poolId >>> 32));
        result = 31 * result + (int) (lruPosition ^ (lruPosition >>> 32));
        result = 31 * result + (int) (space ^ (space >>> 32));
        result = 31 * result + (int) (pageNumber ^ (pageNumber >>> 32));
        result = 31 * result + (pageType != null ? pageType.hashCode() : 0);
        result = 31 * result + (int) (flushType ^ (flushType >>> 32));
        result = 31 * result + (int) (fixCount ^ (fixCount >>> 32));
        result = 31 * result + (isHashed != null ? isHashed.hashCode() : 0);
        result = 31 * result + (int) (newestModification ^ (newestModification >>> 32));
        result = 31 * result + (int) (oldestModification ^ (oldestModification >>> 32));
        result = 31 * result + (int) (accessTime ^ (accessTime >>> 32));
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        result = 31 * result + (int) (numberRecords ^ (numberRecords >>> 32));
        result = 31 * result + (int) (dataSize ^ (dataSize >>> 32));
        result = 31 * result + (int) (compressedSize ^ (compressedSize >>> 32));
        result = 31 * result + (compressed != null ? compressed.hashCode() : 0);
        result = 31 * result + (ioFix != null ? ioFix.hashCode() : 0);
        result = 31 * result + (isOld != null ? isOld.hashCode() : 0);
        result = 31 * result + (int) (freePageClock ^ (freePageClock >>> 32));
        return result;
    }
}
