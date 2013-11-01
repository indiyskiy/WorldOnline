package model.xmlparser.xmlview.route.routeroute;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@Root(name = "Route")
public class Route {
    @Attribute(name = "id", required = true)
    public String id;

    @Element(name = "ParentMenuID", required = false)
    public String parentMenuID;
    @Element(name = "Cards", required = false)
    public String cards;
    @Element(name = "DescrEn", required = false)
    public String descrEn;
    @Element(name = "DescrRu", required = false)
    public String descrRu;
    @Element(name = "NameEn", required = false)
    public String nameEn;
    @Element(name = "NameRu", required = false)
    public String nameRu;
    @Element(name = "Order", required = false)
    public String Order;
    @Element(name = "GeoPoints", required = false)
    public String geoPoints;
}
