package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.UserGlobalUpdatedParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.UserGlobalUpdatedHandler;


public class UserGlobalUpdatedServlet extends MobileServlet {

    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserGlobalUpdatedServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new UserGlobalUpdatedHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new UserGlobalUpdatedParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
