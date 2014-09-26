package view.servlet.admin;

import model.additionalentity.admin.ParsedRequest;
import model.constants.Component;
import model.constants.databaseenumeration.ImageType;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import org.apache.commons.fileupload.FileUploadException;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageMenuUploadParser {

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageMenuUploadParser.class);

    private MenuEntity menuEntity;
    private long menuID;
    HashMap<String, File> fileMap;

    public void parse(HttpServletRequest request) throws ServletException, IOException, FileUploadException {
        ParsedRequest parsedRequest = ServletHelper.getParametersMap(request);
        HashMap<String, String> textMap = parsedRequest.getTextMap();
        fileMap = parsedRequest.getFileMap();

        try {
            menuID = Long.parseLong(textMap.get("menuID"));
        } catch (Exception e) {
            loggerFactory.warning(e);
            throw new ServletException("incorrect menu id");
        }
        menuEntity = MenuRequest.getMenu(menuID);
        if (menuEntity == null) {
            loggerFactory.warning("menu null");
            throw new ServletException("menu not found");
        }
        if (fileMap.isEmpty()) {
            loggerFactory.warning("file null");
            throw new ServletException("image not found");
        }
    }

    public MenuEntity getMenuEntity() {
        return menuEntity;
    }

    public long getMenuID() {
        return menuID;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }
}
