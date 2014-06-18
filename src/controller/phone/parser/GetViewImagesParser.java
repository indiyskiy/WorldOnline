package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.GetViewImagesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.GetViewImagesResponse;
import model.phone.responseentity.MobileViewImage;
import model.phone.responseentity.MobileResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 21.05.14.
 */
public class GetViewImagesParser implements MobileParser {

    @Override
    public GetViewImagesRequest parse(HttpServletRequest request) throws ParseRequestException {
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.getViewImagesUserNotExistException);
        }
        GetViewImagesRequest getViewImagesRequest = new GetViewImagesRequest();
        getViewImagesRequest.setUserID(userID);
        return getViewImagesRequest;
    }

    public String getResponse(GetViewImagesResponse getViewImagesResponse) {
        JsonArray jsonArray = new JsonArray();
        for (MobileViewImage mobileViewImage : getViewImagesResponse.getMobileViewImages()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("imageID", mobileViewImage.getImageID());
            jsonObject.addProperty("cardID", mobileViewImage.getCardID());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }


    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != GetViewImagesResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, GetViewImagesResponse.class);
        }
        GetViewImagesResponse getViewImagesResponse = (GetViewImagesResponse) mobileResponseEntity;
        return getResponse(getViewImagesResponse);
    }
}
