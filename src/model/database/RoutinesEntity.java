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
@javax.persistence.Table(name = "ROUTINES", schema = "", catalog = "information_schema")
@Entity
public class RoutinesEntity {
    private String specificName;

    @javax.persistence.Column(name = "SPECIFIC_NAME")
    @Basic
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    private String routineCatalog;

    @javax.persistence.Column(name = "ROUTINE_CATALOG")
    @Basic
    public String getRoutineCatalog() {
        return routineCatalog;
    }

    public void setRoutineCatalog(String routineCatalog) {
        this.routineCatalog = routineCatalog;
    }

    private String routineSchema;

    @javax.persistence.Column(name = "ROUTINE_SCHEMA")
    @Basic
    public String getRoutineSchema() {
        return routineSchema;
    }

    public void setRoutineSchema(String routineSchema) {
        this.routineSchema = routineSchema;
    }

    private String routineName;

    @javax.persistence.Column(name = "ROUTINE_NAME")
    @Basic
    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    private String routineType;

    @javax.persistence.Column(name = "ROUTINE_TYPE")
    @Basic
    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    private String dataType;

    @javax.persistence.Column(name = "DATA_TYPE")
    @Basic
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    private int characterMaximumLength;

    @javax.persistence.Column(name = "CHARACTER_MAXIMUM_LENGTH")
    @Basic
    public int getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(int characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    private int characterOctetLength;

    @javax.persistence.Column(name = "CHARACTER_OCTET_LENGTH")
    @Basic
    public int getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(int characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    private long numericPrecision;

    @javax.persistence.Column(name = "NUMERIC_PRECISION")
    @Basic
    public long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    private int numericScale;

    @javax.persistence.Column(name = "NUMERIC_SCALE")
    @Basic
    public int getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(int numericScale) {
        this.numericScale = numericScale;
    }

    private long datetimePrecision;

    @javax.persistence.Column(name = "DATETIME_PRECISION")
    @Basic
    public long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    private String characterSetName;

    @javax.persistence.Column(name = "CHARACTER_SET_NAME")
    @Basic
    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    private String collationName;

    @javax.persistence.Column(name = "COLLATION_NAME")
    @Basic
    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    private String dtdIdentifier;

    @javax.persistence.Column(name = "DTD_IDENTIFIER")
    @Basic
    public String getDtdIdentifier() {
        return dtdIdentifier;
    }

    public void setDtdIdentifier(String dtdIdentifier) {
        this.dtdIdentifier = dtdIdentifier;
    }

    private String routineBody;

    @javax.persistence.Column(name = "ROUTINE_BODY")
    @Basic
    public String getRoutineBody() {
        return routineBody;
    }

    public void setRoutineBody(String routineBody) {
        this.routineBody = routineBody;
    }

    private String routineDefinition;

    @javax.persistence.Column(name = "ROUTINE_DEFINITION")
    @Basic
    public String getRoutineDefinition() {
        return routineDefinition;
    }

    public void setRoutineDefinition(String routineDefinition) {
        this.routineDefinition = routineDefinition;
    }

    private String externalName;

    @javax.persistence.Column(name = "EXTERNAL_NAME")
    @Basic
    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    private String externalLanguage;

    @javax.persistence.Column(name = "EXTERNAL_LANGUAGE")
    @Basic
    public String getExternalLanguage() {
        return externalLanguage;
    }

    public void setExternalLanguage(String externalLanguage) {
        this.externalLanguage = externalLanguage;
    }

    private String parameterStyle;

    @javax.persistence.Column(name = "PARAMETER_STYLE")
    @Basic
    public String getParameterStyle() {
        return parameterStyle;
    }

    public void setParameterStyle(String parameterStyle) {
        this.parameterStyle = parameterStyle;
    }

    private String isDeterministic;

    @javax.persistence.Column(name = "IS_DETERMINISTIC")
    @Basic
    public String getDeterministic() {
        return isDeterministic;
    }

    public void setDeterministic(String deterministic) {
        isDeterministic = deterministic;
    }

    private String sqlDataAccess;

    @javax.persistence.Column(name = "SQL_DATA_ACCESS")
    @Basic
    public String getSqlDataAccess() {
        return sqlDataAccess;
    }

    public void setSqlDataAccess(String sqlDataAccess) {
        this.sqlDataAccess = sqlDataAccess;
    }

    private String sqlPath;

    @javax.persistence.Column(name = "SQL_PATH")
    @Basic
    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    private String securityType;

    @javax.persistence.Column(name = "SECURITY_TYPE")
    @Basic
    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    private Timestamp created;

    @javax.persistence.Column(name = "CREATED")
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private Timestamp lastAltered;

    @javax.persistence.Column(name = "LAST_ALTERED")
    @Basic
    public Timestamp getLastAltered() {
        return lastAltered;
    }

    public void setLastAltered(Timestamp lastAltered) {
        this.lastAltered = lastAltered;
    }

    private String sqlMode;

    @javax.persistence.Column(name = "SQL_MODE")
    @Basic
    public String getSqlMode() {
        return sqlMode;
    }

    public void setSqlMode(String sqlMode) {
        this.sqlMode = sqlMode;
    }

    private String routineComment;

    @javax.persistence.Column(name = "ROUTINE_COMMENT")
    @Basic
    public String getRoutineComment() {
        return routineComment;
    }

    public void setRoutineComment(String routineComment) {
        this.routineComment = routineComment;
    }

    private String definer;

    @javax.persistence.Column(name = "DEFINER")
    @Basic
    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    private String characterSetClient;

    @javax.persistence.Column(name = "CHARACTER_SET_CLIENT")
    @Basic
    public String getCharacterSetClient() {
        return characterSetClient;
    }

    public void setCharacterSetClient(String characterSetClient) {
        this.characterSetClient = characterSetClient;
    }

    private String collationConnection;

    @javax.persistence.Column(name = "COLLATION_CONNECTION")
    @Basic
    public String getCollationConnection() {
        return collationConnection;
    }

    public void setCollationConnection(String collationConnection) {
        this.collationConnection = collationConnection;
    }

    private String databaseCollation;

    @javax.persistence.Column(name = "DATABASE_COLLATION")
    @Basic
    public String getDatabaseCollation() {
        return databaseCollation;
    }

    public void setDatabaseCollation(String databaseCollation) {
        this.databaseCollation = databaseCollation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutinesEntity that = (RoutinesEntity) o;

        if (characterMaximumLength != that.characterMaximumLength) return false;
        if (characterOctetLength != that.characterOctetLength) return false;
        if (datetimePrecision != that.datetimePrecision) return false;
        if (numericPrecision != that.numericPrecision) return false;
        if (numericScale != that.numericScale) return false;
        if (characterSetClient != null ? !characterSetClient.equals(that.characterSetClient) : that.characterSetClient != null)
            return false;
        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (collationConnection != null ? !collationConnection.equals(that.collationConnection) : that.collationConnection != null)
            return false;
        if (collationName != null ? !collationName.equals(that.collationName) : that.collationName != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (databaseCollation != null ? !databaseCollation.equals(that.databaseCollation) : that.databaseCollation != null)
            return false;
        if (definer != null ? !definer.equals(that.definer) : that.definer != null) return false;
        if (dtdIdentifier != null ? !dtdIdentifier.equals(that.dtdIdentifier) : that.dtdIdentifier != null)
            return false;
        if (externalLanguage != null ? !externalLanguage.equals(that.externalLanguage) : that.externalLanguage != null)
            return false;
        if (externalName != null ? !externalName.equals(that.externalName) : that.externalName != null) return false;
        if (isDeterministic != null ? !isDeterministic.equals(that.isDeterministic) : that.isDeterministic != null)
            return false;
        if (lastAltered != null ? !lastAltered.equals(that.lastAltered) : that.lastAltered != null) return false;
        if (parameterStyle != null ? !parameterStyle.equals(that.parameterStyle) : that.parameterStyle != null)
            return false;
        if (routineBody != null ? !routineBody.equals(that.routineBody) : that.routineBody != null) return false;
        if (routineCatalog != null ? !routineCatalog.equals(that.routineCatalog) : that.routineCatalog != null)
            return false;
        if (routineComment != null ? !routineComment.equals(that.routineComment) : that.routineComment != null)
            return false;
        if (routineDefinition != null ? !routineDefinition.equals(that.routineDefinition) : that.routineDefinition != null)
            return false;
        if (routineName != null ? !routineName.equals(that.routineName) : that.routineName != null) return false;
        if (routineSchema != null ? !routineSchema.equals(that.routineSchema) : that.routineSchema != null)
            return false;
        if (routineType != null ? !routineType.equals(that.routineType) : that.routineType != null) return false;
        if (securityType != null ? !securityType.equals(that.securityType) : that.securityType != null) return false;
        if (specificName != null ? !specificName.equals(that.specificName) : that.specificName != null) return false;
        if (sqlDataAccess != null ? !sqlDataAccess.equals(that.sqlDataAccess) : that.sqlDataAccess != null)
            return false;
        if (sqlMode != null ? !sqlMode.equals(that.sqlMode) : that.sqlMode != null) return false;
        if (sqlPath != null ? !sqlPath.equals(that.sqlPath) : that.sqlPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = specificName != null ? specificName.hashCode() : 0;
        result = 31 * result + (routineCatalog != null ? routineCatalog.hashCode() : 0);
        result = 31 * result + (routineSchema != null ? routineSchema.hashCode() : 0);
        result = 31 * result + (routineName != null ? routineName.hashCode() : 0);
        result = 31 * result + (routineType != null ? routineType.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + characterMaximumLength;
        result = 31 * result + characterOctetLength;
        result = 31 * result + (int) (numericPrecision ^ (numericPrecision >>> 32));
        result = 31 * result + numericScale;
        result = 31 * result + (int) (datetimePrecision ^ (datetimePrecision >>> 32));
        result = 31 * result + (characterSetName != null ? characterSetName.hashCode() : 0);
        result = 31 * result + (collationName != null ? collationName.hashCode() : 0);
        result = 31 * result + (dtdIdentifier != null ? dtdIdentifier.hashCode() : 0);
        result = 31 * result + (routineBody != null ? routineBody.hashCode() : 0);
        result = 31 * result + (routineDefinition != null ? routineDefinition.hashCode() : 0);
        result = 31 * result + (externalName != null ? externalName.hashCode() : 0);
        result = 31 * result + (externalLanguage != null ? externalLanguage.hashCode() : 0);
        result = 31 * result + (parameterStyle != null ? parameterStyle.hashCode() : 0);
        result = 31 * result + (isDeterministic != null ? isDeterministic.hashCode() : 0);
        result = 31 * result + (sqlDataAccess != null ? sqlDataAccess.hashCode() : 0);
        result = 31 * result + (sqlPath != null ? sqlPath.hashCode() : 0);
        result = 31 * result + (securityType != null ? securityType.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastAltered != null ? lastAltered.hashCode() : 0);
        result = 31 * result + (sqlMode != null ? sqlMode.hashCode() : 0);
        result = 31 * result + (routineComment != null ? routineComment.hashCode() : 0);
        result = 31 * result + (definer != null ? definer.hashCode() : 0);
        result = 31 * result + (characterSetClient != null ? characterSetClient.hashCode() : 0);
        result = 31 * result + (collationConnection != null ? collationConnection.hashCode() : 0);
        result = 31 * result + (databaseCollation != null ? databaseCollation.hashCode() : 0);
        return result;
    }
}
