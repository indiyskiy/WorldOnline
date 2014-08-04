package model.xmlparser.xmlview.people.peopleaboutcity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class PeopleAboutCity {
    @ElementList(inline = true, name = "Aboutcity")
    public List<AboutCity> aboutCities;
}
