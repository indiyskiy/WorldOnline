package controller.phone.parser;

import controller.phone.entity.AllPricesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class AllPricesParser extends MobileParser {
    private final Integer MaxLimit = 100;

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
        String limitString = request.getParameter("limit");
        if (limitString != null && !limitString.isEmpty()) {
            Integer limit;
            try {
                limit = Integer.parseInt(limitString);
                allPricesRequest.setLimit(limit);
                if (limit > MaxLimit) {
                    throw new ParseRequestException(ExceptionTexts.allPricesLimitTooBigException);
                }
            } catch (ParseRequestException e) {
                throw e;
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.allPricesLimitIncorrectException);
            }
        } else {
            throw new ParseRequestException(ExceptionTexts.allPricesLimitEmptyException);
        }
        String offsetString = request.getParameter("offset");
        if (offsetString != null && !offsetString.isEmpty()) {
            Integer offset;
            try {
                offset = Integer.parseInt(offsetString);
                allPricesRequest.setOffset(offset);
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.allPricesOffsetIncorrectException);
            }
        }
        return allPricesRequest;
    }
}
