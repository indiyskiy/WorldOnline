package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.PricesForUpdateParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.PricesForUpdateHandler;


public class PricesForUpdateServlet extends MobileServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardsForUpdateServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new PricesForUpdateHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new PricesForUpdateParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
