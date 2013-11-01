package model.xmlparser.xmlview.people.peopleaboutcity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class PeopleAboutCity {
    @ElementList(inline = true, name = "Aboutcity")
    public List<AboutCity> aboutCities;
}
