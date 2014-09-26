package model.database.requests;

import model.additionalentity.admin.CardLink;
import model.additionalentity.admin.CompleteCardInfo;
import model.constants.Component;
import model.constants.databaseenumeration.CardToCardLinkType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardToCardLinkEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LinkRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, LinkRequest.class);

    public static ArrayList<CardToCardLinkEntity> getAllCardToCardLink() {
        ArrayList<CardToCardLinkEntity> cardToCardLinkEntities = new ArrayList<CardToCardLinkEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardToCardLinkEntities = (ArrayList<CardToCardLinkEntity>)
                    session.createCriteria(CardToCardLinkEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cardToCardLinkEntities;
    }

    public static CardToCardLinkEntity getCardToCardLinkByID(Long cardToCardLinkID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (CardToCardLinkEntity) session.get(CardToCardLinkEntity.class, cardToCardLinkID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addCardToCardLink(CardToCardLinkEntity cardToCardLinkEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cardToCardLinkEntity);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardToCardLinkEntity getCardToCardLinkByResultSet(ResultSet rs, String linkRequest) throws SQLException {
        CardToCardLinkEntity cardToCardLinkEntity = null;
        Long cardToCardLinkID = rs.getLong(linkRequest + ".CardToCardLinkID");
        if (cardToCardLinkID != 0 && !rs.wasNull()) {
            cardToCardLinkEntity = new CardToCardLinkEntity();
            cardToCardLinkEntity.setCardToCardLinkID(cardToCardLinkID);
            cardToCardLinkEntity.setCardToCardLinkType(rs.getInt(linkRequest + ".CardToCardLinkType"));
        }
        return cardToCardLinkEntity;
    }

    public static void setSourceCardLinks(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        ArrayList<CardLink> sourceCardLinks = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM Card " +
                            "JOIN CardToCardLink ON (CardToCardLink.TargetCardID=Card.CardID) " +
                            "JOIN Card AS SourceCard ON (CardToCardLink.SourceCardID=SourceCard.CardID) " +
                            "WHERE Card.CardID=? ORDER BY CardToCardLink.CardToCardLinkType ";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardLink cardLink = new CardLink();
                cardLink.setCardID(rs.getLong("SourceCard.CardID"));
                cardLink.setCardToCardLinkID(rs.getLong("CardToCardLink.CardToCardLinkID"));
                cardLink.setCardName(rs.getString("SourceCard.CardName"));
                cardLink.setLinkType(CardToCardLinkType.parseInt(rs.getInt("CardToCardLink.CardToCardLinkType")));
                sourceCardLinks.add(cardLink);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setSourceCardLinks(sourceCardLinks);
    }

    public static void setTargetCardLinks(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        ArrayList<CardLink> targetCardLinks = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM Card " +
                            "JOIN CardToCardLink ON (CardToCardLink.SourceCardID=Card.CardID) " +
                            "JOIN Card AS TargetCard ON (CardToCardLink.TargetCardID=TargetCard.CardID) " +
                            "WHERE Card.CardID=? ORDER BY CardToCardLink.CardToCardLinkType";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardLink cardLink = new CardLink();
                cardLink.setCardID(rs.getLong("TargetCard.CardID"));
                cardLink.setCardToCardLinkID(rs.getLong("CardToCardLink.CardToCardLinkID"));
                cardLink.setCardName(rs.getString("TargetCard.CardName"));
                cardLink.setLinkType(CardToCardLinkType.parseInt(rs.getInt("CardToCardLink.CardToCardLinkType")));
                targetCardLinks.add(cardLink);
//                loggerFactory.debug("add "+cardLink.getCardID()+" to "+card.getCardInfo().getCardID());
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setTargetCardLinks(targetCardLinks);
    }

    public static boolean isLinkExist(Long sourceCardID, Long targetCardID, CardToCardLinkType cardToCardLinkType) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM  CardToCardLink " +
                            "WHERE CardToCardLink.SourceCardID=? " +
                            "AND CardToCardLink.TargetCardID=? " +
                            "AND CardToCardLink.CardToCardLinkType=?";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, sourceCardID);
            ps.setLong(2, targetCardID);
            ps.setLong(3, cardToCardLinkType.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static void deleteCardToCardLink(Long cardToCardLinkID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "DELETE FROM  CardToCardLink " +
                            "WHERE CardToCardLink.CardToCardLinkID=?";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardToCardLinkID);
            ps.executeUpdate();
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }
}