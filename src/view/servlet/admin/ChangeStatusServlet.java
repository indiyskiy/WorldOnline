package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Илья on 29.05.14.
 */
public class ChangeStatusServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ProtectedServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loggerFactory.debug("do post");
            long cardID;
            CardEntity cardEntity;
            try {
                cardID = Long.parseLong(request.getParameter("cardID"));
            } catch (Exception e) {
                throw new ServletException("incorrect card ID");
            }
            cardEntity = CardRequest.getCardByID(cardID);
            int stateID;
            try {
                stateID = Integer.parseInt(request.getParameter("state"));
            } catch (Exception e) {
                throw new ServletException("incorrect state id");
            }
            CardState cardState = CardState.parseInt(stateID);
            if (cardEntity == null) {
                throw new ServletException("incorrect card ID - card not found");
            }
            ArrayList<String> exceptions = CardRequest.checkCardStatus(cardEntity, cardState);
            if (exceptions.isEmpty()) {
                cardEntity.setCardState(cardState);
                CardRequest.updateCard(cardEntity);
            } else {
                ServletHelper.throwServletException(exceptions);
            }
            loggerFactory.debug("/do post");
            ServletHelper.sendForward("/completecardinfo?CardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loggerFactory.debug("do get");
            long cardID;
            try {
                cardID = Long.parseLong(request.getParameter("cardID"));
            } catch (Exception e) {
                throw new ServletException("incorrect card ID");
            }
            loggerFactory.debug("/do get");
            ServletHelper.sendForward("/completecardinfo.jsp?CardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }
}
