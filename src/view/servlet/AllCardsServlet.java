package view.servlet;

import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.TagType;
import model.database.requests.CardRequest;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.TagEntity;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            if (request.getParameter("CardType") == null || request.getParameter("CardType").isEmpty()) {
                ArrayList<CardEntity> cardEntities = CardRequest.getAllCards();
                request.setAttribute("cardList", cardEntities);
            } else {
                int type = Integer.parseInt(request.getParameter("CardType"));
                CardType cardType = CardType.parseInt(type);
                ArrayList<CardEntity> cardEntities = CardRequest.getAllCards(cardType);
                request.setAttribute("cardList", cardEntities);
            }
            request.setAttribute("cardTypes",CardType.values());
            ServletHelper.sendForward("/allcards.jsp", this, request, response);
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            throw new ServletException(e);
        }
    }
}