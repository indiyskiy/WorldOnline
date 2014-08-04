package controller.phone.parser;

import controller.phone.entity.AllTagsRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllTagsParser extends MobileParser {
    @Override
    public AllTagsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllTagsRequest allTagsRequest = new AllTagsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserNotExistException);
        }
        allTagsRequest.setUserID(userID);
        return allTagsRequest;
    }
}
