package controller.parser.adminparser;

import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.constants.databaseenumeration.ImageType;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageCardUploadParser {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageCardUploadParser.class);

    private ImageType imageType;
    private CardEntity cardEntity;
    private long cardID;
    HashMap<String, File> fileMap;

    public void parse(HttpServletRequest request) throws ServletException, IOException, FileUploadException {
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();
        try {
            cardID = Long.parseLong(textMap.get("cardID"));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect card id");
        }
        try {
            imageType = ImageType.parseInt(Integer.parseInt(textMap.get("cardImageType")));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect card image type");
        }
        if (imageType == null) {
            loggerFactory.warning("type null");
            throw new ServletException("incorrect card image type");
        }
        cardEntity = CardRequest.getCardByID(cardID);
        if (cardEntity == null) {
            loggerFactory.warning("card null");
            throw new ServletException("card not found");
        }
        if (fileMap.isEmpty()) {
            loggerFactory.warning("file null");
            throw new ServletException("image not found");
        }
    }

    public ImageType getImageType() {
        return imageType;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public long getCardID() {
        return cardID;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }
}
