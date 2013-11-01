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
@javax.persistence.Table(name = "PARAMETERS", schema = "", catalog = "information_schema")
@Entity
public class ParametersEntity {
    private String specificCatalog;

    @javax.persistence.Column(name = "SPECIFIC_CATALOG")
    @Basic
    public String getSpecificCatalog() {
        return specificCatalog;
    }

    public void setSpecificCatalog(String specificCatalog) {
        this.specificCatalog = specificCatalog;
    }

    private String specificSchema;

    @javax.persistence.Column(name = "SPECIFIC_SCHEMA")
    @Basic
    public String getSpecificSchema() {
        return specificSchema;
    }

    public void setSpecificSchema(String specificSchema) {
        this.specificSchema = specificSchema;
    }

    private String specificName;

    @javax.persistence.Column(name = "SPECIFIC_NAME")
    @Basic
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    private int ordinalPosition;

    @javax.persistence.Column(name = "ORDINAL_POSITION")
    @Basic
    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    private String parameterMode;

    @javax.persistence.Column(name = "PARAMETER_MODE")
    @Basic
    public String getParameterMode() {
        return parameterMode;
    }

    public void setParameterMode(String parameterMode) {
        this.parameterMode = parameterMode;
    }

    private String parameterName;

    @javax.persistence.Column(name = "PARAMETER_NAME")
    @Basic
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
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

    private String routineType;

    @javax.persistence.Column(name = "ROUTINE_TYPE")
    @Basic
    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParametersEntity that = (ParametersEntity) o;

        if (characterMaximumLength != that.characterMaximumLength) return false;
        if (characterOctetLength != that.characterOctetLength) return false;
        if (datetimePrecision != that.datetimePrecision) return false;
        if (numericPrecision != that.numericPrecision) return false;
        if (numericScale != that.numericScale) return false;
        if (ordinalPosition != that.ordinalPosition) return false;
        if (characterSetName != null ? !characterSetName.equals(that.characterSetName) : that.characterSetName != null)
            return false;
        if (collationName != null ? !collationName.equals(that.collationName) : that.collationName != null)
            return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (dtdIdentifier != null ? !dtdIdentifier.equals(that.dtdIdentifier) : that.dtdIdentifier != null)
            return false;
        if (parameterMode != null ? !parameterMode.equals(that.parameterMode) : that.parameterMode != null)
            return false;
        if (parameterName != null ? !parameterName.equals(that.parameterName) : that.parameterName != null)
            return false;
        if (routineType != null ? !routineType.equals(that.routineType) : that.routineType != null) return false;
        if (specificCatalog != null ? !specificCatalog.equals(that.specificCatalog) : that.specificCatalog != null)
            return false;
        if (specificName != null ? !specificName.equals(that.specificName) : that.specificName != null) return false;
        if (specificSchema != null ? !specificSchema.equals(that.specificSchema) : that.specificSchema != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = specificCatalog != null ? specificCatalog.hashCode() : 0;
        result = 31 * result + (specificSchema != null ? specificSchema.hashCode() : 0);
        result = 31 * result + (specificName != null ? specificName.hashCode() : 0);
        result = 31 * result + ordinalPosition;
        result = 31 * result + (parameterMode != null ? parameterMode.hashCode() : 0);
        result = 31 * result + (parameterName != null ? parameterName.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + characterMaximumLength;
        result = 31 * result + characterOctetLength;
        result = 31 * result + (int) (numericPrecision ^ (numericPrecision >>> 32));
        result = 31 * result + numericScale;
        result = 31 * result + (int) (datetimePrecision ^ (datetimePrecision >>> 32));
        result = 31 * result + (characterSetName != null ? characterSetName.hashCode() : 0);
        result = 31 * result + (collationName != null ? collationName.hashCode() : 0);
        result = 31 * result + (dtdIdentifier != null ? dtdIdentifier.hashCode() : 0);
        result = 31 * result + (routineType != null ? routineType.hashCode() : 0);
        return result;
    }
}
