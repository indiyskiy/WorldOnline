package model.database.requests;

import model.additionalentity.admin.CardImage;
import model.additionalentity.admin.CompleteCardInfo;
import model.additionalentity.phone.MobileCardImage;
import model.additionalentity.phone.MobileCardImagePair;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.ImageType;
import model.database.session.DatabaseConnection;
import model.database.session.HibernateUtil;
import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.ImageEntity;
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
        try {
            session.beginTransaction();
            session.save(imageEntity);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            loggerFactory.error(e);
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

    public static ImageEntity getImageByHash(String hash) {
        ImageEntity image = null;
        DatabaseConnection dbConnection = new DatabaseConnection("getImageByHash");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT Image.ImageID FROM Image " +
                    "WHERE Image.ImageMD5Hash=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, hash);
            rs = ps.executeQuery();
            if (rs.first()) {
                image = getImage(rs.getLong("Image.ImageID"));
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return image;
    }

    public static File getImageFile(long imageID) {
        ImageEntity imageEntity = getImageByID(imageID);
        if (imageEntity != null) {
            return getFile(imageEntity.getImageFilePath());
        }
        return null;
    }

    private static File getFile(String imageFilePath) {
        return new File(imageFilePath);
    }

    public static LinkedList<MobileCardImagePair> getCardImages(ImageType imageType) {
        LinkedList<MobileCardImagePair> mobileCardImagePairs = new LinkedList<>();
        DatabaseConnection dbConnection = new DatabaseConnection("getCardImages");
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
                    ") AND CardImage.CardImageType=" + imageType.getValue();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardImagePair mobileCardImagePair = new MobileCardImagePair();
                mobileCardImagePair.setCardID(rs.getLong("Card.CardID"));
                mobileCardImagePair.setImageID(rs.getLong("Image.ImageID"));
                mobileCardImagePairs.add(mobileCardImagePair);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        return mobileCardImagePairs;
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

    public static void setCardImages(CompleteCardInfo card, long cardID) {
        DatabaseConnection dbConnection = new DatabaseConnection("setCardImages");
        ArrayList<CardImage> cardImages = new ArrayList<>();
        Connection connection;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sqlString =
                    "SELECT Image.ImageID," +
                            "CardImage.CardImageType, " +
                            "CardImage.CardImageName, " +
                            "CardImage.CardImageID " +
                            "FROM Card " +
                            "JOIN CardImage ON (Card.CardID=CardImage.CardID) " +
                            "JOIN Image ON (Image.ImageID=CardImage.ImageID) " +
                            "WHERE Card.CardID=? ORDER BY CardImage.CardImageType";
            ps = connection.prepareStatement(sqlString);
            ps.setLong(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CardImage cardImage = new CardImage();
                cardImage.setImageID(rs.getLong("Image.ImageID"));
                cardImage.setCardImageID(rs.getLong("CardImage.CardImageID"));
                cardImage.setImageType(ImageType.parseInt(rs.getInt("CardImage.CardImageType")));
                cardImage.setImageName(rs.getString("CardImage.CardImageName"));
                cardImages.add(cardImage);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
        card.setCardImages(cardImages);
    }

    public static ImageEntity getImage(Long imageID) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        try {
            return (ImageEntity) session.get(ImageEntity.class, imageID);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static void deleteCardImage(Long cardImageID) {
        DatabaseConnection dbConnection = new DatabaseConnection("deleteCardImage");
        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "DELETE FROM CardImage WHERE CardImage.CardImageID=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, cardImageID);
            ps.executeUpdate();
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, null);
        }
    }

    public static void setMobileImages(HashMap<Long, MobileCardInfo> mobileCardInfoHashMap, String cardIDs) {
        DatabaseConnection dbConnection = new DatabaseConnection("setMobileImages");
        Connection connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            @Language("MySQL") String sql = "SELECT " +
                    "CardImage.ImageID, " +
                    "CardImage.CardImageType, " +
                    "CardImage.CardID " +
                    "FROM CardImage " +
                    "WHERE CardImage.CardID IN (" + cardIDs + ")";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MobileCardInfo mobileCardInfo = mobileCardInfoHashMap.get(rs.getLong("CardImage.CardID"));
                MobileCardImage mobileCardImage = new MobileCardImage();
//                mobileCardImage.setImageSize();
//                mobileCardImage.setImageHeight();
                mobileCardImage.setImageType(ImageType.parseInt(rs.getInt("CardImage.CardImageType")));
                mobileCardImage.setImageID(rs.getLong("CardImage.ImageID"));
//                mobileCardImage.setImageWidth();
                mobileCardInfo.getImages().add(mobileCardImage);
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            dbConnection.closeConnections(ps, rs);
        }
    }

}
