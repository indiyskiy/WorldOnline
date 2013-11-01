package model.database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 15.10.13
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(model.database.ProcEntityPK.class)
@javax.persistence.Table(name = "proc", schema = "", catalog = "mysql")
@Entity
public class ProcEntity {
    private String db;

    @javax.persistence.Column(name = "db")
    @Id
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @javax.persistence.Column(name = "type")
    @Id
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String specificName;

    @javax.persistence.Column(name = "specific_name")
    @Basic
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    private String language;

    @javax.persistence.Column(name = "language")
    @Basic
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String sqlDataAccess;

    @javax.persistence.Column(name = "sql_data_access")
    @Basic
    public String getSqlDataAccess() {
        return sqlDataAccess;
    }

    public void setSqlDataAccess(String sqlDataAccess) {
        this.sqlDataAccess = sqlDataAccess;
    }

    private String isDeterministic;

    @javax.persistence.Column(name = "is_deterministic")
    @Basic
    public String getDeterministic() {
        return isDeterministic;
    }

    public void setDeterministic(String deterministic) {
        isDeterministic = deterministic;
    }

    private String securityType;

    @javax.persistence.Column(name = "security_type")
    @Basic
    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    private byte[] paramList;

    @javax.persistence.Column(name = "param_list")
    @Basic
    public byte[] getParamList() {
        return paramList;
    }

    public void setParamList(byte[] paramList) {
        this.paramList = paramList;
    }

    private byte[] returns;

    @javax.persistence.Column(name = "returns")
    @Basic
    public byte[] getReturns() {
        return returns;
    }

    public void setReturns(byte[] returns) {
        this.returns = returns;
    }

    private byte[] body;

    @javax.persistence.Column(name = "body")
    @Basic
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    private String definer;

    @javax.persistence.Column(name = "definer")
    @Basic
    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    private Timestamp created;

    @javax.persistence.Column(name = "created")
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private Timestamp modified;

    @javax.persistence.Column(name = "modified")
    @Basic
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    private String sqlMode;

    @javax.persistence.Column(name = "sql_mode")
    @Basic
    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
    }

    private String comment;

    @javax.persistence.Column(name = "comment")
    @Basic
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String characterSetClient;

    @javax.persistence.Column(name = "character_set_client")
    @Basic
    public String getCharacterSetClient() {
        return characterSetClient;
    }

    public void setCharacterSetClient(String characterSetClient) {
        this.characterSetClient = characterSetClient;
    }

    private String collationConnection;

    @javax.persistence.Column(name = "collation_connection")
    @Basic
    public String getCollationConnection() {
        return collationConnection;
    }

    public void setCollationConnection(String collationConnection) {
        this.collationConnection = collationConnection;
    }

    private String dbCollation;

    @javax.persistence.Column(name = "db_collation")
    @Basic
    public String getDbCollation() {
        return dbCollation;
    }

    public void setDbCollation(String dbCollation) {
        this.dbCollation = dbCollation;
    }

    private byte[] bodyUtf8;

    @javax.persistence.Column(name = "body_utf8")
    @Basic
    public byte[] getBodyUtf8() {
        return bodyUtf8;
    }

    public void setBodyUtf8(byte[] bodyUtf8) {
        this.bodyUtf8 = bodyUtf8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcEntity that = (ProcEntity) o;

        if (!Arrays.equals(body, that.body)) return false;
        if (!Arrays.equals(bodyUtf8, that.bodyUtf8)) return false;
        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (db != null ? !db.equals(that.db) : that.db != null) return false;
        if (dbCollation != null ? !dbCollation.equals(that.dbCollation) : that.dbCollation != null) return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (isDeterministic != null ? !isDeterministic.equals(that.isDeterministic) : that.isDeterministic != null)
            return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(paramList, that.paramList)) return false;
        if (!Arrays.equals(returns, that.returns)) return false;
        if (securityType != null ? !securityType.equals(that.securityType) : that.securityType != null) return false;
        if (specificName != null ? !specificName.equals(that.specificName) : that.specificName != null) return false;
        if (sqlDataAccess != null ? !sqlDataAccess.equals(that.sqlDataAccess) : that.sqlDataAccess != null)
            return false;
        if (sqlMode != null ? !sqlMode.equals(that.sqlMode) : that.sqlMode != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = db != null ? db.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (specificName != null ? specificName.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (sqlDataAccess != null ? sqlDataAccess.hashCode() : 0);
        result = 31 * result + (isDeterministic != null ? isDeterministic.hashCode() : 0);
        result = 31 * result + (securityType != null ? securityType.hashCode() : 0);
        result = 31 * result + (paramList != null ? Arrays.hashCode(paramList) : 0);
        result = 31 * result + (returns != null ? Arrays.hashCode(returns) : 0);
        result = 31 * result + (body != null ? Arrays.hashCode(body) : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (sqlMode != null ? sqlMode.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        result = 31 * result + (dbCollation != null ? dbCollation.hashCode() : 0);
        result = 31 * result + (bodyUtf8 != null ? Arrays.hashCode(bodyUtf8) : 0);
        return result;
    }
}
