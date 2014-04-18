package view.servlet.phone;

import controller.phone.parser.GetAllMenusParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetAllMenusHandler;
import model.phone.requesthandler.MobileHandler;

/**
 * Created by Илья on 14.04.14.
 */
public class GetAllMenusServlet extends MobileServlet {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetAllMenusServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new GetAllMenusHandler();
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
