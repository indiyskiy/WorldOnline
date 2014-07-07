package model.database.requests;

import model.additionalentity.CompleteCardRouteInfo;
import model.additionalentity.CompleteTextGroupInfo;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RouteRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, RouteRequest.class);

    public static CardRouteEntity getCardRouteByResultSet(ResultSet rs) throws SQLException {
        Long cardRouteID = rs.getLong("CardRoute.CardRouteID");
        if (cardRouteID == 0 || rs.wasNull()) {
            return null;
        }
        CardRouteEntity cardRouteEntity = new CardRouteEntity();
        cardRouteEntity.setCardRouteID(cardRouteID);
        cardRouteEntity.setCardRouteName(rs.getString("CardRoute.CardRouteName"));
        return cardRouteEntity;
    }

    public static RouteElementEntity getRouteElementByResultSet(ResultSet rs) throws SQLException {
        Long routeElementID = rs.getLong("RouteElement.RouteElementID");
        if (routeElementID == 0 || rs.wasNull()) {
            return null;
        }
        RouteElementEntity routeElementEntity = new RouteElementEntity();
        routeElementEntity.setRouteElementID(routeElementID);
        routeElementEntity.setRouteElementNumber(rs.getInt("RouteElement.RouteElementNumber"));
        return routeElementEntity;
    }

    public static void addCardRoute(CardRouteEntity cardRouteEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardRouteEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addRouteElement(RouteElementEntity routeElementEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(routeElementEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardCoordinateEntity getCardCoordinateByResultSet(ResultSet rs) throws SQLException {
        Long cardCoordinateID = rs.getLong("CardCoordinate.CardCoordinateID");
        if (cardCoordinateID == 0 || rs.wasNull()) {
            return null;
        }
        CardCoordinateEntity cardCoordinateEntity = new CardCoordinateEntity();
        cardCoordinateEntity.setCardCoordinateID(cardCoordinateID);
        cardCoordinateEntity.setLatitude(rs.getDouble("CardCoordinate.Latitude"));
        cardCoordinateEntity.setLongitude(rs.getDouble("CardCoordinate.Longitude"));
        return cardCoordinateEntity;
    }

    public static void addCardCoordinate(CardCoordinateEntity cardCoordinateEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardCoordinateEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static HashMap<Long, CompleteCardRouteInfo> getCompleteCardRoutes() {
        HashMap<Long, CompleteCardRouteInfo> cardRoutes = new HashMap<Long, CompleteCardRouteInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoute " +
                    "LEFT OUTER JOIN RouteElement ON (RouteElement.CardRouteID=CardRoute.CardRouteID) " +
                    "LEFT OUTER JOIN Card AS RouteCard ON (RouteCard.CardID=RouteElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoute.RouteDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID)";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardRouteInfo cardRoute;
                Long cardRouteID = rs.getLong("CardImage.CardImageID");
                if (cardRouteID != 0 && !rs.wasNull()) {
                    if (cardRoutes.containsKey(cardRouteID) && cardRoutes.get(cardRouteID) != null) {
                        cardRoute = cardRoutes.get(cardRouteID);
                    } else {
                        cardRoute = new CompleteCardRouteInfo(getCardRouteByResultSet(rs));
                        cardRoutes.put(cardRouteID, cardRoute);
                    }
                    getCompleteCardRoute(rs, cardRoute, "RouteCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoutes;
    }

    public static HashMap<Long, CompleteCardRouteInfo> getCompleteCardRoutesByCard(long cardID) {
        HashMap<Long, CompleteCardRouteInfo> cardRoutes = new HashMap<Long, CompleteCardRouteInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoute " +
                    "LEFT OUTER JOIN RouteElement ON (RouteElement.CardRouteID=CardRoute.CardRouteID) " +
                    "LEFT OUTER JOIN Card AS RouteCard ON (RouteCard.CardID=RouteElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoute.RouteDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardRoute.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardRouteInfo cardRoute;
                Long cardRouteID = rs.getLong("CardImage.CardImageID");
                if (cardRouteID != 0 && !rs.wasNull()) {
                    if (cardRoutes.containsKey(cardRouteID) && cardRoutes.get(cardRouteID) != null) {
                        cardRoute = cardRoutes.get(cardRouteID);
                    } else {
                        cardRoute = new CompleteCardRouteInfo(getCardRouteByResultSet(rs));
                        cardRoutes.put(cardRouteID, cardRoute);
                    }
                    getCompleteCardRoute(rs, cardRoute, "RouteCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoutes;
    }

    public static CompleteCardRouteInfo getCompleteCardRouteByCardRouteID(long cardRouteID) throws SQLException {
        CompleteCardRouteInfo cardRoute = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoute " +
                    "LEFT OUTER JOIN RouteElement ON (RouteElement.CardRouteID=CardRoute.CardRouteID) " +
                    "LEFT OUTER JOIN Card AS RouteCard ON (RouteCard.CardID=RouteElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoute.RouteDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardRoute.CardRouteID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardRouteID);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long cardRouteID2 = rs.getLong("CardImage.CardImageID");
                if (cardRouteID2 != 0 && cardRouteID == cardRouteID2 && !rs.wasNull()) {
                    cardRoute = new CompleteCardRouteInfo(getCardRouteByResultSet(rs));
                    getCompleteCardRoute(rs, cardRoute, "RouteCard", "TextGroup", "Text");
                } else {
                    return null;
                }
                while (rs.next()) {
                    getCompleteCardRoute(rs, cardRoute, "RouteCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoute;
    }

    public static void getCompleteCardRoute(ResultSet rs, CompleteCardRouteInfo completeCardRouteInfo, String routeCard, String textGroup, String text) throws SQLException {
        //route element
        Long routeElementID = rs.getLong("RouteElement.RouteElementID");
        if (routeElementID != 0 && !rs.wasNull()) {
            RouteElementEntity routeElementEntity;
            if (completeCardRouteInfo.getRouteElementEntityMap().containsKey(routeElementID) &&
                    completeCardRouteInfo.getRouteElementEntityMap().get(routeElementID) != null) {
                routeElementEntity = completeCardRouteInfo.getRouteElementEntityMap().get(routeElementID);
            } else {
                routeElementEntity = RouteRequest.getRouteElementByResultSet(rs);
                routeElementEntity.setCardRoute(completeCardRouteInfo.getCardRouteEntity());
                completeCardRouteInfo.getRouteElementEntityMap().put(routeElementID, routeElementEntity);
            }
            if (routeElementEntity.getPlaceCard() == null) {
                //route element placeCard
                Long routeCardID = rs.getLong(routeCard + ".CardID");
                if (routeCardID != 0 && !rs.wasNull()) {
                    CardEntity cardEntity = CardRequest.getCardFromResultSet(rs, routeCard);
                    routeElementEntity.setPlaceCard(cardEntity);
                }
            }
        }
        //Route Text Group
        Long routeTextGroupID = rs.getLong(textGroup + ".TextGroupID");
        if (routeTextGroupID != 0 && !rs.wasNull()) {
            CompleteTextGroupInfo completeTextGroupInfo;
            if (completeCardRouteInfo.getCompleteTextGroupInfo() == null) {
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, textGroup);
                completeTextGroupInfo = new CompleteTextGroupInfo(textGroupEntity);
                completeCardRouteInfo.setCompleteTextGroupInfo(completeTextGroupInfo);
                completeCardRouteInfo.getCardRouteEntity().setRouteDescriptionTextGroup(textGroupEntity);
            } else {
                completeTextGroupInfo = completeCardRouteInfo.getCompleteTextGroupInfo();
            }
            TextRequest.getCompleteTextGroupInfo(rs, completeTextGroupInfo, text);
        }
    }
}
