package view.servlet.phone;

import controller.phone.parser.AllMenuIDsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllMenuIDsHandler;
import model.phone.requesthandler.MobileHandler;


public class AllMenusIdsServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllMenuIDsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllMenuIDsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }


}