package model.database.requests;

import model.additionalentity.CompleteCardImageInfo;
import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.phone.MobileViewImage;
import model.constants.Component;
import model.constants.databaseenumeration.CardImageType;
import model.constants.databaseenumeration.CardState;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.ImageEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.intellij.lang.annotations.Language;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ImageRequest {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Database, ImageRequest.class);

    public static void addCardImage(CardImageEntity image) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(image);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void addImage(ImageEntity imageEntity) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(imageEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static ArrayList<ImageEntity> getAllImages() {
        ArrayList<ImageEntity> imageEntities = new ArrayList<ImageEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            imageEntities = (ArrayList<ImageEntity>) session.createCriteria(ImageEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return imageEntities;
    }

    public static ArrayList<CardImageEntity> getAllCardImages() {
        ArrayList<CardImageEntity> cardImageEntities = new ArrayList<CardImageEntity>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            cardImageEntities = (ArrayList<CardImageEntity>) session.createCriteria(CardImageEntity.class).list();
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cardImageEntities;
    }

    public static ImageEntity getImageByID(Long imageID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (ImageEntity) session.get(ImageEntity.class, imageID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardImageEntity getCardImageByID(Long cardImageID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
//        Session session = new HibernateUtil().getSessionFactory().openSession();
        try {
            return (CardImageEntity) session.get(CardImageEntity.class, cardImageID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static CardImageEntity getCardImageByResultSet(ResultSet rs) throws SQLException {
        return getCardImageByResultSet(rs, "CardImage");
    }

    public static CardImageEntity getCardImageByResultSet(ResultSet rs, String cardImage) throws SQLException {
        CardImageEntity cardImageEntity = null;
        Long cardImageID = rs.getLong(cardImage + ".CardImageID");
        if (cardImageID != 0 && !rs.wasNull()) {
            cardImageEntity = new CardImageEntity();
            cardImageEntity.setCardImageID(cardImageID);
            cardImageEntity.setCardImageName(rs.getString(cardImage + ".CardImageName"));
            cardImageEntity.setCardImageType(rs.getInt(cardImage + ".CardImageType"));
        }
        return cardImageEntity;
    }

    public static ImageEntity getImageFromResultSet(ResultSet rs) throws SQLException {
        return getImageFromResultSet(rs, "Image");
    }

    public static ImageEntity getImageFromResultSet(ResultSet rs, String image) throws SQLException {
        ImageEntity imageEntity = null;
        if (rs.getLong(image + ".ImageID") != 0 && !rs.wasNull()) {
            imageEntity = new ImageEntity();
            imageEntity.setImageFileSize(rs.getLong(image + ".ImageFileSize"));
            imageEntity.setImageHeight(rs.getInt(image + ".ImageHeight"));
            imageEntity.setImageID(rs.getLong(image + ".ImageID"));
            imageEntity.setImageMD5Hash(rs.getString(image + ".ImageMD5Hash"));
            imageEntity.setImageFilePath(rs.getString(image + ".ImageFilePath"));
            imageEntity.setImageWidth(rs.getInt(image + ".ImageWidth"));
        }
        return imageEntity;
    }

    public static HashMap<Long, CompleteCardImageInfo> getCompleteCardImages() {
        HashMap<Long, CompleteCardImageInfo> cardImages = new HashMap<Long, CompleteCardImageInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM CardImage " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup  ON (CardImage.ImageDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text ON (Text.TextGroupID=TextGroup.TextGroupID)";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardImageInfo cardImage;
                Long cardImageID = rs.getLong("CardImage.CardImageID");
                if (cardImageID != 0 && !rs.wasNull()) {
                    if (cardImages.containsKey(cardImageID) && cardImages.get(cardImageID) != null) {
                        cardImage = cardImages.get(cardImageID);
                    } else {
                        cardImage = new CompleteCardImageInfo(getCardImageByResultSet(rs));
                        cardImages.put(cardImageID, cardImage);
                    }
                    getCompleteCardImage(rs, cardImage, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardImages;
    }

    public static HashMap<Long, CompleteCardImageInfo> getCompleteCardImagesByCard(long cardID) {
        HashMap<Long, CompleteCardImageInfo> cardImages = new HashMap<Long, CompleteCardImageInfo>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM CardImage " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup  ON (CardImage.ImageDescriptionTextGroupID=TextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text  ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE CardImage.CardID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompleteCardImageInfo cardImage;
                Long cardImageID = rs.getLong("CardImage.CardImageID");
                if (cardImageID != 0 && !rs.wasNull()) {
                    if (cardImages.containsKey(cardImageID) && cardImages.get(cardImageID) != null) {
                        cardImage = cardImages.get(cardImageID);
                    } else {
                        cardImage = new CompleteCardImageInfo(getCardImageByResultSet(rs));
                        cardImages.put(cardImageID, cardImage);
                    }
                    getCompleteCardImage(rs, cardImage, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardImages;
    }

    public static CompleteCardImageInfo getCompleteCardImageByCardImage(long cardImageID) {
        CompleteCardImageInfo cardImage = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM CardImage " +
                    "LEFT OUTER JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                    "LEFT OUTER JOIN TextGroup  ON (CardImage.ImageDescriptionTextGroupID=CardImageTextGroup.TextGroupID) " +
                    "LEFT OUTER JOIN Text  ON (TextGroup.TextGroupID=Text.TextGroupID) " +
                    "WHERE CardImage.CardImageID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardImageID);
            rs = ps.executeQuery();
            if (rs.first()) {
                Long cardImageID2 = rs.getLong("CardImage.CardImageID");
                if (cardImageID2 != 0 && !rs.wasNull() && cardImageID == cardImageID2) {
                    cardImage = new CompleteCardImageInfo(getCardImageByResultSet(rs));
                    getCompleteCardImage(rs, cardImage, "TextGroup", "Text");
                }
            }
            while (rs.next()) {
                Long cardImageID2 = rs.getLong("CardImage.CardImageID");
                if (cardImageID2 != 0 && !rs.wasNull() && cardImageID == cardImageID2) {
                    getCompleteCardImage(rs, cardImage, "TextGroup", "Text");
                }
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return cardImage;
    }

    public static void getCompleteCardImage(ResultSet rs, CompleteCardImageInfo cardImage, String textGroup, String text) throws SQLException {
        if (cardImage.getCardImageEntity().getImage() == null) {
            //image
            Long imageID = rs.getLong("Image.ImageID");
            if (rs.getLong("Image.ImageID") != 0 && !rs.wasNull()) {
                if (imageID != 0 && !rs.wasNull()) {
                    ImageEntity imageEntity = ImageRequest.getImageFromResultSet(rs);
                    cardImage.getCardImageEntity().setImage(imageEntity);
                }
            }
        }
        //card image text group
        Long imageTextGroupID = rs.getLong(textGroup + ".TextGroupID");
        if (imageTextGroupID != 0 && !rs.wasNull()) {
            CompleteTextGroupInfo completeTextGroupInfo;
            if (cardImage.getCompleteTextGroupInfo() == null) {
                TextGroupEntity textGroupEntity = TextRequest.getTextGroupByResultSet(rs, textGroup);
                completeTextGroupInfo = new CompleteTextGroupInfo(textGroupEntity);
                cardImage.setCompleteTextGroupInfo(completeTextGroupInfo);
                cardImage.getCardImageEntity().setImageDescriptionTextGroup(textGroupEntity);
            } else {
                completeTextGroupInfo = cardImage.getCompleteTextGroupInfo();
            }
            TextRequest.getCompleteTextGroupInfo(rs, completeTextGroupInfo, text);
        }
    }

    public static ImageEntity getImageByHash(String hash) {
        ImageEntity image = null;
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT * FROM Image " +
                    "WHERE Image.ImageMD5Hash=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, hash);
            rs = ps.executeQuery();
            if (rs.first()) {
                image = getImageFromResultSet(rs);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return image;
    }

    public static File getImage(long imageID) {
        ImageEntity imageEntity = getImageByID(imageID);
        if (imageEntity != null) {
            return getFile(imageEntity.getImageFilePath());
        }
        return null;
    }

    private static File getFile(String imageFilePath) {
        return new File(imageFilePath);
    }

    public static LinkedList<MobileViewImage> getViewCardImages() {
        LinkedList<MobileViewImage> mobileViewImages = new LinkedList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Card.CardID, Image.ImageID " +
                    "FROM Image " +
                    "JOIN CardImage ON (Image.ImageID=CardImage.ImageID) " +
                    "JOIN Card ON (CardImage.CardID = Card.CardID) " +
                    "WHERE Card.CardState IN (" +
                    CardState.Active.getValue() +
                    ") AND CardImage.CardImageType=" + CardImageType.ViewImage.getValue();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileViewImage mobileViewImage = new MobileViewImage();
                mobileViewImage.setCardID(rs.getLong("Card.CardID"));
                mobileViewImage.setImageID(rs.getLong("Image.ImageID"));
                mobileViewImages.add(mobileViewImage);
            }
        } catch (SQLException e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileViewImages;
    }

    public static ImageEntity getImageByResultSet(ResultSet rs, String cardParameterType) throws SQLException {
        Long imageID = rs.getLong(cardParameterType + ".ImageID");
        if (imageID != 0 && !rs.wasNull()) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageID(imageID);
            imageEntity.setImageFilePath(rs.getString(cardParameterType + ".ImageFilePath"));
            imageEntity.setImageFileSize(rs.getLong(cardParameterType + ".ImageFileSize"));
            imageEntity.setImageHeight(rs.getInt(cardParameterType + ".ImageHeight"));
            imageEntity.setImageMD5Hash(rs.getString(cardParameterType + ".ImageMD5Hash"));
            imageEntity.setImageWidth(rs.getInt(cardParameterType + ".ImageWidth"));
        }
        return null;
    }
}
