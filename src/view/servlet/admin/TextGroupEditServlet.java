package view.servlet.admin;

import controller.parser.adminparser.TextGroupEditParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TextGroupEditServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TextGroupEditServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextGroupEditParser textGroupEditParser = new TextGroupEditParser(request);
            textGroupEditParser.formLists();
            ArrayList<TextEntity> textEntitiesUpdate = textGroupEditParser.getTextEntityUpdateArrayList();
            for (TextEntity textEntity : textEntitiesUpdate) {
                TextRequest.updateText(textEntity);
            }

            ArrayList<TextEntity> textEntitiesCreate = textGroupEditParser.getTextEntityCreateArrayList();
            for (TextEntity textEntity : textEntitiesCreate) {
                TextRequest.addText(textEntity);
            }
            TextRequest.deleteText(textGroupEditParser.getTextEntityDeleteArrayList());
            boolean redirected = false;
            if (!redirected) {
                String cardID = request.getParameter("cardID");
                if (cardID != null && !cardID.isEmpty()) {
                    ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
                    redirected = true;
                }
            }

            if (!redirected) {
                String tagID = request.getParameter("tagID");
                if (tagID != null && !tagID.isEmpty()) {
                    ServletHelper.sendForward("/tagedit?tagID=" + tagID, this, request, response);
                    redirected = true;
                }
            }

            if (!redirected) {
                String menuID = request.getParameter("menuID");
                if (menuID != null && !menuID.isEmpty()) {
                    ServletHelper.sendForward("/completemenuinfo?tagID=" + menuID, this, request, response);
                    redirected = true;
                }
            }

            if (!redirected) {
                ServletHelper.sendForward("/completetextgroupinfo?textGroupID=" + request.getParameter("textGroupID"), this, request, response);
            }
        } catch (Exception e) {
            loggerFactory.error(e.toString());
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
