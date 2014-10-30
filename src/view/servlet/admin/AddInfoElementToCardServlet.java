package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.InfoRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.CardInformationElementEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddInfoElementToCardServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddInfoElementToCardServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            CardEntity cardEntity = CardRequest.getCardByID(cardID);
            Integer counter = InfoRequest.countInfoElements(cardID) + 1;
            CardInformationElementEntity cardInformationElementEntity = new CardInformationElementEntity();
            cardInformationElementEntity.setCard(cardEntity);
            cardInformationElementEntity.setTextGroup(new TextGroupEntity("infoElementTextGroup" + cardID + "_" + counter));
            cardInformationElementEntity.setPosition(counter);
            InfoRequest.addCardInfoElement(cardInformationElementEntity);
            CardRequest.updateCard(cardEntity);
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
