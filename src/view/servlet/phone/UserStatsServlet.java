package view.servlet.phone;

import controller.phone.parser.MobileParser;
import controller.phone.parser.UserStatsParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.requesthandler.UserStatsHandler;

public class UserStatsServlet extends MobileServlet {

    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, UserStatsServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new UserStatsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new UserStatsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
