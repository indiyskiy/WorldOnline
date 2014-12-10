package model.additionalentity.admin;

import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CompleteUserCardInfo {
    private long userID;
    private String deviceUniqueKey;
    private String deviceTokenKey;
    private MobilePlatform mobilePlatform;
    private String additionalInformation;
    private LanguageType languageType;
    private Timestamp userRegistration;
    private Timestamp lastSynchronized;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getDeviceUniqueKey() {
        return deviceUniqueKey;
    }

    public void setDeviceUniqueKey(String deviceUniqueKey) {
        this.deviceUniqueKey = deviceUniqueKey;
    }

    public String getDeviceTokenKey() {
        return deviceTokenKey;
    }

    public void setDeviceTokenKey(String deviceTokenKey) {
        this.deviceTokenKey = deviceTokenKey;
    }

    public MobilePlatform getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(MobilePlatform mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public Timestamp getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(Timestamp userRegistration) {
        this.userRegistration = userRegistration;
    }

    public Timestamp getLastSynchronized() {
        return lastSynchronized;
    }

    public void setLastSynchronized(Timestamp lastSynchronized) {
        this.lastSynchronized = lastSynchronized;
    }

    private ArrayList<SimpleUserCard> simpleUserCards = new ArrayList<>();

    public ArrayList<SimpleUserCard> getSimpleUserCards() {
        return simpleUserCards;
    }
}
