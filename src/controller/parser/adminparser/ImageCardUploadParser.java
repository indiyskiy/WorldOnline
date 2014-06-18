package controller.parser.adminparser;

import helper.FileHelper;
import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.constants.databaseenumeration.CardImageType;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Илья on 27.05.14.
 */
public class ImageCardUploadParser {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageCardUploadParser.class);

    private CardImageType cardImageType;
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
            cardImageType = CardImageType.parseInt(Integer.parseInt(textMap.get("cardImageType")));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect card image type");
        }
        if (cardImageType == null) {
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

    public CardImageType getCardImageType() {
        return cardImageType;
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
