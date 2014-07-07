package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.RestoreUserRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.RestoreUserResponse;

import javax.servlet.ServletException;

public class RestoreUserHandler implements MobileHandler {


    public RestoreUserResponse handleRequest(RestoreUserRequest restoreUserRequest) throws IllegalTypeException, ServletException {
        RestoreUserResponse restoreUserResponse = UserRequests.getUserByDeviceID(restoreUserRequest.getDeviceID());
        if (restoreUserResponse == null) {
            throw new ServletException(ExceptionTexts.restoreUserDeviceNotFoundException);
        }
        UserRequests.deleteOldUserInfo(restoreUserResponse.getUserID());
        return restoreUserResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException {
        if (mobileRequest.getClass() != RestoreUserRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, RestoreUserRequest.class);
        }
        RestoreUserRequest restoreUserRequest = (RestoreUserRequest) mobileRequest;
        return handleRequest(restoreUserRequest);
    }
}
