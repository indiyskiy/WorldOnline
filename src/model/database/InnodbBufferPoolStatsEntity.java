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
@javax.persistence.Table(name = "INNODB_BUFFER_POOL_STATS", schema = "", catalog = "information_schema")
@Entity
public class InnodbBufferPoolStatsEntity {
    private long poolId;

    @javax.persistence.Column(name = "POOL_ID")
    @Basic
    public long getPoolId() {
        return poolId;
    }

    public void setPoolId(long poolId) {
        this.poolId = poolId;
    }

    private long poolSize;

    @javax.persistence.Column(name = "POOL_SIZE")
    @Basic
    public long getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(long poolSize) {
        this.poolSize = poolSize;
    }

    private long freeBuffers;

    @javax.persistence.Column(name = "FREE_BUFFERS")
    @Basic
    public long getFreeBuffers() {
        return freeBuffers;
    }

    public void setFreeBuffers(long freeBuffers) {
        this.freeBuffers = freeBuffers;
    }

    private long databasePages;

    @javax.persistence.Column(name = "DATABASE_PAGES")
    @Basic
    public long getDatabasePages() {
        return databasePages;
    }

    public void setDatabasePages(long databasePages) {
        this.databasePages = databasePages;
    }

    private long oldDatabasePages;

    @javax.persistence.Column(name = "OLD_DATABASE_PAGES")
    @Basic
    public long getOldDatabasePages() {
        return oldDatabasePages;
    }

    public void setOldDatabasePages(long oldDatabasePages) {
        this.oldDatabasePages = oldDatabasePages;
    }

    private long modifiedDatabasePages;

    @javax.persistence.Column(name = "MODIFIED_DATABASE_PAGES")
    @Basic
    public long getModifiedDatabasePages() {
        return modifiedDatabasePages;
    }

    public void setModifiedDatabasePages(long modifiedDatabasePages) {
        this.modifiedDatabasePages = modifiedDatabasePages;
    }

    private long pendingDecompress;

    @javax.persistence.Column(name = "PENDING_DECOMPRESS")
    @Basic
    public long getPendingDecompress() {
        return pendingDecompress;
    }

    public void setPendingDecompress(long pendingDecompress) {
        this.pendingDecompress = pendingDecompress;
    }

    private long pendingReads;

    @javax.persistence.Column(name = "PENDING_READS")
    @Basic
    public long getPendingReads() {
        return pendingReads;
    }

    public void setPendingReads(long pendingReads) {
        this.pendingReads = pendingReads;
    }

    private long pendingFlushLru;

    @javax.persistence.Column(name = "PENDING_FLUSH_LRU")
    @Basic
    public long getPendingFlushLru() {
        return pendingFlushLru;
    }

    public void setPendingFlushLru(long pendingFlushLru) {
        this.pendingFlushLru = pendingFlushLru;
    }

    private long pendingFlushList;

    @javax.persistence.Column(name = "PENDING_FLUSH_LIST")
    @Basic
    public long getPendingFlushList() {
        return pendingFlushList;
    }

    public void setPendingFlushList(long pendingFlushList) {
        this.pendingFlushList = pendingFlushList;
    }

    private long pagesMadeYoung;

    @javax.persistence.Column(name = "PAGES_MADE_YOUNG")
    @Basic
    public long getPagesMadeYoung() {
        return pagesMadeYoung;
    }

    public void setPagesMadeYoung(long pagesMadeYoung) {
        this.pagesMadeYoung = pagesMadeYoung;
    }

    private long pagesNotMadeYoung;

    @javax.persistence.Column(name = "PAGES_NOT_MADE_YOUNG")
    @Basic
    public long getPagesNotMadeYoung() {
        return pagesNotMadeYoung;
    }

    public void setPagesNotMadeYoung(long pagesNotMadeYoung) {
        this.pagesNotMadeYoung = pagesNotMadeYoung;
    }

    private double pagesMadeYoungRate;

    @javax.persistence.Column(name = "PAGES_MADE_YOUNG_RATE")
    @Basic
    public double getPagesMadeYoungRate() {
        return pagesMadeYoungRate;
    }

