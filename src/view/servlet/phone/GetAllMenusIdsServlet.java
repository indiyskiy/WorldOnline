package view.servlet.phone;

import controller.phone.parser.GetAllMenuIDsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllMenusHandler;
import model.phone.requesthandler.MobileHandler;


public class GetAllMenusIdsServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllMenusHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new GetAllMenuIDsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }


}