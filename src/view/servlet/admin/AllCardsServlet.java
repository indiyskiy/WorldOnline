package view.servlet.admin;

import controller.parser.edit.adminparser.AllCardParser;
import model.constants.Component;
import model.constants.databaseenumeration.CardType;
import model.database.requests.CardRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 29.11.13
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */
public class AllCardsServlet extends HttpServlet {
    private LoggerFactory loggerFactory=new LoggerFactory(Component.Admin,AllCardsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        AllCardParser parser=new AllCardParser();
        parser.parse(request);
        try {
            ServletHelper.setUTF8(request, response);
            ArrayList<CardEntity> cardEntities;
            if (!parser.haveMatter()) {
               cardEntities = CardRequest.getAllCards();
            } else {
                cardEntities = CardRequest.getAllCards(parser);
            }
            request.setAttribute("cardList", cardEntities);
            request.setAttribute("cardTypes",CardType.values());
            ServletHelper.sendForward("/allcards.jsp", this, request, response);
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }
}