    public void setPagesMadeYoungRate(double pagesMadeYoungRate) {
        this.pagesMadeYoungRate = pagesMadeYoungRate;
    }

    private double pagesMadeNotYoungRate;

    @javax.persistence.Column(name = "PAGES_MADE_NOT_YOUNG_RATE")
    @Basic
    public double getPagesMadeNotYoungRate() {
        return pagesMadeNotYoungRate;
    }

    public void setPagesMadeNotYoungRate(double pagesMadeNotYoungRate) {
        this.pagesMadeNotYoungRate = pagesMadeNotYoungRate;
    }

    private long numberPagesRead;

    @javax.persistence.Column(name = "NUMBER_PAGES_READ")
    @Basic
    public long getNumberPagesRead() {
        return numberPagesRead;
    }

    public void setNumberPagesRead(long numberPagesRead) {
        this.numberPagesRead = numberPagesRead;
    }

    private long numberPagesCreated;

    @javax.persistence.Column(name = "NUMBER_PAGES_CREATED")
    @Basic
    public long getNumberPagesCreated() {
        return numberPagesCreated;
    }

    public void setNumberPagesCreated(long numberPagesCreated) {
        this.numberPagesCreated = numberPagesCreated;
    }

    private long numberPagesWritten;

    @javax.persistence.Column(name = "NUMBER_PAGES_WRITTEN")
    @Basic
    public long getNumberPagesWritten() {
        return numberPagesWritten;
    }

    public void setNumberPagesWritten(long numberPagesWritten) {
        this.numberPagesWritten = numberPagesWritten;
    }

    private double pagesReadRate;

    @javax.persistence.Column(name = "PAGES_READ_RATE")
    @Basic
    public double getPagesReadRate() {
        return pagesReadRate;
    }

    public void setPagesReadRate(double pagesReadRate) {
        this.pagesReadRate = pagesReadRate;
    }

    private double pagesCreateRate;

    @javax.persistence.Column(name = "PAGES_CREATE_RATE")
    @Basic
    public double getPagesCreateRate() {
        return pagesCreateRate;
    }

    public void setPagesCreateRate(double pagesCreateRate) {
        this.pagesCreateRate = pagesCreateRate;
    }

    private double pagesWrittenRate;

    @javax.persistence.Column(name = "PAGES_WRITTEN_RATE")
    @Basic
    public double getPagesWrittenRate() {
        return pagesWrittenRate;
    }

    public void setPagesWrittenRate(double pagesWrittenRate) {
        this.pagesWrittenRate = pagesWrittenRate;
    }

    private long numberPagesGet;

    @javax.persistence.Column(name = "NUMBER_PAGES_GET")
    @Basic
    public long getNumberPagesGet() {
        return numberPagesGet;
    }

    public void setNumberPagesGet(long numberPagesGet) {
        this.numberPagesGet = numberPagesGet;
    }

    private long hitRate;

    @javax.persistence.Column(name = "HIT_RATE")
    @Basic
    public long getHitRate() {
        return hitRate;
    }

    public void setHitRate(long hitRate) {
        this.hitRate = hitRate;
    }

    private long youngMakePerThousandGets;

    @javax.persistence.Column(name = "YOUNG_MAKE_PER_THOUSAND_GETS")
    @Basic
    public long getYoungMakePerThousandGets() {
        return youngMakePerThousandGets;
    }

    public void setYoungMakePerThousandGets(long youngMakePerThousandGets) {
        this.youngMakePerThousandGets = youngMakePerThousandGets;
    }

    private long notYoungMakePerThousandGets;

    @javax.persistence.Column(name = "NOT_YOUNG_MAKE_PER_THOUSAND_GETS")
    @Basic
    public long getNotYoungMakePerThousandGets() {
        return notYoungMakePerThousandGets;
    }

    public void setNotYoungMakePerThousandGets(long notYoungMakePerThousandGets) {
        this.notYoungMakePerThousandGets = notYoungMakePerThousandGets;
    }

    private long numberPagesReadAhead;

