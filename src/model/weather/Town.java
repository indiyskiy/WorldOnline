package model.weather;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "TOWN")
public class Town {
    @ElementList(inline = true, name = "FORECAST")
    public List<Forecast> forecasts = new ArrayList<>();
}
