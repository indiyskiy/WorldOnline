package view.servlet.admin;

import model.additionalentity.CompleteCardInfo;
import model.constants.Component;
import model.constants.databaseenumeration.*;
import model.database.requests.CardRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 29.11.13
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public class CompleteCardInfoServlet extends HttpServlet {
LoggerFactory loggerFactory=new LoggerFactory(Component.Admin,CompleteCardInfoServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("cardTypes", CardType.values());
            request.setAttribute("cardStates", CardState.values());
            request.setAttribute("textTypes", TextType.values());
            request.setAttribute("tagTypes", TagType.values());
            request.setAttribute("cardToCardLinkTypes", CardToCardLinkType.values());
            request.setAttribute("imageTypes",ImageType.values());
            String cardIDString = request.getParameter("CardID");
            if (cardIDString != null) {
                int cardID = Integer.parseInt(cardIDString);
                CompleteCardInfo completeCardInfo = CardRequest.getCompleteCardInfo(cardID);
                if (completeCardInfo != null) {
                    if (completeCardInfo.getCardEntity() != null) {
                        request.setAttribute("card", completeCardInfo.getCardEntity());
                    }
                    if (completeCardInfo.getCardCoordinateEntity() != null) {
                        request.setAttribute("cardCoordinate", completeCardInfo.getCardCoordinateEntity());
                    }
                    if (completeCardInfo.getCompleteTextCardInfoMap() != null && !completeCardInfo.getCompleteTextCardInfoMap().isEmpty()) {
                        request.setAttribute("cardTexts", completeCardInfo.getCompleteTextCardInfoMap().values());
                    }
                    if (completeCardInfo.getCompleteCardTagInfoMap() != null && !completeCardInfo.getCompleteCardTagInfoMap().isEmpty()) {
                        request.setAttribute("tags", completeCardInfo.getCompleteCardTagInfoMap().values());
                    }
                    if (completeCardInfo.getCardToCardLinkEntityMap() != null && !completeCardInfo.getCardToCardLinkEntityMap().isEmpty()) {
                        request.setAttribute("links", completeCardInfo.getCardToCardLinkEntityMap().values());
                    }
                    if (completeCardInfo.getCardToCardLinkedOnEntityMap() != null && !completeCardInfo.getCardToCardLinkedOnEntityMap().isEmpty()) {
                        request.setAttribute("linkedOn", completeCardInfo.getCardToCardLinkedOnEntityMap().values());
                    }
                    if (completeCardInfo.getCompleteCardImageInfoMap()!= null && !completeCardInfo.getCompleteCardImageInfoMap().isEmpty()) {
                        request.setAttribute("images", completeCardInfo.getCompleteCardImageInfoMap().values());
                    }
                }
                ServletHelper.sendForward("/completecardinfo.jsp?CardID=" + cardID, this, request, response);
            }
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            loggerFactory.error(e.toString());
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}