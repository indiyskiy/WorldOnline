package controller.phone.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.phone.entity.CardEventRegistrationRequest;
import controller.phone.entity.MobileRequest;
import helper.ServletHelper;
import model.additionalentity.phone.MobileCardEvent;
import model.constants.ExceptionTexts;
import model.constants.databaseenumeration.CardEventType;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

public class CardEventRegistrationParser extends MobileParser {

    @Override
    public MobileRequest parse(HttpServletRequest request) throws ParseRequestException, IOException {
        CardEventRegistrationRequest cardEventRegistrationRequest = new CardEventRegistrationRequest();
        String stringUserID = request.getParameter("userID");
        if (stringUserID == null || stringUserID.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.cardEventRegistrationUserIDEmptyException);
        }
        long userID;
        try {
            userID = Long.parseLong(stringUserID);
        } catch (NumberFormatException nfe) {
            throw new ParseRequestException(ExceptionTexts.cardEventRegistrationUserIDIncorrectException);
        }
        UserEntity userEntity = UserRequests.getUserByID(userID);
        if (userEntity == null) {
            throw new ParseRequestException(ExceptionTexts.cardEventRegistrationUserNotExistException);
        }
        cardEventRegistrationRequest.setUserID(userID);
        String body = ServletHelper.getBody(request);
        JsonObject mainObject = new JsonParser().parse(body).getAsJsonObject();
        JsonArray jsonArray = mainObject.getAsJsonArray("cardEvents");
        for (JsonElement jsonCardActivityElement : jsonArray) {
            JsonObject jsonCardActivity = jsonCardActivityElement.getAsJsonObject();
            String stringCardID = jsonCardActivity.get("cardID").toString();
            String stringEventType = jsonCardActivity.get("eventType").toString();
            String stringEventTimestamp = jsonCardActivity.get("eventTimestamp").toString();
            stringEventTimestamp = stringEventTimestamp.substring(1, stringEventTimestamp.length() - 1);
            long cardID;
            try {
                cardID = Long.parseLong(stringCardID);
            } catch (NumberFormatException nfe) {
                throw new ParseRequestException(ExceptionTexts.cardEventRegistrationCardIDIncorrectException);
            }
            CardEventType eventType;
            try {
                int intEventType = Integer.parseInt(stringEventType);
                eventType = CardEventType.parseInt(intEventType);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new ParseRequestException(ExceptionTexts.cardEventRegistrationEventTypeIncorrectException);
            }
            Timestamp eventTimestamp;
            try {
                eventTimestamp = Timestamp.valueOf(stringEventTimestamp);
            } catch (IllegalArgumentException iae) {
                throw new ParseRequestException(ExceptionTexts.cardEventRegistrationTimestampIncorrectException);
            }
            MobileCardEvent mobileCardEvent = new MobileCardEvent(cardID, eventType, eventTimestamp);
            if (jsonCardActivity.has("text")) {
                String text = jsonCardActivity.get("text").toString();
                text = text.substring(1, text.length() - 1);
                mobileCardEvent.setText(text);
            }
            //add text to mobilecardevent
            cardEventRegistrationRequest.getMobileCardEvents().add(mobileCardEvent);
        }
        return cardEventRegistrationRequest;
    }

}
