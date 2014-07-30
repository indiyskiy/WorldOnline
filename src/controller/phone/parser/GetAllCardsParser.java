package controller.phone.parser;

import com.google.gson.JsonArray;
import controller.phone.entity.AllCardsRequest;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import model.phone.responseentity.AllCardsResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class GetAllCardsParser implements MobileParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetAllCardsParser.class);

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
            } catch (Exception e) {
                throw new ParseRequestException(ExceptionTexts.allCardsLimitIncorrectException);
            }
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

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllCardsResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllCardsResponse.class);
        }
        AllCardsResponse allCardsResponse = (AllCardsResponse) mobileResponseEntity;
        return getResponse(allCardsResponse);
    }

    public String getResponse(AllCardsResponse allCardsResponse) throws IllegalTypeException {
        JsonArray jsonArray = new JsonArray();
        for (MobileCardInfo mobileCardInfo : allCardsResponse.getCardList()) {
            jsonArray.add(mobileCardInfo.toJson());
        }
        return jsonArray.toString();
    }
}
