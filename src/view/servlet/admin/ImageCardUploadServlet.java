package view.servlet.admin;

import controller.parser.adminparser.ImageCardUploadParser;
import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardImageType;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ImageCardUploadServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageCardUploadServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ImageCardUploadParser imageCardUploadParser = new ImageCardUploadParser();
            imageCardUploadParser.parse(request);

            CardEntity cardEntity = imageCardUploadParser.getCardEntity();
            CardImageType cardImageType = imageCardUploadParser.getCardImageType();
            boolean isLoaded = false;
            for (File file : imageCardUploadParser.getFileMap().values()) {
                ImageHelper.saveImage(file, cardEntity, cardImageType);
                isLoaded = true;
            }
            long cardID = imageCardUploadParser.getCardID();
            request.setAttribute("isLoaded", isLoaded);
            request.setAttribute("cardID", cardID);
            request.setAttribute("cardImageTypes", CardImageType.values());
            ServletHelper.sendForward("/imagecardupload.jsp?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            long cardID;
            request.setAttribute("cardImageTypes", CardImageType.values());
            try {
                cardID = Long.parseLong(request.getParameter("cardID"));
                request.setAttribute("cardID", cardID);
                ServletHelper.sendForward("/imagecardupload.jsp?cardID=" + cardID, this, request, response);
            } catch (Exception e) {
                throw new ServletException("incorrect card id");
            }

        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

}
