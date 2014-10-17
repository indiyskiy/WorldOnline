package controller.phone.parser;

import controller.phone.entity.ExchangeRatesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class ExchangeRatesParser extends MobileParser {
    @Override
    public ExchangeRatesRequest parse(HttpServletRequest request) throws ParseRequestException {
        ExchangeRatesRequest exchangeRatesRequest = new ExchangeRatesRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.exchangeRatesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.exchangeRatesUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.weatherUserNotExistException);
        }
        exchangeRatesRequest.setUserID(userID);
        return exchangeRatesRequest;
    }
}
