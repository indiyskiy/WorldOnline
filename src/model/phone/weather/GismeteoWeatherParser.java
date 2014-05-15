package model.phone.weather;

import helper.FileHelper;
import helper.WebPageGetter;
import model.constants.Component;
import model.constants.databaseenumeration.Cloudiness;
import model.constants.databaseenumeration.WeatherDayTime;
import model.database.requests.WeatherRequest;
import model.database.worldonlinedb.WeatherEntity;
import model.logger.LoggerFactory;
import model.scheduler.WeatherTask;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.IOException;

/**
 * Created by Илья on 13.05.14.
 */
public class GismeteoWeatherParser {
    private final String url = "http://xml.meteoservice.ru/export/gismeteo/point/";
    private final String spb = "69";
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Global, GismeteoWeatherParser.class);

    public File getGismeteoWeatherToFile() {
        try {
            String textFromPage = WebPageGetter.getTextFromPage(url + spb + ".xml");
            File file = FileHelper.saveToFile(textFromPage, "D:", "weather.xml");
            return file;
        } catch (IOException e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public Mmweather getWeatherFromFile(File file) {
        Persister serializer = new Persister();
        try {
            return serializer.read(Mmweather.class, file, false);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public Mmweather saveGismeteoWeather() {
        File file = getGismeteoWeatherToFile();
        Mmweather mmweather = getWeatherFromFile(file);
        saveToDB(mmweather);
        return mmweather;
    }

    private void saveToDB(Mmweather mmweather) {
        for (Forecast forecast : mmweather.report.town.forecasts) {
            WeatherEntity weatherEntity = new WeatherEntity(forecast);
            WeatherRequest.addWeather(weatherEntity);
        }
    }

    public static void main(String[] args) {
        GismeteoWeatherParser gismeteoWeatherParser = new GismeteoWeatherParser();
        Mmweather mmweather = gismeteoWeatherParser.saveGismeteoWeather();
        for (Forecast forecasts : mmweather.report.town.forecasts) {
            loggerFactory.info(WeatherDayTime.parseInt(forecasts.tod) + " - " + forecasts.hour + " - " + Cloudiness.parseInt(forecasts.phenomena.cloudiness));
        }
    }

}