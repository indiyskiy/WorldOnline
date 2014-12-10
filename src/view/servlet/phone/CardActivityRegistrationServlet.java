package view.servlet.phone;

import controller.phone.parser.CardActivityRegistrationParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardActivityRegistrationHandler;
import model.phone.requesthandler.MobileHandler;

public class CardActivityRegistrationServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardActivityRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardActivityRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardActivityRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
