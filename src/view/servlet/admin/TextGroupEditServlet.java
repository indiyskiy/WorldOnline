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
            loggerFactory.debug("TextGroupEditServlet");
            TextGroupEditParser textGroupEditParser = new TextGroupEditParser(request);
            ArrayList<TextEntity> textEntities = textGroupEditParser.getTextEntityArrayList();
            for (TextEntity textEntity : textEntities) {
//            ParameterValidator.isValidParameter(textEntity.getText(),textEntities.get)
                TextRequest.updateText(textEntity);
            }
            loggerFactory.debug("send forward " + request.getParameter("textGroupID"));
            ServletHelper.sendForward("/completetextgroupinfo?TextGroupID=" + request.getParameter("textGroupID"), this, request, response);
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
