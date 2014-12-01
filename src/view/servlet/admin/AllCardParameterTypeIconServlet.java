package view.servlet.admin;

import controller.parser.adminparser.ImageCardParameterTypeUploadParser;
import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.ImageType;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class AllCardParameterTypeIconServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllCardParameterTypeIconServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ImageCardParameterTypeUploadParser imageCardUploadParser = new ImageCardParameterTypeUploadParser();
            imageCardUploadParser.parse(request);

            CardParameterTypeEntity cardParameterTypeEntity = imageCardUploadParser.getCardParameterTypeEntity();
            boolean isLoaded = false;
            for (File file : imageCardUploadParser.getFileMap().values()) {
                ImageHelper.saveParameterIcon(file, cardParameterTypeEntity);
                isLoaded = true;
            }
            if (isLoaded) {
                UserDataRequest.updateParameterType();
            }
            long cardParameterTypeID = imageCardUploadParser.getCardParameterTypeID();
            request.setAttribute("isLoaded", isLoaded);
            request.setAttribute("cardID", cardParameterTypeID);
            request.setAttribute("cardImageTypes", ImageType.values());
            ServletHelper.sendForward("/completecardparametertypeinfo?cardParameterTypeID=" + cardParameterTypeID, this, request, response);
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
