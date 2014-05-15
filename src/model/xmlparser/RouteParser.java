package model.xmlparser;

import model.constants.Component;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RouteParser {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Parser, RouteParser.class);

    public RouteRoute getRouteRoute(String root) {
        try {
            FileInputStream reader = new FileInputStream(root);
            Persister serializer = new Persister();
            return serializer.read(RouteRoute.class, reader, false);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }


}
