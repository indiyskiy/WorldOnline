package view.servlet.admin;

import controller.parser.adminparser.CreateCardParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCardServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateCardServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            CreateCardParser createCardParser = new CreateCardParser();
            createCardParser.parse(request);

            CardEntity cardEntity = createCardParser.getCard();
            if (cardEntity != null) {
                boolean isLoaded = CardRequest.addCardSafe(cardEntity);
                request.setAttribute("isLoaded", isLoaded);
                if (isLoaded) {
                    request.setAttribute("cardID", cardEntity.getCardID());
                }
                request.setAttribute("cardTypes", CardType.values());
                ServletHelper.sendForward("/createcard.jsp", this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            request.setAttribute("cardTypes", CardType.values());
            ServletHelper.sendForward("/createcard.jsp", this, request, response);
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
        return "Создание карточки";
    }
}
