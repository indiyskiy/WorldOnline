package model.database.requests;

import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.ImageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 05.11.13
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */
public class ImageRequest {
    public static CardImageEntity gerCardImageByResultSet(ResultSet rs) throws SQLException {
        return gerCardImageByResultSet(rs, "CardImage");
    }

    public static CardImageEntity gerCardImageByResultSet(ResultSet rs, String cardImage) throws SQLException {
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
        return getImageFromResultSet(rs,"Image");
    }

    public static ImageEntity getImageFromResultSet(ResultSet rs, String image) throws SQLException {
        ImageEntity imageEntity = null;
        if (rs.getLong(image + ".ImageID") != 0 && !rs.wasNull()) {
            imageEntity = new ImageEntity();
            imageEntity.setImageFileSize(rs.getInt(image + ".ImageFileSize"));
            imageEntity.setImageHeight(rs.getInt(image + ".ImageHeight"));
            imageEntity.setImageID(rs.getLong(image + ".ImageID"));
            imageEntity.setImageMD5Hash(rs.getString(image + ".ImageMD5Hash"));
            imageEntity.setImageURL(rs.getString(image + ".ImageURL"));
            imageEntity.setImageWidth(rs.getInt(image + ".Width"));
        }
        return imageEntity;
    }
}
