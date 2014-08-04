package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.UserRegistrationParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.UserRegistrationHandler;


public class UserRegistrationServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new UserRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new UserRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }


}