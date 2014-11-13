package controller.phone.parser;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.UserGlobalUpdatedRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserGlobalUpdatedParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        UserGlobalUpdatedRequest userGlobalUpdatedRequest = new UserGlobalUpdatedRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.userGlobalUpdatedUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.userGlobalUpdatedIncorrectException);
        }
        UserEntity userEntity = UserRequests.getUserByID(userID);
        if (userEntity == null) {
            throw new ParseRequestException(ExceptionTexts.userGlobalUpdatedUserNotExistException);
        }
        userGlobalUpdatedRequest.setUserID(userID);
        userGlobalUpdatedRequest.setUser(userEntity);
        return userGlobalUpdatedRequest;
    }
}
