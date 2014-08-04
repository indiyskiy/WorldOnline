package controller.phone.parser;

import controller.phone.entity.ViewImagesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class ViewImagesParser extends MobileParser {

    @Override
    public ViewImagesRequest parse(HttpServletRequest request) throws ParseRequestException {
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
        ViewImagesRequest viewImagesRequest = new ViewImagesRequest();
        viewImagesRequest.setUserID(userID);
        return viewImagesRequest;
    }

}
