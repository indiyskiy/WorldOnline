package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.PricesByListParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.PricesByListHandler;


public class PricesByListServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, PricesByListServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new PricesByListHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new PricesByListParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
