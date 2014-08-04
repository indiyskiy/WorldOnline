package controller.phone.parser;

import controller.phone.entity.AllPricesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllPricesParser extends MobileParser {
    @Override
    public AllPricesRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllPricesRequest allPricesRequest = new AllPricesRequest();
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
        allPricesRequest.setUserID(userID);
        return allPricesRequest;
    }
}
