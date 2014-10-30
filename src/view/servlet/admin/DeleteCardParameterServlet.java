package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.worldonlinedb.CardParameterEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardParameterServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteCardParameterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardParameterID = Long.parseLong(request.getParameter("cardParameterID"));
            CardParameterEntity cardParameterEntity = ParameterRequest.getCardParameter(cardParameterID);
            if (cardParameterEntity != null) {
                ParameterRequest.deleteCardParameter(cardParameterEntity);
                CardRequest.updateCard(cardParameterEntity.getCard());
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
