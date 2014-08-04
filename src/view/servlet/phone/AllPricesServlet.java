package view.servlet.phone;

import controller.phone.parser.AllPricesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllPricesHandler;
import model.phone.requesthandler.MobileHandler;

public class AllPricesServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllPricesServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllPricesHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllPricesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
