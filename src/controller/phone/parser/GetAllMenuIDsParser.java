package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import controller.phone.entity.GetAllMenuIDsRequest;
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
public class GetAllMenuIDsParser implements MobileParser {
    public GetAllMenuIDsRequest parse(HttpServletRequest request) throws ParseRequestException {
        GetAllMenuIDsRequest getAllMenuIDsRequest = new GetAllMenuIDsRequest();
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
        getAllMenuIDsRequest.setUserID(userID);
        return getAllMenuIDsRequest;
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
