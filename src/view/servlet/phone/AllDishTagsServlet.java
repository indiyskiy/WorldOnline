package view.servlet.phone;

import controller.phone.parser.AllDishTagsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllDishTagsHandler;
import model.phone.requesthandler.MobileHandler;

public class AllDishTagsServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllDishTagsServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllDishTagsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllDishTagsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
