package view.servlet.admin;

import controller.phone.parser.GetAllCardsParser;
import controller.phone.parser.MobileParser;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.phone.requesthandler.GetAllCardsHandler;
import model.phone.requesthandler.MobileHandler;
import view.servlet.phone.MobileServlet;

public class GetAllCardsServlet extends MobileServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, GetAllCardsServlet.class);

    @Override
    protected MobileHandler getMobileHandler() {
        loggerFactory.debug("GetAllCardsServlet getMobileHandler");
        return new GetAllCardsHandler();
    }

    @Override
    public MobileParser getMobileParser() {
        loggerFactory.debug("GetAllCardsServlet getMobileParser");
        return new GetAllCardsParser();
    }

    @Override
    protected LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }
}
