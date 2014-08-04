package controller.phone.parser;

import controller.phone.entity.AllCardParameterTypesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllCardParameterTypesParser extends MobileParser {
    public AllCardParameterTypesRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllCardParameterTypesRequest allCardParameterTypesRequest = new AllCardParameterTypesRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesUserNotExistException);
        }
        allCardParameterTypesRequest.setUserID(userID);
        return allCardParameterTypesRequest;
    }
}
