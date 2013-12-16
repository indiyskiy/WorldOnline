package view.servlet;

import model.additionalentity.CompleteCardInfo;
import model.additionalentity.CompleteCardTagInfo;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardType;
import model.constants.databaseenumeration.TagType;
import model.constants.databaseenumeration.TextType;
import model.database.requests.CardRequest;
import model.database.requests.TagRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 29.11.13
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public class CompleteCardInfoServlet extends HttpServlet {

    public static void main(String[] args) {
        CompleteCardInfo completeCardInfo = null;
        HashMap<Long, CompleteCardTagInfo> cardTags = TagRequest.getCompleteCardTags();
//        HashMap<Long, CompleteCardTagInfo> cardTags = CardRequest.getCompleteCardInfo();
        for (CompleteCardTagInfo tag : cardTags.values()) {
//            if (tag.getCompleteTagInfo().getTagEntity().getTagType() == TagType.Cuisine.getValue()) {
            if (tag.getCompleteTagInfo().getTagEntity().getTagType() == TagType.Ribbons.getValue()) {
                System.out.println(tag.getCardTagEntity().getCard().getCardID());
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("cardTypes", CardType.values());
            request.setAttribute("cardStates", CardState.values());
            request.setAttribute("textTypes", TextType.values());
            request.setAttribute("tagTypes", TagType.values());
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
                }
                ServletHelper.sendForward("/completecardinfo.jsp?CardID=" + cardID, this, request, response);
            }
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}