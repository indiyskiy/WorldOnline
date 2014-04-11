package controller.phone.parser;

import com.google.gson.JsonObject;
import controller.phone.entity.GetMenuRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.MenuCompleteInformation;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.GetMenuResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 11.04.14.
 */
public class GetMenuParser implements MobileParser {
    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException {
        GetMenuRequest getMenuRequest = new GetMenuRequest();
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
        getMenuRequest.setUserID(userID);

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
        getMenuRequest.setMenuID(menuID);
        return getMenuRequest;
    }

    public String getResponse(GetMenuResponse getMenuResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", getMenuResponse.getStatus().toString());
        MenuCompleteInformation menuCompleteInformation = getMenuResponse.getMenuCompleteInformation();
        responseObj.addProperty("menuID", menuCompleteInformation.getMenuID());
        responseObj.addProperty("iconImageID", menuCompleteInformation.getIconImageID());
        responseObj.addProperty("number", menuCompleteInformation.getNumber());
        responseObj.addProperty("parentMenuID", menuCompleteInformation.getParentMenuID());
        responseObj.addProperty("pushedIconImageID", menuCompleteInformation.getPushedIconImageID());
        responseObj.addProperty("text", menuCompleteInformation.getText());
        return responseObj.toString();
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != GetMenuResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, GetMenuResponse.class);
        }
        GetMenuResponse getMenuResponse = (GetMenuResponse) mobileResponseEntity;
        return getResponse(getMenuResponse);
    }
}
