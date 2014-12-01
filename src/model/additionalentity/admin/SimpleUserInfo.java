package model.additionalentity.admin;


import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;

import java.sql.Timestamp;

public class SimpleUserInfo {

    private long userID;
    private LanguageType userLanguage;
    private String additionalInformation;
    private Timestamp lastConnectionTimestamp;
    private Timestamp lastSynchronizeTimestamp;
    private MobilePlatform mobilePlatform;
    private Timestamp userRegistrationTimestamp;

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserLanguage(LanguageType userLanguage) {
        this.userLanguage = userLanguage;
    }

    public LanguageType getUserLanguage() {
        return userLanguage;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setLastConnectionTimestamp(Timestamp lastConnectionTimestamp) {
        this.lastConnectionTimestamp = lastConnectionTimestamp;
    }

    public Timestamp getLastConnectionTimestamp() {
        return lastConnectionTimestamp;
    }

    public void setLastSynchronizeTimestamp(Timestamp lastSynchronizeTimestamp) {
        this.lastSynchronizeTimestamp = lastSynchronizeTimestamp;
    }

    public Timestamp getLastSynchronizeTimestamp() {
        return lastSynchronizeTimestamp;
    }

    public void setMobilePlatform(MobilePlatform mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }

    public MobilePlatform getMobilePlatform() {
        return mobilePlatform;
    }

    public void setUserRegistrationTimestamp(Timestamp userRegistrationTimestamp) {
        this.userRegistrationTimestamp = userRegistrationTimestamp;
    }

    public Timestamp getUserRegistrationTimestamp() {
        return userRegistrationTimestamp;
    }
}
