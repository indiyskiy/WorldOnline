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
    public static final String getMenuUserIDEmptyException = "phone.getMenu.UserID.empty";
    public static final String getMenuUserIDIncorrectException = "phone.getMenu.UserID.Incorrect";
    public static final String getMenuUserNotExistException = "phone.getMenu.UserID.NotExist";
    public static final String getMenuMenuIDEmptyException = "phone.getMenu.MenuID.empty";
    public static final String getMenuMenuIDIncorrectException = "phone.getMenu.MenuID.Incorrect";
    public static final String allCardsUserIDEmptyException = "phone.getAllCards.UserID.empty";
    public static final String allCardsUserIDIncorrectException = "phone.getAllCards.UserID.Incorrect";
    public static final String allCardsUserNotExistException = "phone.getAllCards.UserID.NotExist";
    public static final String allCardsLimitIncorrectException = "phone.getAllCards.Limit.Incorrect";
    public static final String allCardsLimitTooBigException = "phone.getAllCards.Limit.TooBig";
    public static final String allCardsLimitEmptyException = "phone.getAllCards.Limit.Empty";
    public static final String allCardsOffsetIncorrectException = "phone.getAllCards.Offset.Incorrect";
    public static final String weatherUserIDEmptyException = "phone.weather.UserID.empty";
    public static final String weatherUserIDIncorrectException = "phone.weather.UserID.Incorrect";
    public static final String weatherUserNotExistException = "phone.weather.UserID.NotExist";
    public static final String getViewImagesUserIDEmptyException = "phone.getViewImages.UserID.Empty";
    public static final String getViewImagesUserIDIncorrectException = "phone.getViewImages.UserID.Incorrect";
    public static final String getViewImagesUserNotExistException = "phone.getViewImages.UserID.NotExist";
    public static final String restoreUserDeviceIDEmptyException = "phone.restoreUser.DeviceID.Empty";
    public static final String restoreUserDeviceNotFoundException = "phone.restoreUser.Device.NotFound";
    public static final String getAllCardParameterTypesUserIDEmptyException = "phone.getAllCardParameterTypes.UserID.Empty";
    public static final String getAllCardParameterTypesIncorrectException = "phone.getAllCardParameterTypes.UserID.Incorrect";
    public static final String getAllCardParameterTypesUserNotExistException = "phone.getAllCardParameterTypes.UserID.NotExist";
    public static final String allTagsUserIDEmptyException = "phone.allTags.UserID.Empty";
    public static final String allTagsUserIDIncorrectException = "phone.allTags.UserID.Incorrect";
    public static final String allTagsUserNotExistException = "phone.allTags.UserID.NotExist";
    public static final String getAllDishCategoriesUserIDEmptyException = "phone.getAllDishCategories.UserID.Empty";
    public static final String getAllDishCategoriesIncorrectException = "phone.getAllDishCategories.UserID.Incorrect";
    public static final String getAllDishCategoriesUserNotExistException = "phone.getAllDishCategories.UserID.NotExist";
    public static String getAllDishTagsUserIDEmptyException = "phone.getAllDishTags.UserID.Empty";
    public static String getAllDishTagsIncorrectException = "phone.getAllDishTags.UserID.Incorrect";
    public static String getAllDishTagsUserNotExistException = "phone.getAllDishTags.UserID.NotExist";
    public static String allPricesLimitTooBigException = "phone.allPrices.Limit.TooBig";
    public static String allPricesLimitIncorrectException = "phone.allPrices.Limit.Incorrect";
    public static String allPricesLimitEmptyException = "phone.allPrices.Offset.Empty";
    public static String allPricesOffsetIncorrectException = "phone.allPrices.Offset.Incorrect";


    private static HashSet<String> getMobileErrors() {
        HashSet<String> mobileErrors = new HashSet<String>();
        mobileErrors.add(registrationNoModelException);
        mobileErrors.add(registrationNoDeviceIDException);
        mobileErrors.add(registrationDeviceIDNotUniqueException);
        mobileErrors.add(registrationNoLanguageException);
        mobileErrors.add(registrationIllegalLanguageException);
        mobileErrors.add(allMenusUserIDEmptyException);
        mobileErrors.add(allMenusUserIDIncorrectException);
        mobileErrors.add(allMenusUserNotExistException);
        return mobileErrors;
    }
}
