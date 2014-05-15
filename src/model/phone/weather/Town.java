package model.phone.weather;

import model.xmlparser.xmlview.card.cardaboutcity.AboutCity;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Илья on 13.05.14.
 */
@Root(name = "TOWN")
public class Town {
    @ElementList(inline = true, name = "FORECAST")
    public List<Forecast> forecasts = new ArrayList<>();
}
