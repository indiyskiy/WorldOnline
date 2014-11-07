package model.database.requests;

import controller.parser.adminparser.AllCardParser;
import helper.StringHelper;
import helper.TimeManager;
import model.additionalentity.SimpleCard;
import model.additionalentity.admin.*;
import model.additionalentity.phone.MobileCardInfo;
import model.additionalentity.phone.MobileCoordinate;
import model.additionalentity.phone.MobileUrgencyTime;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.LanguageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.*;

public class CardRequest {

    @Language("MySQL")
    private static final String MobileCardSql = "SELECT " +
            "Card.CardID, " +
            "Card.CardType, " +
            "Card.NumberInList, " +
            //coordinate
            "CardCoordinate.Latitude, " +
            "CardCoordinate.Longitude, " +
            //price
            "Price.PriceID, " +
            //language
            "UserPersonalData.UserLanguage, " +
            "UrgencyTime.UrgencyTimeID, UrgencyTime.OnTimestamp, UrgencyTime.OffTimestamp " +
            "FROM Card " +
            "JOIN User ON (User.UserID=?) " +
            "JOIN UserPersonalData ON (UserPersonalData.UserPersonalDataID=User.UserPersonalDataID) " +
            "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
            "LEFT OUTER JOIN CardPriceLink ON (Card.CardID=CardPriceLink.CardID) " +
            "LEFT OUTER JOIN Price ON (Price.PriceID=CardPriceLink.PriceID) " +
            "LEFT OUTER JOIN UrgencyTime ON (UrgencyTime.cardID=Card.CardID)";

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, CardRequest.class);

    public static ArrayList<CardEntity> getAllCards(int firstElem, int maxElems) {
        ArrayList<CardEntity> cardEntities = new ArrayList<CardEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardEntities = (ArrayList<CardEntity>) session.createCriteria(CardEntity.class).setFirstResult(firstElem).setMaxResults(maxElems).list();
            transaction.commit();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cardEntities;
    }

    public static ArrayList<CardEntity> getAllCards() {
        ArrayList<CardEntity> cardEntities = new ArrayList<CardEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardEntities = (ArrayList<CardEntity>) session.createCriteria(CardEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cardEntities;
    }

    public static CardEntity getCardByID(Long cardID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (CardEntity) session.get(CardEntity.class, cardID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean addCardSafe(CardEntity card) {
        boolean b = false;
        String cardName = card.getCardName();
        boolean exist = isCardExist(cardName);
        if (!exist) {
            b = addCard(card);
            if (CardState.parseInt(card.getCardState()) == CardState.Active) {
                ArrayList<String> errors = checkCardForActivate(card);
                if (errors.size() != 0) {
                    checkCardStatus(card, CardState.NotActive);
                }
            }
        }
        return b;
    }


    public static boolean addCard(CardEntity card) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            session.save(card);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean updateCard(CardEntity card) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        boolean b = false;
        try {
            session.beginTransaction();
            card.setLastUpdateTimestamp(TimeManager.currentTime());
            session.update(card);
            session.getTransaction().commit();
            session.flush();
            b = true;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    public static boolean contains(CardEntity card) {
        if (card != null) {
            CardEntity cardFromBase = getCardByID(card.getCardID());
            if (cardFromBase != null && card.equals(cardFromBase)) {
                return true;
            }
        }
        return false;
    }

    public static void addCard(List<CardEntity> cards) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for (CardEntity card : cards) {
                session.save(card);
            }
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CompleteCardInfo getCompleteCardInfo(long cardID) throws SQLException {
        CompleteCardInfo card = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT * FROM Card " +
                            "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
                            "LEFT OUTER JOIN CardPriceLink ON (Card.CardID=CardPriceLink.CardID) " +
                            "LEFT OUTER JOIN Price ON (CardPriceLink.PriceID=Price.PriceID) " +
                            "LEFT OUTER JOIN UrgencyTime ON (UrgencyTime.cardID=Card.CardID) " +
                            "WHERE Card.CardID=?";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            if (rs.first()) {
                card = new CompleteCardInfo();
                String cardName = rs.getString("Card.CardName");
                CardState cardState = CardState.parseInt(rs.getInt("Card.CardState"));
                CardType cardType = CardType.parseInt(rs.getInt("Card.CardType"));
                Timestamp creationTime = rs.getTimestamp("Card.CreationTimestamp");
                Timestamp updateTimestamp = rs.getTimestamp("Card.LastUpdateTimestamp");
                CardInfo cardInfo = new CardInfo();
                cardInfo.setCardID(cardID);
                cardInfo.setName(cardName);
                cardInfo.setCardState(cardState);
                cardInfo.setCardType(cardType);
                cardInfo.setCreationTime(creationTime);
                cardInfo.setUpdateTime(updateTimestamp);
                card.setCardInfo(cardInfo);
                //coordinate
                Long cardCoordinateID = rs.getLong("CardCoordinate.CardCoordinateID");
                if (cardCoordinateID != 0 && !rs.wasNull()) {
                    CardCoordinate cardCoordinate = new CardCoordinate();
                    cardCoordinate.setLatitude(rs.getDouble("CardCoordinate.Latitude"));
                    cardCoordinate.setLongitude(rs.getDouble("CardCoordinate.Longitude"));
                    card.setCardCoordinate(cardCoordinate);
                }
                //price
                Long priceID = rs.getLong("Price.PriceID");
                if (priceID != 0 && !rs.wasNull()) {
                    CardPrice cardPrice = new CardPrice();
                    cardPrice.setPriceID(priceID);
                    card.setCardPrice(cardPrice);
                }
                Long urgencyTimeID = rs.getLong("UrgencyTime.UrgencyTimeID");
                if (urgencyTimeID != 0 && !rs.wasNull()) {
                    SimpleUrgencyTime simpleUrgencyTime = new SimpleUrgencyTime();
                    simpleUrgencyTime.setStart(rs.getTimestamp("UrgencyTime.OnTimestamp"));
                    simpleUrgencyTime.setEnd(rs.getTimestamp("UrgencyTime.OffTimestamp"));
                    simpleUrgencyTime.setUrgencyTimeID(rs.getLong("UrgencyTime.UrgencyTimeID"));
                    card.setUrgencyTime(simpleUrgencyTime);
                }
                LinkRequest.setSourceCardLinks(card, cardID);
                LinkRequest.setTargetCardLinks(card, cardID);
                MenuRequest.setCardMenus(card, cardID);
                ImageRequest.setCardImages(card, cardID);
                ParameterRequest.setCardParameters(card, cardID);
                TextRequest.setCardTexts(card, cardID);
                TagRequest.setCardTags(card, cardID);
                InfoRequest.setInformationParts(card, cardID);
                card.uploadCardBlocks();
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return card;
    }

    public static CardEntity getCardFromResultSet(ResultSet rs) throws SQLException {
        return getCardFromResultSet(rs, "Card");
    }

    public static CardEntity getCardFromResultSet(ResultSet rs, String card) throws SQLException {
        CardEntity cardEntity = null;
        if (rs.getLong(card + ".CardID") != 0 && !rs.wasNull()) {
            cardEntity = new CardEntity();
            cardEntity.setCardID(rs.getLong(card + ".CardID"));
            cardEntity.setCardName(rs.getString(card + ".CardName"));
            cardEntity.setCardType(rs.getInt(card + ".CardType"));
            cardEntity.setCreationTimestamp(rs.getTimestamp(card + ".CreationTimestamp"));
            cardEntity.setLastUpdateTimestamp(rs.getTimestamp(card + ".LastUpdateTimestamp"));
            cardEntity.setCardState(rs.getInt(card + ".CardState"));
        }
        return cardEntity;
    }


    public static ArrayList<CardEntity> getAllCards(AllCardParser parser) {
        ArrayList<CardEntity> cards = new ArrayList<CardEntity>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT DISTINCT Card.CardName," +
                    "Card.CardType," +
                    "Card.CardID," +
                    "Card.CardState," +
                    "Card.CreationTimestamp," +
                    "Card.LastUpdateTimestamp " +
                    "FROM Card ";
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += "LEFT OUTER JOIN TextCard ON (Card.CardID=TextCard.CardID) " +
                        "LEFT OUTER JOIN TextGroup ON (TextCard.TextGroupID=TextGroup.TextGroupID) " +
                        "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) ";
            }
            sql += "WHERE 1=1 ";
            if (parser.getCardID() != null) {
                sql += "AND (Card.CardID=?) ";
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += "AND (Card.CardName Like ? OR (Text.Text Like ?)) ";
            }
            if (parser.getCardType() != null) {
                sql += "AND (Card.CardType=?) ";
            }
            sql += " LIMIT " + parser.getFirstElem() + ", " + parser.getMaxItems();
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (parser.getCardID() != null) {
                ps.setLong(i, parser.getCardID());
                i++;
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                ps.setString(i, "%" + parser.getCardName() + "%");
                i++;
                ps.setString(i, "%" + parser.getCardName() + "%");
                i++;
            }
            if (parser.getCardType() != null) {
                ps.setInt(i, parser.getCardType().getValue());
                i++;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                CardEntity cardEntity = getCardFromResultSet(rs);
                cards.add(cardEntity);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cards;
    }

    public static Long countCard() {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        return (Long) session.createCriteria(CardEntity.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public static Long countCard(AllCardParser parser) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT count(1) as results FROM Card " +
                    "WHERE 1=1";
            if (parser.getCardID() != null) {
                sql += " AND Card.CardID=?";
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                sql += " AND Card.CardName=?";
            }
            if (parser.getCardType() != null) {
                sql += " AND Card.CardType=?";
            }
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (parser.getCardID() != null) {
                ps.setLong(i, parser.getCardID());
                i++;
            }
            if (parser.getCardName() != null && !parser.getCardName().isEmpty()) {
                ps.setString(i, parser.getCardName());
                i++;
            }
            if (parser.getCardType() != null) {
                ps.setInt(i, parser.getCardType().getValue());
                i++;
            }
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getLong("results");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0L;
    }

    public static CardEntity getCardByName(String name) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Text " +
                    "JOIN TextGroup ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN TextCard ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
                    "JOIN Card ON (TextCard.CardID=Card.CardID) " +
                    "WHERE Text.Text LIKE ? || Card.CardName LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, name);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long cardID = rs.getLong("Card.CardID");
                return getCardByID(cardID);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static boolean isCardExist(String name) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM  Card " +
                    "WHERE Card.CardName LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.first()) {
                return true;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return false;
    }

    public static HashSet<String> getAllCardNames(Long cardID) {
        HashSet<String> res = new HashSet<String>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Text.Text FROM Text " +
                    "JOIN TextGroup ON (Text.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN TextCard ON (TextCard.TextGroupID=TextGroup.TextGroupID) " +
                    "JOIN Card ON (Card.CardID=TextCard.CardID) " +
                    "WHERE Card.CardID=? ";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                res.add(rs.getString("Text.Text"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return res;
    }

    public static LinkedList<MobileCardInfo> getMobileCards(Long userID, Integer limit, Integer offset) {
        String cardIDs = getLimitedCardIDs(limit, offset);
        return getMobileCardInfos(userID, cardIDs);
    }

    private static LinkedList<MobileCardInfo> getMobileCardInfos(Long userID, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        LinkedList<MobileCardInfo> mobileCardInfos = new LinkedList<>();
        HashMap<Long, MobileCardInfo> mobileCardInfoHashMap = new HashMap<>();
        LanguageType languageType = null;
        try {
            if (cardIDs == null || cardIDs.isEmpty()) {
                return mobileCardInfos;
            }
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = MobileCardSql + "WHERE Card.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = new MobileCardInfo();
                mobileCardInfo.setCardID(rs.getLong("Card.CardID"));
                mobileCardInfo.setOrder(rs.getInt("Card.NumberInList"));
                mobileCardInfo.setCardType(CardType.parseInt(rs.getInt("Card.CardType")));
                MobileCoordinate mobileCoordinate = new MobileCoordinate();
                mobileCoordinate.setLatitude(rs.getDouble("CardCoordinate.Latitude"));
                mobileCoordinate.setLongitude(rs.getDouble("CardCoordinate.Longitude"));
                mobileCardInfo.setCoordinate(mobileCoordinate);
                mobileCardInfo.setPriceID(rs.getLong("Price.PriceID"));
                Long urgencyTimeID = rs.getLong("UrgencyTime.UrgencyTimeID");
                if (urgencyTimeID != 0 && !rs.wasNull()) {
                    MobileUrgencyTime mobileUrgencyTime = new MobileUrgencyTime();
                    mobileUrgencyTime.setStart(rs.getTimestamp("UrgencyTime.OnTimestamp"));
                    mobileUrgencyTime.setEnd(rs.getTimestamp("UrgencyTime.OffTimestamp"));
                    mobileCardInfo.setMobileUrgencyTime(mobileUrgencyTime);
                }
                mobileCardInfoHashMap.put(mobileCardInfo.getCardID(), mobileCardInfo);
                mobileCardInfos.add(mobileCardInfo);
                languageType = LanguageType.parseInt(rs.getInt("UserPersonalData.UserLanguage"));
            }
            if (!mobileCardInfos.isEmpty()) {
                TextRequest.setMobileTexts(mobileCardInfoHashMap, cardIDs, languageType);
                MenuRequest.setMobileMenus(mobileCardInfoHashMap, cardIDs);
                ImageRequest.setMobileImages(mobileCardInfoHashMap, cardIDs);
                ParameterRequest.setMobileParameters(mobileCardInfoHashMap, cardIDs);
                TagRequest.setMobileTags(mobileCardInfoHashMap, cardIDs);
                LinkRequest.setMobileLinks(mobileCardInfoHashMap, cardIDs);
                InfoRequest.setMobileInfos(mobileCardInfoHashMap, cardIDs, languageType);
                RouteRequest.setMobileRoute(mobileCardInfoHashMap, cardIDs);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileCardInfos;
    }

    private static String getLimitedCardIDs(Integer limit, Integer offset) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Object> ids = new LinkedList<>();
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID " +
                    "FROM Card WHERE Card.CardState=? LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, CardState.Active.getValue());
            ps.setLong(2, offset);
            ps.setLong(3, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getLong("Card.CardID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return StringHelper.getStringFromArray(ids);
    }

    public static ArrayList<String> checkCardStatus(CardEntity cardEntity, CardState cardState) {
        ArrayList<String> errors = new ArrayList<>();
        switch (cardState) {
            case Unknown: {
                errors.add("unknown card state");
                return errors;
            }
            case Active: {
                return checkCardForActivate(cardEntity);
            }
            case NotActive: {
                return errors;
            }
            case Deleted: {
                return errors;
            }
            default:
                errors.add("unknown card state");
                return errors;
        }
    }

    private static ArrayList<String> checkCardForActivate(CardEntity cardEntity) {
        return new ArrayList<>();
    }

    public static Long countCard(CardType cardType) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT count(1) AS results FROM Card " +
                    "WHERE 1=1 AND Card.CardType=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cardType.getValue());
            rs = ps.executeQuery();
            if (rs.first()) {
                return rs.getLong("results");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0L;
    }

    public static ArrayList<SimpleCard> getAllCardsByTagGroup(Long tagGroupID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SimpleCard> simpleCards = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID, Card.CardName " +
                    "FROM Card " +
                    "JOIN CardTag ON (CardTag.CardID=Card.CardID) " +
                    "JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "JOIN TagGroup ON (TagGroup.TagGroupID=Tag.TagGroupID) " +
                    "WHERE TagGroup.TagGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagGroupID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleCard simpleCard = new SimpleCard();
                simpleCard.setName(rs.getString("Card.CardName"));
                simpleCard.setCardID(rs.getLong("Card.CardID"));
                simpleCards.add(simpleCard);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleCards;
    }

    public static SimpleCard getTagGroupCard(Long tagGroupID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID, Card.CardName " +
                    "FROM Card " +
                    "JOIN TagGroup ON (TagGroup.CardID=Card.CardID) " +
                    "WHERE TagGroup.TagGroupID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagGroupID);
            rs = ps.executeQuery();
            if (rs.first()) {
                SimpleCard simpleCard = new SimpleCard();
                simpleCard.setName(rs.getString("Card.CardName"));
                simpleCard.setCardID(rs.getLong("Card.CardID"));
                return simpleCard;
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return null;
    }

    public static ArrayList<SimpleCard> getAllCardsByTag(Long tagID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SimpleCard> simpleCards = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID, Card.CardName " +
                    "FROM Card " +
                    "JOIN CardTag ON (CardTag.CardID=Card.CardID) " +
                    "JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
                    "WHERE Tag.TagID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, tagID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleCard simpleCard = new SimpleCard();
                simpleCard.setName(rs.getString("Card.CardName"));
                simpleCard.setCardID(rs.getLong("Card.CardID"));
                simpleCards.add(simpleCard);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleCards;
    }

    public static ArrayList<SimpleCard> getAllPriceCards(Long priceID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SimpleCard> simpleCards = new ArrayList<>();
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID, Card.CardName " +
                    "FROM Card " +
                    "JOIN CardPriceLink ON (CardPriceLink.CardID=Card.CardID) " +
                    "JOIN Price ON (Price.PriceID=CardPriceLink.PriceID) " +
                    "WHERE Price.PriceID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, priceID);
            rs = ps.executeQuery();
            while (rs.next()) {
                SimpleCard simpleCard = new SimpleCard();
                simpleCard.setName(rs.getString("Card.CardName"));
                simpleCard.setCardID(rs.getLong("Card.CardID"));
                simpleCards.add(simpleCard);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return simpleCards;
    }

    public static void updateCardsByMenu(long menuID) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "UPDATE Card " +
                    "JOIN MenuCardLink ON (Card.CardID=MenuCardLink.CardID) " +
                    "SET LastUpdateTimestamp=? " +
                    "WHERE MenuCardLink.MenuID=?";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, TimeManager.currentTime());
            ps.setLong(2, menuID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static LinkedList<MobileCardInfo> getMobileCards(Long userID, List<Object> cardIDsList) {
        String cardIDs = StringHelper.getStringFromArray(cardIDsList);
        return getMobileCardInfos(userID, cardIDs);
    }
}