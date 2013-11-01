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
@javax.persistence.Table(name = "STATISTICS", schema = "", catalog = "information_schema")
@Entity
public class StatisticsEntity {
    private String tableCatalog;

    @javax.persistence.Column(name = "TABLE_CATALOG")
    @Basic
    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    private String tableSchema;

    @javax.persistence.Column(name = "TABLE_SCHEMA")
    @Basic
    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
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

    private long nonUnique;

    @javax.persistence.Column(name = "NON_UNIQUE")
    @Basic
    public long getNonUnique() {
        return nonUnique;
    }

    public void setNonUnique(long nonUnique) {
        this.nonUnique = nonUnique;
    }

    private String indexSchema;

    @javax.persistence.Column(name = "INDEX_SCHEMA")
    @Basic
    public String getIndexSchema() {
        return indexSchema;
    }

    public void setIndexSchema(String indexSchema) {
        this.indexSchema = indexSchema;
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

    private long seqInIndex;

    @javax.persistence.Column(name = "SEQ_IN_INDEX")
    @Basic
    public long getSeqInIndex() {
        return seqInIndex;
    }

    public void setSeqInIndex(long seqInIndex) {
        this.seqInIndex = seqInIndex;
    }

    private String columnName;

    @javax.persistence.Column(name = "COLUMN_NAME")
    @Basic
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    private String collation;

    @javax.persistence.Column(name = "COLLATION")
    @Basic
    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }

    private long cardinality;

    @javax.persistence.Column(name = "CARDINALITY")
    @Basic
    public long getCardinality() {
        return cardinality;
    }

    public void setCardinality(long cardinality) {
        this.cardinality = cardinality;
    }

    private long subPart;

    @javax.persistence.Column(name = "SUB_PART")
    @Basic
    public long getSubPart() {
        return subPart;
    }

    public void setSubPart(long subPart) {
        this.subPart = subPart;
    }

    private String packed;

    @javax.persistence.Column(name = "PACKED")
    @Basic
    public String getPacked() {
        return packed;
    }

    public void setPacked(String packed) {
        this.packed = packed;
    }

    private String nullable;

    @javax.persistence.Column(name = "NULLABLE")
    @Basic
    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    private String indexType;

    @javax.persistence.Column(name = "INDEX_TYPE")
    @Basic
    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
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

    private String indexComment;

    @javax.persistence.Column(name = "INDEX_COMMENT")
    @Basic
    public String getIndexComment() {
        return indexComment;
    }

    public void setIndexComment(String indexComment) {
        this.indexComment = indexComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticsEntity that = (StatisticsEntity) o;

        if (cardinality != that.cardinality) return false;
        if (nonUnique != that.nonUnique) return false;
        if (seqInIndex != that.seqInIndex) return false;
        if (subPart != that.subPart) return false;
        if (collation != null ? !collation.equals(that.collation) : that.collation != null) return false;
        if (columnName != null ? !columnName.equals(that.columnName) : that.columnName != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (indexComment != null ? !indexComment.equals(that.indexComment) : that.indexComment != null) return false;
        if (indexName != null ? !indexName.equals(that.indexName) : that.indexName != null) return false;
        if (indexSchema != null ? !indexSchema.equals(that.indexSchema) : that.indexSchema != null) return false;
        if (indexType != null ? !indexType.equals(that.indexType) : that.indexType != null) return false;
        if (nullable != null ? !nullable.equals(that.nullable) : that.nullable != null) return false;
        if (packed != null ? !packed.equals(that.packed) : that.packed != null) return false;
        if (tableCatalog != null ? !tableCatalog.equals(that.tableCatalog) : that.tableCatalog != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (tableSchema != null ? !tableSchema.equals(that.tableSchema) : that.tableSchema != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + (tableSchema != null ? tableSchema.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (int) (nonUnique ^ (nonUnique >>> 32));
        result = 31 * result + (indexSchema != null ? indexSchema.hashCode() : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        result = 31 * result + (int) (seqInIndex ^ (seqInIndex >>> 32));
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (collation != null ? collation.hashCode() : 0);
        result = 31 * result + (int) (cardinality ^ (cardinality >>> 32));
        result = 31 * result + (int) (subPart ^ (subPart >>> 32));
        result = 31 * result + (packed != null ? packed.hashCode() : 0);
        result = 31 * result + (nullable != null ? nullable.hashCode() : 0);
        result = 31 * result + (indexType != null ? indexType.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (indexComment != null ? indexComment.hashCode() : 0);
        return result;
    }
}
