package helper;

import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.CardImageType;
import model.database.requests.ImageRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardImageEntity;
import model.database.worldonlinedb.ImageEntity;
import model.exception.DataIsEmptyException;
import model.logger.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageHelper {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ImageHelper.class);

    public static void saveImages(String imageNames, CardEntity card, CardImageType cardImageType) throws DataIsEmptyException {
        if (imageNames != null && imageNames.contains(";")) {
            String[] imageNameArray = imageNames.split(";");
            for (String imageName : imageNameArray) {
                saveImage(imageName, card, cardImageType);
            }
        }
    }

    public static void saveImage(String imageName, CardEntity card, CardImageType cardImageType) {
        String root = ServerConsts.oldImageRoot + imageName;
        try {
            if (imageName == null || imageName.replaceAll(" ", "").isEmpty() || imageName.equals("null")) {
                throw new DataIsEmptyException(imageName);
            }
            File imageFile = new File(root);
            if (!imageFile.exists()) {
                throw new DataIsEmptyException(imageName);
            }
            BufferedImage bimg = ImageIO.read(imageFile);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            long size = imageFile.length();
            String hash = Md5Hash.getMd5Hash(imageFile);
            ImageEntity imageEntity = ImageRequest.getImageByHash(hash);
            if (imageEntity == null) {
                FileHelper.copyFile(imageFile, imageName, ServerConsts.imageFolder + cardImageType);
                String path = ServerConsts.imageFolder + cardImageType.toString() + "/" + imageName;
                imageEntity = new ImageEntity(path, height, width, size, hash);
                ImageRequest.addImage(imageEntity);
            }
            CardImageEntity cardImageEntity = new CardImageEntity(card, imageEntity, cardImageType);
            cardImageEntity.setCardImageName(imageName);
            ImageRequest.addCardImage(cardImageEntity);
        } catch (DataIsEmptyException e) {
            loggerFactory.error("data is empty, image name is " + String.valueOf(e.getImageName()));
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public static void saveImage(File file, CardEntity card, CardImageType cardImageType) {
        try {
            if (file == null) {
                throw new DataIsEmptyException();
            }
            if (!file.exists()) {
                throw new DataIsEmptyException();
            }
            BufferedImage bimg = ImageIO.read(file);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            long size = file.length();
            String hash = Md5Hash.getMd5Hash(file);
            ImageEntity imageEntity = ImageRequest.getImageByHash(hash);
            if (imageEntity == null) {
                FileHelper.copyFile(file, file.getName(), ServerConsts.imageFolder + cardImageType);
                String path = ServerConsts.imageFolder + cardImageType.toString() + "/" + file.getName();
                imageEntity = new ImageEntity(path, height, width, size, hash);
                ImageRequest.addImage(imageEntity);
            }
            CardImageEntity cardImageEntity = new CardImageEntity(card, imageEntity, cardImageType);
            cardImageEntity.setCardImageName(file.getName());
            ImageRequest.addCardImage(cardImageEntity);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }


}
