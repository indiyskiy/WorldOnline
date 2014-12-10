package view.servlet.admin;

import helper.ServletHelper;
import model.additionalentity.admin.SimpleCardParameterType;
import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.CardEntity;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddCardParameterNewServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCardParameterNewServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> m = request.getParameterMap();
            Set<Map.Entry<String, String[]>> s = m.entrySet();
            long cardID = 0L;
            CardEntity cardEntity = null;
            HashSet<Long> parameterIDsFromRequest = new HashSet<>();
            for (Map.Entry<String, String[]> entry : s) {
                if ("cardID".equals(entry.getKey())) {
                    cardID = Long.parseLong(entry.getValue()[0]);
                    cardEntity = CardRequest.getCardByID(cardID);
                }
                if ("cardParameterType".equals(entry.getKey())) {
                    for (String parameterIDString : entry.getValue()) {
                        Long parameterID = Long.parseLong(parameterIDString);
                        parameterIDsFromRequest.add(parameterID);
                    }
                }
            }
            if (cardEntity != null) {
                HashSet<SimpleCardParameterType> simpleCardParameterTypes =
                        ParameterRequest.getSimpleCardParameterTypes(parameterIDsFromRequest);
                HashSet<Long> cardTextIDs = new HashSet<>();
                HashSet<Long> cardParameterIDs = new HashSet<>();
                for (SimpleCardParameterType simpleCardParameterType : simpleCardParameterTypes) {
                    if (simpleCardParameterType != null) {
                        if (simpleCardParameterType.isTranslatable()) {
                            cardTextIDs.add(simpleCardParameterType.getCardParameterTypeID());
                        } else {
                            cardParameterIDs.add(simpleCardParameterType.getCardParameterTypeID());
                        }
                    } else {
                        throw new ServletException("parameter type not found");
                    }
                }
                if (TextRequest.areRepeatingTextsExists(cardID, cardTextIDs)) {
                    throw new ServletException("text is already added to that card");
                }
                if (ParameterRequest.areRepeatingParametersExists(cardID, cardParameterIDs)) {
                    throw new ServletException("parameter is already added to that card");
                }
                if (cardID != 0L) {
//                    loggerFactory.debug("start");
                    TextRequest.addTextsToCard(cardID, cardTextIDs);
//                    loggerFactory.debug("middle");
                    ParameterRequest.addParametersToCard(cardID, cardParameterIDs);
//                    loggerFactory.debug("end");
                    CardRequest.updateCard(cardEntity);
                    ServletHelper.sendForward("/completecardinfo?cardID=" + cardEntity.getCardID(), this, request, response);
                }
            } else {
                throw new ServletException("card not found");
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            request.setAttribute("blocks", ApplicationBlock.values());
            String cardID = request.getParameter("cardID");
            if (cardID.isEmpty() || cardID == null) {
                throw new ServletException("cardID is not a number");
            }
            long cardIDLong;
            try {
                cardIDLong = Long.parseLong(cardID);
            } catch (NumberFormatException nfe) {
                throw new ServletException("cardId is not a number");
            }
            request.setAttribute("parameterTypes", ParameterRequest.getAvailableCardParameters(cardIDLong));
            request.setAttribute("cardID", cardID);
            ServletHelper.sendForward("/addcardparameternew.jsp?cardID=" + cardID, this, request, response);
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
        return "Добавить поле для карточки";
    }
}
