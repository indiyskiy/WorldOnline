package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.phone.entity.ViewImagesRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.ViewImagesResponse;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.MobileViewImage;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 21.05.14.
 */
public class ViewImagesParser implements MobileParser {

    @Override
    public ViewImagesRequest parse(HttpServletRequest request) throws ParseRequestException {
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
        ViewImagesRequest viewImagesRequest = new ViewImagesRequest();
        viewImagesRequest.setUserID(userID);
        return viewImagesRequest;
    }

    public String getResponse(ViewImagesResponse viewImagesResponse) {
        JsonArray jsonArray = new JsonArray();
        for (MobileViewImage mobileViewImage : viewImagesResponse.getMobileViewImages()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("imageID", mobileViewImage.getImageID());
            jsonObject.addProperty("cardID", mobileViewImage.getCardID());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }


    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != ViewImagesResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, ViewImagesResponse.class);
        }
        ViewImagesResponse viewImagesResponse = (ViewImagesResponse) mobileResponseEntity;
        return getResponse(viewImagesResponse);
    }
}
