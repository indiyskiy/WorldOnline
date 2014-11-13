package controller.parser.adminparser;

import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.database.requests.InfoRequest;
import model.database.worldonlinedb.CardInformationElementEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;

public class CardInfoImageUploadParser {


    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CardInfoImageUploadParser.class);

    private CardInformationElementEntity cardInformationElementEntity;
    HashMap<String, File> fileMap;


    public void parse(HttpServletRequest request) throws FileUploadException, ServletException {
//        loggerFactory.debug("CardInfoImageUploadParser start");
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();
        Long infoID;
        try {
            infoID = Long.parseLong(textMap.get("informationElementID"));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect info id");
        }
        cardInformationElementEntity = InfoRequest.getCardInformationElement(infoID);
        if (cardInformationElementEntity == null) {
            loggerFactory.warning("cardInformationElement null");
            throw new ServletException("card information element not found");
        }
        if (fileMap.isEmpty()) {
            loggerFactory.warning("file null");
            throw new ServletException("image not found");
        }
//        loggerFactory.debug("CardInfoImageUploadParser end");
    }

    public CardInformationElementEntity getCardInformationElementEntity() {
        return cardInformationElementEntity;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }


}
