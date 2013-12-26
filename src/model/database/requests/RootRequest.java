package model.database.requests;

import model.additionalentity.CompleteCardRootInfo;
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

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 14.11.13
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class RootRequest {
    private static LoggerFactory loggerFactory=new LoggerFactory(Component.Database,RootRequest.class);

    public static CardRootEntity getCardRootByResultSet(ResultSet rs) throws SQLException {
        Long cardRootID = rs.getLong("CardRoot.CardRootID");
        if (cardRootID == 0 || rs.wasNull()) {
            return null;
        }
        CardRootEntity cardRootEntity = new CardRootEntity();
        cardRootEntity.setCardRootID(cardRootID);
        cardRootEntity.setCardRootName(rs.getString("CardRoot.CardRootName"));
        return cardRootEntity;
    }

    public static RootElementEntity getRootElementByResultSet(ResultSet rs) throws SQLException {
        Long rootElementID = rs.getLong("RootElement.RootElementID");
        if (rootElementID == 0 || rs.wasNull()) {
            return null;
        }
        RootElementEntity rootElementEntity = new RootElementEntity();
        rootElementEntity.setRootElementID(rootElementID);
        rootElementEntity.setRootElementNumber(rs.getInt("RootElement.RootElementNumber"));
        return rootElementEntity;
    }

    public static void addCardRoot(CardRootEntity cardRootEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardRootEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addRootElement(RootElementEntity rootElementEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(rootElementEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
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
            if (session != null) {
                session.close();
            }
        }
    }

    public static HashMap<Long, CompleteCardRootInfo> getCompleteCardRoots() {
        HashMap<Long, CompleteCardRootInfo> cardRoots = new HashMap<Long, CompleteCardRootInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoot " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoot.RootDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID)";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardRootInfo cardRoot;
                Long cardRootID = rs.getLong("CardImage.CardImageID");
                if (cardRootID != 0 && !rs.wasNull()) {
                    if (cardRoots.containsKey(cardRootID) && cardRoots.get(cardRootID) != null) {
                        cardRoot = cardRoots.get(cardRootID);
                    } else {
                        cardRoot = new CompleteCardRootInfo(getCardRootByResultSet(rs));
                        cardRoots.put(cardRootID, cardRoot);
                    }
                    getCompleteCardRoot(rs, cardRoot, "RootCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoots;
    }

    public static HashMap<Long, CompleteCardRootInfo> getCompleteCardRootsByCard(long cardID) {
        HashMap<Long, CompleteCardRootInfo> cardRoots = new HashMap<Long, CompleteCardRootInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoot " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoot.RootDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardRoot.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardRootInfo cardRoot;
                Long cardRootID = rs.getLong("CardImage.CardImageID");
                if (cardRootID != 0 && !rs.wasNull()) {
                    if (cardRoots.containsKey(cardRootID) && cardRoots.get(cardRootID) != null) {
                        cardRoot = cardRoots.get(cardRootID);
                    } else {
                        cardRoot = new CompleteCardRootInfo(getCardRootByResultSet(rs));
                        cardRoots.put(cardRootID, cardRoot);
                    }
                    getCompleteCardRoot(rs, cardRoot, "RootCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoots;
    }

    public static CompleteCardRootInfo getCompleteCardRootByCardRootID(long cardRootID) throws SQLException {
        CompleteCardRootInfo cardRoot = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT *  FROM CardRoot " +
                    "LEFT OUTER JOIN RootElement ON (RootElement.CardRootID=CardRoot.CardRootID) " +
                    "LEFT OUTER JOIN Card AS RootCard ON (RootCard.CardID=RootElement.PlaceCardID) " +
                    "LEFT OUTER JOIN TextGroup ON(CardRoot.RootDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardRoot.CardRootID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardRootID);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long cardRootID2 = rs.getLong("CardImage.CardImageID");
                if (cardRootID2 != 0 && cardRootID == cardRootID2 && !rs.wasNull()) {
                    cardRoot = new CompleteCardRootInfo(getCardRootByResultSet(rs));
                    getCompleteCardRoot(rs, cardRoot, "RootCard", "TextGroup", "Text");
                } else {
                    return null;
                }
                while (rs.next()) {
                    getCompleteCardRoot(rs, cardRoot, "RootCard", "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardRoot;
    }

    public static void getCompleteCardRoot(ResultSet rs, CompleteCardRootInfo completeCardRootInfo, String rootCard, String textGroup, String text) throws SQLException {
        //root element
        Long rootElementID = rs.getLong("RootElement.RootElementID");
        if (rootElementID != 0 && !rs.wasNull()) {
            RootElementEntity rootElementEntity;
            if (completeCardRootInfo.getRootElementEntityMap().containsKey(rootElementID) &&
                    completeCardRootInfo.getRootElementEntityMap().get(rootElementID) != null) {
                rootElementEntity = completeCardRootInfo.getRootElementEntityMap().get(rootElementID);
            } else {
                rootElementEntity = RootRequest.getRootElementByResultSet(rs);
                rootElementEntity.setCardRoot(completeCardRootInfo.getCardRootEntity());
                completeCardRootInfo.getRootElementEntityMap().put(rootElementID, rootElementEntity);
            }
            if (rootElementEntity.getPlaceCard() == null) {
                //root element placeCard
                Long rootCardID = rs.getLong(rootCard + ".CardID");
                if (rootCardID != 0 && !rs.wasNull()) {
                    CardEntity cardEntity = CardRequest.getCardFromResultSet(rs, rootCard);
                    rootElementEntity.setPlaceCard(cardEntity);
                }
            }
        }
        //Root Text Group
        Long rootTextGroupID = rs.getLong(textGroup + ".TextGroupID");
        if (rootTextGroupID != 0 && !rs.wasNull()) {
            CompleteTextGroupInfo completeTextGroupInfo;
            if (completeCardRootInfo.getCompleteTextGroupInfo() == null) {
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, textGroup);
                completeTextGroupInfo = new CompleteTextGroupInfo(textGroupEntity);
                completeCardRootInfo.setCompleteTextGroupInfo(completeTextGroupInfo);
                completeCardRootInfo.getCardRootEntity().setRootDescriptionTextGroup(textGroupEntity);
            } else {
                completeTextGroupInfo = completeCardRootInfo.getCompleteTextGroupInfo();
            }
            TextRequest.getCompleteTextGroupInfo(rs, completeTextGroupInfo, text);
        }
    }
}
