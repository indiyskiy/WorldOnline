package controller.phone.parser;

import controller.phone.entity.AllCardsRequest;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AllCardsParser extends MobileParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllCardsParser.class);
    public static final Integer MaxLimit = 100;

    @Override
    public AllCardsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllCardsRequest allCardsRequest = new AllCardsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (NumberFormatException e) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserIDIncorrectException);
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ParseRequestException(ExceptionTexts.allCardsUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserNotExistException);
        }
        String limitString = request.getParameter("limit");
        if (limitString != null && !limitString.isEmpty()) {
            Integer limit;
            try {
                limit = Integer.parseInt(limitString);
                allCardsRequest.setLimit(limit);
                if (limit > MaxLimit) {
                    throw new ParseRequestException(ExceptionTexts.allCardsLimitTooSmallException);
                }
            } catch (ParseRequestException e) {
                throw e;
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.allCardsLimitIncorrectException);
            }
        } else {
            throw new ParseRequestException(ExceptionTexts.allCardsLimitEmptyException);
        }
        String offsetString = request.getParameter("offset");
        if (offsetString != null && !offsetString.isEmpty()) {
            Integer offset;
            try {
                offset = Integer.parseInt(offsetString);
                allCardsRequest.setOffset(offset);
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.allCardsOffsetIncorrectException);
            }
        }
        allCardsRequest.setUserID(userID);
        return allCardsRequest;
    }
}
