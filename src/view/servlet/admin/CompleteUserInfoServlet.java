package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;
import model.textparser.RequestParser;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Илья on 18.03.14.
 */
public class CompleteUserInfoServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteUserInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            String userIDValue = request.getParameter("userID");
            Long userID = Long.parseLong(userIDValue);
            UserEntity user = UserRequests.getUserByID(userID);
            String addInformation = request.getParameter("additionalInformation");
            addInformation = RequestParser.shieldText(addInformation);
            user.getUserPersonalData().setAdditionalInformation(addInformation);
            UserRequests.editUser(user);
            doGet(request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            String userIDValue = request.getParameter("userID");
            Long userID = Long.parseLong(userIDValue);
            UserEntity user = UserRequests.getUserByID(userID);
            request.setAttribute("user", user);
            request.setAttribute("mobilePlatforms", MobilePlatform.values());
            request.setAttribute("languages", LanguageType.values());
            ServletHelper.sendForward("/completeuserinfo.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }
}


