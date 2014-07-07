package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.AllMenusRequest;
import model.additionalentity.phone.MenuCompleteInformation;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GetAllMenusParser implements MobileParser {

    public AllMenusRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllMenusRequest allMenusRequest = new AllMenusRequest();
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
        allMenusRequest.setUserID(userID);
        return allMenusRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllMenusResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllMenusResponse.class);
        }
        AllMenusResponse allMenusResponse = (AllMenusResponse) mobileResponseEntity;
        return getResponse(allMenusResponse);
    }

    public String getResponse(AllMenusResponse mobileResponseEntity) throws IllegalTypeException {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", mobileResponseEntity.getStatus().getValue());
        ArrayList<MenuCompleteInformation> informationArrayList = mobileResponseEntity.getMenusCompleteInformation();
        responseObj.addProperty("menuCounter", informationArrayList.size());
        JsonArray menusArray = new JsonArray();
        for (MenuCompleteInformation menuCompleteInformation : informationArrayList) {
            JsonObject menuObj = new JsonObject();
            menuObj.addProperty("menuID", menuCompleteInformation.getMenuID());
            menuObj.addProperty("iconImageID", menuCompleteInformation.getIconImageID());
            menuObj.addProperty("number", menuCompleteInformation.getNumber());
            menuObj.addProperty("parentMenuID", menuCompleteInformation.getParentMenuID());
            menuObj.addProperty("text", menuCompleteInformation.getText());
            menuObj.addProperty("cardCounter", menuCompleteInformation.getCardCounter());
            menusArray.add(menuObj);
        }
        responseObj.add("menus", menusArray);
        return responseObj.toString();
    }
}
