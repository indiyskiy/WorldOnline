package model.scheduler;

import model.weather.GismeteoWeatherParser;

public class WeatherTask extends ExecutableTask {
    public WeatherTask() {
        super(6);
    }

    @Override
    public void execute() {
        GismeteoWeatherParser gismeteoWeatherParser = new GismeteoWeatherParser();
        gismeteoWeatherParser.saveGismeteoWeather();
    }
}
