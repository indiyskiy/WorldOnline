package model.phone.requesthandler;


import controller.phone.entity.MobileRequest;
import controller.phone.entity.UserGlobalUpdatedRequest;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.UserGlobalUpdatedEntity;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class UserGlobalUpdatedHandler implements MobileHandler {


    public UserGlobalUpdatedEntity handleRequest(UserGlobalUpdatedRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        UserGlobalUpdatedEntity userGlobalUpdatedEntity = new UserGlobalUpdatedEntity();
        UserRequests.userGlobalUpdated(mobileRequest.getUser());
        return userGlobalUpdatedEntity;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, ServletException, SQLException {
        if (mobileRequest.getClass() != UserGlobalUpdatedRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, UserGlobalUpdatedRequest.class);
        }
        UserGlobalUpdatedRequest userGlobalUpdatedRequest = (UserGlobalUpdatedRequest) mobileRequest;
        return handleRequest(userGlobalUpdatedRequest);
    }
}
