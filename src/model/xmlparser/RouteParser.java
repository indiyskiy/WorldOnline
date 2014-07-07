package model.xmlparser;

import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.TextType;
import model.database.requests.CardRequest;
import model.database.requests.RouteRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardRouteEntity;
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
            CardEntity card = new CardEntity(CardType.CardRoute, route.nameEn, CardState.Active);
            if (route.order != null && !route.order.isEmpty()) {
                card.setNumberInList(Integer.parseInt(route.order));
            }
            CardRequest.addCardSafe(card);
            globalXmlParser.getCardEntityHashMap().put(route.id, card);
            String[] cards = route.cards.split(",");
            CardRouteEntity cardRouteEntity = new CardRouteEntity(route.nameEn, card);
            RouteRequest.addCardRoute(cardRouteEntity);
            int i = 0;
            for (String cardIDString : cards) {
                try {
                    Integer cardID = Integer.parseInt(cardIDString);
                    if (cardID != 0) {
                        RouteElementEntity routeElementEntity = new RouteElementEntity();
                        routeElementEntity.setCardRoute(cardRouteEntity);
                        routeElementEntity.setPlaceCard(globalXmlParser.getCardEntityHashMap().get(cardID));
                        routeElementEntity.setRouteElementNumber(i);
                        RouteRequest.addRouteElement(routeElementEntity);
                        i++;
                    }
                } catch (Exception e) {
                    loggerFactory.error(e);
                }

            }
//                public String cards;
            globalXmlParser.saveText(route.descrRu, route.descrEn, route.nameRu, route.nameEn, TextType.Description, card);
            globalXmlParser.saveText(route.nameRu, route.nameEn, route.nameRu, route.nameEn, TextType.Description, card);
//                public String geoPoints;
        }

    }
}
