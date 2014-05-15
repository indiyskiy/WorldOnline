package view.servlet;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.logger.LoggerFactory;
import model.xmlparser.GlobalXmlParser;
import view.servlet.admin.ProtectedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalBaseParseServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, GlobalBaseParseServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (CardRequest.getCardByID(1L) == null) {
            request.setAttribute("parse", "true");
        } else {
            request.setAttribute("parse", "false");
        }
        GlobalXmlParser.parse();
        ServletHelper.sendForward("/globalbaseparse.jsp", this, request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.AdminOnly;
    }
}
