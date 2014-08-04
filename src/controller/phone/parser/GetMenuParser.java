package controller.phone.parser;

import controller.phone.entity.MenuRequest;
import controller.phone.entity.MobileRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class GetMenuParser extends MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException {
        MenuRequest menuRequest = new MenuRequest();
        String idString = request.getParameter("userID");
        if (idString == null || idString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getMenuUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(idString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getMenuUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getMenuUserNotExistException);
        }
        menuRequest.setUserID(userID);

        String menuIdString = request.getParameter("menuID");
        if (menuIdString == null || menuIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getMenuMenuIDEmptyException);
        }
        Long menuID;
        try {
            menuID = Long.parseLong(menuIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getMenuMenuIDIncorrectException);
        }
        menuRequest.setMenuID(menuID);
        return menuRequest;
    }
}
