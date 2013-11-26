package model.xmlparser;

import model.xmlparser.xmlview.route.routeroute.RouteRoute;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public class RouteParser {
    public RouteRoute getRouteRoute(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(RouteRoute.class, reader, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
