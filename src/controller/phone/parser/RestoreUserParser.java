package controller.phone.parser;


import com.google.gson.JsonObject;
import controller.phone.entity.RestoreUserRequest;
import model.constants.ExceptionTexts;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.RestoreUserResponse;

import javax.servlet.http.HttpServletRequest;

public class RestoreUserParser implements MobileParser {

    @Override
    public RestoreUserRequest parse(HttpServletRequest request) throws ParseRequestException {
        String deviceID = request.getParameter("deviceID");
        if (deviceID == null || deviceID.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.restoreUserDeviceIDEmptyException);
        }
        RestoreUserRequest restoreUserRequest = new RestoreUserRequest();
        restoreUserRequest.setDeviceID(deviceID);
        return restoreUserRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != RestoreUserResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, RestoreUserResponse.class);
        }
        RestoreUserResponse restoreUserResponse = (RestoreUserResponse) mobileResponseEntity;
        return getResponse(restoreUserResponse);
    }

    public String getResponse(RestoreUserResponse restoreUserResponse) throws IllegalTypeException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", restoreUserResponse.getStatus().toString());
        jsonObject.addProperty("userID", restoreUserResponse.getUserID());
        return jsonObject.toString();
    }
}
