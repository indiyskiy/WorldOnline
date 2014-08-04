package controller.phone.parser;

import controller.phone.entity.AllDishCategoriesRequest;
import controller.phone.entity.MobileRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllDishCategoriesParser extends MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllDishCategoriesRequest allDishCategoriesRequest = new AllDishCategoriesRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getAllDishCategoriesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getAllDishCategoriesIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getAllDishCategoriesUserNotExistException);
        }
        allDishCategoriesRequest.setUserID(userID);
        return allDishCategoriesRequest;
    }
}
