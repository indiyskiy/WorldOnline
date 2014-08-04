package controller.phone.parser;

import controller.phone.entity.AllMenuIDsRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllMenuIDsParser extends MobileParser {
    public AllMenuIDsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllMenuIDsRequest allMenuIDsRequest = new AllMenuIDsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserNotExistException);
        }
        allMenuIDsRequest.setUserID(userID);
        return allMenuIDsRequest;
    }
}
