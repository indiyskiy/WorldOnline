package view.servlet.admin;

import helper.ServletHelper;
import helper.TimeManager;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.requests.TimeRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.UrgencyTimeEntity;
import model.logger.LoggerFactory;

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
            if (cardEntity != null && cardEntity.getCardType() == CardType.CardNews.getValue()) {
                UrgencyTimeEntity urgencyTimeEntity = new UrgencyTimeEntity();
                urgencyTimeEntity.setCard(cardEntity);
                if (TimeRequest.containsUrgencyTimeEntity(cardEntity)) {
                    UrgencyTimeEntity maxUrgencyTimeEntity = TimeRequest.getMaxUrgencyTime(cardEntity);
                    urgencyTimeEntity.setOnTimeStamp(maxUrgencyTimeEntity.getOnTimeStamp());
                    urgencyTimeEntity.setOffTimeStamp(maxUrgencyTimeEntity.getOffTimeStamp());
                } else {
                    urgencyTimeEntity.setOnTimeStamp(TimeManager.currentTime());
                    urgencyTimeEntity.setOffTimeStamp(TimeManager.currentTime());
                }
                TimeRequest.addUrgencyTimeEntity(urgencyTimeEntity);
                CardRequest.updateCard(cardEntity);
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
