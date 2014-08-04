package model.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "FORECAST")
public class Forecast {
    @Attribute(name = "day")
    public int day;
    @Attribute(name = "month")
    public int month;
    @Attribute(name = "year")
    public int year;
    @Attribute(name = "hour")
    public int hour;
    @Attribute(name = "tod")
    public int tod;
    @Attribute(name = "predict")
    public int predict;
    @Attribute(name = "weekday")
    public int weekday;

    @Element(name = "PHENOMENA")
    public Phenomena phenomena;
    @Element(name = "PRESSURE")
    public Pressure pressure;
    @Element(name = "TEMPERATURE")
    public Temperature temperature;
    @Element(name = "WIND")
    public Wind wind;
    @Element(name = "RELWET")
    public Relwet relwet;
    @Element(name = "HEAT")
    public Heat heat;
}
