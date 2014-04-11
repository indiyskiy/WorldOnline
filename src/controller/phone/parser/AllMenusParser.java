package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import controller.phone.entity.AllMenusRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllMenusResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 09.04.14.
 */
public class AllMenusParser implements MobileParser {
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

    public static String getResponse(AllMenusResponse allMenusResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", allMenusResponse.getStatus().toString());
        JsonArray jsonArray = new JsonArray();
        for (Long id : allMenusResponse.getMenuIDs()) {
            jsonArray.add(new JsonPrimitive(id));
        }
        responseObj.add("menuIDs", jsonArray);
        return responseObj.toString();
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllMenusResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllMenusResponse.class);
        }
        AllMenusResponse allMenusResponse = (AllMenusResponse) mobileResponseEntity;
        return getResponse(allMenusResponse);
    }
}
