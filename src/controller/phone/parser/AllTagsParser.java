package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.AllTagsRequest;
import model.additionalentity.phone.MobileTagGroup;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllTagsResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AllTagsParser implements MobileParser {
    @Override
    public AllTagsRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllTagsRequest allTagsRequest = new AllTagsRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.allTagsUserNotExistException);
        }
        allTagsRequest.setUserID(userID);
        return allTagsRequest;
    }

    public String getResponse(AllTagsResponse allTagsResponse) {
        ArrayList<MobileTagGroup> mobileTagGroups = allTagsResponse.getMobileTagGroups();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", allTagsResponse.getStatus().toString());
        JsonArray jsonArray = new JsonArray();
        for (MobileTagGroup mobileTagGroup : mobileTagGroups) {
            jsonArray.add(mobileTagGroup.toJSON());
        }
        jsonObject.add("tagGroups", jsonArray);
        return jsonObject.toString();
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllTagsResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllTagsResponse.class);
        }
        AllTagsResponse allTagsResponse = (AllTagsResponse) mobileResponseEntity;
        return getResponse(allTagsResponse);
    }
}
