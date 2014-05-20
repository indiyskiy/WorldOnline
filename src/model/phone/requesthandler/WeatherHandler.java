package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.WeatherRequest;
import helper.TimeManager;
import model.constants.databaseenumeration.Cloudiness;
import model.constants.databaseenumeration.Precipitation;
import model.database.worldonlinedb.WeatherEntity;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.WeatherResponse;

public class WeatherHandler implements MobileHandler {

    public WeatherResponse handleRequest(WeatherRequest weatherRequest) {
        WeatherResponse weatherResponse = new WeatherResponse();
        WeatherEntity weatherEntity = model.database.requests.WeatherRequest.getCurrentWeather();
        weatherResponse.setCloudiness(Cloudiness.parseInt(weatherEntity.getCloudiness()));
        weatherResponse.setPrecipitation(Precipitation.parseInt(weatherEntity.getPrecipitation()));
        weatherResponse.setTemperature((weatherEntity.getMaxTemperature() + weatherEntity.getMinTemperature()) / 2);
        weatherResponse.setDayTime((TimeManager.currentDayTime()));
        return weatherResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != WeatherRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, WeatherRequest.class);
        }
        WeatherRequest weatherRequest = (WeatherRequest) mobileRequest;
        return handleRequest(weatherRequest);
    }
}

