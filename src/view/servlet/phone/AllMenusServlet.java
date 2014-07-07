package view.servlet.phone;

import controller.phone.parser.GetAllMenusParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllMenusHandler;
import model.phone.requesthandler.MobileHandler;

public class AllMenusServlet extends MobileServlet {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllMenusServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllMenusHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new GetAllMenusParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
