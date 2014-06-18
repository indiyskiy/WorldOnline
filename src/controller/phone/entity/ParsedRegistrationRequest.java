package controller.phone.entity;

import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;

public class ParsedRegistrationRequest extends MobileRequest {
    private String model;
    private String deviceID;
    private LanguageType language;
    private String deviceToken;
    private MobilePlatform mobilePlatform;

    public ParsedRegistrationRequest() {
        this.language = LanguageType.Unknown;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setUserID(String deviceID) {
        this.deviceID = deviceID;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public MobilePlatform getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(MobilePlatform mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }
}
