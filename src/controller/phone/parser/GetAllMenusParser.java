package controller.phone.parser;

import controller.phone.entity.AllMenusRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class GetAllMenusParser extends MobileParser {

    public AllMenusRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllMenusRequest allMenusRequest = new AllMenusRequest();
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
        allMenusRequest.setUserID(userID);
        return allMenusRequest;
    }

}
