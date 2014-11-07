package view.servlet.phone;


import controller.phone.parser.MobileParser;
import controller.phone.parser.PricesRegistrationParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.PricesRegistrationHandler;

public class PricesRegistrationServlet extends MobileServlet {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, PricesRegistrationServlet.class);


    @Override
    protected MobileHandler getMobileHandler() {
        return new PricesRegistrationHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new PricesRegistrationParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
