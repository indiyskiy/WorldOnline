package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.ImageRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteCardImageServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteCardImageServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            if (cardEntity != null) {
                Long cardImageID = Long.parseLong(request.getParameter("cardImageID"));
                ImageRequest.deleteCardImage(cardImageID);
                CardRequest.updateCard(cardEntity);
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + request.getParameter("cardID"), this, request, response);
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
