package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.phone.entity.CardActivityRegistrationRequest;
import controller.phone.entity.MobileRequest;
import helper.ServletHelper;
import model.additionalentity.phone.MobileCardActivity;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

public class CardActivityRegistrationParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        CardActivityRegistrationRequest cardActivityRegistrationRequest = new CardActivityRegistrationRequest();
        String stringUserID = request.getParameter("userID");
        if (stringUserID == null || stringUserID.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.cardActivityRegistrationUserIDEmptyException);
        }
        long userID;
        try {
            userID = Long.parseLong(stringUserID);
        } catch (NumberFormatException nfe) {
            throw new ParseRequestException(ExceptionTexts.cardActivityRegistrationUserIDIncorrectException);
        }
        UserEntity userEntity = UserRequests.getUserByID(userID);
        if (userEntity == null) {
            throw new ParseRequestException(ExceptionTexts.cardActivityRegistrationUserNotExistException);
        }
        cardActivityRegistrationRequest.setUserID(userID);
        String body = ServletHelper.getBody(request);
        JsonObject mainObject = new JsonParser().parse(body).getAsJsonObject();
        JsonArray jsonArray = mainObject.getAsJsonArray("cardActivities");
        for (JsonElement jsonCardActivityElement : jsonArray) {
            JsonObject jsonCardActivity = jsonCardActivityElement.getAsJsonObject();
            String stringCardID = jsonCardActivity.get("cardID").toString();
            String stringOnTimestamp = jsonCardActivity.get("onTimestamp").toString();
            String stringOffTimestamp = jsonCardActivity.get("offTimestamp").toString();
            stringOnTimestamp = stringOnTimestamp.substring(1, stringOnTimestamp.length() - 1);
            stringOffTimestamp = stringOffTimestamp.substring(1, stringOffTimestamp.length() - 1);
            long cardID;
            try {
                cardID = Long.parseLong(stringCardID);
            } catch (NumberFormatException nfe) {
                throw new ParseRequestException(ExceptionTexts.cardActivityRegistrationCardIDIncorrectException);
            }
            Timestamp onTimestamp;
            Timestamp offTimestamp;
            try {
                onTimestamp = Timestamp.valueOf(stringOnTimestamp);
                offTimestamp = Timestamp.valueOf(stringOffTimestamp);
            } catch (IllegalArgumentException iae) {
                throw new ParseRequestException(ExceptionTexts.cardActivityRegistrationTimestampIncorrectException);
            }
            MobileCardActivity mobileCardActivity = new MobileCardActivity(cardID, onTimestamp, offTimestamp);
            cardActivityRegistrationRequest.getMobileCardActivities().add(mobileCardActivity);
        }
        return cardActivityRegistrationRequest;
    }

}
