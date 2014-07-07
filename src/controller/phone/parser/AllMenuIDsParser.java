package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import controller.phone.entity.AllMenuIDsRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllMenuIDsResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class AllMenuIDsParser implements MobileParser {
    public AllMenuIDsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllMenuIDsRequest allMenuIDsRequest = new AllMenuIDsRequest();
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
        allMenuIDsRequest.setUserID(userID);
        return allMenuIDsRequest;
    }

    public static String getResponse(AllMenuIDsResponse allMenuIDsResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", allMenuIDsResponse.getStatus().getValue());
        JsonArray jsonArray = new JsonArray();
        for (Long id : allMenuIDsResponse.getMenuIDs()) {
            jsonArray.add(new JsonPrimitive(id));
        }
        responseObj.add("menuIDs", jsonArray);
        return responseObj.toString();
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllMenuIDsResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllMenuIDsResponse.class);
        }
        AllMenuIDsResponse allMenuIDsResponse = (AllMenuIDsResponse) mobileResponseEntity;
        return getResponse(allMenuIDsResponse);
    }
}
