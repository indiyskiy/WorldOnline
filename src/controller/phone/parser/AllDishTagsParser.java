package controller.phone.parser;

import controller.phone.entity.AllDishTagsRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllDishTagsParser extends MobileParser {

    @Override
    public AllDishTagsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllDishTagsRequest allDishTagsRequest = new AllDishTagsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getAllDishTagsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getAllDishTagsIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getAllDishTagsUserNotExistException);
        }
        allDishTagsRequest.setUserID(userID);
        return allDishTagsRequest;
    }


}
