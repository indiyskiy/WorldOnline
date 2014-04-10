package controller.phone.parser;

import com.google.gson.JsonObject;
import controller.phone.entity.ParsedRegistrationRequest;
import model.constants.ExceptionTexts;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.RegistrationResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationParser implements MobileParser {

    public ParsedRegistrationRequest parse(HttpServletRequest request) throws ParseRequestException {
        ParsedRegistrationRequest parsedRegistrationRequest = new ParsedRegistrationRequest();
        String mobileDeviceModel = request.getParameter("model");
        if (mobileDeviceModel == null || mobileDeviceModel.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.registrationNoModelException);
        }
        parsedRegistrationRequest.setModel(mobileDeviceModel);
        String deviceID = request.getParameter("deviceID");
        if (deviceID == null || deviceID.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.registrationNoDeviceIDException);
        }
        parsedRegistrationRequest.setDeviceID(deviceID);
        boolean isUnique = UserRequests.isDeviceIDUnique(parsedRegistrationRequest.getDeviceID());
        if (!isUnique) {
            throw new ParseRequestException(ExceptionTexts.registrationDeviceIDNotUniqueException);
        }
        parsedRegistrationRequest.setModel(mobileDeviceModel);
        String languageValue = request.getParameter("language");
        if (languageValue == null || languageValue.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.registrationNoLanguageException);
        }
        LanguageType languageType = LanguageType.parse(languageValue);
        if (languageType == null || languageType == LanguageType.Unknown) {
            throw new ParseRequestException(ExceptionTexts.registrationIllegalLanguageException);
        }
        parsedRegistrationRequest.setLanguage(languageType);
        String deviceToken = request.getParameter("deviceToken");
        if (deviceToken == null || deviceToken.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.registrationNoDeviseTokenException);
        }
        parsedRegistrationRequest.setDeviceToken(deviceToken);
        String mobilePlatformValue = request.getParameter("mobilePlatform");
        if (mobilePlatformValue == null || mobilePlatformValue.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.registrationNoPlatformTypeException);
        }
        MobilePlatform mobilePlatform = MobilePlatform.parse(mobilePlatformValue);
        if (mobilePlatform == null || mobilePlatform == MobilePlatform.Unknown) {
            throw new ParseRequestException(ExceptionTexts.registrationIllegalMobilePlatformException);
        }
        parsedRegistrationRequest.setMobilePlatform(mobilePlatform);
        return parsedRegistrationRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != RegistrationResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, RegistrationResponse.class);
        }
        RegistrationResponse registrationResponse = (RegistrationResponse) mobileResponseEntity;
        return getResponse(registrationResponse);
    }

    public static String getResponse(RegistrationResponse registrationResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", registrationResponse.getStatus().toString());
        responseObj.addProperty("id", registrationResponse.getUserID());
        return responseObj.toString();
    }
}
