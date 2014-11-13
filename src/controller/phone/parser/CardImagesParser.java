package controller.phone.parser;

import controller.phone.entity.CardImagesRequest;
import model.constants.Component;
import model.constants.ExceptionTexts;
import model.constants.databaseenumeration.ImageType;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CardImagesParser extends MobileParser {

    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardImagesParser.class);

    @Override
    public CardImagesRequest parse(HttpServletRequest request) throws ParseRequestException {
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserNotExistException);
        }
        ImageType imageType;
        try {
            imageType = ImageType.parseInt(Integer.parseInt(request.getParameter("imageType")));
        } catch (Exception e) {
            loggerFactory.error(e);
            throw new ParseRequestException(e.getMessage());
        }
        CardImagesRequest cardImagesRequest = new CardImagesRequest();
        cardImagesRequest.setUserID(userID);
        cardImagesRequest.setImageType(imageType);
        return cardImagesRequest;
    }

}
