package model.xmlparser;

import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.TextType;
import model.database.requests.CardRequest;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardRouteEntity;
import model.database.worldonlinedb.RouteCoordinateEntity;
import model.database.worldonlinedb.RouteElementEntity;
import model.logger.LoggerFactory;
import model.xmlparser.xmlview.route.routeroute.Route;
import model.xmlparser.xmlview.route.routeroute.RouteRoute;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;

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


    public void saveRoute(GlobalXmlParser globalXmlParser, RouteRoute routeRoute) {
        for (Route route : routeRoute.routes) {
            loggerFactory.debug("parse route " + route.nameRu);
            try {
                CardEntity card = new CardEntity(CardType.CardRoute, route.nameRu, CardState.Active);
                if (route.order != null && !route.order.isEmpty()) {
                    loggerFactory.debug("1");
                    card.setNumberInList(Integer.parseInt(route.order));
                }
                loggerFactory.debug("2");
                CardRequest.addCardSafe(card);
                globalXmlParser.getCardEntityHashMap().put(Long.parseLong(route.id), card);
                String[] cards = route.cards.split(",");
                CardRouteEntity cardRouteEntity = new CardRouteEntity(card);
                RouteRequest.addCardRoute(cardRouteEntity);
                int i = 0;
                loggerFactory.debug("3");
                for (String cardIDString : cards) {
                    loggerFactory.debug("4 " + cardIDString);
                    try {
                        Integer cardID = Integer.parseInt(cardIDString);
                        if (cardID != 0) {
                            RouteElementEntity routeElementEntity = new RouteElementEntity();
                            routeElementEntity.setCardRoute(cardRouteEntity);
                            routeElementEntity.setPlaceCard(globalXmlParser.getCardEntityHashMap().get(Long.valueOf(cardID)));
                            routeElementEntity.setRouteElementNumber(i);
                            RouteRequest.addRouteElement(routeElementEntity);
                            i++;
                        }
                    } catch (Exception e) {
                        loggerFactory.error(e);
                    }
                    loggerFactory.debug("/4 " + cardIDString);
                }
                loggerFactory.debug("5");
                GlobalXmlParser.saveText(route.descrRu, route.descrEn, route.nameRu, route.nameEn, TextType.Description, card);
                GlobalXmlParser.saveText(route.nameRu, route.nameEn, route.nameRu, route.nameEn, TextType.Name, card);
                loggerFactory.debug("6");
                if (route.geoPoints != null && !route.geoPoints.isEmpty()) {
                    loggerFactory.debug("7");
                    String[] geoPoints = route.geoPoints.split(";");
                    for (int k = 0; k < geoPoints.length; k++) {
                        loggerFactory.debug("8 " + k);
                        String point = geoPoints[k];
                        loggerFactory.debug("8 " + point);
                        if (point != null && !point.isEmpty()) {
                            loggerFactory.debug("9");
                            String[] coordinatePair = point.split(",");
                            if (coordinatePair.length == 2) {
                                Double latitude = Double.parseDouble(coordinatePair[0]);
                                Double longitude = Double.parseDouble(coordinatePair[1]);
                                RouteCoordinateEntity routeCoordinateEntity = new RouteCoordinateEntity();
                                routeCoordinateEntity.setCardRoute(cardRouteEntity);
                                routeCoordinateEntity.setLatitude(latitude);
                                routeCoordinateEntity.setLongitude(longitude);
                                routeCoordinateEntity.setPosition(k);
                                RouteRequest.addRouteCoordinate(routeCoordinateEntity);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                loggerFactory.error("error on card " + route.nameRu);
                loggerFactory.error(e);
            }
        }
    }
}
