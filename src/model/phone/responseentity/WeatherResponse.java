package model.phone.responseentity;

import com.google.gson.JsonObject;
import model.constants.Status;
import model.constants.databaseenumeration.Cloudiness;
import model.constants.databaseenumeration.DayTime;
import model.constants.databaseenumeration.Precipitation;

public class WeatherResponse extends MobileResponseEntity {
    private int temperature;
    private Cloudiness cloudiness;
    private Precipitation precipitation;
    private DayTime dayTime;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Cloudiness getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Cloudiness cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    public WeatherResponse() {
        super(Status.OK);
    }

    @Override
    protected JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("temperature", getTemperature());
        jsonObject.addProperty("cloudiness", getCloudiness().getValue());
        jsonObject.addProperty("precipitation", getPrecipitation().getValue());
        jsonObject.addProperty("dayTime", getDayTime().getValue());
        return jsonObject;
    }
}
