package model.xmlparser.xmlview.card.cardaboutcity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class CardAboutCity {
    @ElementList(inline = true, name = "Aboutcity")
    public List<AboutCity> aboutCities;
}
