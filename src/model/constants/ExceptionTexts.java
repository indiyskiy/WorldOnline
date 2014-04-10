package model.constants;

import java.util.HashSet;

public class ExceptionTexts {

    //MOBILE
    public static final HashSet<String> mobileErrors = getMobileErrors();
    //registration
    public static final String registrationNoModelException = "phone.registration.model.empty";
    public static final String registrationNoDeviceIDException = "phone.registration.deviceID.empty";
    public static final String registrationDeviceIDNotUniqueException = "phone.registration.deviceID.notUnique";
    public static final String registrationNoLanguageException = "phone.registration.language.empty";
    public static final String registrationIllegalLanguageException = "phone.registration.language.illegal";
    public static final String registrationNoDeviseTokenException = "phone.registration.deviceToken.empty";
    public static final String registrationNoPlatformTypeException = "phone.registration.mobilePlatform.empty";
    public static final String registrationIllegalMobilePlatformException = "phone.registration.mobilePlatform.illegal";
    public static final String allMenusUserIDEmptyException = "phone.getAllMenus.UserID.empty";
    public static final String allMenusUserIDIncorrectException = "phone.getAllMenus.UserID.Incorrect";
    public static final String allMenusUserNotExistException = "phone.getAllMenus.UserID.NotExist";

    private static HashSet<String> getMobileErrors() {
        HashSet<String> mobileErrors = new HashSet<String>();
        mobileErrors.add(registrationNoModelException);
        mobileErrors.add(registrationNoDeviceIDException);
        mobileErrors.add(registrationDeviceIDNotUniqueException);
        mobileErrors.add(registrationNoLanguageException);
        mobileErrors.add(registrationIllegalLanguageException);
        return mobileErrors;
    }
}
