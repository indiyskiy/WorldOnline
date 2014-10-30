package view.servlet.phone;


import controller.phone.parser.CardRegistrationParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardRegistrationHandler;
import model.phone.requesthandler.MobileHandler;

public class CardRegistrationServlet extends MobileServlet {

    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardRegistrationServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
