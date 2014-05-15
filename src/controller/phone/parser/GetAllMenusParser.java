package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.GetAllMenusRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.GetAllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Илья on 14.04.14.
 */
public class GetAllMenusParser implements MobileParser {

    public GetAllMenusRequest parse(HttpServletRequest request) throws ParseRequestException {
        GetAllMenusRequest getAllMenusRequest = new GetAllMenusRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserNotExistException);
        }
        getAllMenusRequest.setUserID(userID);
        return getAllMenusRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != GetAllMenusResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, GetAllMenusResponse.class);
        }
        GetAllMenusResponse getAllMenusResponse = (GetAllMenusResponse) mobileResponseEntity;
        return getResponse(getAllMenusResponse);
    }

    public String getResponse(GetAllMenusResponse mobileResponseEntity) throws IllegalTypeException {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", mobileResponseEntity.getStatus().toString());
        ArrayList<MenuCompleteInformation> informationArrayList = mobileResponseEntity.getMenusCompleteInformation();
        JsonArray menusArray = new JsonArray();
        for (MenuCompleteInformation menuCompleteInformation : informationArrayList) {
            JsonObject menuObj = new JsonObject();
            menuObj.addProperty("menuID", menuCompleteInformation.getMenuID());
            menuObj.addProperty("iconImageID", menuCompleteInformation.getIconImageID());
            menuObj.addProperty("number", menuCompleteInformation.getNumber());
            menuObj.addProperty("parentMenuID", menuCompleteInformation.getParentMenuID());
            menuObj.addProperty("text", menuCompleteInformation.getText());
            menusArray.add(menuObj);
        }
        responseObj.add("menus", menusArray);
        return responseObj.toString();
    }
}
