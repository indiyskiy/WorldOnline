package model.xmlparser.xmlview.route.routeroute;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "data")
public class RouteRoute {
    @ElementList(inline = true, name = "Route")
    public List<Route> routes;
}