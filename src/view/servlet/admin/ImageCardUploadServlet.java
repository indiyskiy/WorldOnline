package view.servlet.admin;

import controller.parser.adminparser.ImageCardUploadParser;
import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.ImageType;
import model.database.requests.CardRequest;
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
            ImageType imageType = imageCardUploadParser.getImageType();
            boolean isLoaded = false;
            for (File file : imageCardUploadParser.getFileMap().values()) {
                ImageHelper.saveImage(file, cardEntity, imageType);
                isLoaded = true;
            }
            if (isLoaded) {
                CardRequest.updateCard(cardEntity);
            }
            long cardID = imageCardUploadParser.getCardID();
            request.setAttribute("isLoaded", isLoaded);
            request.setAttribute("cardID", cardID);
            request.setAttribute("cardImageTypes", ImageType.values());
            request.setAttribute("title", cutTitle("Загрузка картинки для карточки[" +
                    cardID +
                    "]" +
                    cardEntity.getCardName()));
            ServletHelper.sendForward("/imagecardupload.jsp?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            long cardID;
            request.setAttribute("cardImageTypes", ImageType.cardTypes());
            try {
                cardID = Long.parseLong(request.getParameter("cardID"));
                CardEntity cardEntity = CardRequest.getCardByID(cardID);
                if (cardEntity != null) {
                    request.setAttribute("cardID", cardID);
                    request.setAttribute("title", cutTitle("Загрузка картинки для карточки[" +
                            cardID +
                            "]" +
                            cardEntity.getCardName()));
                    ServletHelper.sendForward("/imagecardupload.jsp?cardID=" + cardID, this, request, response);
                }
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

    @Override
    public String getTitle() {
        return null;
    }

}
