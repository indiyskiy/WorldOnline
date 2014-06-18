package model.database.worldonlinedb;

import helper.TimeManager;
import model.weather.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by Илья on 14.05.14.
 */
@javax.persistence.Table(name = "Weather", schema = "", catalog = "worldonline")
@Entity
public class WeatherEntity {
    @javax.persistence.Column(name = "WeatherID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long weatherID;
    @javax.persistence.Column(name = "WeatherTimestamp")
    @Basic
    private Timestamp weatherTimestamp;
    @javax.persistence.Column(name = "Cloudiness")
    @Basic
    private int cloudiness;
    @javax.persistence.Column(name = "Precipitation")
    @Basic
    private int precipitation;
    @javax.persistence.Column(name = "RainPower")
    @Basic
    private int rainPower;
    @javax.persistence.Column(name = "LightningPower")
    @Basic
    private int lightningPower;
    @javax.persistence.Column(name = "MaxTemperature")
    @Basic
    private int maxTemperature;
    @javax.persistence.Column(name = "MinTemperature")
    @Basic
    private int minTemperature;
    @javax.persistence.Column(name = "HeatMin")
    @Basic
    private int heatMin;
    @javax.persistence.Column(name = "HeatMax")
    @Basic
    private int heatMax;
    @javax.persistence.Column(name = "PressureMin")
    @Basic
    private int pressureMin;
    @javax.persistence.Column(name = "PressureMax")
    @Basic
    private int pressureMax;
    @javax.persistence.Column(name = "RelwetMax")
    @Basic
    private int relwetMax;
    @javax.persistence.Column(name = "RelwetMin")
    @Basic
    private int relwetMin;
    @javax.persistence.Column(name = "WindMax")
    @Basic
    private int windMax;
    @javax.persistence.Column(name = "WindMin")
    @Basic
    private int windMin;
    @javax.persistence.Column(name = "WindDirection")
    @Basic
    private int windDirection;

    public WeatherEntity() {

    }

    public WeatherEntity(Forecast forecast) {
        setTime(forecast);
        setPhenomena(forecast.phenomena);
        setTemperature(forecast.temperature);
        setHeat(forecast.heat);
        setPressure(forecast.pressure);
        setRelwet(forecast.relwet);
        setWind(forecast.wind);
    }

    public Long getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(Long weatherID) {
        this.weatherID = weatherID;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public int getRainPower() {
        return rainPower;
    }

    public void setRainPower(int rainPower) {
        this.rainPower = rainPower;
    }

    public int getLightningPower() {
        return lightningPower;
    }

    public void setLightningPower(int lightningPower) {
        this.lightningPower = lightningPower;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getHeatMin() {
        return heatMin;
    }

    public void setHeatMin(int heatMin) {
        this.heatMin = heatMin;
    }

    public int getHeatMax() {
        return heatMax;
    }

    public void setHeatMax(int heatMax) {
        this.heatMax = heatMax;
    }

    public int getPressureMin() {
        return pressureMin;
    }

    public void setPressureMin(int pressureMin) {
        this.pressureMin = pressureMin;
    }

    public int getPressureMax() {
        return pressureMax;
    }

    public void setPressureMax(int pressureMax) {
        this.pressureMax = pressureMax;
    }

    public int getRelwetMax() {
        return relwetMax;
    }

    public void setRelwetMax(int relwetMax) {
        this.relwetMax = relwetMax;
    }

    public int getRelwetMin() {
        return relwetMin;
    }

    public void setRelwetMin(int relwetMin) {
        this.relwetMin = relwetMin;
    }

    public int getWindMax() {
        return windMax;
    }

    public void setWindMax(int windMax) {
        this.windMax = windMax;
    }

    public int getWindMin() {
        return windMin;
    }

    public void setWindMin(int windMin) {
        this.windMin = windMin;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public Timestamp getWeatherTimestamp() {
        return weatherTimestamp;
    }

    public void setWeatherTimestamp(Timestamp weatherTimestamp) {
        this.weatherTimestamp = weatherTimestamp;
    }

    private void setTime(Forecast forecast) {
        try {
            WeatherTime weatherTime = new WeatherTime(forecast.year, forecast.month, forecast.day, forecast.hour);
            weatherTimestamp = TimeManager.getTimestampFromWeatherTime(weatherTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setPhenomena(Phenomena phenomena) {
        this.cloudiness = phenomena.cloudiness;
        this.lightningPower = phenomena.lightningPower;
        this.precipitation = phenomena.precipitation;
        this.rainPower = phenomena.rainPower;
    }

    private void setTemperature(Temperature temperature) {
        this.maxTemperature = temperature.max;
        this.minTemperature = temperature.min;
    }

    private void setHeat(Heat heat) {
        this.heatMax = heat.max;
        this.heatMin = heat.min;
    }

    private void setWind(Wind wind) {
        this.windMax = wind.max;
        this.windMin = wind.min;
        this.windDirection = wind.direction;
    }

    private void setRelwet(Relwet relwet) {
        this.relwetMax = relwet.max;
        this.relwetMin = relwet.min;
    }

    private void setPressure(Pressure pressure) {
        this.pressureMin = pressure.min;
        this.pressureMax = pressure.max;
    }

}
