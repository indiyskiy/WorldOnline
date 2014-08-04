package view.servlet.phone;

import controller.phone.parser.AllDishCategoriesParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllDishCategoriesHandler;
import model.phone.requesthandler.MobileHandler;

public class AllDishCategoriesServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllDishCategoriesServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllDishCategoriesHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllDishCategoriesParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