    @javax.persistence.Column(name = "NUMBER_PAGES_READ_AHEAD")
    @Basic
    public long getNumberPagesReadAhead() {
        return numberPagesReadAhead;
    }

    public void setNumberPagesReadAhead(long numberPagesReadAhead) {
        this.numberPagesReadAhead = numberPagesReadAhead;
    }

    private long numberReadAheadEvicted;

    @javax.persistence.Column(name = "NUMBER_READ_AHEAD_EVICTED")
    @Basic
    public long getNumberReadAheadEvicted() {
        return numberReadAheadEvicted;
    }

    public void setNumberReadAheadEvicted(long numberReadAheadEvicted) {
        this.numberReadAheadEvicted = numberReadAheadEvicted;
    }

    private double readAheadRate;

    @javax.persistence.Column(name = "READ_AHEAD_RATE")
    @Basic
    public double getReadAheadRate() {
        return readAheadRate;
    }

    public void setReadAheadRate(double readAheadRate) {
        this.readAheadRate = readAheadRate;
    }

    private double readAheadEvictedRate;

    @javax.persistence.Column(name = "READ_AHEAD_EVICTED_RATE")
    @Basic
    public double getReadAheadEvictedRate() {
        return readAheadEvictedRate;
    }

    public void setReadAheadEvictedRate(double readAheadEvictedRate) {
        this.readAheadEvictedRate = readAheadEvictedRate;
    }

    private long lruIoTotal;

    @javax.persistence.Column(name = "LRU_IO_TOTAL")
    @Basic
    public long getLruIoTotal() {
        return lruIoTotal;
    }

    public void setLruIoTotal(long lruIoTotal) {
        this.lruIoTotal = lruIoTotal;
    }

    private long lruIoCurrent;

    @javax.persistence.Column(name = "LRU_IO_CURRENT")
    @Basic
    public long getLruIoCurrent() {
        return lruIoCurrent;
    }

    public void setLruIoCurrent(long lruIoCurrent) {
        this.lruIoCurrent = lruIoCurrent;
    }

    private long uncompressTotal;

    @javax.persistence.Column(name = "UNCOMPRESS_TOTAL")
    @Basic
    public long getUncompressTotal() {
        return uncompressTotal;
    }

    public void setUncompressTotal(long uncompressTotal) {
        this.uncompressTotal = uncompressTotal;
    }

    private long uncompressCurrent;

    @javax.persistence.Column(name = "UNCOMPRESS_CURRENT")
    @Basic
    public long getUncompressCurrent() {
        return uncompressCurrent;
    }

