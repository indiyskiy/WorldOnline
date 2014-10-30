package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.TimeRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UrgencyTimeEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUrgencyTimeServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddUrgencyTimeServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            if (cardEntity != null) {
                if (!TimeRequest.containsUrgencyTimeEntity(cardEntity)) {
                    UrgencyTimeEntity urgencyTimeEntity = new UrgencyTimeEntity();
                    urgencyTimeEntity.setCard(cardEntity);
                    TimeRequest.addUrgencyTimeEntity(urgencyTimeEntity);
                }
                ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
            } else {
                throw new ServletException("incorrect card ID");
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
