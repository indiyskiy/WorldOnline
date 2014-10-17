package model.database.requests;

import helper.StringHelper;
import model.additionalentity.CompleteCardRouteInfo;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileCardRoute;
import model.additionalentity.phone.MobileRouteCoordinate;
import model.additionalentity.phone.MobileRouteElement;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.*;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            session.flush();
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
            session.flush();
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

    public static void addRouteCoordinate(RouteCoordinateEntity routeCoordinateEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(routeCoordinateEntity);
            transaction.commit();
            session.flush();
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
                Long cardRouteID = rs.getLong("RouteElement.CardRouteID");
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
                Long cardRouteID = rs.getLong("CardRoute.CardRouteID");
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
            TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, textGroup);
            completeCardRouteInfo.getCardRouteEntity().setRouteDescriptionTextGroup(textGroupEntity);
        }
    }

    public static void setMobileRoute(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Long, MobileCardRoute> mobileCardRouteHashMap = new HashMap<>();
        ArrayList<Object> ids = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT CardRoute.CardRouteID," +
                    "RouteElement.RouteElementID, " +
                    "RouteElement.PlaceCardID, " +
                    "RouteElement.RouteElementID, " +
                    "RouteElement.RouteElementNumber, " +
                    "CardRoute.CardID " +
                    "FROM CardRoute " +
                    "LEFT OUTER JOIN RouteElement ON (RouteElement.CardRouteID=CardRoute.CardRouteID) " +
                    "WHERE CardRoute.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long mobileRouteID = rs.getLong("CardRoute.CardRouteID");
                MobileCardRoute mobileRoute;
                if (mobileCardRouteHashMap.containsKey(mobileRouteID)) {
                    mobileRoute = mobileCardRouteHashMap.get(mobileRouteID);
                } else {
                    mobileRoute = new MobileCardRoute();
                    //set stats
                    MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("CardRoute.CardID"));
                    if (mobileCardInfo != null) {
                        mobileCardInfo.getMobileCardRoutes().add(mobileRoute);
                        mobileCardRouteHashMap.put(mobileRouteID, mobileRoute);
                    }
                    ids.add(mobileRouteID);
                }
                MobileRouteElement mobileRouteElement = new MobileRouteElement();
                mobileRouteElement.setNumber(rs.getInt("RouteElement.RouteElementNumber"));
                mobileRouteElement.setRouteElementID(rs.getLong("RouteElement.RouteElementID"));
                mobileRouteElement.setPlaceCardID(rs.getLong("RouteElement.PlaceCardID"));
                mobileRoute.getMobileRouteElements().add(mobileRouteElement);
            }
            String idString = StringHelper.getStringFromArray(ids);
            addCoordinates(idString, mobileCardRouteHashMap);
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    private static void addCoordinates(String idString, HashMap<Long, MobileCardRoute> mobileCardRouteHashMap) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (idString == null || idString.isEmpty()) {
            return;
        }
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT CardRoute.CardRouteID, " +
                    "RouteCoordinate.Position, " +
                    "RouteCoordinate.Longitude, " +
                    "RouteCoordinate.Latitude, " +
                    "RouteCoordinate.RouteCoordinateID " +
                    "FROM CardRoute " +
                    "LEFT OUTER JOIN RouteCoordinate ON (RouteCoordinate.CardRouteID=CardRoute.CardRouteID) " +
                    "WHERE CardRoute.CardRouteID IN (" + idString + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long mobileCardRouteID = rs.getLong("CardRoute.CardRouteID");
                if (mobileCardRouteHashMap.containsKey(mobileCardRouteID)) {
                    MobileCardRoute mobileCardRoute = mobileCardRouteHashMap.get(mobileCardRouteID);
                    MobileRouteCoordinate mobileRouteCoordinate = new MobileRouteCoordinate();
                    mobileRouteCoordinate.setPosition(rs.getInt("RouteCoordinate.Position"));
                    mobileRouteCoordinate.setLatitude(rs.getDouble("RouteCoordinate.Latitude"));
                    mobileRouteCoordinate.setLongitude(rs.getDouble("RouteCoordinate.Longitude"));
                    mobileRouteCoordinate.setMobileRouteCoordinateID(rs.getLong("RouteCoordinate.RouteCoordinateID"));
                    mobileCardRoute.getMobileRouteCoordinates().add(mobileRouteCoordinate);
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }
}
