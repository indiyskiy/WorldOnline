package view.servlet.admin;

import controller.parser.adminparser.EditCardCoordinateParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.CoordinateRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCardCoordinateServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, EditCardCoordinateServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            EditCardCoordinateParser editCardCoordinateParser = new EditCardCoordinateParser(request);
            CoordinateRequest.editCardCoordinate(editCardCoordinateParser.getCardCoordinate());
            CardRequest.updateCard(editCardCoordinateParser.getCardEntity());
            ServletHelper.sendForward("/completecardinfo?cardID=" + editCardCoordinateParser.getCardEntity().getCardID(), this, request, response);
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
