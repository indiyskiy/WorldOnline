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
        CardImageEntity cardImageEntity = null;
        Long cardImageID = rs.getLong("CardImage.CardImageID");
        if (cardImageID != 0 && !rs.wasNull()) {
            cardImageEntity = new CardImageEntity();
            cardImageEntity.setCardImageID(cardImageID);
            cardImageEntity.setCardImageName(rs.getString("CardImage.CardImageName"));
            cardImageEntity.setCardImageType(rs.getInt("CardImage.CardImageType"));
        }
        return cardImageEntity;
    }

    public static ImageEntity getImageFromResultSet(ResultSet rs) throws SQLException{
        ImageEntity imageEntity=null;
        if(rs.getLong("Image.ImageID")!=0 && !rs.wasNull()){
            imageEntity=new ImageEntity();
            imageEntity.setImageFileSize(rs.getInt("Image.ImageFileSize"));
            imageEntity.setImageHeight(rs.getInt("Image.ImageHeight"));
            imageEntity.setImageID(rs.getLong("Image.ImageID"));
            imageEntity.setImageMD5Hash(rs.getString("Image.ImageMD5Hash"));
            imageEntity.setImageURL(rs.getString("Image.ImageURL"));
            imageEntity.setImageWidth(rs.getInt("Image.Width"));
        }
        return imageEntity;
    }
}
