package controller.phone.parser;

import com.google.gson.JsonObject;
import controller.phone.entity.ParsedRegistrationRequest;
import model.constants.ExceptionTexts;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;
import model.phone.responseentity.RegistrationResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationParser {

    public static ParsedRegistrationRequest parse(HttpServletRequest request) throws ParseRequestException {
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
        parsedRegistrationRequest.setDeviceID(deviceID);

        //todo write method
        return parsedRegistrationRequest;
    }

    public static String getResponse(RegistrationResponse registrationResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", registrationResponse.getStatus().toString());
        responseObj.addProperty("id", registrationResponse.getUserID());
        return responseObj.toString();
    }
}
