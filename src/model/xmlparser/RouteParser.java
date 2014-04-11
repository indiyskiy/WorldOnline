package model.xmlparser;

import model.constants.Component;
import model.logger.LoggerFactory;
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
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, RouteParser.class);

    public RouteRoute getRouteRoute(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(RouteRoute.class, reader, false);
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }


}
