package controller.parser.adminparser;

import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.database.requests.ParameterRequest;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;

public class ImageCardParameterTypeUploadParser {
    private CardParameterTypeEntity cardParameterTypeEntity;
    private HashMap<String, File> fileMap = new HashMap<>();
    private long cardParameterTypeID;
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageCardParameterTypeUploadParser.class);

    public void parse(HttpServletRequest request) throws ServletException, FileUploadException {
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();
        try {
            cardParameterTypeID = Long.parseLong(textMap.get("cardParameterTypeID"));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect parameter type ID");
        }

        cardParameterTypeEntity = ParameterRequest.getCardParameterType(cardParameterTypeID);
        if (cardParameterTypeEntity == null) {
            loggerFactory.warning("cardParameterType is null");
            throw new ServletException("card Parameter Type not found");
        }
        if (fileMap.isEmpty()) {
            loggerFactory.warning("file null");
            throw new ServletException("image not found");
        }
    }

    public CardParameterTypeEntity getCardParameterTypeEntity() {
        return cardParameterTypeEntity;
    }

    public void setCardParameterTypeEntity(CardParameterTypeEntity cardParameterTypeEntity) {
        this.cardParameterTypeEntity = cardParameterTypeEntity;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }

    public long getCardParameterTypeID() {
        return cardParameterTypeID;
    }

    public void setCardParameterTypeID(long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }
}
