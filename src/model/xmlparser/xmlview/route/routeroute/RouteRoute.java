package model.xmlparser.xmlview.route.routeroute;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "data")
public class RouteRoute {
    @ElementList(inline = true,name = "Route")
    List<Route> routes;
}