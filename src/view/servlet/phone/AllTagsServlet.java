package view.servlet.phone;

import controller.phone.parser.AllTagsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.AllTagsHandler;
import model.phone.requesthandler.MobileHandler;

public class AllTagsServlet extends MobileServlet {

    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllTagsServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new AllTagsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new AllTagsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
