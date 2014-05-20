package controller.phone.parser;

import com.google.gson.JsonObject;
import controller.phone.entity.WeatherRequest;
import model.constants.ExceptionTexts;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.WeatherResponse;

import javax.servlet.http.HttpServletRequest;


public class WeatherParser implements MobileParser {
    @Override
    public WeatherRequest parse(HttpServletRequest request) throws ParseRequestException {
        WeatherRequest weatherRequest = new WeatherRequest();
        String userIdString = request.getParameter("userID");
        if (userIdString == null || userIdString.isEmpty()) {
            throw new ParseRequestException(ExceptionTexts.weatherUserIDEmptyException);
        }
        Long userID;
        try {
            userID = Long.parseLong(userIdString);
        } catch (Exception e) {
            throw new ParseRequestException(ExceptionTexts.weatherUserIDIncorrectException);
        }
        if (!UserRequests.isUserExist(userID)) {
            throw new ParseRequestException(ExceptionTexts.weatherUserNotExistException);
        }
        weatherRequest.setUserID(userID);
        return weatherRequest;
    }

    @Override
    public String getResponse(MobileResponseEntity mobileResponseEntity) throws IllegalTypeException {
        if (mobileResponseEntity.getClass() != WeatherResponse.class) {
            throw new IllegalTypeException(MobileResponseEntity.class, WeatherResponse.class);
        }
        WeatherResponse weatherResponse = (WeatherResponse) mobileResponseEntity;
        return getResponse(weatherResponse);
    }

    public String getResponse(WeatherResponse weatherResponse) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("temperature", weatherResponse.getTemperature());
        jsonObject.addProperty("cloudiness", weatherResponse.getCloudiness().toString());
        jsonObject.addProperty("precipitation", weatherResponse.getPrecipitation().toString());
        jsonObject.addProperty("dayTime", weatherResponse.getDayTime().toString());
        return jsonObject.toString();
    }
}
