package view.servlet.admin;

import model.additionalentity.admin.CardBlock;
import model.additionalentity.admin.CardParameter;
import model.additionalentity.admin.CardText;
import model.additionalentity.admin.CompleteCardInfo;
import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.CardState;
import model.constants.databaseenumeration.CardToCardLinkType;
import model.database.requests.CardRequest;
import model.database.requests.MenuRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CompleteCardInfoServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteCardInfoServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("cardStates", CardState.getValues());
            request.setAttribute("parameterBlocks", ApplicationBlock.values());
            request.setAttribute("cardToCardLinkTypes", CardToCardLinkType.values());
            request.setAttribute("cardMenus", MenuRequest.getLastLevelMenus());
            String cardIDString = request.getParameter("cardID");
            if (cardIDString != null) {
                long cardID = Long.parseLong(cardIDString);
                CompleteCardInfo completeCardInfo = CardRequest.getCompleteCardInfo(cardID);
                if (completeCardInfo != null) {
                    request.setAttribute("cardInfo", completeCardInfo.getCardInfo());
                    request.setAttribute("title", cutTitle("Карточка [" +
                            completeCardInfo.getCardInfo().getCardID() +
                            "]-" +
                            completeCardInfo.getCardInfo().getName()));
                    request.setAttribute("coordinate", completeCardInfo.getCardCoordinate());
                    request.setAttribute("price", completeCardInfo.getCardPrice());
                    if (completeCardInfo.getSourceCardLinks() != null && !completeCardInfo.getSourceCardLinks().isEmpty()) {
                        request.setAttribute("sourceCardLinks", completeCardInfo.getSourceCardLinks());
                    }
                    if (completeCardInfo.getTargetCardLinks() != null && !completeCardInfo.getTargetCardLinks().isEmpty()) {
                        request.setAttribute("targetCardLinks", completeCardInfo.getTargetCardLinks());
                    }
                    if (completeCardInfo.getCardMenuArrayList() != null && !completeCardInfo.getCardMenuArrayList().isEmpty()) {
                        request.setAttribute("menus", completeCardInfo.getCardMenuArrayList());
                    }
                    if (completeCardInfo.getCardImages() != null && !completeCardInfo.getCardImages().isEmpty()) {
                        request.setAttribute("images", completeCardInfo.getCardImages());
                    }
                    if (completeCardInfo.getCardParameterArrayList() != null && !completeCardInfo.getCardParameterArrayList().isEmpty()) {
                        request.setAttribute("parameters", completeCardInfo.getCardParameterArrayList());
                    }
                    if (completeCardInfo.getCardTextArrayList() != null && !completeCardInfo.getCardTextArrayList().isEmpty()) {
                        request.setAttribute("texts", completeCardInfo.getCardTextArrayList());
                    }
                    if (completeCardInfo.getCardTagArrayList() != null && !completeCardInfo.getCardTagArrayList().isEmpty()) {
                        request.setAttribute("tags", completeCardInfo.getCardTagArrayList());
                    }
                    request.setAttribute("cardBlocks", completeCardInfo.getCardBlocks());
                }
                ServletHelper.sendForward("/completecardinfo.jsp", this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
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