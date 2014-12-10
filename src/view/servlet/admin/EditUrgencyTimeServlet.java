package view.servlet.admin;

import helper.TimeManager;
import model.additionalentity.admin.SimpleDate;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.TimeRequest;
import model.database.worldonlinedb.UrgencyTimeEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


public class EditUrgencyTimeServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteCardInfoServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            String cardIDString = request.getParameter("cardID");


            SimpleDate start = new SimpleDate(request, "start");
            SimpleDate end = new SimpleDate(request, "end");
//            Timestamp startTime = TimeManager.getTimestamp(start);
//            Timestamp endTime = TimeManager.getTimestamp(end);
            UrgencyTimeEntity urgencyTimeEntity = TimeRequest.getUrgencyTimeEntity(Long.parseLong(request.getParameter("urgencyTimeID")));
            urgencyTimeEntity.setOnTimeStamp(start.getTimestamp());
            urgencyTimeEntity.setOffTimeStamp(end.getTimestamp());
            TimeRequest.updateUrgencyTimeEntity(urgencyTimeEntity);
            CardRequest.updateCard(urgencyTimeEntity.getCard());
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardIDString, this, request, response);
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
