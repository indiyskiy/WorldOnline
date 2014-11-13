package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.UserGlobalUpdateParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.UserGlobalUpdateHandler;

public class UserGlobalUpdateServlet extends MobileServlet {

    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserGlobalUpdateServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new UserGlobalUpdateHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new UserGlobalUpdateParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
