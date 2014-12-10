package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardToCardLinkType;
import model.database.requests.CardRequest;
import model.database.requests.LinkRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardToCardLinkEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardToCardLinkServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCardToCardLinkServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long targetCardID = Long.parseLong(request.getParameter("targetCardID"));
            Long sourceCardID = Long.parseLong(request.getParameter("sourceCardID"));
            String from = request.getParameter("from");
            CardToCardLinkType cardToCardLinkType = CardToCardLinkType.parseInt(Integer.parseInt(request.getParameter("cardToCardLinkType")));
            if (!LinkRequest.isLinkExist(sourceCardID, targetCardID, cardToCardLinkType)) {
                CardEntity targetCard = CardRequest.getCardByID(targetCardID);
                CardEntity sourceCard = CardRequest.getCardByID(sourceCardID);
                CardToCardLinkEntity cardToCardLinkEntity = new CardToCardLinkEntity(sourceCard, targetCard, cardToCardLinkType);
                LinkRequest.addCardToCardLink(cardToCardLinkEntity);
                CardRequest.updateCard(targetCard);
                CardRequest.updateCard(sourceCard);
            }
            Long cardID;
            if (from.equals("target")) {
                cardID = sourceCardID;
            } else {
                cardID = targetCardID;
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
