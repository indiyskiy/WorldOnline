package view.servlet.admin;

import controller.parser.adminparser.EditCardParametersParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.ParameterRequest;
import model.database.worldonlinedb.CardParameterEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCardParametersServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, EditCardParametersServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            EditCardParametersParser editCardParametersParser = new EditCardParametersParser(request);
            String errors = "";
            boolean edited = false;
            for (CardParameterEntity cardParameter : editCardParametersParser.getCardParameters()) {
                try {
                    ParameterRequest.updateCardParameter(cardParameter);
                    edited = true;
                } catch (Exception e) {
                    errors += e.toString() + "\n";
                }
            }
            if (edited) {
                CardRequest.updateCard(CardRequest.getCardByID(Long.parseLong(request.getParameter("cardID"))));
            }
            if (!errors.isEmpty()) {
                throw new ServletException(errors);
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + request.getParameter("cardID"), this, request, response);

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
