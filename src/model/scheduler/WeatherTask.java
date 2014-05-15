package model.scheduler;

import model.phone.weather.GismeteoWeatherParser;

/**
 * Created by Илья on 14.05.14.
 */
public class WeatherTask extends ExecutableTask {
    public WeatherTask() {
        super(60 * 12);
    }

    @Override
    public void execute() {
        GismeteoWeatherParser gismeteoWeatherParser = new GismeteoWeatherParser();
        gismeteoWeatherParser.saveGismeteoWeather();
    }
}
