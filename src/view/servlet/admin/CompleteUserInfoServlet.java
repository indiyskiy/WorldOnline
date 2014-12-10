package view.servlet.admin;

import helper.ServletHelper;
import model.additionalentity.admin.CompleteUserCardInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.UserCardState;
import model.database.requests.UserDataRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;
import model.textparser.RequestParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteUserInfoServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteUserInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            String userIDValue = request.getParameter("userID");
            String additionalInformation = request.getParameter("additionalInformation");
            if (userIDValue != null && !userIDValue.isEmpty() && additionalInformation != null) {
                Long userID = Long.parseLong(userIDValue);
                UserEntity user = UserRequests.getUserByID(userID);
                additionalInformation = RequestParser.shieldText(additionalInformation);
                user.getUserPersonalData().setAdditionalInformation(additionalInformation);
                UserRequests.editUser(user);
            }
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
            CompleteUserCardInfo completeUserCardInfo = UserDataRequest.getCompleteUserCardInfo(userID);
            UserDataRequest.getCompleteUserCardInfo(userID, completeUserCardInfo);
            request.setAttribute("completeUserCardInfo", completeUserCardInfo);
            request.setAttribute("userCardStates", UserCardState.values());
            request.setAttribute("title", cutTitle("Пользователь ID[" + userID + "]"));
            ServletHelper.sendForward("/completeuserinfo.jsp?o?userID=437", this, request, response);
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


