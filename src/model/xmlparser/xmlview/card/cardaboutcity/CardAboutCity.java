package model.xmlparser.xmlview.card.cardaboutcity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 18.10.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardAboutCity {
    @ElementList(inline = true, name = "Aboutcity")
    public List<AboutCity> aboutCities;
}
