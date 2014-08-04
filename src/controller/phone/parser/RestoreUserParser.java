package controller.phone.parser;


import controller.phone.entity.RestoreUserRequest;
import model.constants.ExceptionTexts;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class RestoreUserParser extends MobileParser {

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

}
