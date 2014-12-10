package view.servlet.admin;

import controller.parser.adminparser.CreateCardParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class CreateCardServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateCardServlet.class);
    private static final Random rnd = new Random(System.currentTimeMillis());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            CreateCardParser createCardParser = new CreateCardParser();
            createCardParser.parse(request);

            CardEntity cardEntity = createCardParser.getCard();
            if (cardEntity != null) {
                boolean isLoaded = CardRequest.addCardSafe(cardEntity);
                MenuEntity menuEntity = createCardParser.getMenuEntity();
                if (menuEntity != null) {
                    MenuRequest.addMenuCardLink(menuEntity, cardEntity);
                }
                request.setAttribute("isLoaded", isLoaded);
                if (isLoaded) {
                    request.setAttribute("cardID", cardEntity.getCardID());
                    //5 imgs in card  upload folder
                    request.setAttribute("imgID", rnd.nextInt(5) + 1);
                } else {
                    //5 imgs in card not uploaded folder
                    request.setAttribute("imgID", rnd.nextInt(5) + 1);
                }
                request.setAttribute("cardTypes", CardType.values());
                ServletHelper.sendForward("/createcard.jsp", this, request, response);
            } else {
                throw new ServletException("new card entity is null");
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            request.setAttribute("cardTypes", CardType.values());
            request.setAttribute("hidden", ServletHelper.saveAllParamsToHiddenInputType(request));
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
