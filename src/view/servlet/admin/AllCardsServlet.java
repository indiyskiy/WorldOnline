package view.servlet.admin;

import controller.parser.adminparser.AllCardParser;
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
import java.util.ArrayList;


public class AllCardsServlet extends ProtectedServlet {
    private final int MAX_ITEMS = 50;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllCardsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        AllCardParser parser = new AllCardParser(MAX_ITEMS);
        parser.parse(request);
        try {
            ServletHelper.setUTF8(request, response);
            ArrayList<CardEntity> cardEntities;
            long pages;
            if (!parser.haveMatter()) {
                cardEntities = CardRequest.getAllCards(parser.getFirstElem(), MAX_ITEMS);
                long results = CardRequest.countCard();
                pages = (results / MAX_ITEMS);
            } else {
                cardEntities = CardRequest.getAllCards(parser);
                Long results = CardRequest.countCard(parser);
                pages = (results / MAX_ITEMS);
            }
            request.setAttribute("pages", pages);
            request.setAttribute("cardList", cardEntities);
            request.setAttribute("cardTypes", CardType.values());
            ServletHelper.sendForward("/allcards.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }
}