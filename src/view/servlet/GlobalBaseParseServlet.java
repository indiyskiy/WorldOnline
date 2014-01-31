package view.servlet;

import model.constants.Component;
import model.database.requests.CardRequest;
import model.logger.LoggerFactory;
import model.xmlparser.GlobalXmlParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 29.01.14
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class GlobalBaseParseServlet extends HttpServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, GlobalBaseParseServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (CardRequest.getCardByID(1L) == null) {
            GlobalXmlParser.parse();
        }
        ServletHelper.sendForward("/globalbaseparse.jsp", this, request, response);
    }
}
