package model.database.requests;

import controller.parser.adminparser.AllCardParser;
import model.additionalentity.admin.CardCoordinate;
import model.additionalentity.admin.CardInfo;
import model.additionalentity.admin.CardPrice;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.phone.*;
import model.constants.Component;
import model.constants.databaseenumeration.*;
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
    private static final String MobileCardSql = "SELECT Card.CardID, " +
            "Card.CardType, TextGroup.TextGroupID, " +
            "Card.NumberInList, " +
            "Text.Text, " +
            "TextCard.CardParameterTypeID, " +
            "Menu.MenuID, " +
            "CardImage.CardImageType, " +
            "Image.ImageID, " +
            "Image.ImageWidth, " +
            "Image.ImageFileSize, " +
            "Image.ImageHeight, " +
            "CardParameter.CardParameterValue, " +
            "CardParameter.CardParameterID, " +
            "CardParameter.CardParameterTypeID, " +
            "CardCoordinate.CardCoordinateID, " +
            "CardCoordinate.Latitude, " +
            "CardCoordinate.Longitude, " +
            "Price.PriceID, " +
            "Tag.TagID," +
            "TargetCard.SourceCardID," +
            "TargetCard.CardToCardLinkID," +
            "TargetCard.CardToCardLinkType," +
            "SourceCard.TargetCardID, " +
            "SourceCard.CardToCardLinkID, " +
            "SourceCard.CardToCardLinkType " +
            "FROM Card " +
            "LEFT OUTER JOIN TextCard ON (TextCard.CardID=Card.CardID) " +
            "LEFT OUTER JOIN TextGroup ON (TextGroup.TextGroupID=TextCard.TextGroupID) " +
            "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID) " +
            "LEFT OUTER JOIN User ON (User.UserID=?) " +
            "LEFT OUTER JOIN UserPersonalData ON (UserPersonalData.UserPersonalDataID=User.UserPersonalDataID) " +
            "LEFT OUTER JOIN MenuCardLink ON (MenuCardLink.CardID=Card.CardID) " +
            "LEFT OUTER JOIN Menu ON (MenuCardLink.MenuID=Menu.MenuID) " +
            "LEFT OUTER JOIN CardImage ON (Card.CardID=CardImage.CardID) " +
            "LEFT OUTER JOIN Image ON (CardImage.ImageID=Image.ImageID) " +
            "LEFT OUTER JOIN CardParameter ON (Card.CardID=CardParameter.CardID) " +
            "LEFT OUTER JOIN CardCoordinate ON (Card.CardID=CardCoordinate.CardID) " +
            "LEFT OUTER JOIN CardRoute ON (CardRoute.CardID) " +
            "LEFT OUTER JOIN CardTag ON (Card.CardID=CardTag.CardID) " +
            "LEFT OUTER JOIN Tag ON (Tag.TagID=CardTag.TagID) " +
            "LEFT OUTER JOIN CardPriceLink ON (Card.CardID=CardPriceLink.CardID) " +
            "LEFT OUTER JOIN Price ON (Price.PriceID=CardPriceLink.PriceID) " +
            "LEFT OUTER JOIN CardToCardLink AS SourceCard ON (SourceCard.SourceCardID=Card.CardID) " +
            "LEFT OUTER JOIN CardToCardLink AS TargetCard ON (TargetCard.TargetCardID=Card.CardID) ";
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
//        Session session = new HibernateUtil().getSessionFactory().openSession();
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
            session.update(card);
            session.getTransaction().commit();
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
                LinkRequest.setSourceCardLinks(card, cardID);
                LinkRequest.setTargetCardLinks(card, cardID);
                MenuRequest.setCardMenus(card, cardID);
                ImageRequest.setCardImages(card, cardID);
                ParameterRequest.setCardParameters(card, cardID);
                TextRequest.setCardTexts(card, cardID);
                TagRequest.setCardTags(card, cardID);
                card.uploadCardBlocks();
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
                return true;
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return res;
    }

    public static LinkedList<MobileCardInfo> getAllMobileCards(Long userID, Integer limit, Integer offset) {
//        TimeCounter timeCounter = new TimeCounter();
        HashMap<Long, MobileCardInfo> cardMap = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = MobileCardSql +
                    "JOIN (" +
                    "SELECT FilterCard.CardID " +
                    "FROM Card AS FilterCard " +
                    "WHERE (FilterCard.CardState IN ("
                    + CardState.Active.getValue() + ")) ORDER BY FilterCard.CardID LIMIT " + offset + "," + limit + ") " +
                    "AS T ON (Card.CardID=T.CardID) " +
                    "WHERE (UserPersonalData.UserLanguage=Text.LanguageID OR Text.LanguageID IS NULL) " +
                    "AND (Card.CardState IN (" + CardState.Active.getValue() + "))";

            ps = connection.prepareStatement(sql);
            ps.setLong(1, userID);
//            timeCounter.logTime(loggerFactory, "create request");
            rs = ps.executeQuery();
//            timeCounter.logTime(loggerFactory, "execute request");
            cardMap = parseMobileCardSQLRequest(rs);
//            timeCounter.logTime(loggerFactory, "parse request");
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        LinkedList<MobileCardInfo> mobileCardInfos = new LinkedList<>();
        if (cardMap != null) {
            mobileCardInfos.addAll(cardMap.values());
        }
//        timeCounter.logTime(loggerFactory, "request done");
        return mobileCardInfos;
    }

    private static HashMap<Long, MobileCardInfo> parseMobileCardSQLRequest(ResultSet rs) throws SQLException {
        HashMap<Long, HashSet<Long>> textSets = new HashMap<>();
        HashMap<Long, HashSet<Long>> imageSets = new HashMap<>();
        HashMap<Long, HashSet<Long>> parameterSets = new HashMap<>();
        HashMap<Long, HashSet<Long>> sourceLinks = new HashMap<>();
        HashMap<Long, HashSet<Long>> targetLinks = new HashMap<>();

        HashMap<Long, MobileCardInfo> cardMap = new HashMap<>();
        while (rs.next()) {
            Long cardID = rs.getLong("Card.CardID");
            if (cardID != 0 && !rs.wasNull()) {
                MobileCardInfo mobileCardInfo;
                if (cardMap.containsKey(cardID)) {
                    mobileCardInfo = cardMap.get(cardID);
                } else {
                    mobileCardInfo = new MobileCardInfo();
                    mobileCardInfo.setCardID(cardID);
                    mobileCardInfo.setCardType(CardType.parseInt(rs.getInt("Card.CardType")));
                    mobileCardInfo.setOrder(rs.getInt("Card.NumberInList"));
                    cardMap.put(cardID, mobileCardInfo);
                }
                if (mobileCardInfo != null) {
                    HashSet<Long> textSet;
                    if (textSets.containsKey(cardID)) {
                        textSet = textSets.get(cardID);
                    } else {
                        textSet = new HashSet<>();
                        textSets.put(cardID, textSet);
                    }
                    HashSet<Long> imageSet;
                    if (imageSets.containsKey(cardID)) {
                        imageSet = imageSets.get(cardID);
                    } else {
                        imageSet = new HashSet<>();
                        imageSets.put(cardID, imageSet);
                    }
                    HashSet<Long> parameterSet;
                    if (parameterSets.containsKey(cardID)) {
                        parameterSet = parameterSets.get(cardID);
                    } else {
                        parameterSet = new HashSet<>();
                        parameterSets.put(cardID, parameterSet);
                    }
                    HashSet<Long> sourceLinkSet;
                    if (sourceLinks.containsKey(cardID)) {
                        sourceLinkSet = sourceLinks.get(cardID);
                    } else {
                        sourceLinkSet = new HashSet<>();
                        sourceLinks.put(cardID, sourceLinkSet);
                    }
                    HashSet<Long> targetLinkSet;
                    if (targetLinks.containsKey(cardID)) {
                        targetLinkSet = targetLinks.get(cardID);
                    } else {
                        targetLinkSet = new HashSet<>();
                        targetLinks.put(cardID, targetLinkSet);
                    }
                    Long textID = rs.getLong("TextGroup.TextGroupID");
                    MobileText mobileText;
                    if (!textSet.contains(textID)) {
                        mobileText = new MobileText();
                        mobileText.setTextGroupID(textID);
                        mobileText.setText(rs.getString("Text.Text"));
                        mobileText.setCardParameterTypeID(rs.getLong("TextCard.CardParameterTypeID"));
                        mobileCardInfo.getMobileTexts().add(mobileText);
                        textSet.add(textID);
                    }
                    Long sourceID = rs.getLong("SourceCard.CardToCardLinkID");
                    if (sourceID != 0 && !rs.wasNull()) {
                        MobileCardToCardLink mobileCardToCardLinkSource;
                        if (!sourceLinkSet.contains(sourceID)) {
                            mobileCardToCardLinkSource = new MobileCardToCardLink();
                            mobileCardToCardLinkSource.setLinkID(sourceID);
                            mobileCardToCardLinkSource.setCardID(rs.getString("SourceCard.TargetCardID"));
                            mobileCardToCardLinkSource.setLinkType(rs.getString("SourceCard.CardToCardLinkType"));
                            mobileCardInfo.getSourceLinks().add(mobileCardToCardLinkSource);
                            sourceLinkSet.add(sourceID);
                        }
                    }
                    Long targetID = rs.getLong("TargetCard.CardToCardLinkID");
                    if (targetID != 0 && !rs.wasNull()) {
                        MobileCardToCardLink mobileCardToCardLinkTarget;
                        if (!targetLinkSet.contains(targetID)) {
                            mobileCardToCardLinkTarget = new MobileCardToCardLink();
                            mobileCardToCardLinkTarget.setLinkID(targetID);
                            mobileCardToCardLinkTarget.setCardID(rs.getString("TargetCard.SourceCardID"));
                            mobileCardToCardLinkTarget.setLinkType(rs.getString("TargetCard.CardToCardLinkType"));
                            mobileCardInfo.getTargetLinks().add(mobileCardToCardLinkTarget);
                            targetLinkSet.add(targetID);
                        }
                    }
                    Long menuID = rs.getLong("Menu.MenuID");
                    if (menuID != 0 && !rs.wasNull()) {
                        mobileCardInfo.getMenuIDs().add(menuID);
                    }
                    Long tagID = rs.getLong("Tag.TagID");
                    if (tagID != 0 && !rs.wasNull()) {
                        mobileCardInfo.getTagIDs().add(tagID);
                    }
                    Long imageID = rs.getLong("Image.ImageID");
                    if (imageID != 0 && !rs.wasNull()) {
                        if (!imageSet.contains(imageID)) {
                            MobileCardImage mobileCardImage = new MobileCardImage();
                            mobileCardImage.setImageID(imageID);
                            mobileCardImage.setImageType(ImageType.parseInt(rs.getInt("CardImage.CardImageType")));
                            mobileCardImage.setImageHeight(rs.getInt("Image.ImageHeight"));
                            mobileCardImage.setImageSize(rs.getInt("Image.ImageFileSize"));
                            mobileCardImage.setImageWidth(rs.getInt("Image.ImageWidth"));
                            mobileCardInfo.getImages().add(mobileCardImage);
                            imageSet.add(imageID);
                        }
                    }
                    Long parameterID = rs.getLong("CardParameter.CardParameterID");
                    if (!rs.wasNull() && parameterID != 0) {
                        if (!parameterSet.contains(parameterID)) {
                            MobileParameter mobileParameter = new MobileParameter();
                            mobileParameter.setParameterID(parameterID);
                            mobileParameter.setParameterTypeID(rs.getLong("CardParameter.CardParameterTypeID"));
                            mobileParameter.setValue(rs.getString("CardParameter.CardParameterValue"));
                            mobileCardInfo.getMobileParameters().add(mobileParameter);
                            parameterSet.add(parameterID);
                        }
                    }
                    Long coordinateID = rs.getLong("CardCoordinate.CardCoordinateID");
                    if (coordinateID != 0 && !rs.wasNull()) {
                        MobileCoordinate mobileCoordinate = new MobileCoordinate();
                        mobileCoordinate.setLatitude(rs.getDouble("CardCoordinate.Latitude"));
                        mobileCoordinate.setLongitude(rs.getDouble("CardCoordinate.Longitude"));
                        mobileCardInfo.setCoordinate(mobileCoordinate);
                    }
                    Long priceID = rs.getLong("Price.PriceID");
                    if (priceID != 0 && !rs.wasNull()) {
                        mobileCardInfo.setPriceID(priceID);
                    }
                }
            }
        }
        return cardMap;
    }

    public static ArrayList<String> checkCardStatus(CardEntity cardEntity, CardState cardState) {
        ArrayList<String> errors = new ArrayList<String>();
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
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return 0L;
    }
}