    public void setUncompressCurrent(long uncompressCurrent) {
        this.uncompressCurrent = uncompressCurrent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnodbBufferPoolStatsEntity that = (InnodbBufferPoolStatsEntity) o;

        if (databasePages != that.databasePages) return false;
        if (freeBuffers != that.freeBuffers) return false;
        if (hitRate != that.hitRate) return false;
        if (lruIoCurrent != that.lruIoCurrent) return false;
        if (lruIoTotal != that.lruIoTotal) return false;
        if (modifiedDatabasePages != that.modifiedDatabasePages) return false;
        if (notYoungMakePerThousandGets != that.notYoungMakePerThousandGets) return false;
        if (numberPagesCreated != that.numberPagesCreated) return false;
        if (numberPagesGet != that.numberPagesGet) return false;
        if (numberPagesRead != that.numberPagesRead) return false;
        if (numberPagesReadAhead != that.numberPagesReadAhead) return false;
        if (numberPagesWritten != that.numberPagesWritten) return false;
        if (numberReadAheadEvicted != that.numberReadAheadEvicted) return false;
        if (oldDatabasePages != that.oldDatabasePages) return false;
        if (Double.compare(that.pagesCreateRate, pagesCreateRate) != 0) return false;
        if (Double.compare(that.pagesMadeNotYoungRate, pagesMadeNotYoungRate) != 0) return false;
        if (pagesMadeYoung != that.pagesMadeYoung) return false;
        if (Double.compare(that.pagesMadeYoungRate, pagesMadeYoungRate) != 0) return false;
        if (pagesNotMadeYoung != that.pagesNotMadeYoung) return false;
        if (Double.compare(that.pagesReadRate, pagesReadRate) != 0) return false;
        if (Double.compare(that.pagesWrittenRate, pagesWrittenRate) != 0) return false;
        if (pendingDecompress != that.pendingDecompress) return false;
        if (pendingFlushList != that.pendingFlushList) return false;
        if (pendingFlushLru != that.pendingFlushLru) return false;
        if (pendingReads != that.pendingReads) return false;
        if (poolId != that.poolId) return false;
        if (poolSize != that.poolSize) return false;
        if (Double.compare(that.readAheadEvictedRate, readAheadEvictedRate) != 0) return false;
        if (Double.compare(that.readAheadRate, readAheadRate) != 0) return false;
        if (uncompressCurrent != that.uncompressCurrent) return false;
        if (uncompressTotal != that.uncompressTotal) return false;
        if (youngMakePerThousandGets != that.youngMakePerThousandGets) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (poolId ^ (poolId >>> 32));
        result = 31 * result + (int) (poolSize ^ (poolSize >>> 32));
        result = 31 * result + (int) (freeBuffers ^ (freeBuffers >>> 32));
        result = 31 * result + (int) (databasePages ^ (databasePages >>> 32));
        result = 31 * result + (int) (oldDatabasePages ^ (oldDatabasePages >>> 32));
        result = 31 * result + (int) (modifiedDatabasePages ^ (modifiedDatabasePages >>> 32));
        result = 31 * result + (int) (pendingDecompress ^ (pendingDecompress >>> 32));
        result = 31 * result + (int) (pendingReads ^ (pendingReads >>> 32));
        result = 31 * result + (int) (pendingFlushLru ^ (pendingFlushLru >>> 32));
        result = 31 * result + (int) (pendingFlushList ^ (pendingFlushList >>> 32));
        result = 31 * result + (int) (pagesMadeYoung ^ (pagesMadeYoung >>> 32));
        result = 31 * result + (int) (pagesNotMadeYoung ^ (pagesNotMadeYoung >>> 32));
        temp = pagesMadeYoungRate != +0.0d ? Double.doubleToLongBits(pagesMadeYoungRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = pagesMadeNotYoungRate != +0.0d ? Double.doubleToLongBits(pagesMadeNotYoungRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (numberPagesRead ^ (numberPagesRead >>> 32));
        result = 31 * result + (int) (numberPagesCreated ^ (numberPagesCreated >>> 32));
        result = 31 * result + (int) (numberPagesWritten ^ (numberPagesWritten >>> 32));
        temp = pagesReadRate != +0.0d ? Double.doubleToLongBits(pagesReadRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = pagesCreateRate != +0.0d ? Double.doubleToLongBits(pagesCreateRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = pagesWrittenRate != +0.0d ? Double.doubleToLongBits(pagesWrittenRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (numberPagesGet ^ (numberPagesGet >>> 32));
        result = 31 * result + (int) (hitRate ^ (hitRate >>> 32));
        result = 31 * result + (int) (youngMakePerThousandGets ^ (youngMakePerThousandGets >>> 32));
        result = 31 * result + (int) (notYoungMakePerThousandGets ^ (notYoungMakePerThousandGets >>> 32));
        result = 31 * result + (int) (numberPagesReadAhead ^ (numberPagesReadAhead >>> 32));
        result = 31 * result + (int) (numberReadAheadEvicted ^ (numberReadAheadEvicted >>> 32));
        temp = readAheadRate != +0.0d ? Double.doubleToLongBits(readAheadRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = readAheadEvictedRate != +0.0d ? Double.doubleToLongBits(readAheadEvictedRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (lruIoTotal ^ (lruIoTotal >>> 32));
        result = 31 * result + (int) (lruIoCurrent ^ (lruIoCurrent >>> 32));
        result = 31 * result + (int) (uncompressTotal ^ (uncompressTotal >>> 32));
        result = 31 * result + (int) (uncompressCurrent ^ (uncompressCurrent >>> 32));
        return result;
    }
}
