package controller.parser.adminparser;


import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ImageTagUploadParser {

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageTagUploadParser.class);

    private TagEntity tagEntity;
    private long tagID;
    HashMap<String, File> fileMap;

    public void parse(HttpServletRequest request) throws ServletException, IOException, FileUploadException, SQLException {
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();

        try {
            tagID = Long.parseLong(textMap.get("tagID"));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect tag id");
        }
        tagEntity = TagRequest.getTag(tagID);
        if (tagEntity == null) {
            loggerFactory.warning("tag null");
            throw new ServletException("tag not found");
        }
        if (fileMap.isEmpty()) {
            loggerFactory.warning("file null");
            throw new ServletException("image not found");
        }
    }

    public static LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public long getTagID() {
        return tagID;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }
}
