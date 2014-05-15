package model.xmlparser.xmlview.card.cardroute;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 20.10.13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class CardRoute {
    @ElementList(inline = true, name = "Route", required = false)
    public List<Route> routes = new ArrayList<>();
}
