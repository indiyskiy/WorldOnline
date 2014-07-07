package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.AllCardParameterTypesRequest;
import model.additionalentity.phone.MobileParameterType;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.AllCardParameterTypesResponse;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class AllCardParameterTypesParser implements MobileParser {
    public AllCardParameterTypesRequest parse(HttpServletRequest request) throws ParseRequestException {
        AllCardParameterTypesRequest allCardParameterTypesRequest = new AllCardParameterTypesRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getAllCardParameterTypesUserNotExistException);
        }
        allCardParameterTypesRequest.setUserID(userID);
        return allCardParameterTypesRequest;
    }

    public static String getResponse(AllCardParameterTypesResponse allCardParameterTypesResponse) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", allCardParameterTypesResponse.getStatus().getValue());
        JsonArray cardParameterTypesArray = new JsonArray();
        for (MobileParameterType mobileParameterType : allCardParameterTypesResponse.getMobileParameterTypes()) {
            JsonObject mobileParameterObj = new JsonObject();
            mobileParameterObj.addProperty("cardParameterTypeID", mobileParameterType.getCardParameterTypeID());
            mobileParameterObj.addProperty("iconID", mobileParameterType.getIconID());
            mobileParameterObj.addProperty("name", mobileParameterType.getName());
            mobileParameterObj.addProperty("position", mobileParameterType.getPosition());
            mobileParameterObj.addProperty("block", mobileParameterType.getBlock().getValue());
            mobileParameterObj.addProperty("dataType", mobileParameterType.getDataType().getValue());
            cardParameterTypesArray.add(mobileParameterObj);
        }
        responseObj.add("cardParameterTypes", cardParameterTypesArray);
        return responseObj.toString();
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != AllCardParameterTypesResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, AllCardParameterTypesResponse.class);
        }
        AllCardParameterTypesResponse allCardParameterTypesResponse = (AllCardParameterTypesResponse) mobileResponseEntity;
        return getResponse(allCardParameterTypesResponse);
    }
}
