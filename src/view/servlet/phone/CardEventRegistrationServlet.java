package view.servlet.phone;

import controller.phone.parser.CardActivityRegistrationParser;
import controller.phone.parser.CardEventRegistrationParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardActivityRegistrationHandler;
import model.phone.requesthandler.CardEventRegistrationHandler;
import model.phone.requesthandler.MobileHandler;

public class CardEventRegistrationServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardEventRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardEventRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardEventRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
