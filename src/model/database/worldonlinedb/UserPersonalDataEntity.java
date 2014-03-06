package model.database.worldonlinedb;

import model.constants.databaseenumeration.LanguageType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 25.10.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "UserPersonalData", schema = "", catalog = "worldonline")
@Entity
public class UserPersonalDataEntity {
    @javax.persistence.Column(name = "UserPersonalDataID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userPersonalData;
    @javax.persistence.Column(name = "UserLanguage")
    @Basic
    private Integer userLanguage;
    @javax.persistence.Column(name = "AdditionalInformation")
    @Basic
    private String additionalInformation;

    public UserPersonalDataEntity(LanguageType userLanguage) {
        this.userLanguage = userLanguage.getValue();
        this.additionalInformation = "";
    }

    public UserPersonalDataEntity() {

    }

    public Long getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(Long userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

    public Integer getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(Integer userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPersonalDataEntity that = (UserPersonalDataEntity) o;

        if (userPersonalData != null ? !userPersonalData.equals(that.userPersonalData) : that.userPersonalData != null)
            return false;
        if (userLanguage != null ? !userLanguage.equals(that.userLanguage) : that.userLanguage != null)
            return false;
        if (additionalInformation != null ? !additionalInformation.equals(that.additionalInformation) : that.additionalInformation != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userPersonalData != null ? userPersonalData.hashCode() : 0;
        result = 31 * result + (userLanguage != null ? userLanguage.hashCode() : 0);
        result = 31 * result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
        return result;
    }
}
