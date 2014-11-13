package model.database.requests;

import model.additionalentity.admin.CardInfoElement;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileInformationElement;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardInformationElementEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class InfoRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, InfoRequest.class);

    public static void setMobileInfos(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs, LanguageType languageType) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileInfos");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            final @Language("MySQL") String sql = "SELECT CardInformationElement.ImageID, " +
                    "CardInformationElement.CardID, " +
                    "CardInformationElement.Position, " +
                    "CardInformationElement.CardInformationElementID, " +
                    "Text.Text " +
                    "FROM CardInformationElement " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=CardInformationElement.TextGroupID) " +
                    "WHERE CardInformationElement.CardID IN (" + cardIDs + ") " +
                    "AND (Text.LanguageID=" + languageType.getValue() + " OR Text.TextID IS NULL)";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("CardInformationElement.CardID"));
                MobileInformationElement mobileInformationElement = new MobileInformationElement();
                mobileInformationElement.setText(rs.getString("Text.Text"));
                mobileInformationElement.setImageID(rs.getLong("CardInformationElement.ImageID"));
                mobileInformationElement.setPosition(rs.getInt("CardInformationElement.Position"));
                mobileInformationElement.setInformationElementID(rs.getLong("CardInformationElement.CardInformationElementID"));
                mobileCardInfo.getInformationElements().add(mobileInformationElement);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static void setInformationParts(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setInformationParts");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            final @Language("MySQL") String sql = "SELECT CardInformationElement.ImageID, " +
                    "CardInformationElement.CardID, " +
                    "CardInformationElement.Position, " +
                    "CardInformationElement.CardInformationElementID, " +
                    "CardInformationElement.TextGroupID, " +
                    "Text.Text " +
                    "FROM CardInformationElement " +
                    "LEFT OUTER JOIN TextGroup ON (CardInformationElement.TextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "WHERE CardInformationElement.CardID=? " +
                    "AND (Text.LanguageID=" + LanguageType.Russian.getValue() + " OR Text.TextID IS NULL)" +
                    "ORDER BY CardInformationElement.Position";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardInfoElement informationElement = new CardInfoElement();
                informationElement.setText(rs.getString("Text.Text"));
                informationElement.setImageID(rs.getLong("CardInformationElement.ImageID"));
                informationElement.setPosition(rs.getInt("CardInformationElement.Position"));
                informationElement.setInformationElementID(rs.getLong("CardInformationElement.CardInformationElementID"));
                informationElement.setTextGroupID(rs.getLong("CardInformationElement.TextGroupID"));
                card.getCardInfoElements().add(informationElement);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

    public static int countInfoElements(Long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("countInfoElements");
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            final @Language("MySQL") String sql = "SELECT count(*) AS C " +
                    "FROM CardInformationElement " +
                    "WHERE CardInformationElement.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getInt("C");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0;
    }

    public static void addCardInfoElement(CardInformationElementEntity cardInformationElementEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardInformationElementEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardInformationElementEntity getCardInformationElement(Long infoID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardInformationElementEntity) session.get(CardInformationElementEntity.class, infoID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void updateInfoImage(CardInformationElementEntity cardInformationElementEntity) {
        DatabaseConnection dbConnection = new DatabaseConnection("updateInfoImage");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            final @Language("MySQL") String sql = "UPDATE  CardInformationElement " +
                    "SET ImageID=? " +
                    "WHERE CardInformationElementID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardInformationElementEntity.getImage().getImageID());
            ps.setLong(2, cardInformationElementEntity.getCardInformationElementID());
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }
}
