package model.scheduler;

import model.weather.GismeteoWeatherParser;

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
