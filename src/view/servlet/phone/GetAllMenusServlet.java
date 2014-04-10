package view.servlet.phone;

import controller.phone.entity.AllMenusRequest;
import controller.phone.parser.AllMenusParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllMenusHandler;
import model.phone.requesthandler.MobileHandler;
import model.phone.responseentity.AllMenusResponse;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllMenusServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllMenusHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllMenusParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

  /*  public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            AllMenusRequest allMenusRequest = new AllMenusParser().parse(request);
            AllMenusResponse allMenusResponse =new AllMenusHandler().handleRequest(allMenusRequest);
            String responseString = AllMenusParser.getResponse(allMenusResponse);
            ServletHelper.sendJson(response, responseString);
        } catch (Exception e) {
            ServletHelper.sendMobileError(loggerFactory, e, response);
        }
    }*/

}