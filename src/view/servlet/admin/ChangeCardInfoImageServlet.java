package view.servlet.admin;

import controller.parser.adminparser.CardInfoImageUploadParser;
import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.worldonlinedb.CardInformationElementEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ChangeCardInfoImageServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ChangeCardInfoImageServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            loggerFactory.debug("ChangeCardInfoImageServlet start");
            CardInfoImageUploadParser cardInfoImageUploadParser = new CardInfoImageUploadParser();
            cardInfoImageUploadParser.parse(request);
            CardInformationElementEntity cardInformationElementEntity = cardInfoImageUploadParser.getCardInformationElementEntity();
            File file = (File) cardInfoImageUploadParser.getFileMap().values().toArray()[0];
            ImageHelper.saveCardInfoImage(file, cardInformationElementEntity);
            Long cardID = cardInformationElementEntity.getCard().getCardID();
            loggerFactory.debug("card id=" + cardID);
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
