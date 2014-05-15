package controller.phone.parser;

import com.google.gson.JsonArray;
import controller.phone.entity.GetAllCardsRequest;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import model.phone.responseentity.GetAllCardsResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 18.04.14.
 */
public class GetAllCardsParser implements MobileParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetAllCardsParser.class);

    @Override
    public GetAllCardsRequest parse(HttpServletRequest request) throws ParseRequestException {
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allCardsUserNotExistException);
        }
        GetAllCardsRequest getAllCardsRequest = new GetAllCardsRequest();
        getAllCardsRequest.setUserID(userID);
        return getAllCardsRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != GetAllCardsResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, GetAllCardsResponse.class);
        }
        GetAllCardsResponse getAllCardsResponse = (GetAllCardsResponse) mobileResponseEntity;
        return getResponse(getAllCardsResponse);
    }

    public String getResponse(GetAllCardsResponse getAllCardsResponse) throws IllegalTypeException {
        JsonArray jsonArray = new JsonArray();
        for (MobileCardInfo mobileCardInfo : getAllCardsResponse.getCardList()) {
            jsonArray.add(mobileCardInfo.toJson());
        }
        return jsonArray.toString();
    }
}
