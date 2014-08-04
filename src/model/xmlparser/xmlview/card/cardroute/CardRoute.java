package model.xmlparser.xmlview.card.cardroute;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "data")
public class CardRoute {
    @ElementList(inline = true, name = "Route", required = false)
    public List<Route> routes = new ArrayList<>();
}
