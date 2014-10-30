package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.LinkRequest;
import model.database.worldonlinedb.CardToCardLinkEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardToCardLinkServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteCardToCardLinkServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            Long cardToCardLinkID = Long.parseLong(request.getParameter("cardToCardLinkID"));
            CardToCardLinkEntity cardToCardLinkEntity = LinkRequest.getCardToCardLinkByID(cardToCardLinkID);
            CardRequest.updateCard(cardToCardLinkEntity.getSourceCard());
            CardRequest.updateCard(cardToCardLinkEntity.getTargetCard());
            LinkRequest.deleteCardToCardLink(cardToCardLinkID);
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
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
