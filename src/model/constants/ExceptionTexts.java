package model.constants;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionTexts {
    //MOBILE
    //registration
    public static final String registrationNoModelException="phone.registration.model.empty";
    public static final String registrationNoDeviceIDException="phone.registration.deviceID.empty";
    public static final String registrationDeviceIDNotUniqueException="phone.registration.deviceID.notUnique";
    public static final String registrationNoLanguageException="phone.registration.language.empty";
    public static final String registrationIllegalLanguageException="phone.registration.language.illegal";
    public static final HashSet<String> mobileErrors=getMobileErrors();

    private static HashSet<String> getMobileErrors() {
        HashSet<String> mobileErrors=new HashSet<String>();
        mobileErrors.add(registrationNoModelException);
        mobileErrors.add(registrationNoDeviceIDException);
        mobileErrors.add(registrationDeviceIDNotUniqueException);
        mobileErrors.add(registrationNoLanguageException);
        mobileErrors.add(registrationIllegalLanguageException);
        return mobileErrors;
    }
}
