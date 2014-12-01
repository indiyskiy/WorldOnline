package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.ImageRequest;
import model.database.requests.InfoRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardInformationElementEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardInfoElementServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteCardInfoElementServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardInfoID = Long.parseLong(request.getParameter("cardInfoID"));
            CardInformationElementEntity cardInformationElementEntity = InfoRequest.getCardInformationElement(cardInfoID);

            if (cardInformationElementEntity != null) {
                CardEntity cardEntity = cardInformationElementEntity.getCard();
                InfoRequest.deleteInfoElement(cardInfoID);
                CardRequest.updateCard(cardEntity);
                ServletHelper.sendForward("/completecardinfo?cardID=" + cardEntity.getCardID(), this, request, response);
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
