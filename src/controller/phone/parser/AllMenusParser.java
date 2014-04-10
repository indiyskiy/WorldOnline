package controller.phone.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import controller.phone.entity.AllMenusRequest;
import controller.phone.entity.MobileRequest;
import model.constants.ExceptionTexts;
import model.database.requests.AdminUserRequest;
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
        String idString = request.getParameter("userID");
        if (idString == null || idString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allMenusUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(idString);
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
