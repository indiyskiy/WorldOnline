package model.database.requests;

import controller.parser.adminparser.CardPopularityParser;
import model.additionalentity.admin.CardPopularity;
import model.additionalentity.phone.MobileCardActivity;
import model.additionalentity.phone.MobileCardEvent;
import model.additionalentity.phone.MobileFieldActivity;
import model.constants.Component;
import model.database.session.DatabaseConnection;
import model.logger.LoggerFactory;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatisticRequest {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Database, UserDataRequest.class);

    public static void addCardActivities(ArrayList<MobileCardActivity> mobileCardActivities, long userContentID) {
        if (mobileCardActivities.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("addCardActivities");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO CardActivity (CardID,UserContentID,OnTimestamp,OffTimestamp) " +
                    "VALUES ";
            for (MobileCardActivity mobileCardActivity : mobileCardActivities) {
                sql += "(" + mobileCardActivity.getCardID() + "," + userContentID + ",'" +
                        mobileCardActivity.getOnTimestamp() + "','" + mobileCardActivity.getOffTimestamp() + "'),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void addCardEvents(ArrayList<MobileCardEvent> mobileCardEvents, long userContentID) {
        if (mobileCardEvents.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("addCardEvents");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO CardEvent (CardID,UserContentID,EventType,EventTimestamp,Text) " +
                    "VALUES ";
            for (MobileCardEvent mobileCardEvent : mobileCardEvents) {
                String textValue;
                if (mobileCardEvent.getText() == null) {
                    textValue = "NULL";
                } else {
                    textValue = "'" + mobileCardEvent.getText() + "'";
                }
                sql += "(" + mobileCardEvent.getCardID() + "," + userContentID + "," +
                        mobileCardEvent.getEventType().getValue() + ",'" + mobileCardEvent.getEventTimestamp() + "'," +
                        textValue + "),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void addFieldActivities(ArrayList<MobileFieldActivity> mobileFieldActivities, long userContentID) {
        if (mobileFieldActivities.isEmpty()) {
            return;
        }
        DatabaseConnection dbConnection = new DatabaseConnection("addFieldActivities");
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "INSERT INTO FieldActivity (CardID,UserContentID,ActivityTimestamp,CardParameterTypeID,Text) " +
                    "VALUES ";
            for (MobileFieldActivity mobileFieldActivity : mobileFieldActivities) {
                String textValue;
                if (mobileFieldActivity.getText() == null) {
                    textValue = "NULL";
                } else {
                    textValue = "'" + mobileFieldActivity.getText() + "'";
                }
                sql += "(" + mobileFieldActivity.getCardID() + "," + userContentID + ",'" +
                        mobileFieldActivity.getActivityTimestamp() + "'," + mobileFieldActivity.getCardParameterTypeID() + "," +
                        textValue + "),";
            }
            sql = sql.substring(0, sql.lastIndexOf(","));
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static boolean isCardActivitiesTableEmpty() {
        DatabaseConnection dbConnection = new DatabaseConnection("addFieldActivities");
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT 1 FROM CardActivity LIMIT 1";
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            return !rs.first();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
        return false;
    }

    public static ArrayList<CardPopularity> getCardPopularity(CardPopularityParser cardPopularityParser) {
        DatabaseConnection dbConnection = new DatabaseConnection("addFieldActivities");
        ResultSet rs;
        PreparedStatement ps = null;
        ArrayList<CardPopularity> cardPopularities = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            String stateString;
            if (cardPopularityParser.getState() == null) {
                stateString = "";
            } else {
                switch (cardPopularityParser.getState()) {
                    case Active:
                        stateString = "WHERE Card.CardState = 1 ";
                        break;
                    case NotActive:
                        stateString = "WHERE Card.CardState = 2 ";
                        break;
                    case Deleted:
                        stateString = "WHERE Card.CardState = 3 ";
                        break;
                    default:
                        stateString = "";
                        break;
                }
            }
            String sortByString;
            if (cardPopularityParser.getSortBy() == null) {
                sortByString = "ORDER BY AllTime DESC, CardID ";
            } else {
                switch (cardPopularityParser.getSortBy()) {
                    case CARD_ID:
                        sortByString = "ORDER BY CardID, AllTime DESC ";
                        break;
                    case ALL_VISITS:
                        sortByString = "ORDER BY AllVisits DESC, CardID ";
                        break;
                    case ALL_USERS:
                        sortByString = "ORDER BY AllUsers DESC, CardID ";
                        break;
                    default:
                        sortByString = "ORDER BY AllTime DESC, CardID ";
                        break;
                }
            }
            @Language("MySQL") String sql = "SELECT " +
                    "  Card.CardID, " +
                    "  Card.CardName, " +
                    "  CASE 1 " +
                    "  WHEN CardActivity.CardActivityID IS NOT NULL THEN SUM( " +
                    "                                                        TIMESTAMPDIFF(SECOND, CardActivity.OnTimestamp, " +
                    "                                                                      CardActivity.OffTimestamp)) DIV 60 " +
                    "  ELSE 0 END " +
                    "    AS AllTime, " +
                    "  COUNT(*) AS AllVisits, " +
                    "  COUNT(DISTINCT CardActivity.UserContentID) AS AllUsers " +
                    "FROM Card " +
                    "  LEFT OUTER JOIN CardActivity ON (Card.CardID = CardActivity.CardID) " +
                    stateString +
                    "GROUP BY Card.CardID " +
                    sortByString;
            loggerFactory.debug(sql);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                long cardID = rs.getLong("CardID");
                String cardName = rs.getString("CardName");
                long allTime = rs.getLong("AllTime");
                cardPopularities.add(new CardPopularity(cardID, cardName, allTime));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
        return cardPopularities;
    }

}
