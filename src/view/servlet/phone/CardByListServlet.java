package view.servlet.phone;

import controller.phone.parser.CardByListParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.CardByListHandler;
import model.phone.requesthandler.MobileHandler;

public class CardByListServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, CardByListServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        return new CardByListHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        return new CardByListParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
