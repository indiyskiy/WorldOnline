package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalBaseParseServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, GlobalBaseParseServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletHelper.sendForward("/globalbaseparse.jsp", this, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        if (CardRequest.getCardByID(1L) == null) {
//            request.setAttribute("parse", "true");
//        } else {
//            request.setAttribute("parse", "false");
//        }
//        GlobalXmlParser.parse();
//        ServletHelper.sendForward("/globalbaseparse.jsp", this, request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.AdminOnly;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
