package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.RestoreUserParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.RestoreUserHandler;

public class RestoreUserServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, RestoreUserServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new RestoreUserHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new RestoreUserParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
