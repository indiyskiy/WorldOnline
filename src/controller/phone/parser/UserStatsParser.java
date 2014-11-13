package controller.phone.parser;


import controller.phone.entity.MobileRequest;
import controller.phone.entity.UserStatsRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserStatsParser extends MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        UserStatsRequest allCardParameterTypesRequest = new UserStatsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.userStatsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.userStatsIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.userStatsUserNotExistException);
        }
        allCardParameterTypesRequest.setUserID(userID);
        return allCardParameterTypesRequest;
    }
}
